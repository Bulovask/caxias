package org.bulovask.controller;

import lombok.Data;
import org.bulovask.entity.Produto;
import org.bulovask.repository.Produtos;
import org.bulovask.service.ProdutoService;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Data
public class ProdutoBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private Produto produto;
    @Inject
    private Produtos produtos;

    private List<Produto> listaProdutos;

    @Inject
    private ProdutoService produtoService;

    @PostConstruct
    public void init() {
        carregarLista();
    }

    public void carregarLista() {
        listaProdutos = produtos.listarTudo();
    }

    public void salvar() {
        produtoService.salvar(produto);
        carregarLista();
        produto = new Produto();
    }
}
