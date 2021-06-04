import java.sql.*;

public class DietaryIngredients {

    private String TABLE_NAME = "Dietary_Ingredients";
    private String COLUMN_ID = "IngredientID";
    private String COLUMN_NAME = "IngredientName";
    private String COLUMN_ROLE = "IngredientRole";
    //establishes connection for this class
    Connection con= new Connect().getConnection();













    //select all query from the Dietary_Ingredients table
    public void selectAllQueryIngredients() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);


            while(result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString(2) + " " + result.getString(3));
                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Could not select the data: " + e.getMessage());
        }
    }

    //insert a new ingredient record
    public void insertQueryIngredients() {
        String sql = "INSERT INTO " + TABLE_NAME + "(" + COLUMN_NAME + "," + COLUMN_ROLE + ")"  + " VALUES (?, ?)";

        try {
            PreparedStatement statement = con.prepareStatement(sql);


            statement.setString(1, "Vitamin C");
            statement.setString(2, "Antixoidant");
            statement.executeUpdate();

            System.out.println("Ingredient has been added!");

        } catch (SQLException e) {
            System.out.println("Could not make the updates: " + e.getMessage());
        }
    }

    public void updateQueryIngredientName() {
        String sql = "UPDATE " + TABLE_NAME + " SET "+ COLUMN_NAME + " = ? " + "WHERE " + COLUMN_ID+ " = ?";

        try {
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, "Ascorbic Acid");
            statement.setInt(2, 9);

            statement.executeUpdate();
            System.out.println("Ingredient has been updated successfully!");

        } catch (SQLException e) {
            System.out.println("Name could not be updated: " + e.getMessage());
        }
    }
    //update an ingredient role
    public void updateQueryIngredientRole() {
        String sql = "UPDATE " + TABLE_NAME + " SET "+ COLUMN_ROLE + " = ? " + "WHERE " + COLUMN_ID+ " = ?";

        try {
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setString(1, "Immune Health");
            statement.setInt(2, 9);

            statement.executeUpdate();
            System.out.println("Ingredient has been updated successfully!");

        } catch (SQLException e) {
            System.out.println("Role could not be updated: " + e.getMessage());
        }
    }
}
