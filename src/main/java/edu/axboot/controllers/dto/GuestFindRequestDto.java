package edu.axboot.controllers.dto;

import edu.axboot.domain.standard.GuestInfo;
import edu.axboot.domain.standard.RoomInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestFindRequestDto {

    private String guestNm;
    private String guestTel;
    private String email;

    @Builder
    public GuestFindRequestDto(String guestNm, String guestTel, String email) {
        this.guestNm = guestNm;
        this.guestTel = guestTel;
        this.email = email;
    }


    public GuestFindRequestDto(GuestInfo entity){
        this.guestNm= entity.getGuestNm();
        this.guestTel =entity.getGuestTel();
        this.email = entity.getEmail();

    }



}
