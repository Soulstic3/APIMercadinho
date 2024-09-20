package com.br.mercadinho.entity.produto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "produtos")
@Table(name = "produtos")
public class Produto {
    @Id
    private Long codigo_barras;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Long quantidade;

    @Column(nullable = false)
    private double preco_compra;

    @Column(nullable = false)
    private double preco_venda;

    @Column(nullable = false)
    private Long ncm;

    @Column(nullable = false)
    private double lucro;

    public Produto() {}

    public Produto(Long codigo_barras, String nome, Long quantidade, double preco_compra, double lucro, Long ncm) {
        this.codigo_barras = codigo_barras;
        this.nome = nome;
        this.quantidade = quantidade;
        this.preco_compra = preco_compra;
        this.lucro = lucro;
        this.ncm = ncm;
        this.preco_venda = calcularPrecoVenda();
    }

    public Long getCodigo_barras() {
        return codigo_barras;
    }

    public void setCodigo_barras(Long codigo_barras) {
        this.codigo_barras = codigo_barras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco_compra() {
        return preco_compra;
    }

    public void setPreco_compra(double preco_compra) {
        this.preco_compra = preco_compra;
        this.preco_venda = calcularPrecoVenda();
    }

    public double getPreco_venda() {
        return preco_venda;
    }

    public Long getNcm() {
        return ncm;
    }

    public void setNcm(Long ncm) {
        this.ncm = ncm;
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        this.lucro = lucro;
        this.preco_venda = calcularPrecoVenda();
    }

    private double calcularPrecoVenda() {
        if (preco_compra == 0 || lucro == 0) {
            return 0.0;
        } else {
            return preco_compra / (1 - lucro / 100);
        }
    }
}