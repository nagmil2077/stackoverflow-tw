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
        List<Question> questionDAOList = questionsDAO.getAll();
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question questionDAO : questionDAOList) {
            questionDTOList.add(new QuestionDTO(
                    questionDAO.getId(),
                    questionDAO.getTitle(),
                    questionDAO.getDescription(),
                    questionDAO.getLocalDate().atStartOfDay()));
        }

        return questionDTOList;
    }

    public QuestionDTO getQuestionById(int id) {
        Question question = questionsDAO.get(id);
        return new QuestionDTO(
                question.getId(),
                question.getTitle(),
                question.getDescription(),
                question.getLocalDate().atStartOfDay());
    }

    public boolean deleteQuestionById(int id) {
        return questionsDAO.delete(id);
    }

    public int addNewQuestion(NewQuestionDTO question) {
        int createdId = 0;
        questionsDAO.add(question);
        return createdId;
    }
}
