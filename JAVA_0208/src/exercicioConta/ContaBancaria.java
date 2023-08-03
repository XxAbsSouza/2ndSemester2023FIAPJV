package exercicioConta;

import exercicio.excecao.ContaExcecao;

public class ContaBancaria {
	private double saldo;
	private double limite;
	
	public ContaBancaria(double saldo, double limite) {
		super();
		this.saldo = saldo;
		this.limite = limite;
	}
	
	public double getSaldoLimite() {
		
		return saldo + limite;
	}
	
	public boolean sacar(double valor) throws ContaExcecao {
		if(valor >= 500 || getSaldoLimite() <= 0) {
			//throw - lançar. Lançar uma exceção, lançar um objeto que representa uma exceção
			throw new ContaExcecao("Valor de saque não permitido");
			//|_> Esse comando faz com que saia do método
			//|_> Método é habilidado a mandar uma exceção/devolver a exceção. Dentro dele n tem o trycat
		}
		saldo -= valor;
		return true;
		
	}
	
	public boolean depositar(double valor) throws ContaExcecao {
		if(valor > 1000) {
			throw new ContaExcecao("Valor de depósito acima do permitido");
		}
		saldo += valor;
		return true;
	}
	
	
}
