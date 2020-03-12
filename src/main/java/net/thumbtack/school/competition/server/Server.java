package net.thumbtack.school.competition.server;

import com.google.gson.Gson;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.dto.DtoError;
import net.thumbtack.school.competition.dto.DtoResponse;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.service.ExpertService;
import net.thumbtack.school.competition.service.MemberService;
import net.thumbtack.school.competition.service.UserService;

import java.util.List;

public class Server {
    private boolean run = false;
    private UserService userService;
    private MemberService memberService;
    private ExpertService expertService;
    private Gson json = new Gson();

    public String startServer(String savedDataFileName) {
        try {
            run = Database.uploadDatabase(savedDataFileName);
            Database database = Database.getInstance();
            userService = new UserService(database);
            memberService = new MemberService(database);
            expertService = new ExpertService(database);
            return json.toJson(new DtoResponse("Database uploaded"));
        } catch (CompetitionException e) {
            return json.toJson(new DtoError(e.getErrorCode().getErrorString()));
        }

    }

    public String stopServer(String savedDataFileName) {
        try {
            run = !Database.saveDatabase(savedDataFileName);
            return json.toJson(new DtoResponse("Database saved"));
        } catch (CompetitionException e) {
            return json.toJson(new DtoError(e.getErrorCode().getErrorString()));
        }
    }

    public String registerMember(String requestJsonString) {
        if (run) {
            return memberService.registerMember(requestJsonString);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String registerExpert(String requestJsonString) {
        if (run) {
            return expertService.registerExpert(requestJsonString);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String deleteUser(String token) {
        if (run) {
            return userService.deleteUser(token);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String loginUser(String loginDtoJson) {
        if (run) {
            return userService.login(loginDtoJson);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String logoutUser(String tokenJsonDtoResponse) {
        if (run) {
            return userService.logout(tokenJsonDtoResponse);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String addApplication(String token, String appJsonDto) {
        if (run) {
            return memberService.addApplication(token, appJsonDto);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    /*Перегруженный метод, который обращается к одному и тому же методу класса UserService*/
    public String addApplication(String token, String[] appJsonDto) {
        String[] errors = new String[appJsonDto.length];
        StringBuilder response = new StringBuilder();
        boolean anyErrors = false;
        int i = 0;
        if (run) {
            for (String app : appJsonDto) {
                response.append(memberService.addApplication(token, app));    //Добавление каждой заявки отдельно
                if (!response.toString().equals("{\"response\":\"ok\"}")) { //Если вернуло ошибку
                    errors[i] = app + response.toString();   //Добавляем в массив с ошибками в виде "заявка ошибка"
                    i++;
                    anyErrors = true;
                }
                response.delete(0, response.length());  //Работает как буфер, хранит 1 ответ
            }
            StringBuilder errorsString = new StringBuilder();
            if (anyErrors) {                        //Если среди заявок хотябы одна не добавилась
                for (String error : errors) {
                    if (error != null) {    //В массиве множество null элементов, от них избавляемся
                        errorsString.append(error);
                    }
                }
            }
            return anyErrors ? errorsString.toString() : json.toJson(new DtoResponse("ok"));
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String memberShowApplications(String tokenDtoJson) {
        if (run) {
            return memberService.memberShowApplications(tokenDtoJson);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String memberDeleteApplication(String tokenDtoJson, String appJsonDto) {
        if (run) {
            return memberService.memberDeleteApplication(tokenDtoJson, appJsonDto);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String expertShowApplications(String tokenDtoJson) {
        if (run) {
            return expertService.expertShowApplications(tokenDtoJson);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String expertShowApplications(String tokenDtoJson, List<String> subjects) {
        if (run) {
            return expertService.expertShowApplications(tokenDtoJson, subjects);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String rateApplication(String tokenDtoJson, String applicationDtoJson, int rating) {
        if (run) {
            return expertService.rateApplication(tokenDtoJson, applicationDtoJson, rating);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String showRatedApplications(String tokenDtoJson) {
        if (run) {
            return expertService.showRatedApplications(tokenDtoJson);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String changeRating(String tokenDtoJson, String applicationDtoJson, int rating) {
        if (run) {
            return expertService.changeRating(tokenDtoJson, applicationDtoJson, rating);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String deleteRating(String tokenDtoJson, String applicationDtoJson) {
        if (run) {
            return expertService.deleteRating(tokenDtoJson, applicationDtoJson);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String summarize(String summarizeDtoJson) {
        if (run) {
            return userService.summarize(summarizeDtoJson);
        }
        return json.toJson(new DtoError("Database is not available"));
    }
}
