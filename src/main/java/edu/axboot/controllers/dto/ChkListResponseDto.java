package edu.axboot.controllers.dto;

import edu.axboot.domain.education.book.EducationBook;
import edu.axboot.domain.reservation.Chk;
import lombok.Getter;

@Getter
public class ChkListResponseDto {
    private Long id;
    private String roomNum;
    private int nightCnt;
    private String roomTypCd;
    private int sno;
    private String arrDt;
    private String depDt;

    public ChkListResponseDto(Chk entity) {
        this.id = entity.getId();
        this.roomNum = entity.getRoomNum();
        this.nightCnt = entity.getNightCnt();
        this.roomTypCd = entity.getRoomTypCd();
        this.sno = entity.getSno();
        this.arrDt = entity.getArrDt();
        this.depDt = entity.getDepDt();
    }
}
