package com.example.edux_backend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)//thêm anotation này nếu có json null thì không cần hiển thị, vd message

public class ApiResponse <T>{
    int code = 1000;
    String message;
    T result;
}
