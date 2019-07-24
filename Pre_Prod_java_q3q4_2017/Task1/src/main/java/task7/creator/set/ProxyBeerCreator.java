package task7.creator.set;

import task7.Beer;

import java.lang.reflect.Proxy;

public class ProxyBeerCreator {
    Beer beer;

    public ProxyBeerCreator(Beer beer) {
        this.beer = beer;
    }

    public Object create() {
        return Proxy.newProxyInstance(Beer.class.getClassLoader(),
                new Class[]{Beer.class}, new BeerCheck(beer));
    }
}
