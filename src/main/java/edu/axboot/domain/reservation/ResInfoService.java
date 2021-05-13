package edu.axboot.domain.reservation;

import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;

import java.util.List;

@Service
public class ResInfoService extends BaseService<ResInfo, Long> {
    private ResInfoRepository resInfoRepository;

    @Inject
    public ResInfoService(ResInfoRepository resInfoRepository) {
        super(resInfoRepository);
        this.resInfoRepository = resInfoRepository;
    }

    public List<ResInfo> gets(RequestParams<ResInfo> requestParams) {
        return findAll();
    }




}