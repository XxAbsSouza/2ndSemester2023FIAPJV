//dao - data acess object
package empregado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import empregado.conexao.Conexao;
import entidade.Departamento;

public class DepartamentoDAO {
    private PreparedStatement ps;
    private String sql;
    private Conexao conexao; //para fazer conexao com o banco, é necessário criar um objeto para isso.
    private ResultSet rs;//pega as variáveis/valor que voltam do banco de dados

    public DepartamentoDAO(){
        conexao = new Conexao();
    }

    //método para inserir os dados do departamento
    public void inserir(Departamento departamento){
        sql = "INSERT INTO java_departamento(id, nome) values(?, ?)";

        try(Connection connection = conexao.conectar()){ //abre a conexao com a base de dados
            ps = connection.prepareStatement(sql);
            ps.setInt(1, departamento.getId()); // SET - Configurar. ou seja, configurar o int. primeiro argumento = ordem da interrogação. O segundo argumento é a varíavel/valor que vc quer substituir o: ?
            ps.setString(2, departamento.getNome());
            ps.execute();
            ps.close(); //diz: objeto, pff morra :v
        } catch(SQLException e) {
            System.out.println(e);
        }
    }

    //método para pesquisar a existência ou não do departamento na tabela
    public boolean pesquisar(Departamento departamento){
        boolean aux = false;
        sql = "SELECT * FROM java_departamento WHERE id = ?  OR nome = ?";

        try(Connection connection = conexao.conectar()){
            ps = connection.prepareStatement(sql);
            ps.setInt(1, departamento.getId()); // SET - Configurar. ou seja, configurar o int. primeiro argumento = ordem da interrogação. O segundo argumento é a varíavel/valor que vc quer substituir o: ?
            ps.setString(2, departamento.getNome());
            rs = ps.executeQuery(); //esse método é apenas para consulta, ele executa o SELECT e retorna um objeto do tipo 
            if (rs.next()) {
                aux = true;
            }
            ps.close();
            rs.close(); //com isso matamos a conecxao, para n ficar com conexao presa
        } catch(SQLException e) {
            System.out.println(e);
        }

        return aux;
    }
}
