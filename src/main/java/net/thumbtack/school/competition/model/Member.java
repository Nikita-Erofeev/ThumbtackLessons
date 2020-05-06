package net.thumbtack.school.competition.model;

import java.io.Serializable;

public class Member extends User implements Serializable {
    private String companyName;

    public Member(int id, String firstName, String lastName, String companyName, String login, String password) {
        super(id, firstName, lastName, login, password);
        this.companyName = companyName;
    }

    public Member(String firstName, String lastName, String companyName, String login, String password) {
        super(firstName, lastName, login, password);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        if (!super.equals(o)) return false;

        Member member = (Member) o;

        return getCompanyName() != null ? getCompanyName().equals(member.getCompanyName()) : member.getCompanyName() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCompanyName() != null ? getCompanyName().hashCode() : 0);
        return result;
    }
}
