package net.thumbtack.school.competition.database;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.competition.dto.LoginDto;
import net.thumbtack.school.competition.dto.TokenDtoResponse;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.exceptions.ErrorCode;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.Member;
import net.thumbtack.school.competition.model.User;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Database implements Serializable {
    private static Database instance;
    private Set<Member> members = new HashSet<>(); //Для более быстрой работы
    private Set<Expert> experts = new HashSet<>();
    private MultiValuedMap<Member, Application> applicationWithMember = new ArrayListValuedHashMap<>();
    private Map<Expert, Map<Application, Integer>> applicationsWithRating = new HashMap<>();
    private Map<UUID, User> usersOnline = new HashMap<>();
    transient private Gson json = new Gson();

    private Database() {
    }

    private boolean sameLists(List<String> list1, List<String> list2) {
        for (String item2 : list2) {
            if (list1.contains(item2)) {
                return true;
            }
        }
        return false;
    }

    public static Database getInstance() {
        return instance;
    }

    public static boolean uploadDatabase(String savedDataFileName) throws CompetitionException {
        if (savedDataFileName == null || savedDataFileName.equals("")) {
            instance = new Database();
            return true;
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(savedDataFileName)))) {
                Database instance = (Database) ois.readObject();
                return true;
            } catch (IOException | ClassNotFoundException e) {
                throw new CompetitionException(ErrorCode.ERROR_UPLOAD_DATABASE);
            }
        }
    }

    private void deleteUsersOnline() {
        usersOnline = new HashMap<>();
    }

    public static boolean saveDatabase(String savedDataFileName) throws CompetitionException {
        if (savedDataFileName == null || savedDataFileName.equals("")) {
            return true;
        } else {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(savedDataFileName)))) {
                instance.deleteUsersOnline();
                oos.writeObject(instance);
                return true;
            } catch (IOException e) {
                throw new CompetitionException(ErrorCode.ERROR_SAVING_DATABASE);
            }
        }
    }

    public String insert(Member member) throws CompetitionException {
        for (Expert expert : experts) {
            if (expert.getLogin().equals(member.getLogin())) {
                throw new CompetitionException(ErrorCode.DUPLICATE_USER);
            }
        }
        if (!members.contains(member)) {
            members.add(member);
        } else {
            throw new CompetitionException(ErrorCode.DUPLICATE_USER);
        }

        UUID token = UUID.randomUUID();
        usersOnline.put(token, member);
        return token.toString();
    }

    public String insert(Expert expert) throws CompetitionException {
        for (Member member : members) {
            if (member.getLogin().equals(expert.getLogin())) {
                throw new CompetitionException(ErrorCode.DUPLICATE_USER);
            }
        }
        if (!experts.contains(expert)) {
            experts.add(expert);
        } else {
            throw new CompetitionException(ErrorCode.DUPLICATE_USER);
        }
        UUID token = UUID.randomUUID();
        usersOnline.put(token, expert);
        return token.toString();
    }

    public String deleteUser(String token) throws CompetitionException {
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                User toDelete = usersOnline.get(key);
                usersOnline.remove(key, toDelete);
                if (toDelete.getClass() == Member.class) {
                    members.remove(toDelete);
                    if (applicationWithMember.containsKey(toDelete)) {
                        Collection<Application> appsToDelete = applicationWithMember.get((Member) toDelete);
                        for (Expert expert : experts) {
                            Map<Application, Integer> map = applicationsWithRating.get(expert);
                            for (Application app : appsToDelete) {
                                if (map.containsKey(app)) {
                                    map.remove(app, map.get(app)); // Никак не могу придумать, как упростить
                                }
                            }
                            applicationsWithRating.put(expert, map);
                        }
                        applicationWithMember.remove(toDelete);
                    }
                } else {
                    experts.remove(toDelete);
                    applicationsWithRating.remove(toDelete);
                }
            } else {
                throw new CompetitionException(ErrorCode.ERROR_DELETING_USER);
            }
        } catch (JsonSyntaxException e) {
            throw new CompetitionException(ErrorCode.ERROR_DELETING_USER);
        }
        return "User successfully deleted from the server";
    }

    public String login(LoginDto loginDto) throws CompetitionException {
        for (Member member : members) {
            if (member.getLogin().equals(loginDto.getLogin()) && member.getPassword().equals(loginDto.getPassword())) {
                UUID token = UUID.randomUUID();
                usersOnline.put(token, member);
                return token.toString();
            } else if (member.getLogin().equals(loginDto.getLogin()) &&
                    !member.getPassword().equals(loginDto.getPassword())) {
                throw new CompetitionException(ErrorCode.WRONG_PASSWORD);
            }
        }
        for (Expert expert : experts) {
            if (expert.getLogin().equals(loginDto.getLogin()) && expert.getPassword().equals(loginDto.getPassword())) {
                UUID token = UUID.randomUUID();
                usersOnline.put(token, expert);
                return token.toString();
            } else if (expert.getLogin().equals(loginDto.getLogin()) &&
                    !expert.getPassword().equals(loginDto.getPassword())) {
                throw new CompetitionException(ErrorCode.WRONG_PASSWORD);
            }
        }
        throw new CompetitionException(ErrorCode.WRONG_LOGIN_PASSWORD);
    }

    public String logout(String token) throws CompetitionException {
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                usersOnline.remove(key, usersOnline.get(key));
                return "You are logged out";
            } else {
                throw new CompetitionException(ErrorCode.LOGIN_ERROR);
            }
        } catch (JsonSyntaxException e) {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    public String insertApplication(TokenDtoResponse tokenDto, Application application) throws CompetitionException {
        try {
            UUID token = json.fromJson(tokenDto.getToken(), UUID.class);
            if (usersOnline.containsKey(token) && usersOnline.get(token) instanceof Member) {
                Member member = (Member) usersOnline.get(token);
                if (!applicationWithMember.containsMapping(member, application)) {
                    applicationWithMember.put(member, application);
                    return "ok";
                } else {
                    throw new CompetitionException(ErrorCode.DUPLICATE_APPLICATION);
                }
            } else {
                throw new CompetitionException(ErrorCode.LOGIN_ERROR);
            }
        } catch (JsonSyntaxException e) {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    public String memberShowApplications(TokenDtoResponse tokenDto) throws CompetitionException {
        try {
            UUID token = json.fromJson(tokenDto.getToken(), UUID.class);
            if (usersOnline.containsKey(token)) {
                Member member = (Member) usersOnline.get(token);
                Collection<Application> result = applicationWithMember.get(member);
                return json.toJson(result);
            } else {
                throw new CompetitionException(ErrorCode.LOGIN_ERROR);
            }
        } catch (JsonSyntaxException e) {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    private void deleteAppFromAppWithRating(Application application) {
        for (Expert expert : experts) {
            if (applicationsWithRating.containsKey(expert)) {
                Map<Application, Integer> map = applicationsWithRating.get(expert);
                if (map.containsKey(application)) {
                    map.remove(application);
                    if (map.size() > 0) {
                        applicationsWithRating.put(expert, map);
                    } else {
                        applicationsWithRating.remove(expert);
                    }
                }
            }
        }
    }

    public String memberDeleteApplication(String token, Application application) throws CompetitionException {
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                Member member = (Member) usersOnline.get(key);
                if (applicationWithMember.containsMapping(member, application)) {
                    applicationWithMember.removeMapping(member, application);
                    deleteAppFromAppWithRating(application);
                    return "application deleted";
                } else {
                    throw new CompetitionException(ErrorCode.APPLICATION_NOT_EXIST);
                }
            } else {
                throw new CompetitionException(ErrorCode.LOGIN_ERROR);
            }
        } catch (JsonSyntaxException e) {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    public String expertShowApplications(String token) throws CompetitionException {
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                Expert expert = (Expert) usersOnline.get(key);
                List<Application> result = new ArrayList<>();
                Collection<Application> collection = applicationWithMember.values();
                for (Application app : collection) {
                    if (sameLists(app.getSubjectsList(), expert.getSubjectsList())) {
                        result.add(app);
                    }
                }
                if (result.size() > 0) {
                    return json.toJson(result);
                } else {
                    throw new CompetitionException(ErrorCode.APPLICATION_NOT_EXIST);
                }
            } else {
                throw new CompetitionException(ErrorCode.LOGIN_ERROR);
            }
        } catch (JsonSyntaxException e) {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    public String expertShowApplications(String token, List<String> subjects) throws CompetitionException {
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                Expert expert = (Expert) usersOnline.get(key);
                if (!expert.getSubjectsList().containsAll(subjects)) {
                    throw new CompetitionException(ErrorCode.INVALID_REQUEST);
                }
                List<Application> result = new ArrayList<>();
                Collection<Application> collection = applicationWithMember.values();
                for (Application app : collection) {
                    if (sameLists(app.getSubjectsList(), subjects)) {
                        result.add(app);
                    }
                }
                if (result.size() > 0) {
                    return json.toJson(result);
                } else {
                    throw new CompetitionException(ErrorCode.APPLICATION_NOT_EXIST);
                }
            } else {
                throw new CompetitionException(ErrorCode.LOGIN_ERROR);
            }
        } catch (JsonSyntaxException e) {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    public String rateApplication(String token, Application application, int rating) throws CompetitionException {
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                Expert expert = (Expert) usersOnline.get(key);
                if (applicationWithMember.containsValue(application)
                        && sameLists(application.getSubjectsList(), expert.getSubjectsList())) {
                    Map<Application, Integer> map = new HashMap<>();
                    if (applicationsWithRating.get(expert) != null && applicationsWithRating.get(expert).size() > 0) {
                        map.putAll(applicationsWithRating.get(expert));
                    }
                    map.put(application, rating);
                    applicationsWithRating.put(expert, map);
                    return "You rated application: " + application.getName();
                } else {
                    throw new CompetitionException(ErrorCode.APPLICATION_NOT_EXIST);
                }
            } else {
                throw new CompetitionException(ErrorCode.LOGIN_ERROR);
            }
        } catch (JsonSyntaxException e) {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    public String showRatedApplications(String token) throws CompetitionException {
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                Expert expert = (Expert) usersOnline.get(key);
                if (applicationsWithRating.containsKey(expert)) {
                    Map<Application, Integer> map = applicationsWithRating.get(expert);
                    Set<Application> set = map.keySet();
                    List<String> result = new ArrayList<>();
                    for (Application app : set) {
                        result.add(map.get(app) + ": " + app.toString());
                    }
                    return json.toJson(result);
                } else {
                    throw new CompetitionException(ErrorCode.RATINGS_NOT_EXIST);
                }
            } else {
                throw new CompetitionException(ErrorCode.LOGIN_ERROR);
            }
        } catch (JsonSyntaxException e) {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    public String changeRating(String token, Application application, int rating) throws CompetitionException {
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                Expert expert = (Expert) usersOnline.get(key);
                if (applicationsWithRating.containsKey(expert)
                        && applicationsWithRating.get(expert).containsKey(application)) {
                    Map<Application, Integer> map = applicationsWithRating.get(expert);
                    map.replace(application, rating);
                    applicationsWithRating.put(expert, map);
                    return "ok";
                } else {
                    throw new CompetitionException(ErrorCode.RATINGS_NOT_EXIST);
                }
            } else {
                throw new CompetitionException(ErrorCode.LOGIN_ERROR);
            }
        } catch (JsonSyntaxException e) {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    public String deleteRating(String token, Application application) throws CompetitionException {
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                Expert expert = (Expert) usersOnline.get(key);
                if (applicationsWithRating.containsKey(expert)
                        && applicationsWithRating.get(expert).containsKey(application)) {
                    Map<Application, Integer> map = applicationsWithRating.get(expert);
                    map.remove(application);
                    if (map.size() > 0) {
                        applicationsWithRating.put(expert, map);
                    } else {
                        applicationsWithRating.remove(expert);
                    }
                    return "ok";
                } else {
                    throw new CompetitionException(ErrorCode.RATINGS_NOT_EXIST);
                }
            } else {
                throw new CompetitionException(ErrorCode.LOGIN_ERROR);
            }
        } catch (JsonSyntaxException e) {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    private List<Map.Entry<Application, Float>> sortMapAppFloat(Map<Application, Float> mapAverage) {
        return mapAverage.entrySet().stream()
                .sorted((e1, e2) -> {
                    if (e1.getValue().equals(e2.getValue())) { //Если оценки равны, сортируем по запрашиваемой сумме
                        if (e1.getKey().getAmountRequested() <= e2.getKey().getAmountRequested()) {
                            return -1;
                        } else {
                            return 1;
                        }
                    } else {
                        return -e1.getValue().compareTo(e2.getValue());
                    }
                }).collect(Collectors.toList());
    }

    private Map<Application, Float> generateMapAverage(Collection<Application> collectionApp,
                                                       Map<Application, Integer> appSum,
                                                       Map<Application, Integer> appAmount) {
        Map<Application, Float> mapAverage = new HashMap<>(); //Карта: заявка -> среднее значение
        float averageRate;
        for (Application application : collectionApp) {       //Заполняем mapAverage
            if (appSum.containsKey(application)) {
                averageRate = (float) appSum.get(application) / (float) appAmount.get(application);
                mapAverage.put(application, averageRate);
            }
        }
        return mapAverage;
    }


    public List<Map.Entry<Application, Float>> summarize() throws CompetitionException {
        if (applicationsWithRating.size() > 0) {
            Collection<Map<Application, Integer>> mapCollection = applicationsWithRating.values();
            Collection<Application> collectionApp = applicationWithMember.values();
            Map<Application, Integer> appSum = new HashMap<>();
            Map<Application, Integer> appAmount = new HashMap<>();
            /*Создаем две map для подсчета средней оценки и дальнейшей работы с заявками*/
            /*Так же, нет идей как упростить*/
            for (Map<Application, Integer> map : mapCollection) { //Перебор карт с заявками
                for (Application application : collectionApp) {   //Перебор заявок
                    if (map.containsKey(application)) {
                        if (appSum.containsKey(application)) {    //Если заявка уже добавлялась, то пересчитываем
                            int rate = appSum.get(application) + map.get(application);
                            appSum.replace(application, rate);
                            int amount = appAmount.get(application) + 1;
                            appAmount.replace(application, amount);
                        } else {
                            appSum.put(application, map.get(application));
                            appAmount.put(application, 1);
                        }
                    }
                }
            }
            Map<Application, Float> mapAverage = generateMapAverage(collectionApp, appSum, appAmount);
            return sortMapAppFloat(mapAverage);
        } else {
            throw new CompetitionException(ErrorCode.SUMMARIZE_ERROR);
        }
    }

}
