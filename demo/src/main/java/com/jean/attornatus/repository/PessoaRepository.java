package com.jean.attornatus.repository;

import com.jean.attornatus.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
