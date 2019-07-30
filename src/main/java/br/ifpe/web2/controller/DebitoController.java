package br.ifpe.web2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.ifpe.web2.domain.Debito;
import br.ifpe.web2.domain.FiltroDebito;
import br.ifpe.web2.domain.Status;
import br.ifpe.web2.service.DebitoService;

@Controller
public class DebitoController {

	@Autowired
	private DebitoService debitoService;
	
	@GetMapping("/debito/{codigo}")
	public ModelAndView editDebito(@PathVariable("codigo") Integer codigo) {
		ModelAndView mv = new ModelAndView("debito-list :: modalContents");
		mv.addObject("debito", this.debitoService.findDebito(codigo));
		mv.addObject("listaStatus", Status.values());
		return mv;
	}
	
	@PostMapping("salvarDebito")
	public String salvarDebito(Debito debito) {
		this.debitoService.salvarDebito(debito);
		return "redirect:/";
	}

	@GetMapping("/")
	public ModelAndView listarTodos(@RequestParam(defaultValue="1") int page) {
		ModelAndView mv = new ModelAndView("debito-list");		
		Pageable paginaReq = PageRequest.of(page - 1, 6, Sort.by("cliente.nome", "vencimento"));
		Page<Debito> paginaResult = this.debitoService.listarTodos(paginaReq);
		System.out.println("Resultados: " + paginaResult.getTotalElements());
		mv.addObject("listaDebitos", paginaResult);
		mv.addObject("listaStatus", Status.values());

		return mv;
	}

	@PostMapping(value= {"pesquisarDebitos"}, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ModelAndView pesquisarDebito(@RequestBody FiltroDebito filtro) {
		System.out.println("Nome: " + filtro.getNomeCliente());
		System.out.println("Status: " + filtro.getStatus());
		int page = filtro.getPage();
		ModelAndView mv = new ModelAndView("debito-list :: frag-debitos");
		Page<Debito> paginaResult;
		Pageable paginaReq = PageRequest.of(page - 1, 6, Sort.by("cliente.nome", "vencimento"));
		paginaResult = this.debitoService.pesquisarDebitosPorFiltro(filtro, paginaReq);	
		System.out.println("Resultados: " + paginaResult.getTotalElements());
		mv.addObject("listaDebitos", paginaResult);
		mv.addObject("listaStatus", Status.values());

		return mv;
	}
}
