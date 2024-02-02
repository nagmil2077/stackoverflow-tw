package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.model.Answer;
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
class AnswerServiceTest {

    @Mock
    private AnswersDAO mockAnswersDao;

    private AnswerService answerService;

    private Answer answer;

    @BeforeEach
    void setUp() {
        mockAnswersDao = mock(AnswersDAO.class);
        answerService = new AnswerService(mockAnswersDao);

        answer = new Answer(
                0,
                0,
                "desc",
                LocalDateTime.of(2014, Month.APRIL, 3, 10, 10, 30)
        );
    }

    @Test
    void testAddNewAnswer() {
        willDoNothing().given(mockAnswersDao).add(0, "desc");

        NewAnswerDTO newAnswerDTO = new NewAnswerDTO(
                "desc"
        );

        int actual = answerService.addNewAnswer(0, newAnswerDTO);

        assertEquals(0, actual);
    }

    @Test
    void testGetAnswersByQuestionId_withAnswerList() {
        Answer answer1 = new Answer(
                1,
                0,
                "desc1",
                LocalDateTime.of(2023, Month.AUGUST, 5, 15, 30, 25)
        );
        Answer answer2 = new Answer(
                2,
                0,
                "desc2",
                LocalDateTime.of(2024, Month.JANUARY, 1, 9, 45, 17)
        );

        when(mockAnswersDao.getAll(0)).thenReturn(List.of(answer, answer1, answer2));

        List<AnswerDTO> answerList = answerService.getAnswersByQuestionId(0);
        assertNotNull(answerList);
        assertEquals(3, answerList.size());
    }

    @Test
    void testGetAnswersByQuestionId_withEmptyAnswerList() {
        when(mockAnswersDao.getAll(0)).thenReturn(Collections.emptyList());

        List<AnswerDTO> answerList = answerService.getAnswersByQuestionId(0);

        assertTrue(answerList.isEmpty());
        assertEquals(0, answerList.size());
    }

    @Test
    void testGetAnswerByAnswerId() {
        when(mockAnswersDao.get(0)).thenReturn(answer);

        AnswerDTO savedAnswer = answerService.getAnswerByAnswerId(answer.id());

        assertNotNull(savedAnswer);
    }

    @Test
    void testUpdateAnswer() {
        willDoNothing().given(mockAnswersDao).update(1, "modifiedDesc");

        answerService.updateAnswer(1, "modifiedDesc");

        verify(mockAnswersDao, times(1)).update(1, "modifiedDesc");
    }

    @Test
    void testDeleteAnswerById() {
        when(mockAnswersDao.delete(0)).thenReturn(true);

        boolean expected = true;
        boolean actual = answerService.deleteAnswerById(answer.id());

        assertTrue(actual);
        assertEquals(expected, actual);
    }
}
