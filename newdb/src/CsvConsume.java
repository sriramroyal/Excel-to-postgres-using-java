import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class CsvConsume {
    public static void main(String[] args){

        String jdbcUrl = "jdbc:postgresql://localhost:5432/sampledb";
        String userName = "postgres";
        String password = "sriram";

        String filePath = "D:\\Users\\snethibottu\\Desktop\\sampleData.csv";

        int batchSize = 30;

        Connection connection = null;

        try{
            connection = DriverManager.getConnection(jdbcUrl,userName, password);
            connection.setAutoCommit(false);
            String sql = "Insert into Employee" +
                    "(sno, id, name, primaryskill, secondaryskill, domain)" + "values(?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(filePath));

            String lineText = null;
            int count = 0;

            lineReader.readLine();
            while((lineText = lineReader.readLine())!= null){
                String[] data = lineText.split(",");

                String sno = data[0];
                String id = data[1];
                String name = data[2];
                String primaryskill = data[3];
                String secondaryskill = data[4];
                String domain = data[5];

                statement.setString(1,sno);
                statement.setString(2,id);
                statement.setString(3,name);
                statement.setString(4,primaryskill);
                statement.setString(5,secondaryskill);
                statement.setString(6,domain);

                statement.addBatch();


                if(count%batchSize==0){
                    statement.executeBatch();
                }
            }
            lineReader.close();
            statement.executeBatch();
            connection.commit();
            connection.close();

            System.out.println("The data is added successfully to the data base.");
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}