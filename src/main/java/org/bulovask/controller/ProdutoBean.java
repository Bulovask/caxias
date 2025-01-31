package org.bulovask.controller;

import lombok.Data;
import org.bulovask.entity.Produto;
import org.bulovask.repository.Produtos;
import org.bulovask.service.ProdutoService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
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
        listaProdutos = produtoService.listarTudo();
    }

    public void salvar() {
        if(!(
                this.produto.getNome() != null
                || this.produto.getDescricao() != null
                || this.produto.getEstoque() != null
                || this.produto.getPrecoDeVenda() != null
                || this.produto.getPrecoDeCusto() != null
        )) {
            produtoService.salvar(this.produto);
            carregarLista();
            produto = new Produto();
        }
    }
}
