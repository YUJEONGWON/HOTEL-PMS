package edu.axboot.domain.standard;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestInfoRepository extends AXBootJPAQueryDSLRepository<GuestInfo, Long> {
}
