package net.thumbtack.school.competition.dto;

import net.thumbtack.school.competition.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDto {
    private int id;
    private String name;
    private String description;
    private List<Subject> subjectsList = new ArrayList<>();
    private int amountRequested;

    public ApplicationDto(int id, String name, String description, List<Subject> subjectsList, int amountRequested) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subjectsList.addAll(subjectsList);
        this.amountRequested = amountRequested;
    }

    public ApplicationDto(String name, String description, List<Subject> subjectsList, int amountRequested) {
        id = 0;
        this.name = name;
        this.description = description;
        this.subjectsList.addAll(subjectsList);
        this.amountRequested = amountRequested;
    }

    public void setId(int id) {
        this.id = id;
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
}
