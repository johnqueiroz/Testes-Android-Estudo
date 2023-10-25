package br.ufpe.cin.motorola.banco.conta;

import br.ufpe.cin.motorola.banco.cliente.Cliente;
import br.ufpe.cin.motorola.banco.excecoes.SaldoInsuficienteException;

public class Conta extends ContaAbstrata {

	public Conta(String num, Cliente c) {
		super(num, c);
	}
	
	public Conta(String num, double s, Cliente c) {
		super(num,s,c);
	}

	@Override
	public void debitar(double valor) throws SaldoInsuficienteException {
			if (valor <= getSaldo()) {
				setSaldo(getSaldo() - valor);
			} else {
				throw new SaldoInsuficienteException();
			}
	}

}