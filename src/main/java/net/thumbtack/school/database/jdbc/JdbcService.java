package net.thumbtack.school.database.jdbc;

import net.thumbtack.school.database.model.Group;
import net.thumbtack.school.database.model.School;
import net.thumbtack.school.database.model.Subject;
import net.thumbtack.school.database.model.Trainee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcService {
    private static Connection connection = JdbcUtils.getConnection();

    public static void insertTrainee(Trainee trainee) throws SQLException {
        String insertQuery = "INSERT INTO trainee VALUES (?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)){
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setString(2, trainee.getFirstName());
            preparedStatement.setString(3, trainee.getLastName());
            preparedStatement.setInt(4, trainee.getRating());
            preparedStatement.executeUpdate(insertQuery);
        }
    }

//    public static void insertTrainee(Trainee trainee, Group group) throws SQLException {
//        String insertQuery = "INSERT INTO trainee (firstname, lastname, rating) VALUES (\""
//                + trainee.getFirstName() + "\", \"" + trainee.getLastName() + "\"," + trainee.getRating() + ");";
//        Connection connection = JdbcUtils.getConnection();
//        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
//        int index = preparedStatement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
//    }

    public static void updateTrainee(Trainee trainee) throws SQLException {
        String updateQuery = "UPDATE trainee SET firstname = ?, lastname = ?, rating = ? ORDER BY id DESC LIMIT 1;";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setString(1, trainee.getFirstName());
        preparedStatement.setString(2, trainee.getLastName());
        preparedStatement.setInt(3, trainee.getRating());
        preparedStatement.executeUpdate(updateQuery);
    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
        String selectQuery = "SELECT * FROM trainee WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, traineeId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            int rating = resultSet.getInt("rating");
            return new Trainee(id, firstname, lastname, rating);
        }
        return null;
    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        String selectQuery = "SELECT * FROM trainee WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, traineeId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String firstname = resultSet.getString(2);
            String lastname = resultSet.getString(3);
            int rating = resultSet.getInt(4);
            return new Trainee(id, firstname, lastname, rating);
        }
        return null;
    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        String selectQuery = "SELECT * FROM trainee;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Trainee> resultList = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstname = resultSet.getString("firstname");
            String lastname = resultSet.getString("lastname");
            int rating = resultSet.getInt("rating");
            resultList.add(new Trainee(id, firstname, lastname, rating));
        }
        return resultList;
    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        String selectQuery = "SELECT * FROM trainee;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Trainee> resultList = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String firstname = resultSet.getString(2);
            String lastname = resultSet.getString(3);
            int rating = resultSet.getInt(4);
            resultList.add(new Trainee(id, firstname, lastname, rating));
        }
        return resultList;
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {
        if (trainee.getId() != 0) {
            String deleteQuery = "DELETE FROM trainee WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, trainee.getId());
            preparedStatement.execute();
        } else {
            String deleteQuery = "DELETE FROM trainee WHERE firstname = ?, lastname = ?, rating = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, trainee.getFirstName());
            preparedStatement.setString(2, trainee.getLastName());
            preparedStatement.setInt(3, trainee.getRating());
            preparedStatement.execute();
        }
    }

    public static void deleteTrainees() throws SQLException {
        String deleteQuery = "DELETE FROM trainee WHERE NOT id = 0;";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.execute();
    }

    public static void insertSubject(Subject subject) throws SQLException {
        String insertQuery = "INSERT INTO subject (name) VALUES (?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, subject.getName());
        preparedStatement.executeUpdate(insertQuery);
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        String selectQuery = "SELECT * FROM subject WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, subjectId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Subject(id, name);
        }
        return null;
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        String selectQuery = "SELECT * FROM subject WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, subjectId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            return new Subject(id, name);
        }
        return null;
    }

    public static void deleteSubjects() throws SQLException {
        String deleteQuery = "DELETE FROM subject WHERE NOT id = 0";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.execute();
    }

    public static void insertSchool(School school) throws SQLException {
        String insertQuery = "INSERT INTO school (name, year) VALUES (?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, school.getName());
        preparedStatement.setInt(2, school.getYear());
        preparedStatement.execute();
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        String selectQuery = "SELECT * FROM school WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, schoolId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int year = resultSet.getInt("year");
            return new School(id, name, year);
        }
        return null;
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        String selectQuery = "SELECT * FROM school WHERE id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, schoolId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int year = resultSet.getInt(3);
            return new School(id, name, year);
        }
        return null;
    }

    public static void deleteSchools() throws SQLException {
        String deleteQuery = "DELETE FROM school WHERE NOT id = 0";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.execute();
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        String insertQuery = "START TRANSACTION;" +
                "INSERT INTO school (name, year) VALUES (?,?);" +
                "SELECT @schoolid = LAST_INSERT_ID();" +
                "INSERT INTO groups (name, room) VALUES (?,?);" +
                "SELECT @groupid = LAST_INSERT_ID();" +
                "INSERT INTO school_group (schoolid,groupid) VALUES (@schoolid,@groupid);" +
                "COMMIT;";
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, school.getName());
        preparedStatement.setInt(2, school.getYear());
        preparedStatement.setString(3, group.getName());
        preparedStatement.setString(4, group.getRoom());
        preparedStatement.executeUpdate();
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
        String selectQuery = "SELECT school.id, school.name, year, groups.id, groups.name, room FROM school " +
                "INNER JOIN school_group ON school.id = school_group.schoolid " +
                "INNER JOIN groups ON school_group.groupid = groups.id WHERE school.id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        School school = null;
        List<Group> groups = new ArrayList<>();
        while (resultSet.next()) {
            if (school == null) {
                int schoolId = resultSet.getInt(1);
                String schoolName = resultSet.getString(2);
                int schoolYear = resultSet.getInt(3);
                school = new School(schoolId, schoolName, schoolYear);
                int groupId = resultSet.getInt(4);
                String groupName = resultSet.getString(5);
                String groupRoom = resultSet.getString(6);
                groups.add(new Group(groupId, groupName, groupRoom));
            } else {
                int groupId = resultSet.getInt(4);
                String groupName = resultSet.getString(5);
                String groupRoom = resultSet.getString(6);
                groups.add(new Group(groupId, groupName, groupRoom));
            }
        }
        if (school == null) {
            return null;
        } else {
            school.setGroups(groups);
            return school;
        }
    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        String selectQuery = "SELECT school.id, school.name, year, groups.id, groups.name, room FROM school " +
                "INNER JOIN school_group ON school.id = school_group.schoolid " +
                "INNER JOIN groups ON school_group.groupid = groups.id ORDER BY school.name, year;";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<School> schools = new ArrayList<>();
        List<Group> groups = new ArrayList<>();
        School school = null;
        while (resultSet.next()) {
            int schoolId = resultSet.getInt(1);
            if (school == null) {
                String schoolName = resultSet.getString(2);
                int schoolYear = resultSet.getInt(3);
                school = new School(schoolId, schoolName, schoolYear);
            } else if (school.getId() != schoolId) {
                school.setGroups(groups);
                schools.add(school);
                groups = new ArrayList<>();
                String schoolName = resultSet.getString(2);
                int schoolYear = resultSet.getInt(3);
                school = new School(schoolId, schoolName, schoolYear);
            }
            int groupId = resultSet.getInt(4);
            String groupName = resultSet.getString(5);
            String groupRoom = resultSet.getString(6);
            groups.add(new Group(groupId, groupName, groupRoom));
        }
        if (school != null){
            school.setGroups(groups);
            schools.add(school);
        }
        return schools;
    }

}
