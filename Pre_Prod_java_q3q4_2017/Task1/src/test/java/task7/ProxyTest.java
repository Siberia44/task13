package task7;

import org.junit.Test;
import task7.creator.map.ProxyMapHandler;
import task7.creator.set.ProxyBeerCreator;
import task7.impl.BeerImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ProxyTest {

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowException() {
        Beer beer = (Beer) new ProxyBeerCreator(new BeerImpl()).create();
        beer.setAlcoholPercentage(12);
    }

    @Test
    public void shouldReturnExpectedValueWhenCallGetAfterSet() {
        Beer beer = (Beer) new ProxyMapHandler().create();
        beer.setAlcoholPercentage(12);
        int expected = 12;
        int actual = beer.getAlcoholPercentage();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnDefaultValuesIfNotSet() {
        Beer beer = (Beer) new ProxyMapHandler().create();
        assertNull(beer.getCountry());
        assertEquals(0, beer.getCost());
    }
}
