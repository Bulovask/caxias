package org.bulovask.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "produto")
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
}
