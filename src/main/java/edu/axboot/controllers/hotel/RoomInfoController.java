package edu.axboot.controllers.hotel;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.controllers.dto.EducationResponseDto;
import edu.axboot.controllers.dto.EducationSaveRequestDto;
import edu.axboot.controllers.dto.RoomInfoResponseDto;
import edu.axboot.controllers.dto.RoomInfoSaveDto;
import edu.axboot.domain.education.EducationTeach;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import edu.axboot.domain.standard.RoomInfo;
import edu.axboot.domain.standard.RoomInfoService;

import javax.inject.Inject;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/api/v1/standard/roominfo")
public class RoomInfoController extends BaseController {

    @Inject
    private RoomInfoService roomInfoService;

   /* @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<RoomInfo> requestParams) {
        List<RoomInfo> list = roomInfoService.getRoomList(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public RoomInfo view(@PathVariable Long id) {
        RoomInfo entity = roomInfoService.getRoom(id);
        return entity;
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<RoomInfo> request) {
        roomInfoService.save(request);
        return ok();
    }

    @RequestMapping(method = {RequestMethod.DELETE}, produces = APPLICATION_JSON)
    public ApiResponse delete(@RequestParam List<Long> ids) {
        roomInfoService.deleteRoom(ids);
        return ok();
    }*/



    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public List<RoomInfoResponseDto> list(@RequestParam(required = false) String roomTypCd){
        return roomInfoService.getRoomList(roomTypCd);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public RoomInfoResponseDto view(@PathVariable Long id) {
        return roomInfoService.getRoom(id);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<RoomInfo> requestDto) {
        roomInfoService.saveRoom(requestDto);
        return ok();
    }

    @RequestMapping(method = RequestMethod.POST, produces = APPLICATION_JSON)
    public ApiResponse update(@RequestBody RoomInfoSaveDto requestDto) {
        roomInfoService.update(requestDto.getId(),requestDto);
        return ok();
    }

    @RequestMapping(method = {RequestMethod.DELETE}, produces = APPLICATION_JSON)
    public ApiResponse delete(@RequestParam List<Long> ids) {
        roomInfoService.deleteRoom(ids);
        return ok();
    }
}