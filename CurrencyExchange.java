import java.util.*;
public class CurrencyExchange
{
  public static void main(String[] args)
  {    
    System.out.println("Please enter the amount of dollars: $ ");
    Scanner a = new Scanner(System.in);
    int a1 = a.nextInt();
    System.out.println("Please enter the exchange rate to japanese yen: ");
    int b1 = a.nextInt();
    System.out.println("You receive  ?¥?" + (a1*b1));
  }
}