package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.competition.daoimpl.ApplicationDaoImpl;
import net.thumbtack.school.competition.daoimpl.ExpertDaoImpl;
import net.thumbtack.school.competition.dto.*;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.Subject;
import net.thumbtack.school.database.mybatis.daoimpl.SubjectDaoImpl;

import java.util.List;

public class ExpertService {
    private ExpertDaoImpl expertDao;
    private ApplicationDaoImpl applicationDao;

    public ExpertService() {
        expertDao = new ExpertDaoImpl();
        applicationDao = new ApplicationDaoImpl();
    }

    public String registerExpert(String requestJsonString) {
        Gson json = new Gson();
        try {
            RegisterExpertDto red = json.fromJson(requestJsonString, RegisterExpertDto.class);
            if (UserService.validUserDto(red.getFirstName(), red.getLastName(), red.getLogin(), red.getPassword())
                    && red.getSubjectsList().size() > 0) {
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

    public String expertShowApplications(String tokenDtoJson) {
        Gson json = new Gson();
        try {
            TokenDtoResponse tokenDto = json.fromJson(tokenDtoJson, TokenDtoResponse.class);
            if (tokenDto.getToken().length() == 36) {
                String token = tokenDto.getToken();
                try {
                    return json.toJson(new DtoResponse(applicationDao.expertShowApplications(token)));
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

    public String expertShowApplications(String tokenDtoJson, List<Subject> subjects) {
        Gson json = new Gson();
        try {
            TokenDtoResponse tokenDto = json.fromJson(tokenDtoJson, TokenDtoResponse.class);
            if (subjects.size() > 0 && tokenDto.getToken().length() == 36) {
                String token = tokenDto.getToken();
                try {
                    return json.toJson(new DtoResponse(applicationDao.expertShowApplications(token, subjects)));
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
            if (UserService.validApplicationDto(applicationDto) && tokenDto.getToken().length() == 36) {
                Application application = new Application(applicationDto);
                String token = tokenDto.getToken();
                try {
                    return json.toJson(new DtoResponse(applicationDao.rateApplication(token, application, rating)));
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
                    return json.toJson(new DtoResponse(json.toJson(applicationDao.showRatedApplications(token))));
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
            if (UserService.validApplicationDto(applicationDto) && tokenDto.getToken().length() == 36 && rating > 0 && rating < 6) {
                String token = tokenDto.getToken();
                Application application = new Application(applicationDto);
                try {
                    return json.toJson(new DtoResponse(expertDao.changeRating(token, application, rating)));
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
            if (UserService.validApplicationDto(applicationDto) && tokenDto.getToken().length() == 36) {
                String token = tokenDto.getToken();
                Application application = new Application(applicationDto);
                try {
                    return json.toJson(new DtoResponse(expertDao.deleteRating(token, application)));
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

    public String showExpertSubjects(String tokenDtoJson) {
        Gson json = new Gson();
        try {
            TokenDtoResponse tokenDto = json.fromJson(tokenDtoJson, TokenDtoResponse.class);
            if (tokenDto.getToken().length() == 36) {
                String token = tokenDto.getToken();
                try {
                    return json.toJson(new DtoResponse(expertDao.showExpertSubjects(token)));
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
