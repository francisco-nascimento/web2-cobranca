package br.ifpe.web2.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ifpe.web2.domain.Cliente;
import br.ifpe.web2.domain.Debito; 

public interface DebitoDAO extends JpaRepository<Debito, Integer> {

	@Query("select distinct d.cliente from Debito d where d.status ='PENDENTE' "
			+ "and d.vencimento < :data") 
	public List<Cliente> pesquisarDebitosPendentes(Date data);
	
	@Query("select d from Debito d where "
			+ "d.status = 'PENDENTE' "
			+ "and d.vencimento < :data "
			+ "and d.cliente = :cliente")
	public List<Debito> pesquisarDebitosPendentesPorCliente(Date data,
			Cliente cliente);
}
