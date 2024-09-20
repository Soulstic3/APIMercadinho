package com.br.mercadinho.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.mercadinho.entity.produto.Produto;
import com.br.mercadinho.repository.ProdutoRepository;

@RestController
public class ProdutoController {
	@Autowired
	private ProdutoRepository produtoRepository;

	@PostMapping("/saveProduto")
	public String salvarProduto(@RequestBody Produto produto ) {
		produtoRepository.save(produto);
		return "produto salvo";
	}
	
	@GetMapping("/getProdutos")
	public List<Produto> getProdutos(){
		return produtoRepository.findAll();
	}
	
	@PutMapping("/updateProduto/{id}")
	public ResponseEntity<Produto> updateProdutoById(@PathVariable Long id, @RequestBody Produto newProdutoData){
	    Optional<Produto> oldprodutoData = produtoRepository.findById(id);
	    
	    if(oldprodutoData.isPresent()) {
	        Produto updatedProdutoData = oldprodutoData.get();
	        
	        if(newProdutoData.getLucro() != 0) {
	            updatedProdutoData.setLucro(newProdutoData.getLucro());
	        }
	        if(newProdutoData.getNome() != null) {
	            updatedProdutoData.setNome(newProdutoData.getNome());
	        }
	        if(newProdutoData.getQuantidade() != null) {
	            updatedProdutoData.setQuantidade(newProdutoData.getQuantidade());
	        }
	        if(newProdutoData.getPreco_compra() != 0) {
	            updatedProdutoData.setPreco_compra(newProdutoData.getPreco_compra());
	        }
	        
	        Produto produtoObj = produtoRepository.save(updatedProdutoData);
	        return new ResponseEntity<>(produtoObj, HttpStatus.OK);
	    }
	    
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping ("/deleteProduto/{id}")
	public ResponseEntity<HttpStatus> deleteProdutoById(@PathVariable Long id){
			produtoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
	}
}
