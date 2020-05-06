package net.thumbtack.school.competition.mybatis.mappers;

import net.thumbtack.school.competition.model.Application;
import net.thumbtack.school.competition.model.Expert;
import net.thumbtack.school.competition.model.Member;
import net.thumbtack.school.competition.model.Subject;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.mapping.FetchType;

import java.util.ArrayList;
import java.util.List;

public interface ApplicationMapper {

    @Insert("INSERT INTO application VALUES (null, #{member.id}, #{app.name}, #{app.description}, #{app.amountRequested})")
    @Options(useGeneratedKeys = true, keyProperty = "app.id")
    void insert(@Param("member") Member member, @Param("app") Application application);

    @Select("SELECT id, name, description, amountrequested FROM application WHERE id_member = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "subjectsList", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.competition.mybatis.mappers.SubjectMapper.getByApplication",
                            fetchType = FetchType.EAGER))
    })
    List<Application> getAppByMember(Member member);

    @Delete("DELETE FROM application WHERE id = #{id}")
    void deleteApplication(Application application);

    @Select("SELECT DISTINCT id, name, description, amountrequested FROM application WHERE id IN\n" +
            "(SELECT id_application FROM application_subject WHERE id_subject IN \n" +
            "(SELECT id_subject FROM expert_subject WHERE id_expert = #{id}));")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "subjectsList", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.competition.mybatis.mappers.SubjectMapper.getByApplication",
                            fetchType = FetchType.EAGER))
    })
    List<Application> expertShowApplications(Expert expert);

    @Select({"<script>" +
            "SELECT id, name, description, amountrequested FROM application WHERE id " +
            "IN (SELECT id_application FROM application_subject WHERE id_subject IN" +
            "<foreach item='item' index='index' collection='list' open='(' separator=',' close=')'>" +
            " #{item.id}" +
            "</foreach> )" +
            "</script>"})
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "subjectsList", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.competition.mybatis.mappers.SubjectMapper.getByApplication",
                            fetchType = FetchType.EAGER))
    })
    List<Application> expertShowAppBySubjects(@Param("list") List<Subject> subjects);

    @Insert("INSERT INTO rating VALUES (null, #{expert.id}, #{application.id}, #{rating})")
    void rateApplication(@Param("expert") Expert expert, @Param("application") Application application,
                         @Param("rating") int rating);

    @Select("SELECT id, name, description, amountrequested FROM application WHERE id IN " +
            "(SELECT id_application FROM rating WHERE id_expert = #{id}) ORDER BY id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "subjectsList", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.competition.mybatis.mappers.SubjectMapper.getByApplication",
                            fetchType = FetchType.EAGER))
    })
    ArrayList<Application> showRatedApplications(Expert expert);

    @Select("SELECT rating FROM rating WHERE id_expert = #{id}")
    ArrayList<Integer> getRatings(Expert expert);
}
