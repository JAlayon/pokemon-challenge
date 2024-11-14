package mx.bankaya.challenge.pokemon;

import mx.bankaya.challenge.BankayaChallengeApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = {BankayaChallengeApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseConfigIT {
}
