package empregado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import empregado.conexao.Conexao;
import entidade.Departamento;
import entidade.Empregado;

public class EmpregadoDAO {
        private PreparedStatement ps; //-->variável que configuramos para levar a instrução sql para o banco de dados
        private String sql;
        private Conexao conexao; //--> para fazer conexao com o banco, é necessário criar um objeto para isso.
        private ResultSet rs;//--> pega as variáveis/valor que voltam do banco de dados; cópia da tabela, cópia dos elementos que serão processados

        public EmpregadoDAO() {
            conexao = new Conexao();
        }

        //Método para deletar um empregado pelo ID
        public void remover(int id) {
            sql = "delete from java_empregado where id = ?";
            try (Connection connection = conexao.conectar()) {
                ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                ps.execute();
                ps.close();
            } catch (Exception e) {
                System.out.println("Erro ao pesquisar o empregado " + e);
            }
        }

        //Metodo para pesquisar um empregado pelo ID
        public Empregado pesquisar(int id) {
            Empregado empregado = null;

            sql = "select \n" + //
                    "    e.nome, e.salario, d.nome as nome_departamento \n" + //
                    "FROM\n" + //
                    "    java_empregado e\n" + //
                    "inner JOIN\n" + //
                    "    java_departamento d\n" + //
                    "on\n" + //
                    "    e.id_departamento = d.id where e.id = ?";
            try (Connection connection = conexao.conectar()) {
                ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String nome = rs.getString("nome");
                    double salario = rs.getDouble("salario");
                    String nomedp = rs.getString("nome_departamento");
                    Departamento dp = new Departamento(0, nomedp);
                    empregado = new Empregado(0, nome, salario, dp);
                }
                ps.close();
                rs.close();

            } catch (Exception e) {
                System.out.println("Erro ao pesquisar o empregado " + e);
            }

            return empregado;
        }
        
        //Metodo para pesquisar um empregado pelo ID
        public void inserir(Empregado empregado) {
            sql = "INSERT INTO java_empregado(id, nome, salario, id_departamento) values(?, ?, ?, ?)";

            try (Connection connection = conexao.conectar()) { //abre a conexao com a base de dados
                ps = connection.prepareStatement(sql);
                ps.setInt(1, empregado.getId());
                ps.setString(2, empregado.getNome());
                ps.setDouble(3, empregado.getSalario());
                ps.setInt(4, empregado.getDepartamento().getId());
                ps.execute();
                ps.close(); //diz: objeto, pff morra :v
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        //Método para listar todos os empregados
        public List<Empregado> listar() {
            List<Empregado> lista = new LinkedList<>();
            sql = "select \n" + //
                    "    e.nome, e.salario, d.nome as nome_departamento \n" + //
                    "FROM\n" + //
                    "    java_empregado e\n" + //
                    "inner JOIN\n" + //
                    "    java_departamento d\n" + //
                    "on\n" + //
                    "    e.id_departamento = d.id";
            try (Connection connection = conexao.conectar()) {
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String nome = rs.getString("nome");
                    double salario = rs.getDouble("salario");
                    String nomedp = rs.getString("nome_departamento");
                    Departamento dp = new Departamento(0, nomedp);
                    Empregado ep = new Empregado(0, nome, salario, dp);
                    lista.add(ep);
                }
                ps.close();
                rs.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("erro ao listar empregado\n" + e);
            }
            return lista;

        }

        //Método para atualizar o nome e o salário pelo id
        public void atualizar(Empregado empregado) {
            sql = "update \n" + //
                    "    java_empregado \n" + //
                    "set \n" + //
                    "    nome = ?, salario = ? \n" + //
                    "where \n" + //
                    "    id = ?";
            try (Connection connection = conexao.conectar()) { //abre a conexao com a base de dados
                ps = connection.prepareStatement(sql);
                ps.setString(1, empregado.getNome());
                ps.setDouble(2, empregado.getSalario());
                ps.setInt(3, empregado.getId());
                ps.execute();
                ps.close(); //diz: objeto, pff morra :v
            } catch (SQLException e) {
                System.out.println("erro ao atualizar dados" + e);
            }
        }

}
