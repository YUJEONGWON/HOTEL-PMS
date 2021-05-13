package edu.axboot.controllers.dto;

import edu.axboot.domain.standard.RoomInfo;
import lombok.Builder;

public class RoomInfoUpdateRequestDto {

    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusCd;
    private String svcSttusCd;


    @Builder
    public RoomInfoUpdateRequestDto(String roomNum,String roomTypCd,String dndYn,String ebYn,
                           String roomSttusCd,String clnSttusCd,String svcSttusCd){
        this.roomTypCd = roomTypCd;
        this.dndYn = dndYn;
        this.ebYn =ebYn;
        this.roomSttusCd = roomSttusCd;
        this.clnSttusCd =clnSttusCd;
        this.svcSttusCd = svcSttusCd;
    }

    public RoomInfo toEntity(){
        return RoomInfo.builder()
                .roomTypCd(roomTypCd)
                .dndYn(dndYn)
                .ebYn(ebYn)
                .roomSttusCd(roomSttusCd)
                .clnSttusCd(clnSttusCd)
                .svcSttusCd(svcSttusCd)
                .build();
    }
}
