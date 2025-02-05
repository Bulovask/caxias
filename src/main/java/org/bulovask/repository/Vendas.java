package org.bulovask.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.bulovask.entity.Venda;

import java.io.Serializable;
import java.util.List;

public class Vendas implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Inject
    private EntityManager manager;

    public Vendas() {}

    public Vendas(EntityManager manager) {
        this.manager = manager;
    }

    public Venda buscarPorID(Long id) {
        return manager.find(Venda.class, id);
    }

    public List<Venda> listarTudo() {
        return manager.createQuery("from Venda", Venda.class).getResultList();
    }

    public Venda salvar(Venda venda) {
        return manager.merge(venda);
    }

    public void apagar(Venda venda) {
        venda = this.buscarPorID(venda.getId());
        manager.remove(venda);
    }
}
