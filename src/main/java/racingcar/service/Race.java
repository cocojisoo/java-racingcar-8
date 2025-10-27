package racingcar.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import camp.nextstep.edu.missionutils.Randoms;

public class Race {
    
    private final Map<String, Integer> carPositions;

    public Race(List<String> carNames) {
        this.carPositions = new LinkedHashMap<>();
        initializeCars(carNames);
    }

    private void initializeCars(List<String> carNames) {
        for (String name : carNames) {
            carPositions.put(name, 0);
        }
    }

    public void runRound() {
        for (String name : carPositions.keySet()) {
            tryMove(name);
        }
    }

    private void tryMove(String name) {
        int randomValue = Randoms.pickNumberInRange(0,9);
        if (shouldMove(randomValue)) {
            carPositions.put(name, carPositions.get(name) + 1);
        }
    }

    private boolean shouldMove(int randomValue) {
        return randomValue >= 4;
    }

    public Map<String, Integer> getCarPositions() {
        return new LinkedHashMap<>(carPositions);
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
