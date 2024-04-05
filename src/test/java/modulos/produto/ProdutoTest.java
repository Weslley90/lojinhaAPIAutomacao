package modulos.produto;

import dataFactory.ProdutoFactory;
import dataFactory.UsuarioFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ComponentePojo;
import pojo.ProdutoPojo;
import pojo.UsuarioPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do modulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach() {
        // Configurando os dados da API Rest da Login
        baseURI = "http://165.227.93.41";
        basePath = "lojinha";



        //Obter o token do usuário admin
        this.token = given()//Dado que...
                .contentType(ContentType.JSON)
                .body(UsuarioFactory.criarUsuario())
            .when()
                .post("/v2/login")
            .then()
                .extract()
                .path("data.token");
        System.out.println(token);

    }

    @Test
    @DisplayName("Validar os limites proibidos do valor do produto")
    public void testValidarLimiteProibidoValorProdutoZero() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoFactory.criarProduto(0.00))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }

    @Test
    @DisplayName("Validar que o valor de 7000,01 não é permitido")
    public void testValidarLimitesProibidosValorProduto() {

        given()
                .contentType(ContentType.JSON)
                .header("token", this.token)
                .body(ProdutoFactory.criarProduto(7000.01))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                    .statusCode(422);
    }
}