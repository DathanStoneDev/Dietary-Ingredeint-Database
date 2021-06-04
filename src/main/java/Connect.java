import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Connect {

    //Dietary_Ingredients table constants
    public static final String TABLE_IN = "Dietary_Ingredients";
    public static final String COLUMN_ID_IN = "IngredientID";
    public static final String COLUMN_NAME_IN = "IngredientName";
    public static final String COLUMN_ROLE_IN = "IngredientRole";

    //Manufacturers table constants
    private String TABLE_MF = "Manufacturers";
    private String COLUMN_NAME_MF = "ManufacturerName";
    private String COLUMN_COUNTRY_MF = "Country";
    private String COLUMN_PHONE_NUM_MF = "PhoneNUmber";

    //connection variable
    private Connection conn;
    //Connects to database

    public Connection getConnection() {
        ResourceBundle reader;
        reader = ResourceBundle.getBundle("database");
        try {
            conn = DriverManager.getConnection(reader.getString("db.url"), reader.getString("db.username"), reader.getString("db.password"));
            System.out.println("Successfully Connected!");
        } catch (SQLException e) {
            System.out.println("Couldn't connect to the database: " + e.getMessage());
        } return conn;
    }
    //get a list of all ingredients in table
    public List<DietaryIngredients> queryIngredients() {
        String sql = "SELECT * FROM " + TABLE_IN;

        try (Statement statement = conn.createStatement();
        ResultSet results = statement.executeQuery(sql)){

            List<DietaryIngredients> ingredients = new ArrayList<>();
            while (results.next()) {
                DietaryIngredients ingredient = new DietaryIngredients();
                ingredient.setId(results.getInt(COLUMN_ID_IN));
                ingredient.setName(results.getString(COLUMN_NAME_IN));
                ingredient.setRole(results.getString(COLUMN_ROLE_IN));
                ingredients.add(ingredient);
            }

            return ingredients;

        } catch (SQLException e) {
            System.out.println("Query Failed: " + e.getMessage());
        }
        return null;
    }

    public List<Manufacturers> queryManufacturers() {

        String sql = "SELECT * FROM " + TABLE_MF;

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sql)){


            List<Manufacturers> manufacturers = new ArrayList<>();
            while (results.next()) {
                Manufacturers manufacturer = new Manufacturers();
                manufacturer.setName(results.getString(COLUMN_NAME_MF));
                manufacturer.setCountry(results.getString(COLUMN_COUNTRY_MF));
                manufacturer.setPhone(results.getString(COLUMN_PHONE_NUM_MF));
                manufacturers.add(manufacturer);
            }

            return manufacturers;

        } catch (SQLException e) {
            System.out.println("Query Failed: " + e.getMessage());
        }
        return null;
    }
}
