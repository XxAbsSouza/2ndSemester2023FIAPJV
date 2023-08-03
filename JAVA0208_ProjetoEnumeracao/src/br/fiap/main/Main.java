package br.fiap.main;

import br.fiap.cargo.Cargo;
import br.fiap.funcionario.Funcionario;
import br.fiap.venda.Venda;

public class Main {

	public static void main(String[] args) {
		
		Funcionario f = new Funcionario("Franciso", 10000, Cargo.GERENTE);
		Venda v = new Venda(f, 1000);
		
		System.out.println(v.CalcularComissao());
	}

}
