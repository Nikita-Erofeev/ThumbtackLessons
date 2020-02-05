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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Member member = (Member) o;

        return companyName != null ? companyName.equals(member.companyName) : member.companyName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        return result;
    }
}
