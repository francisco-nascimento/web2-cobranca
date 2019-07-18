package br.ifpe.web2.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifpe.web2.domain.CartaCobranca;
import br.ifpe.web2.domain.Cliente;
import br.ifpe.web2.domain.Debito;
import br.ifpe.web2.domain.Status;
import br.ifpe.web2.model.CartaCobrancaDAO;
import br.ifpe.web2.model.DebitoDAO;
import br.ifpe.web2.util.Datas;

@Service
public class CartaCobrancaService {

	@Autowired
	private DebitoDAO debitoDAO;
	@Autowired
	private CartaCobrancaDAO cartaCobrancaDAO;
	
	public int gerarCartas(int mes, int ano) {
		
		Date data5Dias = Datas.calcularData(new Date(), -5);
		
		List<Cliente> clientesEmDebito = debitoDAO.pesquisarDebitosPendentes(data5Dias);
		
		int quantidadeCartas = 0;
		
		for (Cliente c : clientesEmDebito) {
		
			Date dataVencimento = Datas.criarData(10, mes, ano);
			boolean existeCarta = this.cartaCobrancaDAO.existsByVencimentoAndCliente(dataVencimento, c);
			if (!existeCarta) {
				List<Debito> debitos = this.debitoDAO.pesquisarDebitosPendentesPorCliente(data5Dias, c);
				double total = 0;
				for(Debito deb : debitos) {
					total += deb.getValor();
					deb.setStatus(Status.COBRADO);
					this.debitoDAO.saveAndFlush(deb);
				}
				CartaCobranca cobranca = new CartaCobranca();
				cobranca.setCliente(c);
				cobranca.setValorTotal(total);
				cobranca.setVencimento(dataVencimento);
				this.cartaCobrancaDAO.save(cobranca);
				quantidadeCartas++;
			}
		}
		
		return quantidadeCartas;
	}
}
