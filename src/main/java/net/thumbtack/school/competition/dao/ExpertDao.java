package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Expert;

public interface ExpertDao {
    String insertExpert(Expert expert) throws CompetitionException;
}
