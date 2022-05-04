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

import com.farmaciaL.farmacia.model.categoria;
import com.farmaciaL.farmacia.model.produto;
import com.farmaciaL.farmacia.repository.CategoriaRepository;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/categoria")
public class CategoriaController {
	
	
	@Autowired	
	private CategoriaRepository categoriaRe;
	
	@GetMapping	
	public ResponseEntity<List<categoria>> getAll(){
		return ResponseEntity.ok(categoriaRe.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<categoria> getById(@PathVariable Long id){
		return categoriaRe.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
		
@GetMapping("/Categoria/{descricao}")
public ResponseEntity<List<categoria>> getByNome(@PathVariable String Nome){
	return ResponseEntity.ok(categoriaRe.findAllByDescricaoContainingIgnoreCase(Nome));

}

@PostMapping
public ResponseEntity<categoria> post(@Valid @RequestBody categoria categoria){
	return ResponseEntity.status(HttpStatus.CREATED)
			.body(categoriaRe.save(categoria));

}

@ResponseStatus(HttpStatus.NO_CONTENT)
@DeleteMapping("/{id}")
public void delete(@PathVariable long id) {
	Optional<categoria> produto = categoriaRe.findById(id);
	
	if (produto.isEmpty())
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	
	categoriaRe.deleteById(id);
		
}
}

