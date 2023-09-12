package com.SportEventz.webApp.dto;
import com.SportEventz.webApp.models.UserEntity;



import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SportDto {
    private Long id;
    private String title;
    private String photoUrl;
    private String content;
    private UserEntity createdBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<SportEventDto> sportEvents;
}
