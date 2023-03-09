
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class RepeatTest {


    @DisplayName("반복 실행 테스트")
    @RepeatedTest(10)
    void repeatTest (RepetitionInfo info) {
        System.out.println("test " + info.getCurrentRepetition() + "/" + info.getTotalRepetitions());
    }

    @DisplayName("반복 테스트 이름 수정")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest2 () {
    }

    @DisplayName("여러 파라미터를 자동으로 넣으면서 반복 테스트")
    @ParameterizedTest(name = "[{index}] {displayName}")
    @ValueSource(strings = {"A", "B", "C"})
    void repeatTestWithRandomParam (String val) {
        System.out.println(val);
    }


    @DisplayName("컨버터 ValueSource 넘겨지는 데이터들의 타입을 변경")
    @ParameterizedTest(name = "{displayName} - [{index}]")
    @ValueSource(ints = {10, 20, 30})
    void test(@ConvertWith(StudyConvertor.class) Study study) {
        System.out.println(study.getLimit());
    }

    static class StudyConvertor extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, () -> "Study 타입만 변환할 수 있습니다");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @DisplayName("valueSource -> CsvSource")
    @ParameterizedTest
    @CsvSource({"1, '자바'", "2, '스프링'"})
    void csvSourceTest (Integer key, String value) {
        System.out.println("[" + key + "] " + value);
    }
}
