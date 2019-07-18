package br.ifpe.web2.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ifpe.web2.domain.Cliente;
import br.ifpe.web2.domain.Debito;
import br.ifpe.web2.domain.Status; 

public interface DebitoDAO extends JpaRepository<Debito, Integer> {

	public List<Debito> findByClienteAndStatus(Cliente cliente, Status status);
	
	@Query("select distinct d.cliente from Debito d where d.status = 'PENDENTE' and d.vencimento <= :vencimento")
	public List<Cliente> findByDebitosPendentes(Date vencimento);
}
