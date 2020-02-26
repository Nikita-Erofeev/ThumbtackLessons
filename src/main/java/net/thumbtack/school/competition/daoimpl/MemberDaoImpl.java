package net.thumbtack.school.competition.daoimpl;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dao.MemberDao;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.dto.TokenDtoResponse;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Member;

import javax.xml.crypto.Data;

public class MemberDaoImpl implements MemberDao {

    @Override
    public String insertMember(Member member) throws CompetitionException {
        Gson json = new Gson();
        return Database.getInstance().insert(member);
    }

    @Override
    public String insertApplication(TokenDtoResponse tokenDto, Application application) throws CompetitionException {
        Gson json = new Gson();
        return Database.getInstance().insertApplication(tokenDto, application);
    }

    @Override
    public String memberShowApplications(TokenDtoResponse token) throws CompetitionException {
        return Database.getInstance().memberShowApplications(token);
    }

    @Override
    public String memberDeleteApplication(String token, Application application) throws CompetitionException {
        return Database.getInstance().memberDeleteApplication(token, application);
    }


}
