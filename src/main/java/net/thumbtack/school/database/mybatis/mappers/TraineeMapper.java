package net.thumbtack.school.database.mybatis.mappers;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.Trainee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TraineeMapper {

    @Insert("INSERT INTO trainee VALUES (#{group.id}, null, #{trainee.firstName}, #{trainee.lastName}, #{trainee.rating})")
    @Options(useGeneratedKeys = true, keyProperty = "trainee.id")
    void insertWithGroup(@Param("group") Group group, @Param("trainee") Trainee trainee);

    @Insert("INSERT INTO trainee VALUES (null, null, #{firstName}, #{lastName}, #{rating})")
    @Options(useGeneratedKeys = true)
    void insert(Trainee trainee);

    @Select("SELECT id, firstname, lastname, rating FROM trainee WHERE id = #{id}")
    Trainee getById(int id);

    @Select("SELECT id, firstname, lastname, rating FROM trainee")
    List<Trainee> getAll();

    @Update("UPDATE trainee SET firstname = #{firstName}, lastname = #{lastName}, rating = #{rating} WHERE id = #{id}")
    void update(Trainee trainee);

    @Select({"<script>",
            "SELECT id, firstname, lastname, rating FROM trainee",
            "<where>" +
                "<if test='firstName != null'> firstname like #{firstName}", "</if>",
                "<if test='lastName != null'> AND lastname like #{lastName}", "</if>",
                "<if test='rating != null'> AND rating = #{rating}", "</if>",
            "</where>" +
            "</script>"})
//    @SelectProvider(method = "selectWithParams", type = net.thumbtack.school.database.mybatis.providers.TraineeDAOProvider.class)
    List<Trainee> getWithParams(@Param("firstName") String firstName, @Param("lastName") String lastName,
                                @Param("rating") Integer rating);

    @Insert({"<script>",
            "INSERT INTO trainee (firstname, lastname, rating) VALUES",
            "<foreach item = 'item' collection = 'list' separator = ', '>",
            "(#{item.firstName}, #{item.lastName}, #{item.rating})",
            "</foreach>",
            "</script>"})
    @Options(useGeneratedKeys = true)
    void batchInsert(@Param("list") List<Trainee> trainee);

    @Delete("DELETE FROM trainee WHERE id = #{id}")
    void delete(Trainee trainee);

    @Delete("DELETE FROM trainee WHERE NOT id = 0")
    void deleteAll();

    @Select("SELECT id, firstname, lastname, rating FROM trainee WHERE groupid = #{group.id}")
    List<Trainee> getByGroup(Group group);

}
