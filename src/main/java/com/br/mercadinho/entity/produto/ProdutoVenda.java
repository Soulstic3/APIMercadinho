package com.br.mercadinho.entity.produto;

public class ProdutoVenda {
    private Long codigo_barras;
    private Long quantidadeVendida;

    // Getters e Setters
    public Long getCodigoBarras() {
        return codigo_barras;
    }

    public void setCodigoBarras(Long codigoBarras) {
        this.codigo_barras = codigoBarras;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(Long quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }
}
