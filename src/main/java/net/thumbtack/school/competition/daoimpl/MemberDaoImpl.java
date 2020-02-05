package net.thumbtack.school.competition.daoimpl;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dao.MemberDao;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Member;

public class MemberDaoImpl implements MemberDao {

    @Override
    public String insertMember(Member member) throws CompetitionException {
        Gson json = new Gson();
        return Database.getInstance().insert(member);
    }
}
