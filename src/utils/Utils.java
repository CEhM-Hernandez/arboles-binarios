package utils;

import javax.swing.*;
import java.awt.*;

public class Utils {

  public static char[] userInputToCharArray() {
    String texto;

    while (true) {
      texto = JOptionPane.showInputDialog(
          null,
          "Ingresa la cadena de caracteres para generar el árbol:",
          "Crear árbol",
          JOptionPane.QUESTION_MESSAGE
      );

      if (texto == null) {
        return new char[0];
      }

      texto = texto.trim();

      if (cadenaValida(texto)) {
        return texto.toCharArray();
      }

      JOptionPane.showMessageDialog(
          null,
          "Error: solo se permiten letras de la A a la Z, sin ñ ni espacios.",
          "Dato inválido",
          JOptionPane.WARNING_MESSAGE
      );
    }
  }

  public static boolean cadenaValida(String texto) {
    if (texto == null || texto.length() == 0) {
      return false;
    }

    for (int i = 0 ; i < texto.length() ; i++) {
      if (!esLetraValida(texto.charAt(i))) {
        return false;
      }
    }

    return true;
  }

  public static boolean esLetraValida(char dato) {
    return (dato >= 'a' && dato <= 'z') || (dato >= 'A' && dato <= 'Z');
  }

  public static void mostrarTexto(String titulo, String texto) {
    JTextArea area = new JTextArea(texto);
    area.setEditable(false);
    area.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
    area.setRows(20);
    area.setColumns(55);

    JScrollPane scroll = new JScrollPane(area);

    JOptionPane.showMessageDialog(
        null,
        scroll,
        titulo,
        JOptionPane.INFORMATION_MESSAGE
    );
  }
}
