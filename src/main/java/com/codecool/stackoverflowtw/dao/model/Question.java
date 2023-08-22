package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDate;

public class Question {
    private final int id;
    private final String title;
    private final String description;
    private final LocalDate localDate;

    public Question(int id, String title, String description, LocalDate localDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.localDate = localDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", localDate=" + localDate +
                '}';
    }
}
