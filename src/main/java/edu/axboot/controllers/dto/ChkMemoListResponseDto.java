package edu.axboot.controllers.dto;

import edu.axboot.domain.reservation.Chk;
import edu.axboot.domain.reservation.ChkMemo;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ChkMemoListResponseDto {
    private Long id;
    private Timestamp memoDtti;
    private String memoCn;

    public ChkMemoListResponseDto(ChkMemo entity) {

        this.id=entity.getId();
        this.memoDtti = entity.getMemoDtti();
        this.memoCn=entity.getMemoCn();
    }
}
