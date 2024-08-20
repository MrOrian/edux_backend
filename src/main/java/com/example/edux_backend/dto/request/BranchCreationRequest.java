package com.example.edux_backend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchCreationRequest {
    String address;
    Long numberOfRooms;
    String roomName;

}
