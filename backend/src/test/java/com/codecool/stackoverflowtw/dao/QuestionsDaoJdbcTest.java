package com.codecool.stackoverflowtw.dao;

import com.codecool.stackoverflowtw.dao.model.Question;
import com.codecool.stackoverflowtw.service.SqlConnector;
import org.junit.jupiter.api.Test;

import java.util.List;

class QuestionsDaoJdbcTest {



    @Test
    void getAll() {
        QuestionsDAO questionsDAO = new QuestionsDaoJdbc(new SqlConnector());
        List<Question> questionList = questionsDAO.getAll();
        System.out.println(questionList);
    }
}