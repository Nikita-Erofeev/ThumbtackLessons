package net.thumbtack.school.competition.model;

import java.io.Serializable;

public class Member extends User implements Serializable {
    private String companyName;

    public Member(String firstName, String lastName, String companyName, String login, String password) {
        super(firstName, lastName, login, password);
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
