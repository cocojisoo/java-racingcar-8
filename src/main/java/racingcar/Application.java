package racingcar;

import java.util.List;
import racingcar.service.ParseInput;
import racingcar.service.Race;
import racingcar.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ParseInput parseInput = new ParseInput();
        
        String carNamesInput = inputView.getCarNames();
        String attemptsInput = inputView.getAttempts();
        
        List<String> carNames = parseInput.parseCarNames(carNamesInput);
        int attempts = Integer.parseInt(attemptsInput);
        
        Race race = new Race(carNames);
        
        System.out.println("\n실행 결과");
        for (int i = 0; i < attempts; i++) {
            race.runRound();
            race.displayRoundResult();
        }
        
        displayWinners(race);
    }

    private static void displayWinners(Race race) {
        List<String> winners = race.getWinners();
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
