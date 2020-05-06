
package net.thumbtack.school.competition.daoimpl;

import net.thumbtack.school.competition.dao.AdminDao;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.exceptions.ErrorCode;
import net.thumbtack.school.competition.model.Application;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDaoImpl extends DaoImplBase implements AdminDao {

    public AdminDaoImpl() {

    }

    @Override
    public boolean clearDatabase() {
        try (SqlSession sqlSession = getSession()) {
            try {
                getAdminMapper(sqlSession).clearUser();
                getAdminMapper(sqlSession).clearApplication();
                getAdminMapper(sqlSession).clearSubject();
            } catch (RuntimeException ex) {
                sqlSession.rollback();
                ex.getCause();
            }
            sqlSession.commit();
        }
        return true;
    }

    @Override
    public Map<Application, Float> summarize() throws CompetitionException {
        try (SqlSession sqlSession = getSession()) {
            List<Application> applicationList = getAdminMapper(sqlSession).getRatedApplications();
            List<Float> avgRatingList = getAdminMapper(sqlSession).getAvgRating();
            Map<Application, Float> result = new HashMap<>();
            for (Application application : applicationList) {
                result.put(application, avgRatingList.get(applicationList.indexOf(application)));
            }
            return result;
        } catch (RuntimeException ex) {
            throw new CompetitionException(ErrorCode.INVALID_REQUEST);
        }
    }

}

