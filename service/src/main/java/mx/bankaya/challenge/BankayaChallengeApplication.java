package mx.bankaya.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableFeignClients
@EnableAspectJAutoProxy
public class BankayaChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankayaChallengeApplication.class, args);
    }
}
