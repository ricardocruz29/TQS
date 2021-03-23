package integration;

import connection.ISimpleHttpClient;
import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressResolverIT {
    private ISimpleHttpClient client;
    private AddressResolver resolver;

    @BeforeEach
    public void init(){
        client = new TqsBasicHttpClient();
        resolver = new AddressResolver(client);
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {
        //Exatamente igual ao anterior. Mas a forma como preparamos o objeto ja e diferente
        //Vamos usar a implementaçao concreta da interface
        Address result = resolver.findAddressForLocation(40.640661, -8.656688);
        assertEquals( result, new Address( "Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null) );

    }

    @Test
    public void whenBadCoordidates_trhowBadArrayindex() throws IOException, URISyntaxException, ParseException {
        assertThrows( IndexOutOfBoundsException.class, () -> resolver.findAddressForLocation(-200,-200), "Bad coordinates not accepted");
    }

}
