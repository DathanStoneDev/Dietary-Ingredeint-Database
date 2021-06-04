import java.sql.*;

public class Manufacturers {

    private String TABLE_NAME = "Manufacturers";
    private String COLUMN_NAME = "ManufacturerName";
    private String COLUMN_COUNTRY = "Country";
    private String COLUMN_PHONE_NUM = "PhoneNUmber";

    Connection con = new Connect().getConnection();
    //Selects all records for manufacturers
    public void selectAllQueryManufacturers() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);


            while(result.next()) {
                System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString(3));
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Could not select the data: " + e.getMessage());
        }
    }
    //insert a new manufacturer
    public void insertQueryManufacturers() {
        String sql = "INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_COUNTRY + "," + COLUMN_PHONE_NUM + ")" + " VALUES (?, ?, ?)";

        try {
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, "AID");
            statement.setString(2, "Canada");
            statement.setString(3, "5555555555");
            statement.executeUpdate();

            System.out.println("Added a new Manufacturer!");

        } catch (SQLException e) {
            System.out.println("Could not make the updates: " + e.getMessage());
        }
    }
}
