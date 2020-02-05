package net.thumbtack.school.competition.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Application implements Serializable {
    private String name;
    private String description;
    private List<String> subjectsList= new ArrayList<>();
    private int amountRequested;

    public Application(String name, String description, List<String> subjectsList, int amountRequested){
        this.name = name;
        this.description = description;
        this.subjectsList.addAll(subjectsList);
        this.amountRequested = amountRequested;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if (amountRequested != that.amountRequested) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return subjectsList != null ? subjectsList.equals(that.subjectsList) : that.subjectsList == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (subjectsList != null ? subjectsList.hashCode() : 0);
        result = 31 * result + amountRequested;
        return result;
    }
}
