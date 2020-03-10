package net.thumbtack.school.competition.daoimpl;

import net.thumbtack.school.competition.dao.MemberDao;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Member;

public class MemberDaoImpl implements MemberDao {
    private Database database;

    public MemberDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String insertMember(Member member) throws CompetitionException {
        return database.insert(member);
    }
}
