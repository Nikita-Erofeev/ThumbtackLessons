package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.competition.daoimpl.ApplicationDaoImpl;
import net.thumbtack.school.competition.daoimpl.MemberDaoImpl;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.dto.*;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Member;

public class MemberService {
    private MemberDaoImpl memberDao;
    private ApplicationDaoImpl applicationDao;

    public MemberService(Database database) {
        memberDao = new MemberDaoImpl(database);
        applicationDao = new ApplicationDaoImpl(database);
    }

    public String registerMember(String requestJsonString) {
        Gson json = new Gson();
        try {
            RegisterMemberDto rmd = json.fromJson(requestJsonString, RegisterMemberDto.class);
            if (UserService.validUserDto(rmd.getFirstName(), rmd.getLastName(), rmd.getLogin(), rmd.getPassword())
                    && UserService.validString(rmd.getCompanyName())) {
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

    public String addApplication(String tokenJsonDto, String appJsonDto) {
        Gson json = new Gson();
        try {
            ApplicationDto applicationDto = json.fromJson(appJsonDto, ApplicationDto.class);
            TokenDtoResponse tokenDto = json.fromJson(tokenJsonDto, TokenDtoResponse.class);
            if (UserService.validApplicationDto(applicationDto) && (tokenJsonDto.length() == 48)) {
                try {
                    Application application = new Application(applicationDto);
                    return json.toJson(new DtoResponse(applicationDao.insertApplication(tokenDto, application)));
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
                    return json.toJson(new DtoResponse(applicationDao.memberShowApplications(tokenDto)));
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
            if (UserService.validApplicationDto(applicationDto) && tokenDto.getToken().length() == 36) {
                String token = tokenDto.getToken();
                Application application = new Application(applicationDto);
                try {
                    return json.toJson(new DtoResponse(applicationDao.memberDeleteApplication(token, application)));
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
