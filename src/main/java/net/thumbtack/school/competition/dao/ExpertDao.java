package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Expert;

public interface ExpertDao {
    String insertExpert(Expert expert) throws CompetitionException;
    String changeRating(String token, Application application, int rating) throws CompetitionException;
    String deleteRating(String token, Application application) throws CompetitionException;
}
