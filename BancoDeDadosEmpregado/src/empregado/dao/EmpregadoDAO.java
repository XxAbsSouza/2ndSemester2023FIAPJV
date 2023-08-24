package empregado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import empregado.conexao.Conexao;
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
            sql = "delete from java_empregado whre id = ?";
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
        public boolean pesquisar(int id) {
            boolean aux = false;

            sql = "SELECT * FROM java_empregado WHERE id = ?";
            try (Connection connection = conexao.conectar()) {
                ps = connection.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                if (rs.next()) {
                    aux = true;
                }
                ps.close();
                rs.close();

            } catch (Exception e) {
                System.out.println("Erro ao pesquisar o empregado " + e);
            }

            return aux;
        }
        
        //Metodo para pesquisar um empregado pelo ID
        public void inserir(Empregado empregado) {
            sql = "INSERT INTO java_empregado(id, nome, salario, id_departamento) values(?, ?, ?, ?)";
            
            try(Connection connection = conexao.conectar()){ //abre a conexao com a base de dados
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
}
