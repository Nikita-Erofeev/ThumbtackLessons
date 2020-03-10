package net.thumbtack.school.competition.dto;

import java.util.ArrayList;
import java.util.List;

public class RegisterExpertDto {
    private String firstName;
    private String lastName;
    private List<String> subjectsList = new ArrayList<>();
    private String login;
    private String password;

    public RegisterExpertDto(String firstName, String lastName, List<String> subjectsList, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subjectsList.addAll(subjectsList);
        this.login = login;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getSubjectsList() {
        return subjectsList;
    }
}
