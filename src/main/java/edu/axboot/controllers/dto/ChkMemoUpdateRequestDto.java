package edu.axboot.controllers.dto;

import edu.axboot.domain.reservation.ChkMemo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class ChkMemoUpdateRequestDto {
    private Long id;
    private Timestamp memoDtti;
    private String memoCn;


    @Builder
    public ChkMemoUpdateRequestDto(Long id, Timestamp memoDtti, String memoCn) {
        this.id=id;
        this.memoDtti = memoDtti;
        this.memoCn=memoCn;

    }


}
