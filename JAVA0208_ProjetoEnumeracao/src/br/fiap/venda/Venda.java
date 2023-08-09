package br.fiap.venda;

import br.fiap.cargo.Cargo;
import br.fiap.funcionario.Funcionario;

public class Venda {

	private Funcionario funcionario;
	private double venda;
	
	public Venda(Funcionario funcionario, double valor) {
		super();
		this.funcionario = funcionario;
		this.venda = valor;
	}
	
	public double CalcularComissao() {
		double comissao = 0;
		Cargo cargo = funcionario.getCargo();
		
		comissao = cargo.calcularComissao(venda);
		return comissao;
	}
	
}
