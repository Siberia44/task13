package task6.input.impl;

import task6.input.InputSource;

import java.util.Random;

public class RandomInput implements InputSource {
    private Random random = new Random();

    @Override
    public String getString(String message) {
        return "word" + random.nextInt(200);
    }

    @Override
    public int getInt(String message) {
        return random.nextInt(50);
    }
}
