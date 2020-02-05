package net.thumbtack.school.competition.service;

import com.google.gson.Gson;
import net.thumbtack.school.competition.daoimpl.ExpertDaoImpl;
import net.thumbtack.school.competition.daoimpl.MemberDaoImpl;
import net.thumbtack.school.competition.daoimpl.UserDaoImpl;
import net.thumbtack.school.competition.dto.*;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.Member;

public class UserService {

    private boolean validString(String string) {
        return string != null && !string.equals("") & string.length() > 2;
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
        RegisterMemberDto rmd = json.fromJson(requestJsonString, RegisterMemberDto.class);
        if (validUserDto(rmd.getFirstName(), rmd.getLastName(), rmd.getLogin(), rmd.getPassword())
                & validString(rmd.getCompanyName())) {
            MemberDaoImpl memberDao = new MemberDaoImpl();
            Member member = new Member(rmd.getFirstName(), rmd.getLastName(), rmd.getCompanyName(), rmd.getLogin(),
                    rmd.getPassword());
            try {
                return json.toJson(new RegisterUserDtoResponse(memberDao.insertMember(member)));
            } catch (CompetitionException e){
                DtoError de = new DtoError(e.getErrorCode().getErrorString());
                return json.toJson(de);
            }
        } else {
            return json.toJson(new DtoError("Error, invalid request"));
        }
    }

    public String registerExpert(String requestJsonString) {
        Gson json = new Gson();
        RegisterExpertDto red = json.fromJson(requestJsonString, RegisterExpertDto.class);
        if (validUserDto(red.getFirstName(), red.getLastName(), red.getLogin(), red.getPassword())
                & red.getSubjectsList().size() > 0) {
            ExpertDaoImpl expertDao = new ExpertDaoImpl();
            Expert expert = new Expert(red.getFirstName(), red.getLastName(), red.getSubjectsList(), red.getLogin(),
                    red.getPassword());
            try {
                return json.toJson(new RegisterUserDtoResponse(expertDao.insertExpert(expert)));
            } catch (CompetitionException e){
                DtoError de = new DtoError(e.getErrorCode().getErrorString());
                return json.toJson(de);
            }

        } else {
            return json.toJson(new DtoError("Error, invalid request"));
        }
    }

    public String deleteUser(String token){
        Gson json = new Gson();
        RegisterUserDtoResponse rudr = json.fromJson(token,RegisterUserDtoResponse.class);
        if(validString(token)){
            UserDaoImpl udi = new UserDaoImpl();
            try {
                return json.toJson(new DtoResponse(udi.deleteUser(rudr.getToken()))) ;
            } catch (CompetitionException e){
                return json.toJson(new DtoError(e.getErrorCode().getErrorString()));
            }
        } else {
            return json.toJson(new DtoError("Error, invalid request"));
        }
    }
}
