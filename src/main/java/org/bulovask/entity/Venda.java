package org.bulovask.entity;

import javax.persistence.*;
import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "venda")
public class Venda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemDeVenda> itensDeVenda;

    private LocalDateTime horaDaCompra;

    public Double getPrecoTotal() {
        Double precoTotal = 0.0;
        for(ItemDeVenda itemDeVenda : this.itensDeVenda) {
            precoTotal += itemDeVenda.getPrecoTotal();
        }
        return precoTotal;
    }

    @PrePersist
    protected void prePersist() {
        this.horaDaCompra = LocalDateTime.now();
    }
}
