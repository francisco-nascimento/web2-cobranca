package br.ifpe.web2.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ifpe.web2.domain.Cliente;
import br.ifpe.web2.domain.Debito;
import br.ifpe.web2.domain.Status; 

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
	
	@Query("select d from Debito d left join d.cliente c where "
			+ " c.nome like %:nome% order by c.nome, d.vencimento")	
	public List<Debito> pesquisarDebitosPorNomeCliente(String nome);

	@Query("select d from Debito d where "
			+ " d.cliente.nome like %:nome%  order by d.cliente.nome, d.vencimento")	
	public Page<Debito> pesquisarDebitosPorNomeCliente(String nome, Pageable pageable);
	
	@Query("select d from Debito d where "
			+ " d.cliente.nome like %:nome% and d.status = :status order by d.cliente.nome, d.vencimento")	
	public Page<Debito> pesquisarDebitosPorNomeClienteAndStatus(String nome, Status status, Pageable pageable);

	public Page<Debito> findByStatus(Status status, Pageable pageable);
}
