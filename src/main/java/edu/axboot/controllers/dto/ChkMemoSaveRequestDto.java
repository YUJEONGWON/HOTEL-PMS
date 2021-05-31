package edu.axboot.controllers.dto;

import edu.axboot.domain.reservation.ChkMemo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//
//@Getter
//@NoArgsConstructor
//public class ChkMemoSaveRequestDto {
//    private Long id;
//    private Integer sno;
//    private Timestamp memoDtti;
//    private String memoCn;
//    private String memoMan;
//    private String delYn;
//
//    @Builder
//    public ChkMemoSaveRequestDto(Long id,Integer sno, Timestamp memoDtti, String memoCn,String memoMan,String delYn) {
//        this.id=id;
//        this.sno=sno;
//        this.memoDtti = memoDtti;
//        this.memoCn=memoCn;
//        this.memoMan=memoMan;
//        this.delYn=delYn;
//    }
//
//    public ChkMemo toEntity() {
//        return ChkMemo.builder()
//                .id(id)
//                .sno(sno)
//                .memoMan(memoMan)
//                .memoDtti(memoDtti)
//                .memoCn(memoCn)
//                .delYn(delYn)
//                .build();
//    }
//
//
//}


@Getter
@NoArgsConstructor
public class ChkMemoSaveRequestDto {
    private Long id;
    private String memoCn;
    private boolean __created__;
    private boolean __modified__;
    private boolean __deleted__;

    @Builder
    public ChkMemoSaveRequestDto(Long id, String memoCn, boolean __created__, boolean __modified__, boolean __deleted__) {
        this.id = id;
        this.memoCn = memoCn;
        this.__created__ = __created__;
        this.__modified__ = __modified__;
        this.__deleted__ = __deleted__;
    }

    public ChkMemo toEntity() {
        return ChkMemo.builder()
                .id(id)
                .memoCn(memoCn)
                .isCreated(__created__)
                .isModified(__modified__)
                .isDeleted(__deleted__)
                .build();
    }
}
