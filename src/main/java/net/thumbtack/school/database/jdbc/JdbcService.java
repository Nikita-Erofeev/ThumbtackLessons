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
        String insertQuery = "INSERT INTO trainee VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setNull(2, Types.INTEGER);
            preparedStatement.setString(3, trainee.getFirstName());
            preparedStatement.setString(4, trainee.getLastName());
            preparedStatement.setInt(5, trainee.getRating());
            trainee.setId(preparedStatement.executeUpdate());
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    trainee.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static void insertTrainee(Trainee trainee, Group group) throws SQLException {
        String insertGroup = "INSERT INTO trainee VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertGroup, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, group.getId());
            stmt.setNull(2, Types.INTEGER);
            stmt.setString(3, trainee.getFirstName());
            stmt.setString(4, trainee.getLastName());
            stmt.setInt(5, trainee.getRating());
            stmt.execute();
            try (ResultSet generatedKey = stmt.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    trainee.setId(generatedKey.getInt(1));
                }
            }
        }
        connection.setAutoCommit(true);
    }

    public static void updateTrainee(Trainee trainee) throws SQLException {
        String updateQuery = "UPDATE trainee SET firstname = ?, lastname = ?, rating = ? ORDER BY id DESC LIMIT 1;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, trainee.getFirstName());
            preparedStatement.setString(2, trainee.getLastName());
            preparedStatement.setInt(3, trainee.getRating());
            preparedStatement.executeUpdate();
        }
    }

    public static Trainee getTraineeByIdUsingColNames(int traineeId) throws SQLException {
        String selectQuery = "SELECT * FROM trainee WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
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
    }

    public static Trainee getTraineeByIdUsingColNumbers(int traineeId) throws SQLException {
        String selectQuery = "SELECT * FROM trainee WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, traineeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(2);
                String firstname = resultSet.getString(3);
                String lastname = resultSet.getString(4);
                int rating = resultSet.getInt(5);
                return new Trainee(id, firstname, lastname, rating);
            }
            return null;
        }
    }

    public static List<Trainee> getTraineesUsingColNames() throws SQLException {
        String selectQuery = "SELECT * FROM trainee;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
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
    }

    public static List<Trainee> getTraineesUsingColNumbers() throws SQLException {
        String selectQuery = "SELECT * FROM trainee;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Trainee> resultList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(2);
                String firstname = resultSet.getString(3);
                String lastname = resultSet.getString(4);
                int rating = resultSet.getInt(5);
                resultList.add(new Trainee(id, firstname, lastname, rating));
            }
            return resultList;
        }
    }

    public static void deleteTrainee(Trainee trainee) throws SQLException {

        if (trainee.getId() != 0) {
            String deleteQuery = "DELETE FROM trainee WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, trainee.getId());
                preparedStatement.execute();
            }
        } else {
            String deleteQuery = "DELETE FROM trainee WHERE firstname = ?, lastname = ?, rating = ?;";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, trainee.getFirstName());
                preparedStatement.setString(2, trainee.getLastName());
                preparedStatement.setInt(3, trainee.getRating());
                preparedStatement.execute();
            }
        }
    }

    public static void deleteTrainees() throws SQLException {
        String deleteQuery = "DELETE FROM trainee WHERE NOT id = 0;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.execute();
        }
    }

    public static void insertSubject(Subject subject) throws SQLException {
        String insertQuery = "INSERT INTO subject (name) VALUES (?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, subject.getName());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subject.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static Subject getSubjectByIdUsingColNames(int subjectId) throws SQLException {
        String selectQuery = "SELECT * FROM subject WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, subjectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new Subject(id, name);
            }
            return null;
        }
    }

    public static Subject getSubjectByIdUsingColNumbers(int subjectId) throws SQLException {
        String selectQuery = "SELECT * FROM subject WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, subjectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                return new Subject(id, name);
            }
            return null;
        }
    }

    public static void deleteSubjects() throws SQLException {
        String deleteQuery = "DELETE FROM subject WHERE NOT id = 0";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.execute();
        }
    }

    public static void insertSchool(School school) throws SQLException {
        String insertQuery = "INSERT INTO school (name, year) VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);) {
            preparedStatement.setString(1, school.getName());
            preparedStatement.setInt(2, school.getYear());
            preparedStatement.execute();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    school.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static School getSchoolByIdUsingColNames(int schoolId) throws SQLException {
        String selectQuery = "SELECT * FROM school WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
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
    }

    public static School getSchoolByIdUsingColNumbers(int schoolId) throws SQLException {
        String selectQuery = "SELECT * FROM school WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
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
    }

    public static void deleteSchools() throws SQLException {
        String deleteQuery = "DELETE FROM school WHERE NOT id = 0";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.execute();
        }
    }

    public static void insertGroup(School school, Group group) throws SQLException {
        String insertGroup = "INSERT INTO groups VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertGroup, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, school.getId());
            preparedStatement.setNull(2, Types.INTEGER);
            preparedStatement.setString(3, group.getName());
            preparedStatement.setString(4, group.getRoom());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKey = preparedStatement.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    group.setId(generatedKey.getInt(1));
                }
            }
        }
    }

    public static School getSchoolByIdWithGroups(int id) throws SQLException {
        String selectQuery = "SELECT * FROM school " +
                "INNER JOIN groups ON school.id = groups.schoolid WHERE school.id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
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
                    int groupId = resultSet.getInt(5);
                    String groupName = resultSet.getString(6);
                    String groupRoom = resultSet.getString(7);
                    groups.add(new Group(groupId, groupName, groupRoom));
                } else {
                    int groupId = resultSet.getInt(5);
                    String groupName = resultSet.getString(6);
                    String groupRoom = resultSet.getString(7);
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
    }

    public static List<School> getSchoolsWithGroups() throws SQLException {
        String selectQuery = "SELECT * FROM school INNER JOIN groups ON school.id = groups.schoolid ORDER BY school.id";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
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
                int groupId = resultSet.getInt(5);
                String groupName = resultSet.getString(6);
                String groupRoom = resultSet.getString(7);
                groups.add(new Group(groupId, groupName, groupRoom));
            }
            if (school != null) {
                school.setGroups(groups);
                schools.add(school);
            }
            return schools;
        }
    }

}
