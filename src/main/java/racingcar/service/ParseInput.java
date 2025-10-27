package racingcar.service;
import java.util.Arrays;
import java.util.List;
import racingcar.view.InputView;

public class ParseInput{
    public List<String> parseCarNames(String InputCarNames){
        return Arrays.asList(InputCarNames.split(","));
    }
}


