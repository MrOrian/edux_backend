package com.example.edux_backend.service;

import com.example.edux_backend.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExaminationService {
    @Autowired
    private ExaminationRepository examinationRepository;
}
