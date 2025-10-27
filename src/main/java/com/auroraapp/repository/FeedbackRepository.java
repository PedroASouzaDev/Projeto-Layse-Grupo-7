package com.auroraapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auroraapp.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}