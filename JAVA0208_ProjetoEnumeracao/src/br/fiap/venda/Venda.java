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
		
		if(cargo.equals(cargo.ATENDENTE))
		{
			comissao = venda*10/100;
		} else if(cargo.equals(cargo.VENDEDOR)) {
			comissao = venda*15/100+5;
		} else if(cargo.equals(cargo.GERENTE)) //pq else if? caso tenha novos cargos na empresa.
			comissao = venda*20/100+10;
		return comissao;
	}
	
}
