//dao - data acess object
package empregado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import empregado.conexao.Conexao;
import entidade.Departamento;

public class DepartamentoDAO {
    private PreparedStatement ps; //-->variável que configuramos para levar a instrução sql para o banco de dados
    private String sql;
    private Conexao conexao; //--> para fazer conexao com o banco, é necessário criar um objeto para isso.
    private ResultSet rs;//--> pega as variáveis/valor que voltam do banco de dados; cópia da tabela, cópia dos elementos que serão processados

    public DepartamentoDAO(){
        conexao = new Conexao();
    }

    //método para inserir os dados do departamento
    public void inserir(Departamento departamento){
        sql = "INSERT INTO java_departamento(id, nome) values(?, ?)"; //--> instruçãp SQL

        try(Connection connection = conexao.conectar()){ //abre a conexao com a base de dados
            ps = connection.prepareStatement(sql);
            ps.setInt(1, departamento.getId()); // SET - Configurar. ou seja, configurar o int. primeiro argumento = ordem da interrogação. O segundo argumento é a varíavel/valor que vc quer substituir o: ?
            ps.setString(2, departamento.getNome());
            ps.execute();
            ps.close(); //diz: objeto, pff morra :v
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    //método para pesquisar a existência ou não do departamento na tabela
    public boolean pesquisar(Departamento departamento) {

        boolean aux = false;
        sql = "SELECT * FROM java_departamento WHERE id = ?  OR nome = ?";

        try (Connection connection = conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, departamento.getId()); // SET - Configurar. ou seja, configurar o int. primeiro argumento = ordem da interrogação. O segundo argumento é a varíavel/valor que vc quer substituir o: ?
            ps.setString(2, departamento.getNome());
            rs = ps.executeQuery(); //esse método é apenas para consulta, ele executa o SELECT e retorna um objeto do tipo 
            if (rs.next()) {
                aux = true;
            }
            ps.close();
            rs.close(); //com isso matamos a conecxao, para n ficar com conexao presa
        } catch (SQLException e) {
            System.out.println(e);
        }

        return aux;
    }

    //Método para lsitar todos os departamentos, buscaremos na base de datos e listaremos
    public List<Departamento> listar() {
        List<Departamento> lista = new LinkedList<>();
        //MEANS--> array list x linked list = (diferença), array list = array que é instanciado com uma quantidade de elementos e quando chega no limite ele cria um novo e copia todos os objetos do velho para o novo e vai continuando. Linked list = lista ligada, inserção de novos objetos é feito pelo nó, melhor que o arrat list, lista só trabalha com objeto

        sql = "select * from java_departamento order by id";
        try (Connection connection = conexao.conectar()) {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Departamento(rs.getInt("id"), rs.getString("nome")));//pra pegar algo rs e o tipo do ngc do objeto: rs.getInt
                //O getInt(0) vc tem que especificar o Número do id, já o segundo é só o nome da coluna(mais fácil)
            }
            ps.close();
            rs.close(); //com isso matamos a conecxao, para n ficar com conexao presa
        } catch (SQLException e) {
            System.out.println(e);
        }

        return lista;
    }
}
