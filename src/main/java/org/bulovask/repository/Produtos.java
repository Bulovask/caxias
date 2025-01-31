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
        return manager.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }

    public Produto salvar(Produto produto) {
        return manager.merge(produto);
    }

    public void apagar(Produto produto) {
        produto = this.buscarPorID(produto.getId());
        manager.remove(produto);
    }
}
