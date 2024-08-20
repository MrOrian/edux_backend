package com.example.edux_backend.controller;

import com.example.edux_backend.dto.request.SubjectCreationRequest;
import com.example.edux_backend.dto.request.SubjectUpdateRequest;
import com.example.edux_backend.dto.response.ApiResponse;
import com.example.edux_backend.dto.response.SubjectResponse;
import com.example.edux_backend.entity.Subject;
import com.example.edux_backend.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping()
    List<Subject> getSubjects(){
        return subjectService.getSubject();
    }

    @GetMapping("/id/{subjectId}")
    Subject getSubjectById(@PathVariable("subjectId") Long id){
        return subjectService.getSubjectById(id);
    }

    @GetMapping("/name/{subjectName}")
    List<Subject> getSubjectByName(@PathVariable("subjectName") String name){
        return subjectService.getSubjectByName(name);
    }

    @PostMapping()
    ApiResponse<Subject> createSubject(@RequestBody SubjectCreationRequest request){
        ApiResponse<Subject> apiResponse = new ApiResponse<>();
        apiResponse.setResult(subjectService.createSubject(request));
        return apiResponse;
    }

    @PutMapping("/{id}")
    SubjectResponse updateSubject
            (@PathVariable("id") Long id, @RequestBody SubjectUpdateRequest request){
        return subjectService.updateSubject(id, request);
    }

    @DeleteMapping("/{id}")
    String deleteSubject(@PathVariable("id") Long id){
        subjectService.deleteSubject(id);
        return "Đã xóa";
    }


}
