package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.AnswersDAO;
import com.codecool.stackoverflowtw.dao.model.Answer;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    private AnswersDAO answersDAO;

    @Autowired
    public AnswerService(AnswersDAO answersDAO) {
        this.answersDAO = answersDAO;
    }

//    public int addNewAnswer(NewAnswerDTO answer) {
//
//    }

    public List<AnswerDTO> getAnswersById(int id) {
        System.out.println("BACKEND ID: " + id);
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
}
