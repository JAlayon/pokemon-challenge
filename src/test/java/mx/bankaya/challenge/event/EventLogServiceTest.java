package mx.bankaya.challenge.event;

import mx.bankaya.challenge.event.entity.EventLogEntity;
import mx.bankaya.challenge.event.enums.EventLogType;
import mx.bankaya.challenge.event.repository.EventLogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EventLogServiceTest {

    @Mock
    private EventLogRepository eventLogRepository;

    @InjectMocks
    private EventLogService eventLogService;


    @Test
    void saveRequest_success() {
        // given
        var ipAddress = "127.0.0.1";
        var eventLogType = EventLogType.GET_NAME;
        int duration = 100;

        // when
        eventLogService.saveRequest(ipAddress, eventLogType, duration);

        // then
        var captor = ArgumentCaptor.forClass(EventLogEntity.class);
        verify(eventLogRepository).save(captor.capture());

        Assertions.assertThat(captor.getValue().getOriginIp()).isEqualTo(ipAddress);
        Assertions.assertThat(captor.getValue().getMethodName()).isEqualTo(eventLogType.name());
        Assertions.assertThat(captor.getValue().getExecutionDuration()).isEqualTo(duration);
    }
}
