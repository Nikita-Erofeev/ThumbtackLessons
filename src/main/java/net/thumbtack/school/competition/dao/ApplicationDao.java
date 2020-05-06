package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.dto.TokenDtoResponse;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Subject;

import java.util.List;
import java.util.Map;

public interface ApplicationDao {
    String insertApplication(TokenDtoResponse tokenDto, Application application) throws CompetitionException;
    String memberShowApplications(TokenDtoResponse token) throws CompetitionException;
    String memberDeleteApplication(String token, Application application) throws CompetitionException;

    String expertShowApplications(String token) throws CompetitionException;
    String expertShowApplications(String token, List<Subject> subjects) throws CompetitionException;
    String rateApplication(String token, Application application, int rating) throws CompetitionException;
    Map<Application, Integer> showRatedApplications(String token) throws CompetitionException;
}
