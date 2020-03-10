package net.thumbtack.school.competition.daoimpl;

import net.thumbtack.school.competition.dao.ApplicationDao;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.dto.TokenDtoResponse;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Application;

import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao {
    private Database database;

    public ApplicationDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String insertApplication(TokenDtoResponse tokenDto, Application application) throws CompetitionException {
        return database.insertApplication(tokenDto, application);
    }

    @Override
    public String memberShowApplications(TokenDtoResponse token) throws CompetitionException {
        return database.memberShowApplications(token);
    }

    @Override
    public String memberDeleteApplication(String token, Application application) throws CompetitionException {
        return database.memberDeleteApplication(token, application);
    }

    @Override
    public String expertShowApplications(String token) throws CompetitionException {
        return database.expertShowApplications(token);
    }

    @Override
    public String expertShowApplications(String token, List<String> subjects) throws CompetitionException {
        return database.expertShowApplications(token, subjects);
    }

    @Override
    public String rateApplication(String token, Application application, int rating) throws CompetitionException {
        return database.rateApplication(token, application, rating);
    }

    @Override
    public String showRatedApplications(String token) throws CompetitionException {
        return database.showRatedApplications(token);
    }
}
