package net.thumbtack.school.competition.daoimpl;

import com.google.gson.Gson;
import net.thumbtack.school.competition.dao.UserDao;
import net.thumbtack.school.competition.dto.LoginDto;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.exceptions.ErrorCode;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.Member;
import net.thumbtack.school.competition.model.User;
import net.thumbtack.school.competition.service.UserOnline;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.util.UUID;

public class UserDaoImpl extends DaoImplBase implements UserDao {

    public UserDaoImpl() {
        super();
    }

    @Override
    public String deleteUser(String token) throws CompetitionException {
        Gson json = new Gson();
        User user = UserOnline.getInstance().getUser(json.fromJson(token, UUID.class));
        if (user != null) {
            try (SqlSession sqlSession = getSession()) {
                try {
                    getUserMapper(sqlSession).deleteUser(user.getId());
                } catch (PersistenceException ex) {
                    sqlSession.rollback();
                    throw new CompetitionException(ErrorCode.INVALID_REQUEST);
                }
                sqlSession.commit();
            }
            return "User successfully deleted from the server";
        } else {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }

    @Override
    public String login(LoginDto loginDto) throws CompetitionException {
        try (SqlSession sqlSession = getSession()) {
            try {
                String comName = getUserMapper(sqlSession).getUserCompanyName(loginDto.getLogin(), loginDto.getPassword());
                if (comName == null || comName.equals("")) {
                    Expert expert = getExpertMapper(sqlSession).getExpert(loginDto.getLogin(), loginDto.getPassword());
                    UUID token = UUID.randomUUID();
                    UserOnline.getInstance().logIn(token, expert);
                    return token.toString();
                } else {
                    Member member = getMemberMapper(sqlSession).getMember(loginDto.getLogin(), loginDto.getPassword());
                    UUID token = UUID.randomUUID();
                    UserOnline.getInstance().logIn(token, member);
                    return token.toString();
                }
            } catch (PersistenceException ex) {
                throw new CompetitionException(ErrorCode.INVALID_REQUEST);
            }
        }
    }

    @Override
    public String logout(String token) throws CompetitionException {
        Gson json = new Gson();
        UUID key = json.fromJson(token, UUID.class);
        if (UserOnline.getInstance().logOut(key)) {
            return "You are logged out";
        } else {
            throw new CompetitionException(ErrorCode.LOGIN_ERROR);
        }
    }
}
