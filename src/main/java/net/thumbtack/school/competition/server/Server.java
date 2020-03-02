package net.thumbtack.school.competition.server;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dto.DtoError;
import net.thumbtack.school.competition.dto.DtoResponse;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.service.UserService;

import java.util.List;

public class Server {
    private boolean run = false;

    private boolean isEmptyString(String string) {
        return string == null || string.equals("");
    }

    public String startServer(String savedDataFileName) {
        Gson json = new Gson();
        try {
            UserService userService = new UserService();
            run = userService.startServer(savedDataFileName);
            return json.toJson(new DtoResponse("Database uploaded"));
        } catch (CompetitionException e) {
            return json.toJson(new DtoError(e.getErrorCode().getErrorString()));
        }

    }

    public String stopServer(String savedDataFileName) {
        Gson json = new Gson();
        try {
            UserService userService = new UserService();
            run = !userService.stopServer(savedDataFileName);
            return json.toJson(new DtoResponse("Database saved"));
        } catch (CompetitionException e) {
            return json.toJson(new DtoError(e.getErrorCode().getErrorString()));
        }
    }

    public String registerMember(String requestJsonString) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.registerMember(requestJsonString);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String registerExpert(String requestJsonString) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.registerExpert(requestJsonString);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String deleteUser(String token) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.deleteUser(token);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String loginUser(String loginDtoJson) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.login(loginDtoJson);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String logoutUser(String tokenJsonDtoResponse) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.logout(tokenJsonDtoResponse);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String addApplication(String token, String appJsonDto) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.addApplication(token, appJsonDto);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    /*Перегруженный метод, который обращается к одному и тому же методу класса UserService*/
    public String addApplication(String token, String[] appJsonDto) {
        Gson json = new Gson();
        String[] errors = new String[appJsonDto.length];
        StringBuilder response = new StringBuilder();
        boolean anyErrors = false;
        int i = 0;
        if (run) {
            UserService userService = new UserService();
            for (String app : appJsonDto) {
                response.append(userService.addApplication(token, app));    //Добавление каждой заявки отдельно
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
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.memberShowApplications(tokenDtoJson);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String memberDeleteApplication(String tokenDtoJson, String appJsonDto) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.memberDeleteApplication(tokenDtoJson, appJsonDto);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String expertShowApplications(String tokenDtoJson) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.expertShowApplications(tokenDtoJson);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String expertShowApplications(String tokenDtoJson, List<String> subjects) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.expertShowApplications(tokenDtoJson, subjects);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String rateApplication(String tokenDtoJson, String applicationDtoJson, int rating) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.rateApplication(tokenDtoJson, applicationDtoJson, rating);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String showRatedApplications(String tokenDtoJson) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.showRatedApplications(tokenDtoJson);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String changeRating(String tokenDtoJson, String applicationDtoJson, int rating) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.changeRating(tokenDtoJson, applicationDtoJson, rating);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String deleteRating(String tokenDtoJson, String applicationDtoJson) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.deleteRating(tokenDtoJson, applicationDtoJson);
        }
        return json.toJson(new DtoError("Database is not available"));
    }

    public String summarize(String summarizeDtoJson) {
        Gson json = new Gson();
        if (run) {
            UserService userService = new UserService();
            return userService.summarize(summarizeDtoJson);
        }
        return json.toJson(new DtoError("Database is not available"));
    }
}
