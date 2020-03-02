package net.thumbtack.school.competition.daoimpl;

import net.thumbtack.school.competition.dao.AdminDao;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.dto.SummarizeDto;
import net.thumbtack.school.competition.exceptions.CompetitionException;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean startServer(String savedDataFileName) throws CompetitionException {
        return Database.uploadDatabase(savedDataFileName);
    }

    @Override
    public boolean stopServer(String savedDataFileName) throws CompetitionException {
        return Database.saveDatabase(savedDataFileName);
    }

    @Override
    public String summarize(int fund, int minRate) throws CompetitionException {
        return Database.getInstance().summarize(fund, minRate);
    }

}
