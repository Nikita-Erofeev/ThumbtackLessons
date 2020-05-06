package net.thumbtack.school.competition.mybatis.mappers;

import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Expert;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ExpertMapper {

    @Insert("INSERT INTO user VALUES (null,  #{firstName}, #{lastName}, null, #{login}, #{password})")
    @Options(useGeneratedKeys = true)
    void insertExpert(Expert expert);

    @Update("UPDATE rating SET rating = #{rating} WHERE id_expert = #{expert.id} AND id_application = #{application.id}")
    void changeRating(@Param("expert") Expert expert, @Param("application") Application application,
                      @Param("rating") int rating);

    @Delete("DELETE FROM rating WHERE id_expert = #{expert.id} AND id_application = #{application.id}")
    void deleteRating(@Param("expert") Expert expert,@Param("application") Application application);

    @Select("SELECT id, firstname, lastname, login, pass AS password FROM user WHERE login = #{login} AND pass = #{password}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "subjectsList", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.competition.mybatis.mappers.SubjectMapper.getByExpert",
                            fetchType = FetchType.LAZY))
    })
    Expert getExpert(@Param("login") String login,@Param("password") String password);
}
