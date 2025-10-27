package racingcar.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Validator {

    public void validateCarNames(List<String> carNames) {
        validateNotEmpty(carNames);
        Set<String> uniqueNames = new HashSet<>();
        
        for (String rawName : carNames) {
            String name = sanitizeName(rawName);
            validateName(name);
            validateUniqueName(name, uniqueNames);
            uniqueNames.add(name);
        }
    }

    public void validateAttempts(int attempts) {
        if (attempts <= 0) {
            throw new IllegalArgumentException("[ERROR] 시도 횟수는 1 이상이어야 합니다.");
        }
    }

    private String sanitizeName(String name) {
        if (name == null) {
            return "";
        }
        return name.trim();
    }

    private void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름은 비어 있을 수 없습니다.");
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름은 5자를 초과할 수 없습니다.");
        }
        validateSpecialCharacters(name);
    }

    private void validateSpecialCharacters(String name) {
        for (char c : name.toCharArray()) {
            if (isControlCharacter(c)) {
                throw new IllegalArgumentException("[ERROR] 자동차 이름에 제어 문자를 사용할 수 없습니다.");
            }
            if (isZeroWidthCharacter(c)) {
                throw new IllegalArgumentException("[ERROR] 자동차 이름에 제로 폭 문자를 사용할 수 없습니다.");
            }
            if (isBidirectionalControlCharacter(c)) {
                throw new IllegalArgumentException("[ERROR] 자동차 이름에 양방향 제어 문자를 사용할 수 없습니다.");
            }
        }
    }

    private boolean isControlCharacter(char c) {
        return (c >= 0x0000 && c <= 0x001F) || (c >= 0x007F && c <= 0x009F);
    }

    private boolean isZeroWidthCharacter(char c) {
        return c == '\u200B' || c == '\u200C' || c == '\u200D' || c == '\uFEFF';
    }

    private boolean isBidirectionalControlCharacter(char c) {
        return c == '\u202A' || c == '\u202B' || c == '\u202C' || 
               c == '\u202D' || c == '\u202E' || c == '\u2066' || c == '\u2067' || 
               c == '\u2068' || c == '\u2069';
    }

    private void validateUniqueName(String name, Set<String> uniqueNames) {
        if (uniqueNames.contains(name)) {
            throw new IllegalArgumentException("[ERROR] 중복된 자동차 이름입니다: " + name);
        }
    }

    private void validateNotEmpty(List<String> carNames) {
        if (carNames == null || carNames.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름을 입력해야 합니다.");
        }
    }
}

