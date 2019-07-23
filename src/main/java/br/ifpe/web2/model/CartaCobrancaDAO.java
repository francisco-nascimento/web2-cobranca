package br.ifpe.web2.model;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifpe.web2.domain.CartaCobranca;
import br.ifpe.web2.domain.Cliente;

public interface CartaCobrancaDAO extends JpaRepository<CartaCobranca, Integer>{

	public boolean existsByVencimentoAndCliente(
			Date dataVencimento, Cliente cliente);
}
