package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionsDAO mockQuestionsDAO;

    private QuestionService questionService;

    private Question question;

    @BeforeEach
    public void setUp() {
        mockQuestionsDAO = mock(QuestionsDAO.class);
        questionService = new QuestionService(mockQuestionsDAO);

        question = new Question(0, "title", "desc", LocalDateTime.of(2014, Month.APRIL, 3, 10, 10, 30));
    }

    @Test
    void testGetAllQuestions() {
        Question question1 = new Question(
                1,
                "title1",
                "desc1",
                LocalDateTime.of(2023, Month.AUGUST, 5, 15, 30, 25)
        );
        Question question2 = new Question(
                2,
                "title2",
                "desc2",
                LocalDateTime.of(2024, Month.JANUARY, 1, 9, 45, 17)
        );

        when(mockQuestionsDAO.getAll()).thenReturn(List.of(question, question1, question2));

        List<QuestionDTO> questionList = questionService.getAllQuestions();
        assertNotNull(questionList);
        assertEquals(3, questionList.size());
    }
}
