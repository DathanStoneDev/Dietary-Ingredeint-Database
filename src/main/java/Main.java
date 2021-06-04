import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
       Connect connect = new Connect();
       connect.getConnection();


        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        //Ask user if they want to add an ingredient
        System.out.println("Would you like to add an ingredient?");
        String answer = scanner1.nextLine();
        boolean yn = (answer.equalsIgnoreCase("yes"));
       if(yn) {
           System.out.println("Please enter your Ingredient name: ");
           String name = scanner2.nextLine();
           System.out.println("Please enter the Ingredient role: ");
           String role = scanner2.nextLine();

           connect.insertIngredient(name, role);
       } else {
           System.out.println("Bye Bye");
       }
        connect.close();
        //list of all ingredients
       /* List<DietaryIngredients> ingredients = connect.queryIngredients();
       if(ingredients == null) {
           System.out.println("No Ingredients!");
           return;
       }
        for(DietaryIngredients ingredient : ingredients) {
            System.out.println("ID = " + ingredient.getId() + ", Name = " + ingredient.getName() + ", Role = " + ingredient.getRole());
        }
        //list of all manufacturers
         List<Manufacturers> manufacturers = connect.queryManufacturers();
        if(manufacturers == null) {
            System.out.println("No Ingredients!");
            return;
        }
        for(Manufacturers manufacturer : manufacturers) {
            System.out.println("Name = " + manufacturer.getName() + ", Country = " + manufacturer.getCountry() + ", Phone = " + manufacturer.getPhone());
        } */

        //prompt if user would like to add a new ingredient
   }
}
