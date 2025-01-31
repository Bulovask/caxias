package org.bulovask.repository;

import javax.persistence.EntityManager;
import org.bulovask.entity.ItemDeVenda;
import org.bulovask.entity.Venda;

import java.io.Serializable;
import java.util.List;

public class ItensDeVenda implements Serializable {
    private static final Long serialVersionUID = 1L;

    private EntityManager manager;

    public ItensDeVenda() {}

    public ItensDeVenda(EntityManager manager) {
        this.manager = manager;
    }

    public ItemDeVenda buscarPorID(Long id) {
        return manager.find(ItemDeVenda.class, id);
    }

    public List<ItemDeVenda> buscarPorVenda(Venda venda) {
        return manager.createQuery("from ItemDeVenda where ItemDeVenda.venda = :venda", ItemDeVenda.class)
                .setParameter("venda", venda).getResultList();
    }

    public List<ItemDeVenda> buscarPorProduto(Venda venda) {
        return manager.createQuery("from ItemDeVenda where ItemDeVenda.produto = :produto", ItemDeVenda.class)
                .setParameter("produto", venda).getResultList();
    }

    public List<ItemDeVenda> listarTudo() {
        return manager.createQuery("from ItemDeVenda", ItemDeVenda.class).getResultList();
    }

    public ItemDeVenda salvar(ItemDeVenda itemDeVenda) {
        return manager.merge(itemDeVenda);
    }

    public void apagar(ItemDeVenda itemDeVenda) {
        itemDeVenda = this.buscarPorID(itemDeVenda.getId());
        manager.remove(itemDeVenda);
    }
}
