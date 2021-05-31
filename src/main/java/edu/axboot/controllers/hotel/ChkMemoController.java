package edu.axboot.controllers.hotel;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import edu.axboot.controllers.dto.ChkMemoListResponseDto;
import edu.axboot.controllers.dto.ChkMemoSaveRequestDto;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.reservation.ChkMemoService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/chkmemo")
public class ChkMemoController extends BaseController {

    @Inject
    private ChkMemoService chkMemoService;

/*
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<ChkMemo> requestParams) {
        List<ChkMemo> list = chkMemoService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }
*/

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(@RequestParam String rsvNum) {
        List<ChkMemoListResponseDto> list = chkMemoService.getMemoList(rsvNum);
        return Responses.ListResponse.of(list);
    }



    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody ChkMemoSaveRequestDto requestDto) {
        chkMemoService.save(requestDto);
        return ok();
    }

//    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
//    public ApiResponse update(@RequestBody ChkMemoSaveRequestDto requestDto) {
//        chkMemoService.update(requestDto);
//        return ok();
//    }
//    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
//    public ApiResponse save(@RequestBody List<ChkMemo> request) {
//        chkMemoService.save(request);
//        return ok();
//    }
}