package com.farmaciaL.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmaciaL.farmacia.model.categoria;



@Repository
public interface CategoriaRepository extends JpaRepository<categoria, Long>{
	
	public List<categoria>findAllByDescricaoContainingIgnoreCase(String descricao);
	//IgnoreCase é para ignorar letra maiscula e miniscula
}
