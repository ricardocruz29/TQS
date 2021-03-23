import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//imports para o exercicio 1b)
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class StockPortfolioTest {

    // This annotations need another annotation
    @Mock
    IStockMarket market;
    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    void getTotalValue() {
        /*
        1.Prepare a mock to substitute the remote service (@Mock annotation)
        2.Create an instance of the subject under test (SuT) and use the mock to set the (remote) service instance.
        3.Load the mock with the proper expectations (when...thenReturn)
        4.Execute the test (use the service in the SuT)
        5.Verify the result (assert) and the use of the mock (verify)
        */

        //1-Instanciated object that references to the interface IStockMarket
        // IStockMarket market = mock(IStockMarket.class); -> Can be done with annotation @Mock or like this
        //2-Waits for mock to set the dependencies, in this case that market is the argument that the constructor receives
        // StocksPortfolio portfolio = new StocksPortfolio(market); -> Can be done with annotation @InjectMocks or like this
        //3-Predefine some values with mock
        when(market.getPrice("EBAY")).thenReturn(4.0);
        when(market.getPrice("AMAZON")).thenReturn(1.5);
        //4- Test - Add some conditions and check if the values are the expected
        portfolio.addStock(new Stock("EBAY", 2));
        portfolio.addStock(new Stock("AMAZON",4));
        double totalValue = portfolio.getTotalValue();
        //5- Verify the result with an assert and the verify method provided by mock
        assertEquals(14.0,totalValue); //assert the correct result
        //1b- For more a readable assert)
        assertThat( totalValue, is(14.0));

        verify(market, times(2)).getPrice(anyString()); //verify the number of invocations. Even if the when was not defined, and the totalValue was 0, it would be 2 invocations still

    }
}