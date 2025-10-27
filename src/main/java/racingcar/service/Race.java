package racingcar.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import camp.nextstep.edu.missionutils.Randoms;

public class Race {
    private static final int MIN_RANDOM = 0;
    private static final int MAX_RANDOM = 9;
    private static final int MOVING_THRESHOLD = 4;

    private final Map<String, Integer> carPositions;

    public Race(List<String> carNames) {
        this.carPositions = new LinkedHashMap<>();
        initializeCars(carNames);
    }

    private void initializeCars(List<String> carNames) {
        for (String rawName : carNames) {
            String name = sanitizeName(rawName);
            validateName(name);
            carPositions.put(name, 0);
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
            throw new IllegalArgumentException("자동차 이름은 비어 있을 수 없습니다.");
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다.");
        }
    }

    public void runRound() {
        for (String name : carPositions.keySet()) {
            tryMove(name);
        }
    }

    private void tryMove(String name) {
        int randomValue = Randoms.pickNumberInRange(MIN_RANDOM, MAX_RANDOM);
        if (shouldMove(randomValue)) {
            carPositions.put(name, carPositions.get(name) + 1);
        }
    }

    private boolean shouldMove(int randomValue) {
        return randomValue >= MOVING_THRESHOLD;
    }

    public List<String> getRoundResult() {
        List<String> results = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : carPositions.entrySet()) {
            results.add(formatCarResult(entry.getKey(), entry.getValue()));
        }
        return results;
    }

    private String formatCarResult(String name, int position) {
        return name + " : " + "-".repeat(position);
    }

    public List<String> getWinners() {
        int max = findMaxPosition();
        return findCarsAtPosition(max);
    }

    private int findMaxPosition() {
        if (carPositions.isEmpty()) {
            return 0;
        }
        return Collections.max(carPositions.values());
    }

    private List<String> findCarsAtPosition(int position) {
        List<String> winners = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : carPositions.entrySet()) {
            if (entry.getValue() == position) {
                winners.add(entry.getKey());
            }
        }
        return winners;
    }
}
