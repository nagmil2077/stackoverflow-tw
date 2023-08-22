package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDate;

public record Question(int id, String title, String description, LocalDate localDate) {

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
