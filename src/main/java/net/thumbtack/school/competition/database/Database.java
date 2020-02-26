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

public class Database implements Serializable {
    private static Database instance;
    private byte rating;
    private List<Member> members = new ArrayList<>();
    private List<Expert> experts = new ArrayList<>();
    private MultiValuedMap<Member, Application> applicationWithMember = new ArrayListValuedHashMap<>();
    private Map<Expert, Map<Application, Integer>> applicationsWithRating = new HashMap<>();
    private Map<UUID, User> usersOnline = new HashMap<>();

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
        /*if (instance == null) {
            instance = new Database();
        }*/
        return instance;
    }

    public static boolean uploadDatabase(String savedDataFileName) throws CompetitionException {
        if (savedDataFileName == null || savedDataFileName.equals("")) {
            instance = new Database();
            return true;
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(new File(savedDataFileName)))) {
                Gson json = new Gson();
                instance = json.fromJson(br, Database.class);
                return true;
            } catch (IOException e) {
                throw new CompetitionException(ErrorCode.ERROR_UPLOAD_DATABASE);
            }
        }
    }

    public static boolean saveDatabase(String savedDataFileName) throws CompetitionException {
        if (savedDataFileName == null || savedDataFileName.equals("")) {
            return true;
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(savedDataFileName)))) {
                Gson json = new Gson();
                json.toJson(instance, bw);
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

    public String deleteUser(String token) throws CompetitionException { //Добавить удаление заявок и оценок!!!
        Gson json = new Gson();
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                User toDelete = usersOnline.get(key);
                usersOnline.remove(key, toDelete);
                if (toDelete.getClass() == Member.class) {
                    members.remove((Member) toDelete);
                    if (applicationWithMember.containsKey(toDelete)) {
                        Collection<Application> appsToDelete = applicationWithMember.get((Member) toDelete);
                        for (Expert expert : experts) {
                            Map<Application, Integer> map = applicationsWithRating.get(expert);
                            for (Application app : appsToDelete) {
                                if (map.containsKey(app)) {
                                    map.remove(app, map.get(app));
                                }
                            }
                            applicationsWithRating.put(expert, map);
                        }
                        applicationWithMember.remove(toDelete);
                    }
                } else {
                    experts.remove((Expert) toDelete);
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
        Gson json = new Gson();
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
        Gson json = new Gson();
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
        Gson json = new Gson();
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

    public String memberDeleteApplication(String token, Application application) throws CompetitionException {
        Gson json = new Gson();
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                Member member = (Member) usersOnline.get(key);
                if (applicationWithMember.containsMapping(member, application)) {
                    applicationWithMember.removeMapping(member, application);
                    for (Expert expert : experts) {
                        if (applicationsWithRating.containsKey(expert)) {
                            Map<Application, Integer> map = applicationsWithRating.get(expert);
                            if (map.containsKey(application)) {
                                map.remove(application);
                                applicationsWithRating.put(expert, map);
                            }
                        }
                    }
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
        Gson json = new Gson();
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                Expert expert = (Expert) usersOnline.get(key);
                List<Application> result = new ArrayList<>();
                Collection<Application> collection = applicationWithMember.values();
                Iterator<Application> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    Application app = iterator.next();
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
        Gson json = new Gson();
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                Expert expert = (Expert) usersOnline.get(key);
                if (!expert.getSubjectsList().containsAll(subjects)) {
                    throw new CompetitionException(ErrorCode.INVALID_REQUEST);
                }
                List<Application> result = new ArrayList<>();
                Collection<Application> collection = applicationWithMember.values();
                Iterator<Application> iterator = collection.iterator();
                while (iterator.hasNext()) {
                    Application app = iterator.next();
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
        Gson json = new Gson();
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
        Gson json = new Gson();
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
        Gson json = new Gson();
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                Expert expert = (Expert) usersOnline.get(key);
                if (applicationsWithRating.containsKey(expert)
                        && applicationsWithRating.get(expert).containsKey(application)) {
                    Map<Application, Integer> map = applicationsWithRating.get(expert);
                    map.replace(application,rating);
                    applicationsWithRating.put(expert, map);
                    return json.toJson("ok");
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
        Gson json = new Gson();
        try {
            UUID key = json.fromJson(token, UUID.class);
            if (usersOnline.containsKey(key)) {
                Expert expert = (Expert) usersOnline.get(key);
                if (applicationsWithRating.containsKey(expert)
                        && applicationsWithRating.get(expert).containsKey(application)) {
                    Map<Application, Integer> map = applicationsWithRating.get(expert);
                    map.remove(application);
                    applicationsWithRating.put(expert, map);
                    return json.toJson("ok");
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

}