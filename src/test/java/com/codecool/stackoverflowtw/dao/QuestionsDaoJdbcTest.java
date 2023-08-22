package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionsDaoJdbcTest {

    @Test
    void getAll() {
        QuestionsDAO questionsDAO = new QuestionsDaoJdbc();
        List<Question> questionList = questionsDAO.getAll();
        System.out.println(questionList);
    }
}