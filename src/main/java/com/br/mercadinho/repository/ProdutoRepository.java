package com.br.mercadinho.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.mercadinho.entity.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
