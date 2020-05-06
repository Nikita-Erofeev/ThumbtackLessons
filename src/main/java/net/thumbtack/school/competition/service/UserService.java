package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.competition.daoimpl.UserDaoImpl;
import net.thumbtack.school.competition.dto.*;
import net.thumbtack.school.competition.exceptions.CompetitionException;

public class UserService {
    private UserDaoImpl userDao;

    public UserService() {
        userDao = new UserDaoImpl();
    }

    static boolean validString(String string) {
        return string != null && !string.equals("") && string.length() >= 2;
    }

    static boolean validApplicationDto(ApplicationDto applicationDto) {
        return applicationDto != null && validString(applicationDto.getName()) && validString(applicationDto.getDescription())
                && (applicationDto.getAmountRequested() > 1000) && (applicationDto.getSubjectsList().size() > 0);
    }

    static boolean validUserDto(String firstName, String lastName, String login, String password) {
        String[] strings = new String[4];
        strings[0] = firstName;
        strings[1] = lastName;
        strings[2] = login;
        strings[3] = password;
        for (int i = 0; i < 4; i++) {
            if (!validString(strings[i])) {
                return false;
            }
        }
        return strings[2].length() >= 4 && strings[3].length() >= 4;
    }

    public String deleteUser(String tokenJson) {
        Gson json = new Gson();
        try {
            TokenDtoResponse rudr = json.fromJson(tokenJson, TokenDtoResponse.class);
            if (validString(rudr.getToken())) {
                try {
                    return json.toJson(new DtoResponse(userDao.deleteUser(rudr.getToken())));
                } catch (CompetitionException e) {
                    return json.toJson(new DtoError(e.getErrorCode().getErrorString()));
                }
            } else {
                return json.toJson(new DtoError("invalid request"));
            }
        } catch (JsonSyntaxException e) {
            return json.toJson(new DtoError("invalid request"));
        }
    }

    public String login(String loginDtoJson) {
        Gson json = new Gson();
        try {
            LoginDto loginDto = json.fromJson(loginDtoJson, LoginDto.class);
            if (validString(loginDto.getLogin()) && validString(loginDto.getPassword())) {
                try {
                    return json.toJson(new TokenDtoResponse(userDao.login(loginDto)));
                } catch (CompetitionException e) {
                    return json.toJson(new DtoError(e.getErrorCode().getErrorString()));
                }
            } else {
                return json.toJson(new DtoError("invalid request"));
            }
        } catch (JsonSyntaxException e) {
            return json.toJson(new DtoError("invalid request"));
        }
    }

    public String logout(String tokenJsonDtoResponse) {
        Gson json = new Gson();
        try {
            TokenDtoResponse tokenDto = json.fromJson(tokenJsonDtoResponse, TokenDtoResponse.class);
            if (validString(tokenDto.getToken())) {
                try {
                    return json.toJson(new DtoResponse(userDao.logout(tokenDto.getToken())));
                } catch (CompetitionException e) {
                    return json.toJson(new DtoError(e.getErrorCode().getErrorString()));
                }
            } else {
                return json.toJson(new DtoError("invalid request"));
            }
        } catch (JsonSyntaxException e) {
            return json.toJson(new DtoError("invalid request"));
        }
    }


}
