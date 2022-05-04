package com.farmaciaL.farmacia.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.farmaciaL.farmacia.model.produto;
import com.farmaciaL.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping ("/produtos")
@CrossOrigin ("*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRe;

	@GetMapping
	public ResponseEntity<List<produto>> GetAll(){
		
		return ResponseEntity.ok(produtoRe.findAll());

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<produto> getById(@PathVariable Long id){
		return produtoRe.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
		
@GetMapping("/Produto/{nome}")
public ResponseEntity<List<produto>> getByNome(@PathVariable String Nome){
	return ResponseEntity.ok(produtoRe.findAllByNomeContainingIgnoreCase(Nome));

}

@PostMapping
public ResponseEntity<produto> post(@Valid @RequestBody produto produto){
	return ResponseEntity.status(HttpStatus.CREATED)
			.body(produtoRe.save(produto));

}

@ResponseStatus(HttpStatus.NO_CONTENT)
@DeleteMapping("/{id}")
public void delete(@PathVariable long id) {
	Optional<produto> produto = produtoRe.findById(id);
	
	if (produto.isEmpty())
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	
	produtoRe.deleteById(id);
		
}
}
