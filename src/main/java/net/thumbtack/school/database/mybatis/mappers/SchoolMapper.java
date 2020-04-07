package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.School;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface SchoolMapper {

    @Insert("INSERT INTO school VALUES (#{id}, #{name}, #{year})")
    @Options(useGeneratedKeys = true)
    void insert(School school);

    @Select("SELECT * FROM school WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groups", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.GroupMapper.getBySchoolLazy",
                            fetchType = FetchType.LAZY))
    })
    School getById(int id);

    @Select("SELECT * FROM school")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "groups", column = "id", javaType = List.class,
            many = @Many(select = "net.thumbtack.school.database.mybatis.mappers.GroupMapper.getBySchoolLazy",
                    fetchType = FetchType.LAZY))
    })
    List<School> getAllLazy();

    @Update("UPDATE school SET name = #{name}, year = #{year} WHERE id = #{id}")
    void update(School school);

    @Delete("DELETE FROM school WHERE id = #{id}")
    void delete(School school);

    @Delete("DELETE FROM school WHERE NOT id = 0")
    void deleteAll();


}
