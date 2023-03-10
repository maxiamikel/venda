package com.maxi.venda.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemPedido extends BaseEntity {
    private BigDecimal precoDeVenda;
    private BigDecimal quantidade;
    private BigDecimal descontoPercentual;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

    public ItemPedido() {
        super();
    }

    public ItemPedido(Pedido pedido, Produto produto, BigDecimal quantidade, BigDecimal descontoPercentual) {
        this();
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.descontoPercentual = descontoPercentual;
        this.precoDeVenda = produto.getPrecoAtual();
    }
    
    public Produto getProduto() {
        return produto;
    }

    public Pedido getPedido() {
        return pedido;
    }
    public BigDecimal getQuantidade() {
        return quantidade;
    }
    public BigDecimal getDescontoPercentual() {
        return descontoPercentual;
    }

	public BigDecimal getValorTotal() {
        return precoDeVenda.multiply(quantidade)
               .multiply(new BigDecimal("1.00").subtract(descontoPercentual.divide(new BigDecimal("100.00")))).setScale(2, RoundingMode.HALF_DOWN);
	}

}