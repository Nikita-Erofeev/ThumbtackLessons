package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.exceptions.CompetitionException;

public interface AdminDao {
    String summarize(int fund, int minRate) throws CompetitionException;
}

