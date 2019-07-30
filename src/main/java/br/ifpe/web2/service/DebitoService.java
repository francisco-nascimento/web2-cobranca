package br.ifpe.web2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifpe.web2.domain.Debito;
import br.ifpe.web2.domain.FiltroDebito;
import br.ifpe.web2.domain.Status;
import br.ifpe.web2.model.DebitoDAO;

@Service
public class DebitoService {

	@Autowired
	private DebitoDAO debitoDAO;

	public Page<Debito> listarTodos(Pageable pageable) {
		return debitoDAO.findAll(pageable);
	}

	public Debito findDebito(Integer codigo) {
		return this.debitoDAO.getOne(codigo);
	}

	public void salvarDebito(Debito debito) {
		this.debitoDAO.saveAndFlush(debito);
	}

	public Page<Debito> pesquisarDebitosPorNomeCliente(String nome, Pageable pageable) {
		return debitoDAO.pesquisarDebitosPorNomeCliente(nome, pageable);
	}
	
	public List<Debito> pesquisarDebitosPorNomeCliente(String nome) {
		List<Debito> lista = debitoDAO.pesquisarDebitosPorNomeCliente(nome); 
		System.out.println("Lista (" + nome + "): " +  lista.size());
		return lista;
	}

	public Page<Debito> pesquisarDebitosPorFiltro(FiltroDebito filtro, Pageable pageable) {
		if (filtro.getNomeCliente() != null && !filtro.getNomeCliente().isEmpty() &&  filtro.getStatus() != null && !filtro.getStatus().isEmpty()) {
			return debitoDAO.pesquisarDebitosPorNomeClienteAndStatus(filtro.getNomeCliente(), 
					Status.valueOf(filtro.getStatus()), pageable);
		} else if (filtro.getNomeCliente() != null) {
			return this.pesquisarDebitosPorNomeCliente(filtro.getNomeCliente(), 
					pageable);
		} else if (filtro.getStatus() != null) {
			return this.debitoDAO.findByStatus(Status.valueOf(filtro.getStatus()), pageable);
		} else {
			return this.listarTodos(pageable);
		}
	}

	
}
