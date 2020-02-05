package net.thumbtack.school.competition.daoimpl;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dao.ExpertDao;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.model.Expert;

public class ExpertDaoImpl implements ExpertDao {
    @Override
    public String insertExpert(Expert expert) throws CompetitionException {
        Gson json = new Gson();
        return Database.getInstance().insert(expert);
    }
}
