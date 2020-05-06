package net.thumbtack.school.competition.mybatis.mappers;

import net.thumbtack.school.competition.model.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface MemberMapper {

    @Insert("INSERT INTO user VALUES (null, #{firstName}, #{lastName}, #{companyName}, #{login}, #{password})")
    @Options(useGeneratedKeys = true)
    void insertMember(Member member);

    @Select("SELECT * FROM user WHERE login = #{login} AND pass = #{password}")
    Member getMember(@Param("login") String login,@Param("password") String password);
}
