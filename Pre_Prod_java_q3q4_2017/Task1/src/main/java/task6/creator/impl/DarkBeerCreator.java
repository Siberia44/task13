package task6.creator.impl;

import task4.entity.Beer;
import task4.entity.DarkBeer;
import task6.input.InputSource;

public class DarkBeerCreator extends BeerCreator {

    @Override
    public Beer create(InputSource inputSource) {
        DarkBeer darkBeer = new DarkBeer();
        fillBeer(inputSource, darkBeer);
        return darkBeer;
    }

    public void fillBeer(InputSource inputSource, Beer beer) {
        DarkBeer darkBeer = (DarkBeer) beer;
        super.fillBeer(inputSource, darkBeer);
        darkBeer.setPercentageRoastedHop(inputSource.getInt("Input roast hop %"));
        darkBeer.setType(inputSource.getString("Input type"));
    }
}
