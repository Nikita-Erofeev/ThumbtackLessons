package net.thumbtack.school.competition.daoimpl;

import net.thumbtack.school.competition.dao.ExpertDao;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Expert;

import java.util.List;

public class ExpertDaoImpl implements ExpertDao {
    @Override
    public String insertExpert(Expert expert) throws CompetitionException {
        return Database.getInstance().insert(expert);
    }

    @Override
    public String expertShowApplications(String token) throws CompetitionException {
        return Database.getInstance().expertShowApplications(token);
    }

    @Override
    public String expertShowApplications(String token, List<String> subjects) throws CompetitionException {
        return Database.getInstance().expertShowApplications(token, subjects);
    }

    @Override
    public String rateApplication(String token, Application application, int rating) throws CompetitionException {
        return Database.getInstance().rateApplication(token, application, rating);
    }

    @Override
    public String showRatedApplications(String token) throws CompetitionException {
        return Database.getInstance().showRatedApplications(token);
    }

    @Override
    public String changeRating(String token, Application application, int rating) throws CompetitionException {
        return Database.getInstance().changeRating(token, application, rating);
    }

    @Override
    public String deleteRating(String token, Application application) throws CompetitionException {
        return Database.getInstance().deleteRating(token, application);
    }
}
