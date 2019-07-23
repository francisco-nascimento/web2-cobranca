package br.ifpe.web2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.ifpe.web2.domain.Debito;
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
}
