package edu.axboot.domain.standard;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.*;
import edu.axboot.domain.education.EducationTeach;
import edu.axboot.domain.education.book.EducationBook;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomInfoService extends BaseService<RoomInfo, Long> {

    private RoomInfoRepository roomInfoRepository;

    @Inject
    public RoomInfoService(RoomInfoRepository roomInfoRepository) {
        super(roomInfoRepository);
        this.roomInfoRepository = roomInfoRepository;
    }

    //find,insert,delete,update
    //JPA
    /*public List<RoomInfo> getRoomList(RequestParams<RoomInfo> requestParams) {
        List<RoomInfo> targets = new ArrayList<RoomInfo>();
        String filter =requestParams.getString("roomTypCd","");
        for(RoomInfo entity : findAll()){
            if("".equals(filter)){
                targets.add(entity);
            }else{
                if(entity.getRoomTypCd().equals(filter)){
                    targets.add(entity);
                }
            }
        }
        return targets;

    }

    public RoomInfo getRoom(Long id) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qRoomInfo.id.eq(id));

        RoomInfo entity = select()
                .from(qRoomInfo)
                .where(builder)
                .fetchOne();
        return entity;
    }

    public void saveRoom(RoomInfo entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            this.roomInfoRepository.save(entity);
        } else {
            update(qRoomInfo)
                    .set(qRoomInfo.id, entity.getId())
                    .set(qRoomInfo.roomNum, entity.getRoomNum())
                    .set(qRoomInfo.roomTypCd, entity.getRoomTypCd())
                    .set(qRoomInfo.roomSttusCd, entity.getRoomSttusCd())
                    .set(qRoomInfo.clnSttusCd, entity.getClnSttusCd())
                    .set(qRoomInfo.svcSttusCd,entity.getSvcSttusCd())
                    .set(qRoomInfo.dndYn, entity.getDndYn())
                    .set(qRoomInfo.ebYn, entity.getEbYn())
                    .where(qEducationTeach.id.eq(entity.getId()))
                    .execute();
        }
    }

    @Transactional
    public void deleteRoom(List<Long> ids) {
        for (Long id : ids) {
            delete(qRoomInfo).where(qRoomInfo.id.eq(id)).execute();

        }
    }*/

    /*
    public void delete(List<Long> ids){
        this.roomInfoRepository.deleteRooms(ids);
    }
    */

    @Transactional(readOnly = true)
    public List<RoomInfoResponseDto> getRoomList(String roomTypcd) {
        BooleanBuilder builder = new BooleanBuilder();

        if(isNotEmpty(roomTypcd)){
            builder.and(qRoomInfo.roomTypCd.contains(roomTypcd));
        }
        List<RoomInfo> entitis = select()
                .from(qRoomInfo)
                .where(builder)
                .orderBy(qRoomInfo.roomNum.asc())
                .fetch();

        return entitis.stream()
                .map(RoomInfoResponseDto::new)
                .collect(Collectors.toList());

    }

    public RoomInfoResponseDto getRoom(Long id){
        RoomInfo entity = roomInfoRepository.findOne(id);

        if(entity ==null){
            throw new IllegalStateException("해당 객실이 없습니다. id ="+id);
        }
        return new RoomInfoResponseDto(entity);
    }


    @Transactional
    public Long update(Long id,RoomInfoSaveDto requestDto){

        RoomInfo roomInfo = roomInfoRepository.findOne(id);

        if (roomInfo == null) {
            throw new IllegalArgumentException("해당 객실이 없습니다. id=" + id);
        }
        roomInfo.update(requestDto.getRoomTypCd(), requestDto.getDndYn(),
                    requestDto.getEbYn(), requestDto.getRoomSttusCd(),requestDto.getClnSttusCd(),requestDto.getSvcSttusCd());

        return id;
    }

    @Transactional
    public void saveRoom(List<RoomInfo> entities){
        for(RoomInfo entity: entities){
            saveRoom(entity);
        }
    }


    @Transactional
    public void saveRoom(RoomInfo entity) {
        if (entity.getId() == null || entity.getId() == 0) {
            this.roomInfoRepository.save(entity);
        } else if (entity.isDeleted()) {
            deleteRoom(entity.getId());
        } else {
            update(qRoomInfo)
                    //.set(qRoomInfo.id, entity.getId())
                    .set(qRoomInfo.roomNum, entity.getRoomNum())
                    .set(qRoomInfo.roomTypCd, entity.getRoomTypCd())
                    .set(qRoomInfo.roomSttusCd, entity.getRoomSttusCd())
                    .set(qRoomInfo.clnSttusCd, entity.getClnSttusCd())
                    .set(qRoomInfo.svcSttusCd,entity.getSvcSttusCd())
                    .set(qRoomInfo.dndYn, entity.getDndYn())
                    .set(qRoomInfo.ebYn, entity.getEbYn())
                    .where(qRoomInfo.id.eq(entity.getId()))
                    .execute();
        }
    }

/*    @Transactional
    public void saveRoom(List<RoomInfoSaveDto> requestDtos) {
        for(RoomInfoSaveDto requestDto : requestDtos)
        {
            roomInfoRepository.save(requestDto.toEntity());
//            saveRoom(requestDtos);
        }
    }

    @Transactional
    public void saveRoom(RoomInfoSaveDto requestDto) {
        roomInfoRepository.save(requestDto.toEntity());
    }*/

/*    @Transactional
    public Long update(Long id, RoomInfoSaveDto requestDto) {
        RoomInfo roomInfo1 = roomInfoRepository.findOne(requestDto.toEntity().getId());

        if (roomInfo1 == null) {
            throw new IllegalArgumentException("해당 객실이 없습니다. id=" + id);
        }
        roomInfo1.update(requestDto.getRoomNum(),requestDto.getRoomTypCd(), requestDto.getDndYn(),
                requestDto.getEbYn(), requestDto.getRoomSttusCd(),requestDto.getClnSttusCd(),requestDto.getSvcSttusCd());

        return id;
    }*/

    @Transactional
    public void deleteRoom(List<Long> ids) {
        for (Long id : ids) {
            deleteRoom(id);

        }
    }

    @Transactional
    public void deleteRoom(Long id) {
        delete(qRoomInfo)
                .where(qRoomInfo.id.eq(id))
                .execute();
    }
}