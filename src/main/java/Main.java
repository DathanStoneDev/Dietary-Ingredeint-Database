import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //menu.
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Hello! Please choose an option on the menu by typing the number in.");
        System.out.println("1. Enter a new record into the database");
        System.out.println("2. Get a list of all Ingredient data in the database.");
        System.out.println("3. Get a list of all Manufacturer data in the database.");
        System.out.println("4. Update a manufacturer's phone number");
        System.out.println("5. Update an ingredient's role");
        System.out.println("Press any number above '6' to exit.");
        System.out.println("-----------------------------------------------------");
        int answer = scanner1.nextInt();
        //loops until number above cases is hit.
        while (answer < 7) {
            switch (answer) {
                case 1: addRecord();
                        break;
                case 2: listIngredients();
                        break;
                case 3: listManufacturers();
                        break;
                case 4: manufacturerUpdate();
                        break;
                case 5: ingredientUpdate();
                        break;
            }
            System.out.println("-----------------------------------------------------");
            System.out.println("Please select your next action.");
            System.out.println("1. Enter a new record into the database");
            System.out.println("2. Get a list of all Ingredient data in the database.");
            System.out.println("3. Get a list of all Manufacturer data in the database.");
            System.out.println("4. Update a manufacturer's phone number");
            System.out.println("5. Update an ingredient's role");
            System.out.println("Press any number above '6' to exit.");
            answer = scanner1.nextInt();
        }
    }
    //Adds a record to the database
    public static void addRecord() {
        Connect connect = new Connect();
        connect.getConnection();
        Scanner scanner2 = new Scanner(System.in);
        System.out.println("Please enter your Ingredient name: ");
        String inName = scanner2.nextLine();
        System.out.println("Please enter the Ingredient role: ");
        String role = scanner2.nextLine();
        System.out.println("Please enter the Manufacturer's name: ");
        String manName = scanner2.nextLine();
        System.out.println("Please enter the Manufacturer's country: ");
        String country = scanner2.nextLine();
        System.out.println("Please enter the Manufacturer's phone number: ");
        String phone = scanner2.nextLine();

        connect.insertRecord(inName, role, manName, country, phone);
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
            System.out.println("ID = " + ingredient.getId() + ", Name = " + ingredient.getName() + ", Role = " + ingredient.getRole() + ", ManufacturerID = " + ingredient.getManId());
        }
        connect.close();
    }
    //gets a list off all manufacturers from the database.
    public static void listManufacturers () {
        Connect connect = new Connect();
        connect.getConnection();
        List<Manufacturers> manufacturers = connect.queryManufacturers();
        if(manufacturers == null) {
            System.out.println("No Manufacturers!");
            return;
        }
        for(Manufacturers manufacturer : manufacturers) {
            System.out.println("Name = " + manufacturer.getName() + ", Country = " + manufacturer.getCountry() + ", Phone = " + manufacturer.getPhone() + ", ManufacturerID = " + manufacturer.getId());
        }
        connect.close();
    }
    //updates a manufacturer's phone number
    public static void manufacturerUpdate() {
        Connect connect = new Connect();
        connect.getConnection();
        Scanner scanner4 = new Scanner(System.in);
        System.out.println("Please enter the manufacturer's name that will have their phone number updated");
        String name = scanner4.nextLine();
        System.out.println("Please enter the new phone number");
        String phone = scanner4.nextLine();

        connect.updateManufacturerPhone(phone, name);
        connect.close();
    }
    //updates the role of an ingredient. Will update all ingredients that have the same name.
    public static void ingredientUpdate() {
        Connect connect = new Connect();
        connect.getConnection();
        Scanner scanner5 = new Scanner(System.in);
        System.out.println("Please enter the Ingredient's name that will have their role updated");
        String name = scanner5.nextLine();
        System.out.println("Please enter the new role");
        String role = scanner5.nextLine();

        connect.updateIngredientRole(role, name);
        connect.close();
    }
}
