import java.sql.*;

public class Backend {
    
    String[] getData() {
        String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
        String USERNAME = "SHREYANSH";
        String PASSWORD = "mysql";
        String[] dataArray = new String[126];
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            if (connection != null) {
                // System.out.println("Connected to the database!");
                String sql = "SELECT * FROM AMS";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                int index = 0;
                
                // Storing the results in an array
                while (resultSet.next()) {
                    int seatNo = resultSet.getInt("SEATNO");
                    String seatNoString = Integer.toString(seatNo);
                    
                    String name = resultSet.getString("NAME");
                    
                    int age = resultSet.getInt("AGE");
                    String ageString = Integer.toString(age);
                    
                    String email = resultSet.getString("EMAIL");
                    String src = resultSet.getString("SRC");
                    String dest = resultSet.getString("DEST");

                    dataArray[index] = "Seat :- " + seatNoString + "\n" + "Name :- " +  name + "\n" + "Age :- " +  ageString + "\n" + "Email :- " +  email + "\n" + "Source :- " +  src + "\n" + "Destination :- " +  dest + "\n\n";
                    index += 1;
                }
                
                resultSet.close();
                statement.close();
                connection.close();
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database!");
            e.printStackTrace();
        }
        return dataArray;
    }

    int insertData(int seatNo, String name, int age, String email, String src, String dest) {
        String JDBC_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
        String USERNAME = "SHREYANSH";
        String PASSWORD = "mysql";

        int success = 0;

        String selectQuery = "SELECT * FROM AMS WHERE SEATNO = ?";
        String insertQuery = "INSERT INTO AMS (SEATNO, NAME, AGE, EMAIL, SRC, DEST) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        ) {

            PreparedStatement selectQ = connection.prepareStatement(selectQuery);
            selectQ.setInt(1, seatNo);

            int found = selectQ.executeUpdate();
            
            preparedStatement.setInt(1, seatNo);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, src);
            preparedStatement.setString(6, dest);

            int rowsInserted = preparedStatement.executeUpdate();
            
            if (rowsInserted > 0) {
                success = 1;
            } else {
                success = 0;
            }
        } catch (SQLException e) {
            success = -1;
        }
        return success;
    }
}