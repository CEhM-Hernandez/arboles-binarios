package ui;

import classes.ArbolBinario;
import javax.swing.JOptionPane;

public class Menu {

    private ArbolBinario arbol;

    // -------------- Constructor -------------- //
    public Menu(ArbolBinario arbol) {
        this.arbol = arbol;
    }


    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            String menu = "========== MENÚ PRINCIPAL ==========\n"
                    + "1. Insertar un nodo\n"
                    + "2. Recorrido InOrden\n"
                    + "3. Recorrido PreOrden\n"
                    + "4. Recorrido PostOrden\n"
                    + "5. Mostrar Árbol\n"
                    + "0. Salir\n"
                    + "====================================";

            String opcionStr = JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.QUESTION_MESSAGE);

            if (opcionStr == null) {
                opcion = 0; // Si cierra el diálogo
            } else {
                try {
                    opcion = Integer.parseInt(opcionStr);

                    switch (opcion) {
                        case 1:
                            insertarNodo();
                            break;
                        case 2:
                            recorridoInorden();
                            break;
                        case 3:
                            recorridoPreorden();
                            break;
                        case 4:
                            recorridoPostorden();
                            break;
                        case 5:
                            mostrarArbol();
                            break;
                        case 0:
                            JOptionPane.showMessageDialog(null, "Hasta luego", "Salida", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida. Intenta de nuevo.", "Error", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error: Ingresa un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }


    private void insertarNodo() {
        String input = JOptionPane.showInputDialog(null, "Ingresa un carácter para insertar:", "Insertar Nodo", JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.isEmpty()) {
            if (input.length() == 1) {
                char dato = input.charAt(0);
                arbol.insertar(dato);
                JOptionPane.showMessageDialog(null, "Carácter '" + dato + "' insertado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor ingresa solo un carácter.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Realiza recorrido Inorden del árbol
     */
    private void recorridoInorden() {
        if (arbol.getRaiz() == null) {
            JOptionPane.showMessageDialog(null, "El árbol está vacío.", "Recorrido InOrden", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder resultado = new StringBuilder("Recorrido InOrden:\n");
            capturarRecorridoInorden(arbol.getRaiz(), resultado);
            JOptionPane.showMessageDialog(null, resultado.toString(), "Recorrido InOrden", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Captura el recorrido Inorden en un StringBuilder
     */
    private void capturarRecorridoInorden(classes.Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            capturarRecorridoInorden(nodo.getHijoIzquierdo(), sb);
            sb.append(nodo.getDato());
            capturarRecorridoInorden(nodo.getHijoDerecho(), sb);
        }
    }

    /**
     * Realiza recorrido Preorden del árbol
     */
    private void recorridoPreorden() {
        if (arbol.getRaiz() == null) {
            JOptionPane.showMessageDialog(null, "El árbol está vacío.", "Recorrido PreOrden", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder resultado = new StringBuilder("Recorrido PreOrden:\n");
            capturarRecorridoPreorden(arbol.getRaiz(), resultado);
            JOptionPane.showMessageDialog(null, resultado.toString(), "Recorrido PreOrden", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Captura el recorrido Preorden en un StringBuilder
     */
    private void capturarRecorridoPreorden(classes.Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            capturarRecorridoPreorden(nodo.getHijoIzquierdo(), sb);
            capturarRecorridoPreorden(nodo.getHijoDerecho(), sb);
            sb.append(nodo.getDato());
        }
    }

    /**
     * Realiza recorrido Postorden del árbol
     */
    private void recorridoPostorden() {
        if (arbol.getRaiz() == null) {
            JOptionPane.showMessageDialog(null, "El árbol está vacío.", "Recorrido PostOrden", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder resultado = new StringBuilder("Recorrido PostOrden:\n");
            capturarRecorridoPostorden(arbol.getRaiz(), resultado);
            JOptionPane.showMessageDialog(null, resultado.toString(), "Recorrido PostOrden", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Captura el recorrido Postorden en un StringBuilder
     */
    private void capturarRecorridoPostorden(classes.Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            sb.append(nodo.getDato());
            capturarRecorridoPostorden(nodo.getHijoIzquierdo(), sb);
            capturarRecorridoPostorden(nodo.getHijoDerecho(), sb);
        }
    }

    /**
     * Muestra el árbol en formato inorden
     */
    private void mostrarArbol() {
        String arbolStr = "Árbol (formato InOrden):\n" + arbol;
        JOptionPane.showMessageDialog(null, arbolStr, "Mostrar Árbol", JOptionPane.INFORMATION_MESSAGE);
    }
}
