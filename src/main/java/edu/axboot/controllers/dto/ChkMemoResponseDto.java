package edu.axboot.controllers.dto;

import edu.axboot.domain.reservation.ChkMemo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Getter
@NoArgsConstructor
public class ChkMemoResponseDto {
    private Long id;
    private String memoCn;
    private String memoDate;

    public ChkMemoResponseDto(ChkMemo entity) {
        DateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

        this.id = entity.getId();
        this.memoCn = entity.getMemoCn();
        this.memoDate = format.format(entity.getMemoDtti());
    }
}
