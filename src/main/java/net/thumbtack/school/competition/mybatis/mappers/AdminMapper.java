package net.thumbtack.school.competition.mybatis.mappers;

import net.thumbtack.school.competition.model.Application;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface AdminMapper {
    @Delete("DELETE FROM user WHERE NOT id = 0")
    void clearUser();

    @Delete("DELETE FROM application WHERE NOT id = 0")
    void clearApplication();

    @Delete("DELETE FROM subject WHERE NOT id = 0")
    void clearSubject();

    @Select("SELECT AVG(rating) FROM rating GROUP BY id_application ORDER BY id_application")
    List<Float> getAvgRating();

    @Select("SELECT id, name, description, amountrequested FROM application WHERE id IN " +
            "(SELECT DISTINCT id_application FROM rating) ORDER BY id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "subjectsList", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.competition.mybatis.mappers.SubjectMapper.getByApplication",
                            fetchType = FetchType.EAGER))
    })
    List<Application> getRatedApplications();
}
