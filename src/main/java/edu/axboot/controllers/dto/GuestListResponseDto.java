package edu.axboot.controllers.dto;

import edu.axboot.domain.reservation.Chk;
import edu.axboot.domain.standard.GuestInfo;
import lombok.Getter;

import java.util.List;

@Getter
public class GuestListResponseDto {
    private Long id;
    private String guestNm;
    private String guestTel;
    private String langCd;
    private String guestNmEng;
    private String email;
    private String brth;
    private String gender;

    private String rmk;

    public GuestListResponseDto(GuestInfo entity) {
        this.id = entity.getId();
        this.guestNm = entity.getGuestNm();
        this.guestNmEng=entity.getGuestNmEng();
        this.guestTel = entity.getGuestTel();
        this.langCd= entity.getLangCd();
        this.rmk =entity.getRmk();
        this.email = entity.getEmail();
        this.brth = entity.getBrth();
        this.gender = entity.getGender();

    }
}
