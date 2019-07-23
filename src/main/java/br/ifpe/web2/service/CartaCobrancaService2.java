package br.ifpe.web2.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.ifpe.web2.domain.CartaCobranca;
import br.ifpe.web2.domain.Cliente;
import br.ifpe.web2.domain.Debito;
import br.ifpe.web2.domain.Status;
import br.ifpe.web2.model.CartaCobrancaDAO;
import br.ifpe.web2.model.DebitoDAO;
import br.ifpe.web2.util.Datas;

@Service
public class CartaCobrancaService2 {

	@Autowired
	private DebitoDAO debitoDAO;
	@Autowired
	private CartaCobrancaDAO cartaDAO;

	public Page<Debito> findAll(Pageable pageable) {
		return debitoDAO.findAll(pageable);
	}
	
	public Integer gerarCartasCobranca(int mes, int ano) {
		// 1. Validacao do mes/ano
		// 2. Obter clientes com debitos pendentes
		
		Date dataVencimento5 = Datas.calcularData(new Date(),
				-5);
		List<Cliente> clientesComDebito = 
				this.debitoDAO.pesquisarDebitosPendentes(
						dataVencimento5);
		
		int quantidadeCartas = 0;
		// 3. Para cada cliente:
		for(Cliente c : clientesComDebito) {
			//    a. verificar existencia de carta de cobranca
			Date dataVencimento = Datas.criarData(10, mes, ano);
			boolean existe = this.cartaDAO.existsByVencimentoAndCliente(
					dataVencimento, c);
			if (!existe) {
				//    b. Caso não exista, calcular a soma dos débitos
				//      Gerar uma carta cobrança
				//      Atualizar débitos		
				List<Debito> debitos = this.debitoDAO
						.pesquisarDebitosPendentesPorCliente(
								dataVencimento5, c);
				
				double total = 0;
				for(Debito d : debitos) {
					total += d.getValor();
					
					// Alterar o status do Débito para COBRADO
					d.setStatus(Status.COBRADO);
					this.debitoDAO.save(d);
				}
				CartaCobranca carta = new CartaCobranca();
				carta.setCliente(c);
				carta.setVencimento(dataVencimento);
				carta.setValorTotal(total);
				
				this.cartaDAO.save(carta);
				quantidadeCartas++;
				
			}
		}
		
		return quantidadeCartas;
		
	}
	
}
