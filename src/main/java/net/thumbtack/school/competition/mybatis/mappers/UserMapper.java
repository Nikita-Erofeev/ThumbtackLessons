package net.thumbtack.school.competition.mybatis.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(int id);

    @Select("SELECT companyname FROM user WHERE login = #{login} AND pass = #{password}")
    String getUserCompanyName(@Param("login") String login, @Param("password") String password);


}
