package br.ufpe.cin.motorola.banco.conta;

import br.ufpe.cin.motorola.banco.cliente.Cliente;
import br.ufpe.cin.motorola.banco.excecoes.SaldoInsuficienteException;

public abstract class ContaAbstrata {
	private String numero;
	private double saldo;
	private Cliente cliente;

	public ContaAbstrata(String num, Cliente c) {
		this(num, 0, c);
	}

	public ContaAbstrata(String num, double s, Cliente c) {
		setNumero(num);
		setSaldo(s);
		setCliente(c);
	}

	public void creditar(double valor) {
		saldo = saldo + valor;
	}

	public abstract void debitar(double valor) throws SaldoInsuficienteException;
	
	protected void diminuiSaldo(double valor) throws SaldoInsuficienteException{
		saldo = saldo - valor;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public String getNumero() {
		return numero;
	}

//sempre vai retornar o saldo como 0
	//ajustado por john
	public double getSaldo() {
		return saldo;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setNumero(String num) {
		this.numero = num;
	}

	protected void setSaldo(double valor) {
		saldo = valor;
	}

	public void transferir(ContaAbstrata c, double v) throws SaldoInsuficienteException {
		this.debitar(v);
		c.creditar(v);
	}
}
