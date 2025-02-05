package org.bulovask.service;

import org.bulovask.entity.Produto;
import org.bulovask.repository.Produtos;
import org.bulovask.utils.Transacional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class ProdutoService implements Serializable {
    private static final Long serialVersionUID = 1L;
    
    @Inject
    private Produtos produtos;

    @Transacional
    public void salvar(Produto produto) {
        this.produtos.salvar(produto);
    }

    @Transacional
    public void apagar(Produto produto) {
        this.produtos.apagar(produto);
    }

    @Transacional
    public List<Produto> listarTudo() {
        return this.produtos.listarTudo();
    }

    @Transacional
    public List<Produto> pesquisar(String pesquisa) {
        return this.produtos.pesquisar(pesquisa);
    }

}
