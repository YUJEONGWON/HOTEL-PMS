package edu.axboot.domain.standard;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomInfoRepository extends AXBootJPAQueryDSLRepository<RoomInfo, Long> {

/*    @Modifying
    @Query("delete from RoomInfo r where r.id=:id")
    void deleteRooms(List<Long> id);*/

}
