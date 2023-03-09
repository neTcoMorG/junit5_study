import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


class StudyTest {

    @DisplayName("커스텀 SlowTest")
    void test_slow_custom_anno () {
        System.out.println("성공");
    }

    @Test
    @DisplayName("스터디 객체 예외 테스트")
    @Tag("fast")
    void new_study_exception () {
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> new Study(-10));
        String message = runtimeException.getMessage();
        assertEquals("0보다 커야합니다", message);
    }

    @Test
    @DisplayName("스터디 객체 생성하기")
    @Tag("slow")
    void create_new_study () {
        Study study = new Study(-10);

        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음만들면 상태값이 DRAFT 여야 한다"),
                () -> assertTrue(study.getLimit() >= 0)
        );
        assertNotNull(study);
//      assertEquals(기대값, 실제 나오는 값, 메세지);~~`
        assertTrue(study.getLimit() >= 0);
    }

    @Test
    @DisplayName("assertAll를 사용하여 assert 한번에 수행 후 결과 얻기")
    @Tag("slow")
    void use_assertAll () {
        Study study = new Study(1);
        
        assertAll(
                () -> assertNotNull(study, () -> "스터디 객체를 생성할 때 NULL이면 안된다"),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디 객체를 생성하면 상태는 DRAFT여야 한다"),
                () -> assertTrue(study.getLimit() >= 0, () -> "스터디의 최소 참석 인원은 0명 이상이여야 한다")
        );
    }

    @Test
    //@Disabled // 해당 테스트를 실행하고 싶지 않을 때
    @DisplayName("스터디 객체 재생성하기")
    void create_new_study_again () {
        System.out.println("create test 2");
    }

//    @BeforeAll <- 모든 @Test들이 실행되기 전에 딱 1번 호출됨
//                  반드시 static여야함 private 안되고 default는 됨 return도 있으면 안됨
    @BeforeAll
    static void beforeAll () {
        System.out.println("beforeAll \n");
    }
//  @AfterAll <- 모든 @Test들이 실행된 이후에 딱 1번 호출됨
//             @BeforeAll랑 똑같음
    @AfterAll
    static void afterAll () {
        System.out.println("afterAll");
    }

    // 각각의 @Test가 실행되기 전에 실행됨
    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach");
    }

    // 각각의 @Test가 실행된 후에 실행됨
    @AfterEach
    void afterEach() {
        System.out.println("afterEach \n");
    }
}