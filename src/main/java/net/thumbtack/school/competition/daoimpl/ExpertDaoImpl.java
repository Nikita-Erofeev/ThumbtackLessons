package net.thumbtack.school.competition.daoimpl;

import net.thumbtack.school.competition.dao.ExpertDao;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Expert;

public class ExpertDaoImpl implements ExpertDao {
    private Database database;

    public ExpertDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String insertExpert(Expert expert) throws CompetitionException {
        return database.insert(expert);
    }

    @Override
    public String changeRating(String token, Application application, int rating) throws CompetitionException {
        return database.changeRating(token, application, rating);
    }

    @Override
    public String deleteRating(String token, Application application) throws CompetitionException {
        return database.deleteRating(token, application);
    }
}
