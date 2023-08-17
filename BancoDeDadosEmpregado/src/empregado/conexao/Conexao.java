package empregado.conexao;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private final String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
	private String username;
	private String password;
		
	public Connection conectar() {
		Properties properties = new Properties(); //Esse properties 'e como se fosse um txt, porém uma extenção própria do java que permite a linguagem lê-lo melhor
				
		try(FileInputStream file = new FileInputStream("config.properties")){ //para abrir o arquivo usamos esse fileinputStrem.
			// se n especificar o diretório, o programa vai procurar algum arquivo (file)
			properties.load(file); //vai ler o arquivo
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		}
		catch(IOException e) {
			System.out.println("Erro ao ler o arquivo de configuração\n" + e);
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao carregar o driver de conexão\n" + e);
		} catch (SQLException e) {
			System.out.println("Erro ao abrir conexão com o banco de dados\n" + e);
		}
		return null;
	}
}
