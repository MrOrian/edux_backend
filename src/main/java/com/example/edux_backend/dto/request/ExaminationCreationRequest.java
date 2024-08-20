package com.example.edux_backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExaminationCreationRequest {
    int code;
    Long idSubject;
    Long totalQuestion;
}
