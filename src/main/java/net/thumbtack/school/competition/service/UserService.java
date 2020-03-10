package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.competition.daoimpl.AdminDaoImpl;
import net.thumbtack.school.competition.daoimpl.UserDaoImpl;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.dto.*;
import net.thumbtack.school.competition.exceptions.CompetitionException;

public class UserService {
    private AdminDaoImpl adminDao;
    private UserDaoImpl userDao;

    public UserService() {
        adminDao = new AdminDaoImpl();
    }

    public UserService(Database database) {
        adminDao = new AdminDaoImpl(database);
        userDao = new UserDaoImpl(database);
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

    public boolean startServer(String savedDataFileName) throws CompetitionException {
        return adminDao.startServer(savedDataFileName);
    }

    public boolean stopServer(String savedDataFileName) throws CompetitionException {
        return adminDao.stopServer(savedDataFileName);
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

    public String summarize(String summarizeDtoJson) {
        Gson json = new Gson();
        try {
            SummarizeDto summarizeDto = json.fromJson(summarizeDtoJson, SummarizeDto.class);
            if (summarizeDto.getMinRate() > 0 && summarizeDto.getMinRate() < 6) {
                try {
                    return json.toJson(new DtoResponse(adminDao.summarize(summarizeDto.getFund(),
                            summarizeDto.getMinRate())));
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
