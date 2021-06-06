import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Connect {

    //Dietary_Ingredients table constants.
    public static final String TABLE_IN = "Dietary_Ingredients";
    public static final String COLUMN_ID_IN = "IngredientID";
    public static final String COLUMN_NAME_IN = "IngredientName";
    public static final String COLUMN_ROLE_IN = "IngredientRole";
    public static final String COLUMN_MAN_IN = "Manufacturer";

    //Manufacturers table constants.
    public static final String TABLE_MF = "Manufacturers";
    public static final String COLUMN_NAME_MF = "ManufacturerName";
    public static final String COLUMN_COUNTRY_MF = "Country";
    public static final String COLUMN_PHONE_NUM_MF = "PhoneNUmber";
    public static final String COLUMN_MAN_ID = "ManID";

    //connection variable.
    private Connection conn;

    //Connects to database.
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
    //closes database
    public Connection close() {
        try {
            if(conn != null) {
                conn.close();
                System.out.println("Connection has been closed.");
            }
        } catch (SQLException e) {
            System.out.println("Cannot close connection: " + e.getMessage());
        } return null;
    }
    //Creates a list of all ingredients in the Ingredients table.
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
    //Creates a list of all manufacturers in the Manufacturers table.
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
    //Takes user input to add an ingredient record.
    public void insertIngredient(String inName, String role, String manName, String country, String phone) {

        String sql1 = "INSERT INTO " + TABLE_IN + " (" + COLUMN_NAME_IN + "," + COLUMN_ROLE_IN + "," + COLUMN_MAN_IN + ")"
                + "VALUES (? , ?, ?)";
        String sql2 = "INSERT INTO " + TABLE_MF + " (" + COLUMN_NAME_MF + "," + COLUMN_COUNTRY_MF + "," + COLUMN_PHONE_NUM_MF +")"
                + "VALUES (? , ?, ?)";
        ResultSet rs = null;
        long manID = 0;
        try(PreparedStatement statement2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {

            statement2.setString(1, manName);
            statement2.setString(2, country);
            statement2.setString(3, phone);
            statement2.executeUpdate();
            rs = statement2.getGeneratedKeys();
            if(rs.next()) {
                manID = rs.getLong(1);
            } else {
                throw new SQLException("Id section was not generated: ");
            }

            System.out.println("record has been added!");

        } catch (SQLException e) {
            System.out.println("Record has not been added: " + e.getMessage());
        }

        try(PreparedStatement statement1 = conn.prepareStatement(sql1)) {

            statement1.setString(1, inName);
            statement1.setString(2, role);
            statement1.setLong(3, manID);
            statement1.executeUpdate();

            System.out.println("Ingredient has been added!");

        } catch (SQLException e) {
            System.out.println("Could not make the insert: " + e.getMessage());
        }
    }
    //takes user input to add a manufacturer
    /*public void insertManufacturer(String name, String country, String phone) {

        String sql = "INSERT INTO " + TABLE_MF + " (" + COLUMN_NAME_MF + "," + COLUMN_COUNTRY_MF + "," + COLUMN_PHONE_NUM_MF + ")"
                + "VALUES (? , ?, ?)";

        try(PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, country);
            statement.setString(3, phone);
            statement.executeUpdate();

            System.out.println("Manufacturer has been added!");

        } catch (SQLException e) {
            System.out.println("Could not make the insert: " + e.getMessage());
        }
    }*/
    //updates a manufacturer's phone number.
    public void updateManufacturerPhone (String phone, String name) {

        String sql = "UPDATE " + TABLE_MF + " SET " + COLUMN_PHONE_NUM_MF + " = ? WHERE " + COLUMN_NAME_MF + " = ?";

        try(PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, phone);
            statement.setString(2, name);
            statement.executeUpdate();

            System.out.println("Update has been executed!");
        } catch (SQLException e) {
            System.out.println("Could not make the update: " + e.getMessage());
        }
    }
    //updates an ingredient's role
    public void updateIngredientRole (String role, String name) {

        String sql = "UPDATE " + TABLE_IN + " SET " + COLUMN_ROLE_IN + " = ? WHERE " + COLUMN_NAME_IN + " = ?";

        try(PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setString(1, role);
            statement.setString(2, name);
            statement.executeUpdate();

            System.out.println("Update has been executed!");
        } catch (SQLException e) {
            System.out.println("Could not make the update: " + e.getMessage());
        }
    }
}
