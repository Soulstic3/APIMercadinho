package com.br.mercadinho.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.mercadinho.entity.produto.Produto;
import com.br.mercadinho.entity.produto.ProdutoVenda;
import com.br.mercadinho.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseEntity<String> venderProdutos(List<ProdutoVenda> produtosVendidos) {
        for (ProdutoVenda produtoVendido : produtosVendidos) {
            Optional<Produto> produtoOptional = produtoRepository.findById(produtoVendido.getCodigoBarras());

            if (produtoOptional.isPresent()) {
                Produto produto = produtoOptional.get();

                // Verifica se a quantidade em estoque é suficiente
                if (produto.getQuantidade() >= produtoVendido.getQuantidadeVendida()) {
                    // Atualiza a quantidade em estoque
                    produto.setQuantidade(produto.getQuantidade() - produtoVendido.getQuantidadeVendida());
                    produtoRepository.save(produto);  // Salva a atualização
                } else {
                    return new ResponseEntity<>("Estoque insuficiente para o produto: " + produto.getNome(), HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("Produto com código de barras " + produtoVendido.getCodigoBarras() + " não encontrado.", HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>("Venda registrada e estoque atualizado.", HttpStatus.OK);
    }
}

