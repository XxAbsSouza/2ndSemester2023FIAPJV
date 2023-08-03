package exercicioMain;

import exercicio.excecao.ContaExcecao;
import exercicioConta.ContaBancaria;

public class main {

	public static void main(String[] args) {
		
		ContaBancaria cb = new ContaBancaria(500, 1000);
		
		try {
			//cb.sacar(650);
			cb.depositar(1200);
		} catch (ContaExcecao e) {
			//e.printStackTrace(); --> imprime a pilha por onde foi acontecendo a chamado dos métodos
			System.out.println(e.getMessage()); //Esse GetMessage faz com que apareça apenas a mensagem de erro, sem a classe(sem o tipo)
		} finally {
			System.out.println("Estou no finally");
			
			//O "finally" é SEMPRE executado, msm se tiver um return. Se tiver um return o finally é executado primeiro e dps o return pra ir embora

			// Ele é usado para fazer fechamento de alguma coisa, algum arquivo, banco de dados, etc.
			
		}
		

	}

}
