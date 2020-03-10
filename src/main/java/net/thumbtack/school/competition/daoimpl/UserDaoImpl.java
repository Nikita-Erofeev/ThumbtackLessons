package net.thumbtack.school.competition.daoimpl;

import net.thumbtack.school.competition.dao.UserDao;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.dto.LoginDto;
import net.thumbtack.school.competition.exceptions.CompetitionException;

public class UserDaoImpl implements UserDao {
    private Database database;

    public UserDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String deleteUser(String token) throws CompetitionException {
        return database.deleteUser(token);
    }

    @Override
    public String login(LoginDto loginDto) throws CompetitionException {
        return database.login(loginDto);
    }

    @Override
    public String logout(String token) throws CompetitionException {
        return database.logout(token);
    }


}
