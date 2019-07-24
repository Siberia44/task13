package task6.input;

import task6.input.impl.ConsoleInput;
import task6.input.impl.RandomInput;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputManager {
    private Map<String, InputSource> inputSourceMap = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    /**
     * chooses a beer making strategy
     *
     * @return strategy
     */
    public InputSource getInputStrategy() {
        System.out.println("Choice input type" + System.lineSeparator()
                + "0 - CONSOLE   1 - RANDOM  ");
        inputSourceMap.put("0", new ConsoleInput());
        inputSourceMap.put("1", new RandomInput());
        String type = scanner.nextLine();
        if (inputSourceMap.containsKey(type)) {
            return inputSourceMap.get(type);
        } else {
            return getInputStrategy();
        }
    }
}
