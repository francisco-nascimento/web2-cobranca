package br.ifpe.web2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ifpe.web2.domain.Debito;
import br.ifpe.web2.service.CartaCobrancaService;
import br.ifpe.web2.service.DebitoService;

@Controller
public class CobrancaController {

	@Autowired
	private DebitoService debitoService;
	@Autowired
	private CartaCobrancaService cartaCobrancaService;
	
	@GetMapping("/")
	public ModelAndView debitos(@RequestParam(defaultValue="1") int page) {
		ModelAndView mv = new ModelAndView("debito-list");
		Page<Debito> pagina = this.debitoService.findAll(PageRequest.of(page - 1, 6, Sort.by("cliente.nome")));
		mv.addObject("listaDebitos", pagina) ;
		return mv;
	}
	
	@PostMapping("/gerarCartas")
	public ModelAndView gerarCartas(@RequestParam int mes, @RequestParam int ano) {
		Integer numCartas = cartaCobrancaService.gerarCartas(mes, ano);
		ModelAndView mv = new ModelAndView("debito-list");
		Page<Debito> pagina = this.debitoService.findAll(PageRequest.of(0, 6, Sort.by("vencimento")));
		mv.addObject("listaDebitos", pagina) ;
		mv.addObject("numCartas", numCartas);
		
		return mv;
	}
 
	
//	@GetMapping("/filtrar")
//	@ResponseBody
//	public Page<Debito> filtrarPorNomeCliente(String nome, @RequestParam int page){
//		return this.debitoService.findByNomeCliente(nome, PageRequest.of(page - 1, 2, Sort.by("vencimento")));
//	}
}
