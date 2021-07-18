package com.rbittencourt.pa.logistics.infrastructure.logistics;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LogisticsRepository extends JpaRepository<LogisticsRecord, Long> {

}
