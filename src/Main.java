import classes.ArbolBinario;

import static utils.Utils.userInputToCharArray;

void main() {
  Scanner scanner = new Scanner(System.in);
  char[] vectorArbol = userInputToCharArray(scanner);
  ArbolBinario arbol = new ArbolBinario(vectorArbol);

  System.out.println("Árbol binario ingresado en inorden: " + arbol);

  System.out.print("Mostrar datos con un solo hijo: ");
  arbol.mostrarDatosUnSoloHijo(arbol.getRaiz());
  System.out.println("\ncontar datos con un solo hijo: " + arbol.contarDatosUnSoloHijo(arbol.getRaiz(), 0));

  System.out.println("\nCuantos datos tienen solo hijo derecho?: " + arbol.contarDatosSoloHijoDerecho(arbol.getRaiz(),
      0));
}
