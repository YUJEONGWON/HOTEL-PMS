package edu.axboot.domain.standard;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import edu.axboot.controllers.dto.*;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestInfoService extends BaseService<GuestInfo, Long> {
    private GuestInfoRepository guestInfoRepository;

    @Inject
    public GuestInfoService(GuestInfoRepository guestInfoRepository) {
        super(guestInfoRepository);
        this.guestInfoRepository = guestInfoRepository;
    }

    @Transactional(readOnly = true)
    public GuestResponseDto getGuestList(Long id) {
        GuestInfo guest = guestInfoRepository.findOne(id);

        if (guest == null) {
            throw new IllegalArgumentException("해당 투숙객 정보가 없습니다. id=" + id);
        }

        return new GuestResponseDto(guest);
    }


    @Transactional(readOnly = true)
    public List<GuestListResponseDto> getGuestList(String guestNm, String guestTel, String email) {
        BooleanBuilder builder = new BooleanBuilder();

        if(isNotEmpty(guestNm)){
            builder.and(qGuestInfo.guestNm.contains(guestNm));
        }
        if(isNotEmpty(guestTel)){
            builder.and(qGuestInfo.guestTel.contains(guestTel));
        }
        if(isNotEmpty(email)){
            builder.and(qGuestInfo.email.contains(email));
        }

/*
        List<GuestInfo> entitis = select()
                .from(qGuestInfo)
                .where(builder)
                .orderBy(qGuestInfo.guestNm.asc())
                .fetch();
*/


        List<GuestInfo> entities = select().select(
                Projections.fields(GuestInfo.class,
                        qGuestInfo.id, qGuestInfo.langCd ,qGuestInfo.guestNmEng,qGuestInfo.guestNm, qGuestInfo.guestTel, qGuestInfo.email, qGuestInfo.gender, qGuestInfo.brth
                ))
                .from(qGuestInfo)
                .where(builder)
                .orderBy(qGuestInfo.guestNm.asc())
                .fetch();

        return entities.stream()
                .map(GuestListResponseDto::new)
                .collect(Collectors.toList());


    }


    @Transactional
    public long save(GuestSaveRequestDto saveDto) {
        return guestInfoRepository.save(saveDto.toEntity()).getId();
    }

    @Transactional
    public long update(GuestSaveRequestDto saveDto) {
        return guestInfoRepository.save(saveDto.toEntity()).getId();
    }

    @Transactional
    public Long update(GuestUpdateRequestDto updateDto) {
        GuestInfo guest = guestInfoRepository.findOne(updateDto.getId());

        if (guest == null) {
            throw new IllegalArgumentException("해당 투숙객 정보가 없습니다. id=" + updateDto.getId());
        }

        // JPA 영속성 컨텍스트 사용 (엔티티를 영구 저장하는 환경)
        guest.update(updateDto.getGuestTel(), updateDto.getEmail(), updateDto.getRmk());

        return guest.getId();
    }



}