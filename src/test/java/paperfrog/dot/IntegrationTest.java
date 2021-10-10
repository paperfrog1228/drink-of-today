package paperfrog.dot;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
/***
    유닛테스트와 통합테스트를 분리하기 위한 추상클래스.
    통합테스트를 작성하는 클래스는 이 클래스를 상속받아야한다.
 ***/
@SpringBootTest
@ActiveProfiles("test")
@Tag("IntegrationTest")
public abstract class IntegrationTest {
}