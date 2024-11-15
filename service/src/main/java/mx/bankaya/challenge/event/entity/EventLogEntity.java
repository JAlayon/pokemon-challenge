package mx.bankaya.challenge.event.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "event_log")
public class EventLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin_ip")
    private String originIp;

    @Column(name = "request_date")
    private LocalDateTime requestDate;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "execution_duration")
    private Long executionDuration; // duration in milliseconds

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginIp() {
        return originIp;
    }

    public void setOriginIp(String originIp) {
        this.originIp = originIp;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getExecutionDuration() {
        return executionDuration;
    }

    public void setExecutionDuration(Long executionDuration) {
        this.executionDuration = executionDuration;
    }
}
