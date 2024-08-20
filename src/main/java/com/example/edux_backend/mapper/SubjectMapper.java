package com.example.edux_backend.mapper;

import com.example.edux_backend.dto.request.SubjectCreationRequest;
import com.example.edux_backend.dto.request.SubjectUpdateRequest;
import com.example.edux_backend.dto.response.SubjectResponse;
import com.example.edux_backend.entity.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    Subject toSubject(SubjectCreationRequest request);

//    @Mapping(source = "", target = "")
    SubjectResponse toSubjectResponse(Subject subject);

    void updateSubject(@MappingTarget Subject subject, SubjectUpdateRequest request);

}
