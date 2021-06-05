import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //menu.
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Hello! Please choose an option on the menu by typing the number in.");
        System.out.println("1. Enter a new Ingredient into the database.");
        System.out.println("2. Enter a new Manufacturer into the database.");
        System.out.println("3. Get a list of all Ingredients in the database.");
        System.out.println("4. Get a list of all Manufacturers in the database.");
        System.out.println("5. Exit");
        System.out.println("-----------------------------------------------------");
        int answer = scanner1.nextInt();

        switch (answer) {
            case 1: addIngredient();
                break;
            case 2: addManufacturer();
                break;
            case 3: listIngredients();
                break;
            case 4: listManufacturers();
                break;
            default: System.out.println("Invalid Selection.");
        }

    }
    //adds an ingredient to the database.
    public static void addIngredient() {
        Connect connect = new Connect();
        connect.getConnection();
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter your Ingredient name: ");
        String name = scanner2.nextLine();
        System.out.println("Please enter the Ingredient role: ");
        String role = scanner2.nextLine();

        connect.insertIngredient(name, role);
        connect.close();
    }
    //adds a manufacturer to the database.
    public static void addManufacturer() {
        Connect connect = new Connect();
        connect.getConnection();
        Scanner scanner3 = new Scanner(System.in);
        System.out.println("Please enter your Manufacturer's name: ");
        String name = scanner3.nextLine();
        System.out.println("Please enter the Manufacturer's country: ");
        String country = scanner3.nextLine();
        System.out.println("Please enter the Manufacturer's phone number: ");
        String phone = scanner3.nextLine();

        connect.insertManufacturer(name, country, phone);
        connect.close();
    }

    //gets a list of all ingredients from the database.
    public static void listIngredients() {
        Connect connect = new Connect();
        connect.getConnection();
        List<DietaryIngredients> ingredients = connect.queryIngredients();
        if(ingredients == null) {
            System.out.println("No Ingredients!");
            return;
        }
        for(DietaryIngredients ingredient : ingredients) {
            System.out.println("ID = " + ingredient.getId() + ", Name = " + ingredient.getName() + ", Role = " + ingredient.getRole());
        }
        connect.close();
    }
    //gets a list off all manufacturers from the database.
    public static void listManufacturers () {
        Connect connect = new Connect();
        connect.getConnection();
        List<Manufacturers> manufacturers = connect.queryManufacturers();
        if(manufacturers == null) {
            System.out.println("No Ingredients!");
            return;
        }
        for(Manufacturers manufacturer : manufacturers) {
            System.out.println("Name = " + manufacturer.getName() + ", Country = " + manufacturer.getCountry() + ", Phone = " + manufacturer.getPhone());
        }
        connect.close();
    }
}
