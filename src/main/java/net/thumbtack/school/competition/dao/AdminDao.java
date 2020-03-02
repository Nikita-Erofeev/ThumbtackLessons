package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.dto.SummarizeDto;
import net.thumbtack.school.competition.exceptions.CompetitionException;

public interface AdminDao {
    boolean startServer(String savedDataFileName) throws CompetitionException;
    boolean stopServer(String savedDataFileName) throws CompetitionException;
    String summarize(int fund, int minRate) throws CompetitionException;
}

