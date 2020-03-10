package net.thumbtack.school.competition.daoimpl;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dao.AdminDao;
import net.thumbtack.school.competition.database.Database;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.exceptions.ErrorCode;
import net.thumbtack.school.competition.model.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminDaoImpl implements AdminDao {
    private Database database;

    public AdminDaoImpl() {
    }

    public AdminDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public boolean startServer(String savedDataFileName) throws CompetitionException {
        return Database.uploadDatabase(savedDataFileName);
    }

    @Override
    public boolean stopServer(String savedDataFileName) throws CompetitionException {
        return Database.saveDatabase(savedDataFileName);
    }

    @Override
    public String summarize(int fund, int minRate) throws CompetitionException {
        Gson json = new Gson();
        List<Map.Entry<Application, Float>> sortedAppList = database.summarize();
        List<String> result = new ArrayList<>();
        boolean error = true;
        /*Проверка условий и формирование результата*/
        for (Map.Entry<Application, Float> item : sortedAppList) {
            if (item.getKey().getAmountRequested() <= fund && item.getValue() >= minRate) {
                error = false;
                fund -= item.getKey().getAmountRequested();
                result.add("Выделенная сумма: " + item.getKey().getAmountRequested()
                        + item.getKey().toString() + " Средняя оценка: " + item.getValue());
            }
        }
        if (error) {
            throw new CompetitionException(ErrorCode.SUMMARIZE_ERROR);
        } else {
            return json.toJson(result.toArray());
        }
    }

}
