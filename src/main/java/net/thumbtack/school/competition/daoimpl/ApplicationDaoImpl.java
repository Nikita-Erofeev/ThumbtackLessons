package net.thumbtack.school.competition.daoimpl;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dao.ApplicationDao;
import net.thumbtack.school.competition.dto.TokenDtoResponse;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.exceptions.ErrorCode;
import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.Member;
import net.thumbtack.school.competition.model.Subject;
import net.thumbtack.school.competition.service.UserOnline;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.util.*;

public class ApplicationDaoImpl extends DaoImplBase implements ApplicationDao {
    private Gson json = new Gson();

    public ApplicationDaoImpl() {
    }

    @Override
    public String insertApplication(TokenDtoResponse tokenDto, Application application) throws CompetitionException {
        UUID key = json.fromJson(tokenDto.getToken(), UUID.class);
        Member member = (Member) UserOnline.getInstance().getUser(key);
        if (member != null) {
            try (SqlSession sqlSession = getSession()) {
                try {
                    getApplicationMapper(sqlSession).insert(member, application);
                    for (Subject subject : application.getSubjectsList()) {
                        try {
                            getSubjectMapper(sqlSession).insertSubject(subject);
                            getSubjectMapper(sqlSession).insertApplicationSubject(application, subject);
                        } catch (PersistenceException ex) {
                            subject.setId(getSubjectMapper(sqlSession).getByName(subject).getId());
                            getSubjectMapper(sqlSession).insertApplicationSubject(application, subject);
                        }
                    }
                } catch (PersistenceException ex) {
                    sqlSession.rollback();
                    throw new CompetitionException(ErrorCode.DUPLICATE_APPLICATION);
                }
                sqlSession.commit();
            } catch (PersistenceException ex) {
                throw new CompetitionException(ErrorCode.ERROR_UPLOAD_DATABASE);
            }
            return "ok";
        } else {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    @Override
    public String memberShowApplications(TokenDtoResponse token) throws CompetitionException {
        UUID key = json.fromJson(token.getToken(), UUID.class);
        Member member = (Member) UserOnline.getInstance().getUser(key);
        if (member != null) {
            try (SqlSession sqlSession = getSession()) {
                List<Application> result = getApplicationMapper(sqlSession).getAppByMember(member);
                return json.toJson(result);
            } catch (PersistenceException ex) {
                throw new CompetitionException(ErrorCode.APPLICATION_NOT_EXIST);
            }
        } else {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    @Override
    public String memberDeleteApplication(String token, Application application) throws CompetitionException {
        UUID key = json.fromJson(token, UUID.class);
        Member member = (Member) UserOnline.getInstance().getUser(key);
        if (application.getId() == 0) {
            throw new CompetitionException(ErrorCode.APPLICATION_NOT_EXIST);
        }
        if (member != null) {
            try (SqlSession sqlSession = getSession()) {
                try {
                    getApplicationMapper(sqlSession).deleteApplication(application);
                } catch (PersistenceException ex) {
                    sqlSession.rollback();
                    throw new CompetitionException(ErrorCode.APPLICATION_NOT_EXIST);
                }
                sqlSession.commit();
            }
            return "application deleted";
        } else {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    @Override
    public String expertShowApplications(String token) throws CompetitionException {
        UUID key = json.fromJson(token, UUID.class);
        Expert expert = (Expert) UserOnline.getInstance().getUser(key);
        if (expert != null) {
            try (SqlSession sqlSession = getSession()) {
                List<Application> result = getApplicationMapper(sqlSession).expertShowApplications(expert);
                return json.toJson(result);
            } catch (PersistenceException ex) {
                throw new CompetitionException(ErrorCode.INVALID_REQUEST);
            }
        } else {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    @Override
    public String expertShowApplications(String token, List<Subject> subjects) throws CompetitionException {
        UUID key = json.fromJson(token, UUID.class);
        Expert expert = (Expert) UserOnline.getInstance().getUser(key);
        if (expert != null) {
            if (!expert.getSubjectsList().containsAll(subjects)) {
                throw new CompetitionException(ErrorCode.INVALID_REQUEST);
            }
            try (SqlSession sqlSession = getSession()) {
                List<Application> result = getApplicationMapper(sqlSession).expertShowAppBySubjects(subjects);
                return json.toJson(result);
            } catch (PersistenceException ex) {
                throw new CompetitionException(ErrorCode.INVALID_REQUEST);
            }
        } else {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    private boolean canRate(List<Subject> expertSubjects, List<Subject> appSubject){
        for (Subject subject: appSubject) {
            if (expertSubjects.contains(subject)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String rateApplication(String token, Application application, int rating) throws CompetitionException {
        UUID key = json.fromJson(token, UUID.class);
        Expert expert = (Expert) UserOnline.getInstance().getUser(key);
        if (expert != null) {
            if (!canRate(expert.getSubjectsList(), application.getSubjectsList())) {
                throw new CompetitionException(ErrorCode.APPLICATION_NOT_EXIST);
            }
            try (SqlSession sqlSession = getSession()) {
                try {
                    getApplicationMapper(sqlSession).rateApplication(expert, application, rating);
                } catch (PersistenceException ex) {
                    sqlSession.rollback();
                    throw new CompetitionException(ErrorCode.INVALID_REQUEST);
                }
                sqlSession.commit();
            }
            return "You rated application: " + application.getName();
        } else {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    @Override
    public Map<Application, Integer> showRatedApplications(String token) throws CompetitionException {
        UUID key = json.fromJson(token, UUID.class);
        Expert expert = (Expert) UserOnline.getInstance().getUser(key);
        if (expert != null) {
            try (SqlSession sqlSession = getSession()) {
                ArrayList<Application> apps = getApplicationMapper(sqlSession).showRatedApplications(expert);
                if (apps.size() == 0) {
                    throw new CompetitionException(ErrorCode.RATINGS_NOT_EXIST);
                }
                ArrayList<Integer> ratings = getApplicationMapper(sqlSession).getRatings(expert);
                Map<Application, Integer> result = new HashMap<>();
                for (Application application : apps) {
                    result.put(application, ratings.get(apps.indexOf(application)));
                }
                return result;
            } catch (PersistenceException ex) {
                throw new CompetitionException(ErrorCode.INVALID_REQUEST);
            }
        } else {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }
}
