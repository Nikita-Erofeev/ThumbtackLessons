package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.dto.TokenDtoResponse;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Application;

import java.util.List;

public interface ApplicationDao {
    String insertApplication(TokenDtoResponse tokenDto, Application application) throws CompetitionException;
    String memberShowApplications(TokenDtoResponse token) throws CompetitionException;
    String memberDeleteApplication(String token, Application application) throws CompetitionException;

    String expertShowApplications(String token) throws CompetitionException;
    String expertShowApplications(String token, List<String> subjects) throws CompetitionException;
    String rateApplication(String token, Application application, int rating) throws CompetitionException;
    String showRatedApplications(String token) throws CompetitionException;
}
