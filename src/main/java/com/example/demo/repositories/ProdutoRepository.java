package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Produto;

// @Repository -> é opcional pois ela ja está herdando do JpaRepository que já está registrando como um componente spring
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
