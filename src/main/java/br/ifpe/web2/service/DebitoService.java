package br.ifpe.web2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifpe.web2.domain.Debito;
import br.ifpe.web2.model.DebitoDAO;

@Service
public class DebitoService {

	@Autowired
	private DebitoDAO debitoDAO;

	public Page<Debito> findAll(Pageable pageable) {
		return debitoDAO.findAll(pageable);
	}

//	public Page<Debito> findByNomeCliente(String nome, Pageable pageable) {
//		return debitoDAO.findByNomeCliente(nome, pageable);
//	}
	
	
}
