package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;

import java.util.List;

public interface QuestionsDAO {
    void sayHi();
    // CREATE
    void add(int id, String title, String description);
    // READ
    Question get(int id);
    // READ ALL
    List<Question> getAll();
    // UPDATE
    void update(int id, String title, String description);
    // DELETE
    boolean delete(int id);
    // DELETE ALL
    boolean deleteAll();
}
