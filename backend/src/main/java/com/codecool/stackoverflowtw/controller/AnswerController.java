package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.AnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.service.AnswerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("answers")
public class AnswerController {

    private final AnswerService answerService;


    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/")
    public int addNewAnswer(@RequestBody NewAnswerDTO answer) {
        return answerService.addNewAnswer(answer);
    }

    @GetMapping("/{id}")
    public AnswerDTO getAnswerById(@PathVariable int id) {
        return answerService.getAnswerById(id);
    }

    @PatchMapping("/update/{id}")
    public AnswerDTO updateAnswerById(@PathVariable int id, @RequestBody NewAnswerDTO answer) {
        answerService.updateAnswer(id, answer.description());

        return answerService.getAnswerById(id);
    }

}