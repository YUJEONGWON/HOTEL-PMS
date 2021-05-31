package edu.axboot.controllers.dto;


import edu.axboot.domain.reservation.Chk;
import edu.axboot.domain.reservation.ChkMemo;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ChkResponseDto {
    private Long id;
    private String arrDt;
    private String depDt;
    private Integer nightCnt;
    private String roomTypCd;
    private Integer adultCnt;
    private Integer chldCnt;

    private String saleTypCd;
    private String srcCd;
    private String payCd;
    private String advnYn;
    private BigDecimal salePrc;
    private BigDecimal svcPrc;

    private Long guestId;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String langCd;
    private String brth;
    private String gender;

    private List<ChkMemoResponseDto> memos = new ArrayList<ChkMemoResponseDto>();

    public ChkResponseDto(Chk entity) {
        this.id = entity.getId();
        this.arrDt = entity.getArrDt();
        this.depDt = entity.getDepDt();
        this.nightCnt = entity.getNightCnt();
        this.roomTypCd = entity.getRoomTypCd();
        this.adultCnt = entity.getAdultCnt();
        this.chldCnt = entity.getChldCnt();
        this.saleTypCd = entity.getSaleTypCd();
        this.srcCd = entity.getSrcCd();
        this.payCd = entity.getPayCd();
        this.advnYn = entity.getAdvnYn();
        this.salePrc = entity.getSalePrc();
        this.svcPrc = entity.getSvcPrc();
        this.guestId = entity.getGuestId();
        this.guestNm = entity.getGuestNm();
        this.guestNmEng = entity.getGuestNmEng();
        this.guestTel = entity.getGuestTel();
        this.email = entity.getEmail();
        this.langCd = entity.getLangCd();
        this.brth = entity.getBrth();
        this.gender = entity.getGender();

        for (ChkMemo memo: entity.getMemos()) {
            this.memos.add(new ChkMemoResponseDto(memo));
        }
    }
}
