package net.thumbtack.school.competition.mybatis.mappers;

import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.Subject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SubjectMapper {

    @Insert("INSERT INTO subject VALUES (null, #{name})")
    @Options(useGeneratedKeys = true)
    void insertSubject(Subject subject);

    @Insert("INSERT INTO expert_subject VALUES (null, #{expert.id}, #{subject.id})")
    void insertExpertSubject(@Param("expert") Expert expert,@Param("subject") Subject subject);

    @Insert("INSERT INTO application_subject VALUES (null, #{app.id}, #{subject.id})")
    void insertApplicationSubject(@Param("app") Application application,@Param("subject") Subject subject);

    @Select("SELECT id, name FROM subject WHERE name = #{name}")
    Subject getByName(Subject subject);

    @Select("SELECT id, name FROM subject WHERE")
    Subject getById(int id);

    @Select("SELECT id, name FROM subject WHERE id IN(SELECT id_subject FROM expert_subject WHERE id_expert = #{id})")
    List<Subject> getByExpert(Expert expert);

    @Select("SELECT id, name FROM subject WHERE id IN(SELECT id_subject FROM application_subject " +
            "WHERE id_application = #{id})")
    List<Subject> getByApplication(Application application);
}
