package org.bulovask.controller;

import lombok.Data;
import lombok.Getter;
import org.bulovask.entity.ItemDeVenda;
import org.bulovask.entity.Produto;
import org.bulovask.entity.Venda;
import org.bulovask.service.ProdutoService;
import org.bulovask.service.VendaService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Named
@SessionScoped
public class VendaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject private VendaService vendaService;
    @Inject private ProdutoService produtoService;

    private Produto produtoAtual;
    private ItemDeVenda itemDeVendaAtual;
    private List<Venda> listaVendas;
    private Venda venda;

    public void novaVenda() {
        venda = new Venda();
        venda.setItensDeVenda(new ArrayList<>());
    }

    public void adicionarProduto() {
        if(produtoAtual != null) {
            itemDeVendaAtual = new ItemDeVenda();
            itemDeVendaAtual.setId(getRandomId());
            itemDeVendaAtual.setVenda(venda);
            itemDeVendaAtual.setProduto(produtoAtual);
            itemDeVendaAtual.setPrecoUnitario(produtoAtual.getPrecoDeVenda());
            itemDeVendaAtual.setPorcentoDesconto(0.0);
            itemDeVendaAtual.setQuantidade(1L);
            venda.getItensDeVenda().add(itemDeVendaAtual);
            venda.setHoraDaCompra(LocalDateTime.now());
            produtoAtual = null;
        }
    }

    public void removerProduto() {
        if(itemDeVendaAtual != null) {
            venda.getItensDeVenda().remove(itemDeVendaAtual);
            itemDeVendaAtual = null;
        }
    }

    public void carregarLista() {
        listaVendas = vendaService.listarTudo();
    }

    public void salvar() {
        if(venda.getHoraDaCompra() != null && venda.getItensDeVenda() != null) {
            vendaService.salvar(venda);
            carregarLista();
            novaVenda();
        }
    }

    public void excluir() {
        vendaService.apagar(venda);
        venda = null;
        carregarLista();
    }

    public boolean isVendaEstaSelecionada() {
        return venda != null && venda.getId() != null;
    }

    public boolean isProdutoAtualENulo() {
        return produtoAtual == null;
    }

    public boolean isItemDeVendaAtualENulo() {
        return itemDeVendaAtual == null;
    }

    public Long getRandomId() {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                + new Random().nextLong();
    }
}
