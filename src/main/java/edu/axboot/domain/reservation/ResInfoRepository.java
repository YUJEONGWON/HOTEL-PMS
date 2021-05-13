package edu.axboot.domain.reservation;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResInfoRepository extends AXBootJPAQueryDSLRepository<ResInfo, Long> {
}
