package mx.bankaya.challenge.event;

import mx.bankaya.challenge.event.entity.EventLogEntity;
import mx.bankaya.challenge.event.enums.EventLogType;
import mx.bankaya.challenge.event.repository.EventLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EventLogService {

    private final EventLogRepository eventLogRepository;

    public EventLogService(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    public void saveRequest(final String ipAddress, EventLogType eventLogType,
                            long duration) {
        var eventLog = new EventLogEntity();
        eventLog.setOriginIp(ipAddress);
        eventLog.setRequestDate(LocalDateTime.now());
        eventLog.setMethodName(eventLogType.name());
        eventLog.setExecutionDuration(duration);
        eventLogRepository.save(eventLog);
    }
}
