package task6.creator.impl;

import task4.entity.Beer;
import task6.creator.CreatorSource;
import task6.input.InputSource;

public class BeerCreator implements CreatorSource<Beer> {

    @Override
    public Beer create(InputSource inputSource) {
        Beer beer = new Beer();
        fillBeer(inputSource, beer);
        return beer;
    }

    public void fillBeer(InputSource inputSource, Beer beer) {
        beer.setName(inputSource.getString("Input name"));
        beer.setAlcoholPercentage(inputSource.getInt("Input alc %"));
        beer.setCost(inputSource.getInt("Input cost"));
        beer.setCountry(inputSource.getString("Input country"));
    }
}
