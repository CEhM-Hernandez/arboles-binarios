package ui;

import classes.ArbolBinario;
import utils.Utils;

import javax.swing.*;

public class Menu {

  private final ArbolBinario arbol;

  public Menu(ArbolBinario arbol) {
    this.arbol = arbol;
  }

  public void mostrarMenu() {
    int opcion = -1;

    while (opcion != 0) {
      String menu = "========== MENÚ PRINCIPAL ==========\n"
          + "1. Insertar dato\n"
          + "2. Eliminar dato\n"
          + "3. Recorrido InOrden\n"
          + "4. Recorrido PreOrden\n"
          + "5. Recorrido PostOrden\n"
          + "6. Mostrar árbol\n"
          + "7. Contar hojas\n"
          + "8. Contar padres\n"
          + "9. Mostrar hermano de un dato\n"
          + "10. Mostrar nivel de un dato\n"
          + "11. Mostrar altura de un dato\n"
          + "12. Mostrar primos hermanos de un dato\n"
          + "13. Mostrar ancestros de un dato\n"
          + "0. Salir\n"
          + "====================================";

      String opcionStr = JOptionPane.showInputDialog(
          null,
          menu,
          "Menú Principal",
          JOptionPane.QUESTION_MESSAGE
      );

      if (opcionStr == null) {
        opcion = 0;
      } else {
        try {
          opcion = Integer.parseInt(opcionStr);

          switch (opcion) {
            case 1:
              insertarDato();
              break;
            case 2:
              eliminarDato();
              break;
            case 3:
              mostrarRecorridoInorden();
              break;
            case 4:
              mostrarRecorridoPreorden();
              break;
            case 5:
              mostrarRecorridoPostorden();
              break;
            case 6:
              mostrarArbol();
              break;
            case 7:
              contarHojas();
              break;
            case 8:
              contarPadres();
              break;
            case 9:
              mostrarHermano();
              break;
            case 10:
              mostrarNivel();
              break;
            case 11:
              mostrarAltura();
              break;
            case 12:
              mostrarPrimosHermanos();
              break;
            case 13:
              mostrarAncestros();
              break;
            case 0:
              JOptionPane.showMessageDialog(
                  null,
                  "Hasta luego.",
                  "Salida",
                  JOptionPane.INFORMATION_MESSAGE
              );
              break;
            default:
              JOptionPane.showMessageDialog(
                  null,
                  "Opción no válida.",
                  "Error",
                  JOptionPane.WARNING_MESSAGE
              );
              break;
          }
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(
              null,
              "Ingresa un número válido.",
              "Error",
              JOptionPane.ERROR_MESSAGE
          );
        }
      }
    }
  }

  private void insertarDato() {
    char dato = pedirCaracter("Ingresa el dato a insertar:");

    if (dato == '\0') {
      return;
    }

    if (arbol.existeDato(dato)) {
      JOptionPane.showMessageDialog(
          null,
          "El dato '" + dato + "' ya existe en el árbol.",
          "Insertar dato",
          JOptionPane.WARNING_MESSAGE
      );
      return;
    }

    arbol.insertar(dato);

    Utils.mostrarTexto(
        "Dato insertado",
        "Dato insertado correctamente.\n"
            + "El árbol fue balanceado automáticamente.\n\n"
            + arbol.mostrarArbol()
    );
  }

  private void eliminarDato() {
    char dato = pedirCaracter("Ingresa el dato a eliminar:");

    if (dato == '\0') {
      return;
    }

    boolean eliminado = arbol.eliminar(dato);

    if (!eliminado) {
      JOptionPane.showMessageDialog(
          null,
          "El dato '" + dato + "' no existe en el árbol.",
          "Eliminar dato",
          JOptionPane.WARNING_MESSAGE
      );
      return;
    }

    Utils.mostrarTexto(
        "Dato eliminado",
        "Dato eliminado correctamente.\n"
            + "El árbol fue balanceado automáticamente.\n\n"
            + arbol.mostrarArbol()
    );
  }

  private void mostrarRecorridoInorden() {
    Utils.mostrarTexto(
        "Recorrido InOrden",
        "Recorrido InOrden:\n\n" + arbol.recorridoInorden()
    );
  }

