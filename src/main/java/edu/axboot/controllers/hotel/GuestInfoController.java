package edu.axboot.controllers.hotel;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import edu.axboot.controllers.dto.GuestFindRequestDto;
import edu.axboot.controllers.dto.GuestFindResponseDto;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.standard.GuestInfo;
import edu.axboot.domain.standard.GuestInfoService;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/guestinfo")
public class GuestInfoController extends BaseController {

    @Inject
    private GuestInfoService guestInfoService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<GuestInfo> requestParams) {
        List<GuestInfo> list = guestInfoService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<GuestInfo> request) {
        guestInfoService.save(request);
        return ok();
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "guestNm", value = "이름", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "guestTel", value = "전화번호", required = false, dataType = "String", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "email", value = "이메일", dataType = "String", paramType = "query", defaultValue = ""),
    })
    @RequestMapping(value="/findguest",method = {RequestMethod.GET}, produces = APPLICATION_JSON)
    public List<GuestFindResponseDto> findList(RequestParams<GuestFindRequestDto> requestParams) {

        String guestNm=requestParams.getString("guestNm","");
        String guestTel=requestParams.getString("guestTel","");
        String email=requestParams.getString("email","");

        List<GuestFindResponseDto> list = guestInfoService.getFindGuestList(guestNm,guestTel,email);
        return list;
    }
}