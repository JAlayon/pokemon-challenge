package mx.bankaya.challenge.event.repository;

import mx.bankaya.challenge.event.entity.EventLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository extends JpaRepository<EventLogEntity, Long> {
}
