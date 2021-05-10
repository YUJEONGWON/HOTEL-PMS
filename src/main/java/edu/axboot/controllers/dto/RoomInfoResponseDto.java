package edu.axboot.controllers.dto;

import edu.axboot.domain.education.book.EducationBook;
import edu.axboot.domain.standard.RoomInfo;
import lombok.Getter;

@Getter
public class RoomInfoResponseDto {
    private Long id;
    private String roomNum;
    private String roomTypCd;
    private String dndYn;
    private String ebYn;
    private String roomSttusCd;
    private String clnSttusCd;
    private String svcSttusCd;


    public RoomInfoResponseDto(RoomInfo entity){
        this.id= entity.getId();
        this.roomNum =entity.getRoomNum();
        this.roomTypCd = entity.getRoomTypCd();
        this.dndYn = entity.getDndYn();
        this.ebYn =entity.getEbYn();
        this.roomSttusCd = entity.getRoomSttusCd();
        this.clnSttusCd =entity.getClnSttusCd();
        this.svcSttusCd = entity.getSvcSttusCd();
    }
}
