package mx.bankaya.challenge.event.repository;

import mx.bankaya.challenge.BaseConfigIT;
import mx.bankaya.challenge.event.entity.EventLogEntity;
import mx.bankaya.challenge.event.enums.EventLogType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventLogRepositoryIT extends BaseConfigIT {

    @Autowired
    private EventLogRepository eventLogRepository;

    @Test
    void saveEventLog_success() {
        var eventLog = new EventLogEntity();
        eventLog.setMethodName(EventLogType.GET_NAME.name());
        eventLog.setOriginIp("127.0.0.1");
        eventLogRepository.save(eventLog);


        assertTrue(eventLogRepository.findById(eventLog.getId()).isPresent());
    }
}
