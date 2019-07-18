package br.ifpe.web2.model;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifpe.web2.domain.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Integer>{

}
