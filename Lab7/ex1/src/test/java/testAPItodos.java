import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testAPItodos {
    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/todos";
    }

    @Test
    public void whenRequestGet_thenOK(){
        given().when().get(baseURI).then().statusCode(200);
    }

    @Test
    public void whenRequestGetIdfour(){
        given().when().get(baseURI + "/4").then().statusCode(200).assertThat()
                .body("title", equalTo("et porro tempora"));
    }

    @Test
    public void givenAllTodos_checkIfCertainIdsExist() {
        given().when().get(baseURI).then().assertThat().statusCode(200)
                .and().body("id", hasItems(198, 199));
    }
}
