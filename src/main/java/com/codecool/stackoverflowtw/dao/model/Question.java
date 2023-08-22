package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Question {
    private final int id;
    private final String title;
    private final String description;
    private final LocalDateTime localDateTime;

    public Question(int id, String title, String description, LocalDateTime localDateTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.localDateTime = localDateTime;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
