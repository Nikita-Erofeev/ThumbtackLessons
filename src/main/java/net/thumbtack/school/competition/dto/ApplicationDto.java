package net.thumbtack.school.competition.dto;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDto {
    private String name;
    private String description;
    private List<String> subjectsList = new ArrayList<>();
    private int amountRequested;

    public ApplicationDto(String name, String description, List<String> subjectsList, int amountRequested) {
        this.name = name;
        this.description = description;
        this.subjectsList.addAll(subjectsList);
        this.amountRequested = amountRequested;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getSubjectsList() {
        return subjectsList;
    }

    public int getAmountRequested() {
        return amountRequested;
    }
}
