import java.util.List;

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

   }
}
