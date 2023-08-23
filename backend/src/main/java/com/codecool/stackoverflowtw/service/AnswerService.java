package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    private final AnswersDAO answersDAO;

    @Autowired
    public AnswerService(AnswersDAO answersDAO) {
        this.answersDAO = answersDAO;
    }

    public List<AnswerDTO> getAnswersByQuestionId(int id) {
        List<Answer> answerDAOList = answersDAO.getAll(id);
        List<AnswerDTO> answerDTOList = new ArrayList<>();

        for (Answer answerDAO : answerDAOList) {
            answerDTOList.add(new AnswerDTO(
                    answerDAO.id(),
                    id,
                    answerDAO.description(),
                    answerDAO.localDateTime()));
        }

        return answerDTOList;
    }

    public AnswerDTO getAnswerByAnswerId(int id) {
        Answer answer = answersDAO.get(id);
        return new AnswerDTO(
                answer.id(),
                answer.questionId(),
                answer.description(),
                answer.localDateTime());
    }

    public void updateAnswer(int id, String answer) {
        System.out.println(id);
        System.out.println(answer);
        answersDAO.update(id, answer);
    }
}