  private void mostrarRecorridoPreorden() {
    Utils.mostrarTexto(
        "Recorrido PreOrden",
        "Recorrido PreOrden:\n\n" + arbol.recorridoPreorden()
    );
  }

  private void mostrarRecorridoPostorden() {
    Utils.mostrarTexto(
        "Recorrido PostOrden",
        "Recorrido PostOrden:\n\n" + arbol.recorridoPostorden()
    );
  }

  private void mostrarArbol() {
    Utils.mostrarTexto(
        "Árbol binario",
        arbol.mostrarArbol()
    );
  }

  private void contarHojas() {
    JOptionPane.showMessageDialog(
        null,
        "Cantidad de hojas: " + arbol.contarHojas(),
        "Hojas",
        JOptionPane.INFORMATION_MESSAGE
    );
  }

  private void contarPadres() {
    JOptionPane.showMessageDialog(
        null,
        "Cantidad de padres: " + arbol.contarPadres(),
        "Padres",
        JOptionPane.INFORMATION_MESSAGE
    );
  }

  private void mostrarHermano() {
    char dato = pedirCaracter("Ingresa el dato:");

    if (dato == '\0') {
      return;
    }

    JOptionPane.showMessageDialog(
        null,
        arbol.obtenerHermano(dato),
        "Hermano",
        JOptionPane.INFORMATION_MESSAGE
    );
  }

  private void mostrarNivel() {
    char dato = pedirCaracter("Ingresa el dato:");

    if (dato == '\0') {
      return;
    }

    int nivel = arbol.getNivelDato(dato);

    if (nivel == -1) {
      JOptionPane.showMessageDialog(
          null,
          "El dato '" + dato + "' no existe en el árbol.",
          "Nivel",
          JOptionPane.WARNING_MESSAGE
      );
    } else {
      JOptionPane.showMessageDialog(
          null,
          "Nivel de '" + dato + "': " + nivel + "\n\nNota: la raíz está en el nivel 0.",
          "Nivel",
          JOptionPane.INFORMATION_MESSAGE
      );
    }
  }

  private void mostrarAltura() {
    char dato = pedirCaracter("Ingresa el dato:");

    if (dato == '\0') {
      return;
    }

    int altura = arbol.getAlturaDato(dato);

    if (altura == -1) {
      JOptionPane.showMessageDialog(
          null,
          "El dato '" + dato + "' no existe en el árbol.",
          "Altura",
          JOptionPane.WARNING_MESSAGE
      );
    } else {
      JOptionPane.showMessageDialog(
          null,
          "Altura de '" + dato + "': " + altura + "\n\nNota: una hoja tiene altura 1.",
          "Altura",
          JOptionPane.INFORMATION_MESSAGE
      );
    }
  }

  private void mostrarPrimosHermanos() {
    char dato = pedirCaracter("Ingresa el dato:");

    if (dato == '\0') {
      return;
    }

    JOptionPane.showMessageDialog(
        null,
        arbol.obtenerPrimosHermanos(dato),
        "Primos hermanos",
        JOptionPane.INFORMATION_MESSAGE
    );
  }

  private void mostrarAncestros() {
    char dato = pedirCaracter("Ingresa el dato:");

    if (dato == '\0') {
      return;
    }

    JOptionPane.showMessageDialog(
        null,
        arbol.obtenerAncestros(dato),
        "Ancestros",
        JOptionPane.INFORMATION_MESSAGE
    );
  }

  private char pedirCaracter(String mensaje) {
    String input;

    while (true) {
      input = JOptionPane.showInputDialog(
          null,
          mensaje,
          "Entrada",
          JOptionPane.QUESTION_MESSAGE
      );

      if (input == null) {
        return '\0';
      }

      input = input.trim();

      if (input.length() == 1 && Utils.esLetraValida(input.charAt(0))) {
        return input.charAt(0);
      }

      JOptionPane.showMessageDialog(
          null,
          "Debes ingresar una sola letra de la A a la Z, sin ñ.",
          "Dato inválido",
          JOptionPane.WARNING_MESSAGE
      );
    }
  }
}
