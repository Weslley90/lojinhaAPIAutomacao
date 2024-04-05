package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoFactory {
    public static ProdutoPojo criarProduto(double valor){
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Xbox Series S");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("Preto");
        cores.add("Verde");
        produto.setProdutoCores(cores);

        List<ComponentePojo> componentes = new ArrayList<>();
        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Controle");
        componente.setComponenteQuantidade(2);
        componentes.add(componente);

        return produto;
    }
}
