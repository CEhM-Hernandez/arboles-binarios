import classes.ArbolBinario;
import ui.Menu;
import utils.Utils;

import javax.swing.*;

void main() {
  char[] vectorArbol = Utils.userInputToCharArray();

  if (vectorArbol.length == 0) {
    JOptionPane.showMessageDialog(
        null,
        "No se creó ningún árbol.",
        "Proceso cancelado",
        JOptionPane.INFORMATION_MESSAGE
    );
    return;
  }

  ArbolBinario arbol = new ArbolBinario(vectorArbol);

  Utils.mostrarTexto(
      "Árbol creado",
      "Árbol creado correctamente.\n\n"
          + "Recorrido InOrden:\n"
          + arbol.recorridoInorden()
          + "\n\n"
          + arbol.mostrarArbol()
          + "\nAltura del árbol: "
          + arbol.getAlturaArbol()
  );

  Menu menu = new Menu(arbol);
  menu.mostrarMenu();
}
