import java.util.Scanner;

public class Conversor {

  public static void main(String[] args) {
    long num, aux, cifra, decimal;
     int expo;
     boolean isBinary;
     Scanner keyboard = new Scanner(System.in);

     do {
          System.out.print("Introduce un numero en sistema binario: ");
          num = keyboard.nextLong();
          isBinary = true;
          aux = num;
          while (aux != 0) {
                     cifra = aux % 10;
                     if (cifra != 0 && cifra != 1) {
                          isBinary = false; 
                     }
                     aux = aux / 10;                       
           }
      } while (!isBinary); 
      expo = 0;
      decimal = 0; 
      while (num != 0) {
                cifra = num % 10;                       
                decimal = decimal + cifra * (int) Math.pow(2, expo);
                expo++;
                num = num / 10;
      }
      System.out.println("Decimal: " + decimal);
   }
}
