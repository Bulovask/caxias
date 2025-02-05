package org.bulovask.repository;

import javax.persistence.EntityManager;
import org.bulovask.entity.Produto;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class Produtos implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Produtos() {}

    public Produtos(EntityManager manager) {
        this.manager = manager;
    }

    public Produto buscarPorID(Long id) {
        return manager.find(Produto.class, id);
    }

    public List<Produto> listarTudo() {
        return manager.createQuery("FROM Produto", Produto.class).getResultList();
    }

    public List<Produto> pesquisar(String pesquisa) {
        return manager.createQuery(
                "from Produto where nome like :pesquisa or descricao like :pesquisa or barcode like :pesquisa",
                Produto.class
        ).setParameter("pesquisa", "%" + pesquisa + "%").getResultList();
    }

    public Produto salvar(Produto produto) {
        return manager.merge(produto);
    }

    public void apagar(Produto produto) {
        produto = this.buscarPorID(produto.getId());
        manager.remove(produto);
    }
}
