import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Connect {


    public static final String TABLE_IN = "Dietary_Ingredients";
    public static final String COLUMN_ID_IN = "IngredientID";
    public static final String COLUMN_NAME_IN = "IngredientName";
    public static final String COLUMN_ROLE_IN = "IngredientRole";
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
}
