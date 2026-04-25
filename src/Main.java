import classes.ArbolBinario;

import static utils.Utils.userInputToCharArray;

void main() {
  Scanner scanner = new Scanner(System.in);
  char[] vectorArbol = userInputToCharArray(scanner);
  ArbolBinario arbol = new ArbolBinario(vectorArbol);

  System.out.println("Árbol binario ingresado en inorden: " + arbol);
}
