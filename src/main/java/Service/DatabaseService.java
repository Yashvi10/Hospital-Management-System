package Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService {

  Connection conn;
  ResultSet resultSet;

  public DatabaseService(Connection conn){
    this.conn=conn;
  }

  public ResultSet executeQuery(String query) throws ClassNotFoundException, SQLException {
    return conn.createStatement().executeQuery(query);
  }

  public boolean closeDB()  throws SQLException {
    conn.close();
    return true;
  }
}
