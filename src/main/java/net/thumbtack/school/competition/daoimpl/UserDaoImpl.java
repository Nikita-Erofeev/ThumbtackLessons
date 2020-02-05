package net.thumbtack.school.competition.daoimpl;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dao.UserDao;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.User;

public class UserDaoImpl implements UserDao {
    @Override
    public String deleteUser(String token) throws CompetitionException {
        Gson json = new Gson();
        return Database.getInstance().deleteUser(token);
    }
}
