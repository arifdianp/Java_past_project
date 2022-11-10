import java.util.Scanner;
public class datacustomer 
{
    public static void main(String args[]) 
    {
        Scanner n = new Scanner(System.in);
        System.out.println("Name: ");
        String name = n.nextLine();
        System.out.println(name);
        n.close();
    }
}