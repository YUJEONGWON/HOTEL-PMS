package edu.axboot.controllers.hotel;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.controllers.dto.ChkListResponseDto;
import edu.axboot.controllers.dto.RoomInfoResponseDto;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import edu.axboot.domain.reservation.Chk;
import edu.axboot.domain.reservation.ChkService;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/chk")
public class ChkController extends BaseController {

    @Inject
    private ChkService chkService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<Chk> requestParams) {
        List<Chk> list = chkService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }
/*
    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = APPLICATION_JSON)
    public List<ChkListResponseDto> list(@PathVariable Long guestId){
        return chkService.getChkList(guestId);
    }*/
    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<Chk> request) {
        chkService.save(request);
        return ok();
    }
}