package com.example.edux_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Entity
@Getter
@Setter
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int code;
    private Long idSubject;
    private Long idUser;
    private Long totalQuestion;
    private Long totalAnswerTrue;
    private Long totalAnswerFalse;
    private Long totalMark;

}
