package br.ifpe.web2.domain;

public class FiltroDebito {

	private String nomeCliente;
	private String status;
	private int page;
	private boolean algoPreenchido;
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
		algoPreenchido = true;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
		algoPreenchido = true;
	}
	public boolean isAlgoPreenchido() {
		return algoPreenchido;
	}
	public void setAlgoPreenchido(boolean algoPreenchido) {
		this.algoPreenchido = algoPreenchido;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
