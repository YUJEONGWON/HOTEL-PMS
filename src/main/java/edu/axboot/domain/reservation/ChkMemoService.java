package edu.axboot.domain.reservation;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.ChkMemoListResponseDto;
import edu.axboot.controllers.dto.ChkMemoSaveRequestDto;
import edu.axboot.controllers.dto.GuestUpdateRequestDto;
import edu.axboot.domain.standard.GuestInfo;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChkMemoService extends BaseService<ChkMemo, Long> {
    private ChkMemoRepository chkMemoRepository;

    @Inject
    public ChkMemoService(ChkMemoRepository chkMemoRepository) {
        super(chkMemoRepository);
        this.chkMemoRepository = chkMemoRepository;
    }

    public List<ChkMemo> gets(RequestParams<ChkMemo> requestParams) {
        return findAll();
    }

    public List<ChkMemoListResponseDto> getMemoList(String rsvNum) {
        BooleanBuilder builder = new BooleanBuilder();

        if(isNotEmpty(rsvNum)){
            builder.and(qGuestInfo.guestNm.contains(rsvNum));
        }

        List<ChkMemo> entitis = select()
                .from(qGuestInfo)
                .where(builder)
                .orderBy(qGuestInfo.guestNm.asc())
                .fetch();


        return entitis.stream()
                .map(ChkMemoListResponseDto::new)
                .collect(Collectors.toList());


    }



    @Transactional
    public Long save(ChkMemoSaveRequestDto saveDto) {
        return chkMemoRepository.save(saveDto.toEntity()).getId();
    }

/*    @Transactional
    public Long update(ChkMemoSaveRequestDto saveDto) {

        return chkMemoRepository.save(saveDto.toEntity()).getId();
    }*/
//    @Transactional
//    public Long update(ChkMemoSaveRequestDto updateDto) {
//        ChkMemo memo = chkMemoRepository.findOne(updateDto.getId());
//
//        if (memo == null) {
//            throw new IllegalArgumentException("해당 투숙객 정보가 없습니다. id=" + updateDto.getId());
//        }
//
//        // JPA 영속성 컨텍스트 사용 (엔티티를 영구 저장하는 환경)
//        memo.update(updateDto.getMemoDtti(),updateDto.getMemoCn());
//
//        return memo.getId();
//    }

}