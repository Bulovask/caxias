package org.bulovask.entity;

import javax.persistence.*;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "item_de_venda")
public class ItemDeVenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne private Venda venda;
    @ManyToOne private Produto produto;

    private Double precoUnitario;
    private Long quantidade;

    private Double desconto;

    public Double getPrecoTotal() {
        return this.quantidade * (this.precoUnitario - this.desconto);
    }

    public void setPorcentoDesconto(Double porcentoDesconto) {
        this.desconto = this.precoUnitario * porcentoDesconto;
    }

    @Override
    public String toString() {
        return "ItemDeVenda(id = " + id + ")";
    }
}
