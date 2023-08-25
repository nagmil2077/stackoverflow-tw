package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Answer;

import java.util.List;

public interface AnswersDAO {
    // CREATE
    void add(int id, String description);
    // READ
    Answer get(int id);
    // READ ALL
    List<Answer> getAll(int id);
    // UPDATE
    void update(int id, String description);
    // DELETE
    boolean delete(int id);
    // DELETE ALL
    boolean deleteAll();
}
