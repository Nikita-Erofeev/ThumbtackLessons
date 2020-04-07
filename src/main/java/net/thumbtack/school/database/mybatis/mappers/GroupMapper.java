package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface GroupMapper {
    @Insert("INSERT INTO groups (schoolid, id, name, room) VALUES (#{school.id}, #{group.id}, #{group.name}, #{group.room})")
    @Options(useGeneratedKeys = true, keyProperty = "group.id")
    void insert(@Param("school") School school,@Param("group") Group group);

    @Update("UPDATE groups SET name = #{name}, room = #{room} WHERE id = #{id}")
    void update(Group group);

    @Select("SELECT (id, name, room) FROM group")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getByGroup",
                            fetchType = FetchType.LAZY)),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getByGroup",
                            fetchType = FetchType.LAZY))
    })
    List<Group> getAll();

    @Delete("DELETE FROM groups WHERE id = #{id}")
    void delete(Group group);

    @Update("UPDATE trainee SET groupid = #{group.id} WHERE id = #{trainee.id}")
    void moveTrainee(@Param("group") Group group,@Param("trainee") Trainee trainee);

    @Update("UPDATE trainee SET groupid = null WHERE id = #{id}")
    void deleteTrainee(Trainee trainee);

    @Insert("INSERT INTO subject_group VALUES (null, #{subject.id}, #{group.id})")
    void addSubject(@Param("group") Group group,@Param("subject") Subject subject);

    @Select("SELECT id, name, room FROM groups WHERE schoolid = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "room", column = "room"),
            @Result(property = "trainees", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.TraineeMapper.getByGroup",
                            fetchType = FetchType.EAGER)),
            @Result(property = "subjects", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.SubjectMapper.getByGroup",
                            fetchType = FetchType.EAGER))
    })
    List<Group> getBySchoolLazy(School school);

    @Delete("DELETE FROM groups WHERE NOT id = 0")
    void deleteAll();
}
