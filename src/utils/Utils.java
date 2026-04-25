package utils;

import java.util.Scanner;
public class Utils {
  public static char[] userInputToCharArray(Scanner scanner) {
    System.out.print("Ingresa la cadena de carácteres para generar el árbol: ");
    char[] userInputArray = scanner.next().trim().toCharArray();
    //TODO: validar userInputArray para que SOLO contenga letras ([a-z][A-Z]). Sin ñ o Ñ porque los gringos son
    // fastidiosos y el ascii pone esas letras en un numero distinto a la posición de la ñ en nuestro abecedario.
    return userInputArray;
  }
}
