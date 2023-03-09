
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

@org.junit.jupiter.api.TestInstance(org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // @Order 어노테이션으로 테스트에 순서를 정해줌
public class TestInstance {


    int value = 1;
    @Test
    @Order(3)
    void test_01 () {
        System.out.println(this);
        System.out.println(value++);
    }

    @Test
    @Order(2)
    void test_02 () {
        System.out.println(this);
        System.out.println(value++);
    }

    @Test
    @Order(1)
    void test_03 () {
        System.out.println(this);
        System.out.println(value++);
    }

    // 테스트마다 테스트 클래스 객체가 새로 만들어지며 진행된다 why? -> Test들 끼리 의존성 제거를 위해 (ex 필드 공유)
    // @Test 메서드 마다 하나의 인스턴스를 공유하려면 @TestInstance(TestInstance.LifeCycle.PER_CLASS)를 사용하면 왼다
}
