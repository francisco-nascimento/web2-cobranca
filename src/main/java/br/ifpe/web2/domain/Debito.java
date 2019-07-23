package br.ifpe.web2.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Debito {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	@ManyToOne
	@JoinColumn(name="CODCLIENTE")
	private Cliente cliente;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date vencimento;
	private Double valor;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Debito() {
		super();
	}
	public Debito(Cliente cliente, Date vencimento, Double valor, Status status) {
		super();
		this.cliente = cliente;
		this.vencimento = vencimento;
		this.valor = valor;
		this.status = status;
	}	
}
