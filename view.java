import java.util.Scanner;

public class view {
    public static void main(String[] args) {
        controller controller=new controller();
        System.out.println("press 1 for total");
        try (Scanner input= new Scanner(System.in)) {
            if(input.nextInt()==1)
            {
                System.out.printf("the total number of student is :%d",controller.Totalnumber()); 
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
