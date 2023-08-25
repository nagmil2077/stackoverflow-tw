package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.service.AnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("answers")
public class AnswerController {

    private final AnswerService answerService;


    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/{id}/create")
    public int addNewAnswer(@PathVariable int id, @RequestBody NewAnswerDTO answer) {
        return answerService.addNewAnswer(id, answer);
    }

    @PatchMapping("/{id}")
    public AnswerDTO updateAnswer(@PathVariable int id, @RequestBody NewAnswerDTO description) {
        answerService.updateAnswer(id, description.description());

        return answerService.getAnswerByAnswerId(id);
    }

    @GetMapping("/all/{id}")
    public List<AnswerDTO> getAnswersByQuestionId(@PathVariable int id) {
        return answerService.getAnswersByQuestionId(id);
    }

    @GetMapping("/{id}")
    public AnswerDTO getQuestionById(@PathVariable int id) {
        return answerService.getAnswerByAnswerId(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteAnswerById(@PathVariable int id) {
        return answerService.deleteAnswerById(id);
    }
}
