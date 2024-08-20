package com.example.edux_backend.service;

import com.example.edux_backend.entity.Schedule;
import com.example.edux_backend.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
}
