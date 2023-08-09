package br.fiap.cargo;

import br.fiap.comissao.Comissao;

public enum Cargo implements Comissao {
	//Enum NÃO gera objeto, enum é uma LISTA  de CONSTANTES.
	
	ATENDENTE {
		public double calcularComissao(double venda){
			return venda *10/100;
		}
	},
	VENDEDOR {
		public double calcularComissao(double venda){
			return venda *15/100 + 5;
		}
	},
	GERENTE {
		public double calcularComissao(double venda){
			return venda *20/100 + 10;
		}
	};

}
