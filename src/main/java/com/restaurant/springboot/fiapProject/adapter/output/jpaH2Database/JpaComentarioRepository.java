package com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ComentarioEntity;

public interface JpaComentarioRepository extends JpaRepository<ComentarioEntity, Long> {

}
