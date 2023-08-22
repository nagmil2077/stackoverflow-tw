package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDateTime;

public record Question(int id, String title, String description, LocalDateTime localDateTime) {

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", localDate=" + localDateTime +
                '}';
    }
}
