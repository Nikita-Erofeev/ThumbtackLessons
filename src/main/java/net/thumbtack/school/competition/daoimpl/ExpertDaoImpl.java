package net.thumbtack.school.competition.daoimpl;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dao.ExpertDao;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.exceptions.ErrorCode;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.Subject;
import net.thumbtack.school.competition.service.UserOnline;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ExpertDaoImpl extends DaoImplBase implements ExpertDao {
    private Gson json = new Gson();

    public ExpertDaoImpl() {
        super();
    }

    @Override
    public String insertExpert(Expert expert) throws CompetitionException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getExpertMapper(sqlSession).insertExpert(expert);
                Set<Subject> toChange = new HashSet<>();
                for (Subject subject : expert.getSubjectsList()) {
                    try {
                        getSubjectMapper(sqlSession).insertSubject(subject);
                        getSubjectMapper(sqlSession).insertExpertSubject(expert, subject);
                    } catch (PersistenceException ex) {
                        Subject subject1 = getSubjectMapper(sqlSession).getByName(subject);
                        getSubjectMapper(sqlSession).insertExpertSubject(expert, subject1);
                        toChange.add(subject1);
                    }
                }
                List<Subject> subjectList = expert.getSubjectsList();
                for (Subject subjectX : subjectList) {
                    for (Subject subjectY : toChange) {
                        if (subjectX.getName().equals(subjectY.getName())) {
                            subjectX.setId(subjectY.getId());
                        }
                    }
                }
            } catch (PersistenceException ex) {
                sqlSession.rollback();
                throw new CompetitionException(ErrorCode.DUPLICATE_USER);
            }
            sqlSession.commit();
        }
        UUID token = UUID.randomUUID();
        UserOnline.getInstance().logIn(token, expert);
        return token.toString();
    }

    @Override
    public String changeRating(String token, Application application, int rating) throws CompetitionException {
        UUID key = json.fromJson(token, UUID.class);
        Expert expert = (Expert) UserOnline.getInstance().getUser(key);
        if (expert != null) {
            try (SqlSession sqlSession = getSession()) {
                try {
                    getExpertMapper(sqlSession).changeRating(expert, application, rating);
                } catch (PersistenceException ex) {
                    sqlSession.rollback();
                    throw new CompetitionException(ErrorCode.INVALID_REQUEST);
                }
                sqlSession.commit();
            }
            return "ok";
        } else {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    @Override
    public String deleteRating(String token, Application application) throws CompetitionException {
        UUID key = json.fromJson(token, UUID.class);
        Expert expert = (Expert) UserOnline.getInstance().getUser(key);
        if (expert != null) {
            try (SqlSession sqlSession = getSession()) {
                try {
                    getExpertMapper(sqlSession).deleteRating(expert, application);
                } catch (PersistenceException ex) {
                    sqlSession.rollback();
                    throw new CompetitionException(ErrorCode.INVALID_REQUEST);
                }
                sqlSession.commit();
            }
            return "ok";
        } else {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    @Override
    public String showExpertSubjects(String token) throws CompetitionException {
        UUID key = json.fromJson(token, UUID.class);
        Expert expert = (Expert) UserOnline.getInstance().getUser(key);
        if (expert != null) {
            try (SqlSession sqlSession = getSession()) {
                List<Subject> list = getSubjectMapper(sqlSession).getByExpert(expert);
                return json.toJson(list);
            } catch (RuntimeException ex) {
                throw new CompetitionException(ErrorCode.INVALID_REQUEST);
            }
        } else {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }
}
