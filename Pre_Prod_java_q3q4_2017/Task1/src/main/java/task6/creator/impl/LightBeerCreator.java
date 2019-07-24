package task6.creator.impl;

import task4.entity.Beer;
import task4.entity.LightBeer;
import task6.input.InputSource;

public class LightBeerCreator extends BeerCreator {
    @Override
    public Beer create(InputSource inputSource) {
        LightBeer lightBeer = new LightBeer();
        fillBeer(inputSource, lightBeer);
        return lightBeer;
    }

    @Override
    public void fillBeer(InputSource inputSource, Beer beer) {
        LightBeer lightBeer = (LightBeer) beer;
        super.fillBeer(inputSource, lightBeer);
        lightBeer.setManufacturer(inputSource.getString("Input manufacturer"));
        lightBeer.setRawMaterials(inputSource.getString("Input raw materials"));
    }

}
