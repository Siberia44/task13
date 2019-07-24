package task9;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task4.entity.Beer;
import task4.service.ShoppingStorageService;
import task4.web.server.tcp.TcpShowCountOfProducts;
import task4.web.server.tcp.TcpShowNameAndPriceById;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TcpSocketProcessorTest {
    @Mock
    ShoppingStorageService shoppingStorageService;

    @InjectMocks
    TcpShowCountOfProducts showCountOfProducts;

    @InjectMocks
    TcpShowNameAndPriceById showNameAndPriceById;

    Beer beer = new Beer();

    @Before
    public void setUp() {
        beer.setName("test");
        beer.setCost(12);
    }

    @Test
    public void shouldReturnZeroWhenCartIsEmpty() {
        when(shoppingStorageService.getCountOfProducts()).thenReturn(0);
        Assert.assertEquals(showCountOfProducts.execute(""), "0");
    }

    @Test
    public void shouldReturnTestNameAndCostOfProductWhenBeerIsExist() {
        when(shoppingStorageService.getBeerById(anyInt())).thenReturn(beer);
        Assert.assertEquals(showNameAndPriceById.execute("2"), "test | 12");
    }

}
