package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.User;

public interface UserDao {
    public String deleteUser(String token) throws CompetitionException;
}
