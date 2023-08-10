import java.sql.Connection;

public class Main {

	public static void main(String[] args) {
		Connection connection = new Conexcao().conectar();
		System.out.println(connection);
	}

}
