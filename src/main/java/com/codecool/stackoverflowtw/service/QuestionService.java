package com.codecool.stackoverflowtw.service;

import com.codecool.stackoverflowtw.controller.dto.NewQuestionDTO;
import com.codecool.stackoverflowtw.controller.dto.QuestionDTO;
import com.codecool.stackoverflowtw.dao.QuestionsDAO;
import com.codecool.stackoverflowtw.dao.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    private QuestionsDAO questionsDAO;

    @Autowired
    public QuestionService(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public List<QuestionDTO> getAllQuestions() {
        // TODO
        List<Question> questionDAOList = questionsDAO.getAll();
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question questionDAO : questionDAOList) {
            questionDTOList.add(new QuestionDTO(
                    questionDAO.getId(),
                    questionDAO.getTitle(),
                    questionDAO.getDescription(),
                    questionDAO.getLocalDateTime()));
        }

//        return List.of(new QuestionDTO(1, "example title", "example desc", LocalDateTime.now()));
        return questionDTOList;
    }

    public QuestionDTO getQuestionById(int id) {
        // TODO
        questionsDAO.sayHi();
        return new QuestionDTO(id, "example title", "example desc", LocalDateTime.now());
    }

    public boolean deleteQuestionById(int id) {
        // TODO
        return false;
    }

    public int addNewQuestion(NewQuestionDTO question) {
        // TODO
        int createdId = 0;
        return createdId;
    }
}
