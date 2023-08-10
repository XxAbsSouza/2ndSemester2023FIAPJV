import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProprietarioDAO {
    private Connection connection;
    private PreparedStatement ps;
    private String sql;

    public void insert(Proprietario p){
        sql = "INSERT INTO java_proprietario(id, nome, cpf) VALUES(?, ?, ?)";
    }
}
