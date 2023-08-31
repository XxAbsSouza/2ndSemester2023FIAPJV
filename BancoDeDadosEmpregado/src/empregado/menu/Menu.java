package empregado.menu;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static javax.swing.JOptionPane.*;

import java.util.List;

import empregado.dao.DepartamentoDAO;
import empregado.dao.EmpregadoDAO;
import empregado.excecao.OpcaoInvalidaException;
import entidade.Departamento;
import entidade.Empregado;

public class Menu {

	// método para exibir  o menu da aplicação
	public void menu() {
		int opcao = 0;
		
		do {
			try {
				opcao = parseInt(showInputDialog(gerarMenu()));
				if(opcao < 1 || opcao > 7) {
					throw new OpcaoInvalidaException("Opção inválida");
				} else {
					switch(opcao) {
						case 1:
							cadastrarDepartamento();
							break;
						case 2:
							cadastrarEmpregado();
							break;
						case 3:
							pesquisarEmpregado();
							break;
						case 4:
							listarEmpregado();
							break;
						case 5:
							atualizarEmpregado();
							break;
						case 6:
							excluirEmpregado();
							break;							
					}
				}
			}
			catch(NumberFormatException e) {
				showMessageDialog(null, "Você deve digitar um número\n" + e);
			}
			catch(OpcaoInvalidaException e) {
				showMessageDialog(null, e.getMessage());
			}
		} while(opcao != 7);		
	}
	
	private void excluirEmpregado() {
		EmpregadoDAO emDao = new EmpregadoDAO();
		int id = parseInt(showInputDialog("ID"));
		Empregado empregado = emDao.pesquisar(id);
		if (empregado == null) {
			showMessageDialog(null, "Empregado não encontrado.");
		} else {
			emDao.remover(id);
			showMessageDialog(null, "Empregado removido com sucesso.");
		}
	}

	private void atualizarEmpregado() {
		EmpregadoDAO emDao = new EmpregadoDAO();
		int id = parseInt(showInputDialog("ID"));
		Empregado empregado = emDao.pesquisar(id);
		String novonome;
		double novosalario;
		if (empregado == null) {
			showMessageDialog(null, "Empregado não encontrado.");
		} else {
			novonome = showInputDialog("Novo nome");
			novosalario = parseDouble(showInputDialog("Novo Salário"));
			empregado.setId(id);
			empregado.setNome(novonome);
			empregado.setSalario(novosalario);
			/* empregado = new Empregado(id, nome, salario, null); */
			emDao.atualizar(empregado);
		}
		
	}

	private void listarEmpregado() {
		EmpregadoDAO empDao = new EmpregadoDAO();
		List<Empregado> lista = empDao.listar();
		String aux = "";
		int count = 1;
		for (Empregado empregado : lista) {
			aux = aux + "\nFuncionário #" + count + empregado;
			count++;
		}
		showMessageDialog(null, aux);
	}

	private void pesquisarEmpregado() {
		EmpregadoDAO emDao = new EmpregadoDAO();
		int id = parseInt(showInputDialog("ID"));
		Empregado empregado = emDao.pesquisar(id);
		if (empregado == null) {
			showMessageDialog(null, "Empregado não encontrado.");
		} else {
			showMessageDialog(null, empregado);
		}
	}

	private void cadastrarEmpregado() {
		DepartamentoDAO depdao = new DepartamentoDAO();
		EmpregadoDAO empDao = new EmpregadoDAO();
		int idEmp, idDep;
		String nome;
		double salario;

		idEmp = parseInt(showInputDialog("ID"));

	
		if (empDao.pesquisar(idEmp) != null) {
			showMessageDialog(null, "Empregado já está cadastrado!");
		} else {
			
			List<Departamento> lista = depdao.listar();
			String aux = "";
			for (Departamento d : lista) {
				aux += d.getId() + " " + d.getNome() + "\n";
			}
			nome = showInputDialog("Nome");
			salario = parseDouble(showInputDialog("Salário"));
			idDep = parseInt(showInputDialog(aux)); //--> mostra o departamento
			Departamento departamento = new Departamento(idDep);
			Empregado empregado = new Empregado(idEmp, nome, salario, departamento);
			empDao.inserir(empregado);
		} 
		
	}

	private void cadastrarDepartamento() {
		int id = parseInt(showInputDialog("ID"));
		String nome = showInputDialog("Nome do Departamento");
		
		Departamento departamento = new Departamento(id, nome);
		DepartamentoDAO dao = new DepartamentoDAO();

		if (dao.pesquisar(departamento)) {
			showMessageDialog(null, "Departamento já existe, insira um diferente");
		} else{
			dao.inserir(departamento);
		} 

	}

	// método para gerar o menu da aplicação
	private String gerarMenu() {
		String menu = "CONTROLE DE EMPREGADOS - ESCOLHA UMA OPÇÃO\n";
		menu += "1. Cadastrar departamento\n";
		menu += "2. Cadastrar empregado\n";
		menu += "3. Pesquisar empregado\n";
		menu += "4. Listar empregado\n";
		menu += "5. Atualizar empregado\n";
		menu += "6. Excluir empregado\n";
		menu += "7. Finalizar\n";
		return menu;
	}
}
