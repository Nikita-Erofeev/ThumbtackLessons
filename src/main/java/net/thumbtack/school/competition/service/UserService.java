package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.competition.daoimpl.ExpertDaoImpl;
import net.thumbtack.school.competition.daoimpl.MemberDaoImpl;
import net.thumbtack.school.competition.daoimpl.UserDaoImpl;
import net.thumbtack.school.competition.dto.*;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.Member;

import java.util.List;

public class UserService {

    private boolean validString(String string) {
        return string != null && !string.equals("") & string.length() >= 2;
    }

    private boolean validApplicationDto(ApplicationDto applicationDto) {
        return validString(applicationDto.getName()) && validString(applicationDto.getDescription())
                && (applicationDto.getAmountRequested() > 1000) && (applicationDto.getSubjectsList().size() > 0);
    }

    private boolean validUserDto(String firstName, String lastName, String login, String password) {
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

    public String registerMember(String requestJsonString) {
        Gson json = new Gson();
        try {
            RegisterMemberDto rmd = json.fromJson(requestJsonString, RegisterMemberDto.class);
            if (validUserDto(rmd.getFirstName(), rmd.getLastName(), rmd.getLogin(), rmd.getPassword())
                    && validString(rmd.getCompanyName())) {
                MemberDaoImpl memberDao = new MemberDaoImpl();
                Member member = new Member(rmd.getFirstName(), rmd.getLastName(), rmd.getCompanyName(), rmd.getLogin(),
                        rmd.getPassword());
                try {
                    return json.toJson(new TokenDtoResponse(memberDao.insertMember(member)));
                } catch (CompetitionException e) {
                    DtoError de = new DtoError(e.getErrorCode().getErrorString());
                    return json.toJson(de);
                }
            } else {
                return json.toJson(new DtoError("invalid request"));
            }
        } catch (JsonSyntaxException e) {
            return json.toJson(new DtoError("invalid request"));
        }
    }

    public String registerExpert(String requestJsonString) {
        Gson json = new Gson();
        try {
            RegisterExpertDto red = json.fromJson(requestJsonString, RegisterExpertDto.class);
            if (validUserDto(red.getFirstName(), red.getLastName(), red.getLogin(), red.getPassword())
                    && red.getSubjectsList().size() > 0) {
                ExpertDaoImpl expertDao = new ExpertDaoImpl();
                Expert expert = new Expert(red.getFirstName(), red.getLastName(), red.getSubjectsList(), red.getLogin(),
                        red.getPassword());
                try {
                    return json.toJson(new TokenDtoResponse(expertDao.insertExpert(expert)));
                } catch (CompetitionException e) {
                    DtoError de = new DtoError(e.getErrorCode().getErrorString());
                    return json.toJson(de);
                }

            } else {
                return json.toJson(new DtoError("invalid request"));
            }
        } catch (JsonSyntaxException e) {
            return json.toJson(new DtoError("invalid request"));
        }
    }

    public String deleteUser(String tokenJson) {
        Gson json = new Gson();
        try {
            TokenDtoResponse rudr = json.fromJson(tokenJson, TokenDtoResponse.class);
            if (validString(rudr.getToken())) {
                UserDaoImpl userDao = new UserDaoImpl();
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
                    return json.toJson(new TokenDtoResponse(new UserDaoImpl().login(loginDto)));
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
                    return json.toJson(new DtoResponse(new UserDaoImpl().logout(tokenDto.getToken())));
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

    public String addApplication(String tokenJsonDto, String appJsonDto) {
        Gson json = new Gson();
        try {
            ApplicationDto applicationDto = json.fromJson(appJsonDto, ApplicationDto.class);
            TokenDtoResponse tokenDto = json.fromJson(tokenJsonDto, TokenDtoResponse.class);
            if (validApplicationDto(applicationDto) && (tokenJsonDto.length() == 48)) {
                try {
                    Application application = new Application(applicationDto);
                    return json.toJson(new DtoResponse(new MemberDaoImpl().insertApplication(tokenDto, application)));
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

    public String memberShowApplications(String tokenDtoJson) {
        Gson json = new Gson();
        try {
            TokenDtoResponse tokenDto = json.fromJson(tokenDtoJson, TokenDtoResponse.class);
            if (tokenDto.getToken().length() == 36) {
                try {
                    return json.toJson(new DtoResponse(new MemberDaoImpl().memberShowApplications(tokenDto)));
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

    public String memberDeleteApplication(String tokenDtoJson, String appJsonDto) {
        Gson json = new Gson();
        try {
            TokenDtoResponse tokenDto = json.fromJson(tokenDtoJson, TokenDtoResponse.class);
            ApplicationDto applicationDto = json.fromJson(appJsonDto, ApplicationDto.class);
            if (validApplicationDto(applicationDto) && tokenDto.getToken().length() == 36) {
                String token = tokenDto.getToken();
                Application application = new Application(applicationDto);
                try {
                    return json.toJson(new DtoResponse(new MemberDaoImpl().memberDeleteApplication(token, application)));
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

    public String expertShowApplications(String tokenDtoJson) {
        Gson json = new Gson();
        try {
            TokenDtoResponse tokenDto = json.fromJson(tokenDtoJson, TokenDtoResponse.class);
            if (tokenDto.getToken().length() == 36) {
                String token = tokenDto.getToken();
                try {
                    return json.toJson(new DtoResponse(new ExpertDaoImpl().expertShowApplications(token)));
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

    public String expertShowApplications(String tokenDtoJson, List<String> subjects) {
        Gson json = new Gson();
        try {
            TokenDtoResponse tokenDto = json.fromJson(tokenDtoJson, TokenDtoResponse.class);
            if (subjects.size() > 0 && tokenDto.getToken().length() == 36) {
                String token = tokenDto.getToken();
                try {
                    return json.toJson(new DtoResponse(new ExpertDaoImpl().expertShowApplications(token, subjects)));
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

    public String rateApplication(String tokenDtoJson, String applicationDtoJson, int rating) {
        Gson json = new Gson();
        if (rating < 1 || rating > 5) {
            return json.toJson(new DtoError("invalid rating"));
        }
        try {
            TokenDtoResponse tokenDto = json.fromJson(tokenDtoJson, TokenDtoResponse.class);
            ApplicationDto applicationDto = json.fromJson(applicationDtoJson, ApplicationDto.class);
            if (validApplicationDto(applicationDto) && tokenDto.getToken().length() == 36) {
                Application application = new Application(applicationDto);
                String token = tokenDto.getToken();
                try {
                    return json.toJson(new DtoResponse(new ExpertDaoImpl().rateApplication(token, application, rating)));
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

    public String showRatedApplications(String tokenDtoJson) {
        Gson json = new Gson();
        try {
            TokenDtoResponse tokenDto = json.fromJson(tokenDtoJson, TokenDtoResponse.class);
            if (tokenDto.getToken().length() == 36) {
                String token = tokenDto.getToken();
                try {
                    return json.toJson(new DtoResponse(new ExpertDaoImpl().showRatedApplications(token)));
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

    public String changeRating(String tokenDtoJson, String applicationDtoJson, int rating) {
        Gson json = new Gson();
        try {
            ApplicationDto applicationDto = json.fromJson(applicationDtoJson, ApplicationDto.class);
            TokenDtoResponse tokenDto = json.fromJson(tokenDtoJson, TokenDtoResponse.class);
            if (validApplicationDto(applicationDto) && tokenDto.getToken().length() == 36 && rating > 0 && rating < 6) {
                String token = tokenDto.getToken();
                Application application = new Application(applicationDto);
                try {
                    return json.toJson(new DtoResponse(new ExpertDaoImpl().changeRating(token, application, rating)));
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

    public String deleteRating(String tokenDtoJson, String applicationDtoJson) {
        Gson json = new Gson();
        try {
            ApplicationDto applicationDto = json.fromJson(applicationDtoJson, ApplicationDto.class);
            TokenDtoResponse tokenDto = json.fromJson(tokenDtoJson, TokenDtoResponse.class);
            if (validApplicationDto(applicationDto) && tokenDto.getToken().length() == 36) {
                String token = tokenDto.getToken();
                Application application = new Application(applicationDto);
                try {
                    return json.toJson(new DtoResponse(new ExpertDaoImpl().deleteRating(token,application)));
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
