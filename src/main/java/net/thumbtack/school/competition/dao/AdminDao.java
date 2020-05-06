package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Application;

import java.util.Map;

public interface AdminDao {
    Map<Application, Float> summarize() throws CompetitionException;
    boolean clearDatabase();
}

