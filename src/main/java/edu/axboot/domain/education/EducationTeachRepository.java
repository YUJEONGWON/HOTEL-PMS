package edu.axboot.domain.education;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface EducationTeachRepository extends AXBootJPAQueryDSLRepository<EducationTeach, Long> {
}
