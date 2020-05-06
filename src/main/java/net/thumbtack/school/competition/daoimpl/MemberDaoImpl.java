package net.thumbtack.school.competition.daoimpl;

import net.thumbtack.school.competition.dao.MemberDao;
import net.thumbtack.school.competition.exceptions.CompetitionException;
import net.thumbtack.school.competition.exceptions.ErrorCode;
import net.thumbtack.school.competition.model.Member;
import net.thumbtack.school.competition.service.UserOnline;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import java.util.UUID;

public class MemberDaoImpl extends DaoImplBase implements MemberDao {
    public MemberDaoImpl() {
        super();
    }

    @Override
    public String insertMember(Member member) throws CompetitionException {
        try (SqlSession sqlSession = getSession()) {
            try {
                getMemberMapper(sqlSession).insertMember(member);
            } catch (PersistenceException ex) {
                sqlSession.rollback();
                throw new CompetitionException(ErrorCode.DUPLICATE_USER);
            }
            sqlSession.commit();
        }
        UUID token = UUID.randomUUID();
        UserOnline.getInstance().logIn(token, member);
        return token.toString();
    }
}
