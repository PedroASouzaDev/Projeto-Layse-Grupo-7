package com.auroraapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auroraapp.model.Feedback;
import com.auroraapp.service.FeedbackService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping("/all")
    public List<Feedback> getAll() {
        System.out.println("Fetching all feedbacks - 200 OK");
        return feedbackService.buscarTodos();
    }

    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable Long id) {
        System.out.println("Fetching feedback with ID: " + id + " - 200 OK");
        return feedbackService.buscarPorId(id);
    }

    @PostMapping
    public Feedback salvar(@RequestBody Feedback feedback) {
        System.out.println("Creating new feedback - 201 Created");
        return feedbackService.salvar(feedback);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        System.out.println("Deleting feedback with ID: " + id + " - 204 No Content");
        feedbackService.deletar(id);
    }
}
