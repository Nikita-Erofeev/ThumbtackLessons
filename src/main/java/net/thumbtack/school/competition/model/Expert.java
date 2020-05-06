package net.thumbtack.school.competition.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Expert extends User implements Serializable {
    private List<Subject> subjectsList = new ArrayList<>();

    public Expert(int id, String firstName, String lastName, List<Subject> subjectsList, String login, String password) {
        super(id, firstName, lastName, login, password);
        this.subjectsList.addAll(subjectsList);
    }

    public Expert(String firstName, String lastName, List<Subject> subjectsList, String login, String password) {
        super(firstName, lastName, login, password);
        this.subjectsList.addAll(subjectsList);
    }

    public Expert(int id, String firstName, String lastName, String login, String password) {
        super(firstName, lastName, login, password);
        subjectsList = new ArrayList<>();
    }

    public List<Subject> getSubjectsList() {
        return subjectsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expert)) return false;
        if (!super.equals(o)) return false;

        Expert expert = (Expert) o;

        return getSubjectsList() != null ? getSubjectsList().equals(expert.getSubjectsList()) : expert.getSubjectsList() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getSubjectsList() != null ? getSubjectsList().hashCode() : 0);
        return result;
    }
}
