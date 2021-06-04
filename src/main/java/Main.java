import java.util.List;

public class Main {

    public static void main(String args[]) {
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
   }
}
