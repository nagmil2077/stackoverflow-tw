package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    private QuestionsDAO mockQuestionsDAO;

    private QuestionService questionService;

    private Question question;

    @BeforeEach
    void setUp() {
        mockQuestionsDAO = mock(QuestionsDAO.class);
        questionService = new QuestionService(mockQuestionsDAO);

        question = new Question(
                0,
                "title",
                "desc",
                LocalDateTime.of(2014, Month.APRIL, 3, 10, 10, 30));
    }

    @Test
    void testGetAllQuestions_withQuestionsList() {
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

    @Test
    void testGetAllQuestions_withEmptyQuestionsList() {
        when(mockQuestionsDAO.getAll()).thenReturn(Collections.emptyList());

        List<QuestionDTO> questionList = questionService.getAllQuestions();

        assertTrue(questionList.isEmpty());
        assertEquals(0, questionList.size());
    }

    @Test
    void testGetQuestionById() {
        when(mockQuestionsDAO.get(0)).thenReturn(question);

        QuestionDTO savedQuestion = questionService.getQuestionById(question.id());

        assertNotNull(savedQuestion);
    }

    @Test
    void testUpdateQuestion() {
        willDoNothing().given(mockQuestionsDAO).update(question.id(), question.title(), question.description());

        questionService.updateQuestion(question.id(), question.title(), question.description());

        verify(mockQuestionsDAO, times(1)).update(question.id(), question.title(), question.description());
    }

    @Test
    void testDeleteQuestionById() {
        when(mockQuestionsDAO.delete(0)).thenReturn(true);

        boolean expected = true;
        boolean actual = questionService.deleteQuestionById(question.id());

        assertTrue(actual);
        assertEquals(expected, actual);
    }

    @Test
    void testAddNewQuestion() {
        willDoNothing().given(mockQuestionsDAO).add(0, "title", "desc");

        NewQuestionDTO newQuestionDTO = new NewQuestionDTO(
                "title",
                "desc"
        );

        int actual = questionService.addNewQuestion(newQuestionDTO);

        assertEquals(0, actual);
    }
}
