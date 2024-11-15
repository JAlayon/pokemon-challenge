package mx.bankaya.challenge.metrics.aspect;

import jakarta.servlet.http.HttpServletRequest;
import mx.bankaya.challenge.event.EventLogService;
import mx.bankaya.challenge.event.enums.EventLogType;
import mx.bankaya.challenge.metrics.LogExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    private final EventLogService eventLogService;
    private final HttpServletRequest httpServletRequest;

    public LoggingAspect(EventLogService eventLogService, HttpServletRequest httpServletRequest) {
        this.eventLogService = eventLogService;
        this.httpServletRequest = httpServletRequest;
    }

    @Around("@annotation(logExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;

        String clientIpAddress = getClientIp();
        EventLogType operation = logExecutionTime.operation();

        saveSafelyLog(clientIpAddress, operation, duration);
        return result;
    }

    private void saveSafelyLog(String clientIpAddress, EventLogType operation, long duration) {
        try {
            logger.info("process=saveSafelyLog, status=started, ipAddress={}, operation={}, executionTime={}",
                    clientIpAddress, operation, duration);
            eventLogService.saveRequest(clientIpAddress, operation, duration);
            logger.info("process=saveSafelyLog, status=finished, ipAddress={}, operation={}, executionTime={}",
                    clientIpAddress, operation, duration);
        } catch (Exception e) {
            logger.error("process=saveSafelyLog, status=error, msg={}", e.getMessage(), e);
        }
    }

    private String getClientIp() {
        String ipAddress = httpServletRequest.getHeader("X-Forwarded-For");
        return (ipAddress != null && !ipAddress.isEmpty()) ? ipAddress : httpServletRequest.getRemoteAddr();
    }
}
