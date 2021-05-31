package edu.axboot.controllers.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChkStatusRequestDto {
    private Long id;
    private String sttusCd;

    @Builder
    ChkStatusRequestDto(Long id, String sttusCd) {
        this.id = id;
        this.sttusCd = sttusCd;
    }
}
