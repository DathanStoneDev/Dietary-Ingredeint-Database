import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
       Connect connect = new Connect();
       connect.getConnection();
        //list of all ingredients
       List<DietaryIngredients> ingredients = connect.queryIngredients();
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
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your Ingredient name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter the Ingredient role: ");
        String role = scanner.nextLine();

        connect.insertIngredient(name, role);
   }
}
