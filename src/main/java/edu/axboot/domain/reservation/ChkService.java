package edu.axboot.domain.reservation;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import edu.axboot.controllers.dto.*;
import edu.axboot.domain.standard.GuestInfo;
import edu.axboot.domain.standard.GuestInfoRepository;
import edu.axboot.domain.standard.RoomInfo;
import edu.axboot.utils.SessionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChkService extends BaseService<Chk, Long> {
    private ChkRepository chkRepository;
    private GuestInfoRepository guestInfoRepository;
    private ChkMemoRepository chkMemoRepository;

    @Inject
    public ChkService(ChkRepository chkRepository) {
        super(chkRepository);
        this.chkRepository = chkRepository;
    }

    @Transactional
    public long save(ChkSaveRequestDto saveDto) {
        long id = 0;

        // 투숙객정보 저장
        GuestInfo guest = GuestInfo.builder()
                .id(saveDto.getGuestId())
                .guestNm(saveDto.getGuestNm())
                .guestNmEng(saveDto.getGuestNmEng())
                .guestTel(saveDto.getGuestTel())
                .email(saveDto.getEmail())
                .brth(saveDto.getBrth())
                .gender(saveDto.getGender())
                .langCd(saveDto.getLangCd())
                .build();

        Long guestId = guestInfoRepository.save(guest).getId();

        Chk chk = null;
        if (saveDto.getId() == null || saveDto.getId() == 0) {
            chk = saveDto.toEntity();
            chk.투숙객번호갱신(guestId);
            id = 신규예약저장(chk);
        } else {
            chk = chkRepository.findOne(saveDto.getId());
            chk.투숙객번호갱신(guestId);
            chk.예약수정(guestId, saveDto.getGuestNm(), saveDto.getGuestNmEng(), saveDto.getGuestTel(), saveDto.getEmail(), saveDto.getBrth(), saveDto.getGender(), saveDto.getLangCd(),
                    saveDto.getArrDt(), saveDto.getDepDt(), saveDto.getNightCnt(), saveDto.getRoomTypCd(), saveDto.getAdultCnt(), saveDto.getChldCnt(),
                    saveDto.getSaleTypCd(), saveDto.getSrcCd(), saveDto.getPayCd(), saveDto.getAdvnYn(), saveDto.getSalePrc(), saveDto.getSvcPrc());
            id = saveDto.getId();
        }

        // 투숙메모 처리
        this.saveToMemo(chk.getRsvNum(), saveDto.getMemos());

        return id;
    }
    private Long 신규예약저장(Chk chk) {
        String rsvDt = LocalDate.now().toString();
        Chk todayLastChk = select().select(
                Projections.fields(Chk.class, qChk.sno))
                .from(qChk)
                .where(qChk.rsvDt.eq(rsvDt))
                .orderBy(qChk.sno.desc())
                .fetchFirst();

        int sno = 1;
        if (todayLastChk != null) {
            sno = todayLastChk.getSno() + 1;
        }

        chk.예약번호생성(rsvDt, sno);

        return chkRepository.save(chk).getId();
    }

    private void saveToMemo(String rsvNum, List<ChkMemoSaveRequestDto> memoDtos) {
        for (ChkMemoSaveRequestDto memoDto: memoDtos) {
            if (memoDto.is__created__()) {
                ChkMemo lastChkMemo = select().select(
                        Projections.fields(ChkMemo.class, qChkMemo.sno))
                        .from(qChkMemo)
                        .where(qChkMemo.rsvNum.eq(rsvNum))
                        .orderBy(qChkMemo.sno.desc())
                        .fetchFirst();

                int snoMemo = 1;
                if (lastChkMemo != null) {
                    snoMemo = lastChkMemo.getSno() + 1;
                }

                ChkMemo memo = ChkMemo.builder()
                        .rsvNum(rsvNum)
                        .sno(snoMemo)
                        .memoCn(memoDto.getMemoCn())
                        .memoDtti(Timestamp.valueOf(LocalDateTime.now()))
                        .memoMan(SessionUtils.getCurrentLoginUserCd())
                        .delYn("N")
                        .build();
                chkMemoRepository.save(memo);
            } else if (memoDto.is__modified__()) {
                ChkMemo memo = chkMemoRepository.findOne(memoDto.getId());
                memo.update(memoDto.getMemoCn());
            } else if (memoDto.is__deleted__()) {
                ChkMemo memo = chkMemoRepository.findOne(memoDto.getId());
                memo.delete();
            }
        }
    }

    @Transactional(readOnly = true)
    public ChkResponseDto findById(Long id) {
        Chk chk = chkRepository.findOne(id);

        if (chk == null) {
            throw new IllegalArgumentException("해당 예약 정보가 없습니다. id=" + id);
        }

        return new ChkResponseDto(chk);
    }

    @Transactional(readOnly = true)
    public List<ChkListResponseDto> findBy(String filter, String rsvNum, String roomTypCd, String rsvSttDate, String rsvEndDate, String arrSttDate, String arrEndDate, String depSttDate, String depEndDate, List<String> sttusCds) {
        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(filter)) {
            BooleanBuilder builder2 = new BooleanBuilder();
            builder2.or(qChk.guestNm.contains(filter));
            builder2.or(qChk.guestTel.contains(filter));
            builder2.or(qChk.email.contains(filter));
            builder.and(builder2);
        }

        if (isNotEmpty(rsvNum)) {
            builder.and(qChk.rsvNum.contains(rsvNum));
        }

        if (isNotEmpty(roomTypCd)) {
            builder.and(qChk.roomTypCd.eq((roomTypCd)));
        }

        if (isNotEmpty(rsvSttDate)) {
            if (isNotEmpty(rsvEndDate)) {
                builder.and(qChk.rsvDt.between(rsvSttDate, rsvEndDate));
            } else {
                builder.and(qChk.rsvDt.goe(rsvSttDate));
            }
        }

        if (isNotEmpty(arrSttDate)) {
            if (isNotEmpty(arrEndDate)) {
                builder.and(qChk.arrDt.between(arrSttDate, arrEndDate));
            } else {
                builder.and(qChk.arrDt.goe(arrSttDate));
            }
        }

        if (isNotEmpty(depSttDate)) {
            if (isNotEmpty(depEndDate)) {
                builder.and(qChk.depDt.between(depSttDate, depEndDate));
            } else {
                builder.and(qChk.depDt.goe(depSttDate));
            }
        }

        if (sttusCds != null) {
            if (sttusCds.size() > 0) {
                BooleanBuilder builder2 = new BooleanBuilder();
                for (String sttusCd: sttusCds) {
                    builder2.or(qChk.sttusCd.eq(sttusCd));
                }
                builder.and(builder2);
            }
        }

        List<Chk> entities = select().select(
                Projections.fields(Chk.class,
                        qChk.id, qChk.rsvNum, qChk.rsvDt, qChk.arrDt, qChk.depDt, qChk.nightCnt,
                        qChk.roomTypCd, qChk.roomNum, qChk.saleTypCd, qChk.srcCd, qChk.sttusCd,
                        qChk.guestNm
                ))
                .from(qChk)
                .where(builder)
                .orderBy(qChk.rsvNum.asc())
                .fetch();

        return entities.stream()
                .map(ChkListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public int updateByStatus(List<ChkStatusRequestDto> reqDtos) {
        int count = 0;
        for (ChkStatusRequestDto dto: reqDtos) {
            Chk chk = chkRepository.findOne(dto.getId());
            chk.예약상태변경(dto.getSttusCd());
            count++;
        }

        return count;
    }


//
//    @Transactional(readOnly = true)
//    public List<ChkListResponseDto> getChkList(Long guestId) {
//        BooleanBuilder builder = new BooleanBuilder();
//
//        findby()
//
//        List<Chk> entitis = select()
//                .from(qChk)
//                .where(builder)
//                .orderBy(qChk.rsvDt.asc())
//                .fetch();
//
//        return entitis.stream()
//                .map(ChkListResponseDto::new)
//                .collect(Collectors.toList());
//
//    }

    public List<Chk> gets(RequestParams<Chk> requestParams) {
        return findAll();
    }
}