package edu.axboot.domain.standard;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.GuestFindResponseDto;
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
    public List<GuestFindResponseDto> getFindGuestList(String guestNm, String guestTel, String email) {
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

        List<GuestInfo> entitis = select()
                .from(qGuestInfo)
                .where(builder)
                .orderBy(qGuestInfo.guestNm.asc())
                .fetch();
/////////////////? new 부분분
       return entitis.stream()
                .map(GuestFindResponseDto::new)
                .collect(Collectors.toList());

    }


    public List<GuestInfo> gets(RequestParams<GuestInfo> requestParams) {

        return findAll();
    }
}