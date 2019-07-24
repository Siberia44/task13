package task6.reflection;

import task4.annotation.Setter;
import task4.entity.Beer;
import task4.entity.DarkBeer;
import task4.entity.Hoegaarden;
import task4.entity.LightBeer;
import task6.input.InputSource;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class InputByReflection {
    private Map<String, Beer> menu = fillMap();
    private InputSource inputSource;
    private ResourceBundle resourceBundle;


    public InputByReflection(InputSource inputSource, ResourceBundle resourceBundle) {
        this.inputSource = inputSource;
        this.resourceBundle = resourceBundle;
    }

    public Beer create() throws InvocationTargetException, IllegalAccessException {
        Beer beer;
        consoleInformation();
        Scanner scanner = new Scanner(System.in);
        String key = scanner.next();
        if (menu.containsKey(key)) {
            beer = menu.get(key);
            fillBeer(beer);
            return beer;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void fillBeer(Beer beer) throws InvocationTargetException, IllegalAccessException {
        Class createdBeer = beer.getClass();
        Method[] methods = createdBeer.getMethods();
        for (Method product : methods) {
            fillEntityIfAnnotationPresent(product, beer);
        }
    }

    private void fillEntityIfAnnotationPresent(Method product, Beer beer) throws InvocationTargetException, IllegalAccessException {
        if (product.isAnnotationPresent(Setter.class)) {
            Method[] methods = inputSource.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if ((product.getParameterTypes()[0]).equals(method.getReturnType())) {
                    product.invoke(beer, method.invoke(inputSource, resourceBundle.getString(product.getAnnotation(Setter.class).name())));
                }
            }
        }
    }

    private void consoleInformation() {
        System.out.println("1 - Beer");
        System.out.println("2 - Dark beer");
        System.out.println("3 - Light beer");
        System.out.println("4 - Hoegarden");
        System.out.println();
        System.out.println("Input key");
    }

    private Map<String, Beer> fillMap() {
        Map<String, Beer> creatorMap = new HashMap<>();
        creatorMap.put("1", new Beer());
        creatorMap.put("2", new DarkBeer());
        creatorMap.put("3", new LightBeer());
        creatorMap.put("4", new Hoegaarden());
        return creatorMap;
    }
}