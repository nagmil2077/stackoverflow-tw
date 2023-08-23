package com.codecool.stackoverflowtw.dao.model;

import java.time.LocalDateTime;

public record Answer(int id, int questionId, String description, LocalDateTime localDateTime) {

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", description='" + description + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
