package task6.creator.impl;

import task4.entity.Beer;
import task4.entity.Hoegaarden;
import task6.input.InputSource;

public class HoegardenCreator extends LightBeerCreator {

    @Override
    public Hoegaarden create(InputSource inputSource) {
        Hoegaarden hoegaarden = new Hoegaarden();
        fillBeer(inputSource, hoegaarden);
        return hoegaarden;
    }

    public void fillBeer(InputSource inputSource, Beer beer) {
        Hoegaarden hoegaarden = (Hoegaarden) beer;
        super.fillBeer(inputSource, hoegaarden);
        hoegaarden.setTaste(inputSource.getString("Input taste"));
        hoegaarden.setTypeOfBottle(inputSource.getString("Input type of bottle"));
    }
}
