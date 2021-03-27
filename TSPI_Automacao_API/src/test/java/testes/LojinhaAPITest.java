package testes;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

public class LojinhaAPITest {

    private String token;

    @Before
    public void setUp(){
        RestAssured.baseURI="http://165.227.93.41/";
        RestAssured.basePath="lojinha";

         token = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        " \"usuariologin\": \"Gleisson\",\n" +
                        " \"usuariosenha\": \"teste123\"\n" +
                        "}")
                .when()
                .post("login")
                .then()
                .extract()
                .path("data.token");
    }


    @Test
    public void testBuscarDadosDeUmProdutoEspecifico(){

        RestAssured
                .given()
                    .header("token", token)
                .when()
                    .get("produto/8416")
                .then()
                    .assertThat()
                        .statusCode(200)
                        .body("data.produtonome", Matchers.equalTo("Notebook"))
                        .body("data.componentes[0].componentenome", Matchers.equalTo("Carregador"))
                        .body("message", Matchers.equalTo("Detalhando dados do produto"));

    }

    @Test
    public void testeBuscarDadosDeUmComponenteDeProduto(){

        RestAssured
                .given()
                .header("token", token)
                .when()
                .get("produto/8489/componente/3529")
                .then()
                .assertThat()
                .statusCode(200)
                .body("data.componentenome", Matchers.equalTo("Componente 1"))
                .body("data.componentequantidade", Matchers.equalTo(2))
                .body("message", Matchers.equalTo("Detalhando dados do componente de produto"));
    }
}
