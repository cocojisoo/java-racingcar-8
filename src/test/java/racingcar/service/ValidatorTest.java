package racingcar.service;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidatorTest {
    private final Validator validator = new Validator();

    @Test
    void 정상적인_이름_검증() {
        List<String> carNames = Arrays.asList("pobi", "woni", "jun");
        
        assertThatThrownBy(() -> validator.validateCarNames(carNames))
            .doesNotThrowAnyException();
    }

    @Test
    void 빈_이름_검증() {
        List<String> carNames = Arrays.asList("pobi", "", "jun");
        
        assertThatThrownBy(() -> validator.validateCarNames(carNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 자동차 이름은 비어 있을 수 없습니다.");
    }

    @Test
    void 이름_길이_초과_검증() {
        List<String> carNames = Arrays.asList("pobi", "javaji");
        
        assertThatThrownBy(() -> validator.validateCarNames(carNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 자동차 이름은 5자를 초과할 수 없습니다.");
    }

    @Test
    void 공백_만_있는_이름_검증() {
        List<String> carNames = Arrays.asList("pobi", "   ");
        
        assertThatThrownBy(() -> validator.validateCarNames(carNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 자동차 이름은 비어 있을 수 없습니다.");
    }

    @Test
    void null_이름_검증() {
        List<String> carNames = Arrays.asList("pobi", null);
        
        assertThatThrownBy(() -> validator.validateCarNames(carNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 자동차 이름은 비어 있을 수 없습니다.");
    }

    @Test
    void 빈_리스트_검증() {
        List<String> carNames = Arrays.asList();
        
        assertThatThrownBy(() -> validator.validateCarNames(carNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 자동차 이름을 입력해야 합니다.");
    }

    @Test
    void 중복_이름_검증() {
        List<String> carNames = Arrays.asList("pobi", "woni", "pobi");
        
        assertThatThrownBy(() -> validator.validateCarNames(carNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 중복된 자동차 이름입니다");
    }

    @Test
    void 공백_제거_후_중복_검증() {
        List<String> carNames = Arrays.asList("pobi", " pobi ");
        
        assertThatThrownBy(() -> validator.validateCarNames(carNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 중복된 자동차 이름입니다");
    }

    @Test
    void 시도횟수_0_검증() {
        assertThatThrownBy(() -> validator.validateAttempts(0))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 시도 횟수는 1 이상이어야 합니다.");
    }

    @Test
    void 시도횟수_음수_검증() {
        assertThatThrownBy(() -> validator.validateAttempts(-1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 시도 횟수는 1 이상이어야 합니다.");
    }

    @Test
    void 정상적인_시도횟수_검증() {
        validator.validateAttempts(1);
        validator.validateAttempts(5);
        validator.validateAttempts(10);
    }

    @Test
    void 제로폭_문자_검증() {
        List<String> carNames = Arrays.asList("pobi", "woni" + "\u200B");
        
        assertThatThrownBy(() -> validator.validateCarNames(carNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 자동차 이름에 제로 폭 문자를 사용할 수 없습니다.");
    }

    @Test
    void 제어_문자_검증() {
        List<String> carNames = Arrays.asList("pobi", "woni" + "\u0000");
        
        assertThatThrownBy(() -> validator.validateCarNames(carNames))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("[ERROR] 자동차 이름에 제어 문자를 사용할 수 없습니다.");
    }
}