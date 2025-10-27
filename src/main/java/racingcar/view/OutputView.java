package racingcar.view;
import java.util.List;
import java.util.Map;

public class OutputView {
    public void printRoundResult(Map<String, Integer> carPositions) {
        for (Map.Entry<String, Integer> entry : carPositions.entrySet()) {
            String name = entry.getKey();
            int position = entry.getValue();
            System.out.println(name + " : " + "-".repeat(position));
        }
        System.out.println();
    }

    public void printWinners(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
