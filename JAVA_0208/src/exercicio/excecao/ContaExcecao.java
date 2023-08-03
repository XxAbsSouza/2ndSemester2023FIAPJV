package exercicio.excecao;

public class ContaExcecao extends Exception {
	//Só de criar essa classe extendida de Exception, já existe uma exceção "Conta Excecao"

	public ContaExcecao() {
		super();
	}
	public ContaExcecao(String mensagem) {
		super(mensagem);
	}
	
	
}
