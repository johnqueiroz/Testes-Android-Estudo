package br.ufpe.cin.motorola.banco.conta;

import br.ufpe.cin.motorola.banco.cliente.Cliente;
import br.ufpe.cin.motorola.banco.excecoes.SaldoInsuficienteException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Double.valueOf;

public class ContaImposto extends ContaAbstrata {
	private final double TAXA = 0.001;
	public ContaImposto(String num, Cliente c) {
		super(num, c);
	}
	
	public ContaImposto(String num, double s, Cliente c) {
		super(num,s,c);
	}

	@Override
	public void debitar(double valor) throws SaldoInsuficienteException {

		double imposto = valor * TAXA;
		double valorDebitado = valor + imposto;
		if (valorDebitado <= getSaldo()) {
			setSaldo(getSaldo() - valorDebitado);
		} else {
			throw new SaldoInsuficienteException();
		}
	}

	//feito por mim
	public void diminuiSaldo(double valor)throws SaldoInsuficienteException {
		if (valor <= getSaldo()){
			super.diminuiSaldo(valor);//saldo = saldo + valor;
		}else{
			throw new SaldoInsuficienteException();
		}

	}

}