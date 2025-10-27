package racingcar.view;
import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String getCarNames(){
        System.out.println("경주할 자동차 이름을 입력해주세요(쉼표(,)로 구분, 5자 이하)");
        return Console.readLine();
    }
    public int getAttempts(){
        System.out.println("시도할 횟수를 입력해주세요");
        return Integer.parseInt(Console.readLine());
    }
}
