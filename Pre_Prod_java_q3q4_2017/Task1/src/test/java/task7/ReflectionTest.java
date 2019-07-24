package task7;

import org.junit.Test;
import task4.entity.Beer;
import task6.input.InputSource;
import task6.input.impl.RandomInput;
import task6.reflection.InputByReflection;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class ReflectionTest {
    InputSource inputSource = new RandomInput();
    ResourceBundle bundle = ResourceBundle.getBundle("resources", Locale.getDefault());
    Beer beer;
    InputByReflection inputByReflection = new InputByReflection(inputSource, bundle);

    @Test
    public void shouldReturnEntityWithRandomValues() throws InvocationTargetException, IllegalAccessException {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        beer = inputByReflection.create();
        assertNotNull(beer.getName());
        assertNotNull(beer.getCountry());
        assertNotEquals(beer.getCost(), 0);
        assertNotEquals(beer.getAlcoholPercentage(), 0);
    }

    @Test
    public void shouldReturnNonNullValueAfterUseMethodCreate() throws InvocationTargetException, IllegalAccessException {
        System.setIn(new ByteArrayInputStream("1".getBytes()));
        beer = inputByReflection.create();
        assertNotNull(beer);
    }
}
