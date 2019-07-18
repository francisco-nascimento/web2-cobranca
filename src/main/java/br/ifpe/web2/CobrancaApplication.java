package br.ifpe.web2;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ifpe.web2.domain.Cliente;
import br.ifpe.web2.domain.Debito;
import br.ifpe.web2.domain.Status;
import br.ifpe.web2.model.ClienteDAO;
import br.ifpe.web2.model.DebitoDAO;

@SpringBootApplication
public class CobrancaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CobrancaApplication.class, args);
	}

	@Autowired
	private DebitoDAO debitoDAO;
	@Autowired
	private ClienteDAO clienteDAO;

	@Bean
	InitializingBean sendDatabase() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		return () -> {
			Cliente c1 = new Cliente("Chaves Girafales", "81 3434-3424");
			Debito d1, d2, d3, d4;
			try {
				c1 = clienteDAO.saveAndFlush(c1);
				d1 = new Debito(c1, sdf.parse("2019-05-10"), 120.50, Status.PAGO);
				d2 = new Debito(c1, sdf.parse("2019-06-10"), 120.50, Status.PENDENTE);
				d3 = new Debito(c1, sdf.parse("2019-07-10"), 120.50, Status.PENDENTE);
				d4 = new Debito(c1, sdf.parse("2019-08-10"), 120.50, Status.PENDENTE);
				debitoDAO.saveAndFlush(d1);
				debitoDAO.saveAndFlush(d2);
				debitoDAO.saveAndFlush(d3);
				debitoDAO.saveAndFlush(d4);
			} catch (ParseException e) {
				e.printStackTrace();
			}			
			
		};
	}

}
