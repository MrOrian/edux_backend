package com.example.edux_backend.controller;

import com.example.edux_backend.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "http://localhost:8088")
public class BranchController {
    @Autowired
    private BranchService branchService;
}
