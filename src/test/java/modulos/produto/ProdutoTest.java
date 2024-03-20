package modulos.produto;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do modulo de Produto")
public class ProdutoTest {
    @Test
    @DisplayName("Validar os limites proibidos do valor do produto")
    public void testValidarLimitesProibidosValorProduto() {
        // Configurando os dados da API Rest da Login
        baseURI = "http://165.227.93.41";
        basePath = "lojinha";

        //Obter o token do usuário admin
        String token = given()//Dado que...
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"usuarioLogin\": \"weslleyNovo\",\n" +
                        "  \"usuarioSenha\": \"cebolinhaemonica\"\n" +
                        "}")
                .when()
                .post("/v2/login")
                .then()
                .extract()
                .path("data.token");
        System.out.println(token);

        //Tentar inserrir um produto com valor 0.00 e validar que a mensagem de erro foi apresentado eo status code 40
         given()
                .contentType(ContentType.JSON)
                 .header("token", token)
                .body("{\n" +
                        "  \"produtoNome\": \"Xbox Series S\",\n" +
                        "  \"produtoValor\": 0.00,\n" +
                        "  \"produtoCores\": [\n" +
                        "    \"Preto\", \"Branco\"\n" +
                        "  ],\n" +
                        "  \"produtoUrlMock\": \"\",\n" +
                        "  \"componentes\": [\n" +
                        "    {\n" +
                        "      \"componenteNome\": \"Controle\",\n" +
                        "      \"componenteQuantidade\": 1\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"componenteNome\": \"Cabo HDMI\",\n" +
                        "      \"componenteQuantidade\": 1\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
         .when()
                .post("/v2/produtos")
         .then()
                 .assertThat()
                 .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                 .statusCode(422);
    }
}
//Os atributos não tem parenteses na frente
//O "import static" serve para facilitar a escrita do código, ao invés de digitar "RestAssured." algum atributo ou método nos apenas digitamos o método ou atributos
//Nós usamos o "port" para colocar a porta de conecxão da API.
//O Rest Assurence é biblioteca para chamar uma API Rest Assurance
//