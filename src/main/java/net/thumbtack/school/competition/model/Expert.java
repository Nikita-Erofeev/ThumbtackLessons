package net.thumbtack.school.competition.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Expert extends User implements Serializable {
    private List<String> subjectsList = new ArrayList<>();

    public Expert(String firstName, String lastName, List<String> subjectsList, String login, String password) {
        super(firstName, lastName, login, password);
        this.subjectsList.addAll(subjectsList);
    }

    public List<String> getSubjectsList() {
        return subjectsList;
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
