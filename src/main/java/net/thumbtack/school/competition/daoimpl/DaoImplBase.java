package net.thumbtack.school.competition.daoimpl;

import net.thumbtack.school.competition.mybatis.mappers.*;
import net.thumbtack.school.competition.mybatis.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

public class DaoImplBase {
    protected SqlSession getSession() {
        return  MyBatisUtils.getSqlSessionFactory().openSession();
    }

    protected MemberMapper getMemberMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(MemberMapper.class);
    }

    protected ExpertMapper getExpertMapper(SqlSession sqlSession){
        return sqlSession.getMapper(ExpertMapper.class);
    }

    protected SubjectMapper getSubjectMapper(SqlSession sqlSession){
        return sqlSession.getMapper(SubjectMapper.class);
    }

    protected UserMapper getUserMapper(SqlSession sqlSession){
        return sqlSession.getMapper(UserMapper.class);
    }

    protected ApplicationMapper getApplicationMapper(SqlSession sqlSession){
        return sqlSession.getMapper(ApplicationMapper.class);
    }

    protected AdminMapper getAdminMapper(SqlSession sqlSession){
        return sqlSession.getMapper(AdminMapper.class);
    }
}
