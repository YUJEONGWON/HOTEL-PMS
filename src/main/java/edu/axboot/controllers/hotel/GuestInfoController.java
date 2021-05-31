package edu.axboot.controllers.hotel;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import edu.axboot.controllers.dto.*;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
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
    public Responses.ListResponse list(@RequestParam(value = "guestNm", required = false) String guestNm,
                                       @RequestParam(value = "guestTel", required = false) String guestTel,
                                       @RequestParam(value = "email", required = false) String email) {
        List<GuestListResponseDto> list = guestInfoService.getGuestList(guestNm, guestTel, email);
        return Responses.ListResponse.of(list);
    }



    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public GuestResponseDto findById(@PathVariable Long id) {
        return guestInfoService.getGuestList(id);
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody GuestSaveRequestDto requestDto) {
        guestInfoService.save(requestDto);
        return ok();
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse update(@RequestBody GuestSaveRequestDto requestDto) {
        guestInfoService.update(requestDto);
        return ok();
    }
   /* @ApiImplicitParams({
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
    }*/
}