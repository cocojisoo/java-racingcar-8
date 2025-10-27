package racingcar.controller;

import java.util.Arrays;
import java.util.List;
import racingcar.service.Race;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {
    private final InputView inputView;
    private final OutputView outputView;

    public RacingController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        List<String> carNames = readCarNames();
        int attempts = readAttempts();
        
        Race race = new Race(carNames);
        
        System.out.println("\n실행 결과");
        for (int i = 0; i < attempts; i++) {
            race.runRound();
            outputView.printRoundResult(race.getCarPositions());
        }
        
        outputView.printWinners(race.getWinners());
    }

    private List<String> readCarNames() {
        String input = inputView.getCarNames();
        return Arrays.asList(input.split(","));
    }

    private int readAttempts() {
        String input = inputView.getAttempts();
        return Integer.parseInt(input);
    }
}

