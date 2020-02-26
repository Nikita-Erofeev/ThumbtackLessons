package net.thumbtack.school.competition.dao;

import net.thumbtack.school.competition.dto.LoginDto;
import net.thumbtack.school.competition.dto.TokenDtoResponse;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.User;

public interface UserDao {
    String deleteUser(String token) throws CompetitionException;
    String login(LoginDto loginDto) throws CompetitionException;
    String logout(String token) throws CompetitionException;
}
