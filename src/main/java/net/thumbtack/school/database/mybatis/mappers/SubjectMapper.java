package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Subject;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SubjectMapper {
    @Insert("INSERT INTO subject VALUES (null, #{name})")
    @Options(useGeneratedKeys = true)
    Integer insert(Subject subject);

    @Select("SELECT id, name FROM subject WHERE id = #{id} ")
    Subject getById(int id);

    @Select("SELECT id, name FROM subject")
    List<Subject> getAll();

    @Update("UPDATE subject SET name = #{name} WHERE id = #{id}")
    void update(Subject subject);

    @Delete("DELETE FROM subject WHERE id = #{id}")
    void delete(Subject subject);

    @Delete("DELETE FROM subject WHERE NOT id = 0")
    void deleteAll();

    @Select("SELECT * FROM subject WHERE id IN (SELECT subjectid FROM subject_group WHERE groupid = #{group.id})")
    List<Subject> getByGroup(Group group);

    @Insert("INSERT INTO subject_group VALUES (null, #{subject.id}, #{group.id})")
    void connectGroupSubject(@Param("group") Group group, @Param("subject") Subject subject);
}
