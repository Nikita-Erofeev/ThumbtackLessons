package net.thumbtack.school.competition.model;

import net.thumbtack.school.competition.dto.ApplicationDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Application implements Serializable {
    private int id;
    private String name;
    private String description;
    private List<Subject> subjectsList = new ArrayList<>();
    private int amountRequested;

    public Application(int id, String name, String description, List<Subject> subjectsList, int amountRequested){
        this.id = id;
        this.name = name;
        this.description = description;
        this.subjectsList.addAll(subjectsList);
        this.amountRequested = amountRequested;
    }

    public Application(String name, String description, List<Subject> subjectsList, int amountRequested) {
        id = 0;
        this.name = name;
        this.description = description;
        this.subjectsList.addAll(subjectsList);
        this.amountRequested = amountRequested;
    }

    public Application(int id, String name, String description, int amountRequested) {
        this.id = id;
        this.name = name;
        this.description = description;
        subjectsList = new ArrayList<>();
        this.amountRequested = amountRequested;
    }

    public Application(ApplicationDto applicationDto) {
        id = applicationDto.getId();
        name = applicationDto.getName();
        description = applicationDto.getDescription();
        subjectsList.addAll(applicationDto.getSubjectsList());
        amountRequested = applicationDto.getAmountRequested();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Subject> getSubjectsList() {
        return subjectsList;
    }

    public int getAmountRequested() {
        return amountRequested;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return id + " " + name + " " + description + " " + subjectsList.toString() + " " + amountRequested;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;

        Application that = (Application) o;

        if (getId() != that.getId()) return false;
        if (getAmountRequested() != that.getAmountRequested()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        return getSubjectsList() != null ? getSubjectsList().equals(that.getSubjectsList()) : that.getSubjectsList() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getSubjectsList() != null ? getSubjectsList().hashCode() : 0);
        result = 31 * result + getAmountRequested();
        return result;
    }
}
