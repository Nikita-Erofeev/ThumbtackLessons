package net.thumbtack.school.competition.daoimpl;

import net.thumbtack.school.competition.dao.UserDao;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.dto.LoginDto;
import net.thumbtack.school.competition.dto.TokenDtoResponse;
import net.thumbtack.school.competition.exceptions.CompetitionException;

public class UserDaoImpl implements UserDao {
    @Override
    public String deleteUser(String token) throws CompetitionException {
        return Database.getInstance().deleteUser(token);
    }

    @Override
    public String login(LoginDto loginDto) throws CompetitionException {
        return Database.getInstance().login(loginDto);
    }

    @Override
    public String logout(String token) throws CompetitionException {
        return Database.getInstance().logout(token);
    }


}
