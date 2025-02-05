package org.bulovask.service;

import org.bulovask.entity.Venda;
import org.bulovask.repository.Vendas;
import org.bulovask.utils.Transacional;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

public class VendaService implements Serializable {
    private static final Long serialVersionUID = 1L;
    
    @Inject
    private Vendas vendas;

    @Transacional
    public void salvar(Venda venda) {
        vendas.salvar(venda);
    }

    @Transacional
    public void apagar(Venda venda) {
        vendas.apagar(venda);
    }

    @Transacional
    public List<Venda> listarTudo() {
        return vendas.listarTudo();
    }
}
