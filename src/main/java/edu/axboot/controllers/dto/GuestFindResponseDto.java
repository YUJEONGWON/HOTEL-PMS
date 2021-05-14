package edu.axboot.controllers.dto;

import edu.axboot.domain.standard.GuestInfo;
import lombok.Getter;

@Getter
public class GuestFindResponseDto {

    private Long id;
    private String guestNm;
    private String guestNmEng;
    private String guestTel;
    private String email;
    private String brth;
    private String gender;
    private String langCd;
    private String rmk;


    public GuestFindResponseDto(GuestInfo guestInfo){
        this.id=guestInfo.getId();
        this.guestNm=guestInfo.getGuestNm();
        this.guestNmEng=guestInfo.getGuestNmEng();
        this.guestTel=guestInfo.getGuestTel();
        this.email= guestInfo.getEmail();
        this.brth=guestInfo.getBrth();
        this.gender= guestInfo.getGender();
        this.langCd= guestInfo.getLangCd();
        this.rmk= guestInfo.getRmk();
    }
}
