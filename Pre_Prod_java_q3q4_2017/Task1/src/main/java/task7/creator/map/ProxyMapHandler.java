package task7.creator.map;

import task7.Beer;

import java.lang.reflect.Proxy;

public class ProxyMapHandler {

    public Object create() {
        return Proxy.newProxyInstance(Beer.class.getClassLoader(),
                new Class[]{Beer.class}, new BeerMap());
    }
}
