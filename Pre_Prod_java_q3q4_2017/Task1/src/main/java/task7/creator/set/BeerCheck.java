package task7.creator.set;

import task7.Beer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BeerCheck implements InvocationHandler {
    Beer beer;

    public BeerCheck(Beer beer) {
        this.beer = beer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().startsWith("set")) {
            throw new UnsupportedOperationException();
        }
        return method.invoke(beer, args);
    }
}
