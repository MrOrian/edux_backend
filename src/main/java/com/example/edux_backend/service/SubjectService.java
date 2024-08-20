package com.example.edux_backend.service;

import com.example.edux_backend.dto.request.SubjectCreationRequest;
import com.example.edux_backend.dto.request.SubjectUpdateRequest;
import com.example.edux_backend.dto.response.SubjectResponse;
import com.example.edux_backend.entity.Subject;
import com.example.edux_backend.exception.AppException;
import com.example.edux_backend.exception.ErrorCode;
import com.example.edux_backend.mapper.SubjectMapper;
import com.example.edux_backend.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private SubjectMapper subjectMapper;

    public Subject createSubject(@RequestBody SubjectCreationRequest request){
        if(subjectRepository.existsBySubjectName(request.getSubjectName()))
            throw new AppException(ErrorCode.SUBJECT_EXITED);
        Subject subject = subjectMapper.toSubject(request);
        return subjectRepository.save(subject);
    }

    public List<Subject> getSubject(){
        return subjectRepository.findAll();
    }

    public List<Subject> getSubjectByName(String name){
        List<Subject> listSubject = subjectRepository.findBySubjectName(name);
        if(listSubject.isEmpty()) throw new RuntimeException("Khong ton tai Mon hoc");
        return listSubject;
    }

    public Subject getSubjectById(Long id){
        return subjectRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("khong ton tai exit id Subject!"));
    }

    public SubjectResponse updateSubject(Long id, SubjectUpdateRequest request){
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(()->new RuntimeException("khong the update"));
        subjectMapper.updateSubject(subject, request);
        Subject updatedSubject = subjectRepository.save(subject);
        return subjectMapper.toSubjectResponse(updatedSubject);
    }

    public void deleteSubject(Long id){
        subjectRepository.deleteById(id);
    }
}
