package org.bulovask.controller;

import lombok.Data;
import org.bulovask.entity.Produto;
import org.bulovask.service.ProdutoService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Data
@Named
@SessionScoped
public class ProdutoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject private ProdutoService produtoService;
    private String pesquisa;

    private List<Produto> listaProdutos;
    private Produto produto;

    public void novoProduto() {
        produto = new Produto();
    }

    public void prepararEdicao() {
        System.out.println(produto);
    }

    public void carregarLista() {
        listaProdutos = produtoService.listarTudo();
    }

    public void pesquisar() {
        System.out.println("Pesquisa: " + pesquisa + "; Resultado: " + isPesquisaEmBranco());
        if(isPesquisaEmBranco()) carregarLista();
        else listaProdutos = produtoService.pesquisar(pesquisa);
    }

    public void salvar() {
        if(produto.getNome() != null && produto.getDescricao() != null && produto.getEstoque() != null
            && produto.getPrecoDeCusto() != null && produto.getPrecoDeVenda() != null) {
            produtoService.salvar(this.produto);
            carregarLista();
            produto = new Produto();
        }
    }

    public void excluir() {
        produtoService.apagar(produto);
        produto = null;
        carregarLista();
    }

    public boolean isProdutoEstaSelecionado() {
        return produto != null && produto.getId() != null;
    }

    public boolean isPesquisaEmBranco() {
        return pesquisa == null || pesquisa.isEmpty();
    }

}
