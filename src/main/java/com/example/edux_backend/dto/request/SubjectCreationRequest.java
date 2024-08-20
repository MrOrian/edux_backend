package com.example.edux_backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectCreationRequest {
    String subjectName;
    Long numberOfSessions;
    Long subjectPrice;
    Long teacher;
    Long teacherSalary;
    Long totalStudent;
}
