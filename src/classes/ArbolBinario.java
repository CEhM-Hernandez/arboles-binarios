package classes;

public class ArbolBinario {
  // -------------- Atributos  -------------- //
  private Nodo raiz;

  // -------------- Constructor  -------------- //
  public ArbolBinario() {
    raiz = null;
  }

  /**
   * Este método constructor crea el árbol binario SIN balanceo.
   *
   * @param vectorArbol Es el vector en base al que se va a construir el árbol binario. Se asume que vectorArbol ya está
   *                    validado por userInputToCharArray()
   */
  public ArbolBinario(char[] vectorArbol) {
    for (int i = 0 ; i < vectorArbol.length ; i++) {
      this.insertar(vectorArbol[i]);
    }
  }

  // -------------- Getters & setters  -------------- //
  public Nodo getRaiz() {
    return raiz;
  }

  public void setRaiz(Nodo raiz) {
    this.raiz = raiz;
  }

  // -------------- Métodos  -------------- //

  /**
   * Este método inserta un dato en el árbol en el orden correcto. Este método es una completa fumada, NO TOCAR. xd...
   *
   * @param dato Es el dato que se va a insertar como un nodo en el árbol. Es un char
   */
  public void insertar(char dato) {
    if (this.raiz == null) {
      this.raiz = new Nodo(dato);
      return;
    }

    Nodo actual = this.raiz;
    boolean insertado = false;

    do {
      if (dato == actual.getDato()) {
        System.out.println("El dato ya se encuentra en el árbol. No han habido cambios.");
      } else if (dato < actual.getDato()) {
        if (actual.getHijoIzquierdo() == null) {
          actual.setHijoIzquierdo(new Nodo(dato));
          insertado = true;
        }
        actual = actual.getHijoIzquierdo();
      } else {
        if (actual.getHijoDerecho() == null) {
          actual.setHijoDerecho(new Nodo(dato));
          insertado = true;
        }
        actual = actual.getHijoDerecho();
      }
    } while (!insertado);
  }

  /**
   * Función recursiva que recorre y muestra cada Nodo del árbol binario en el protocolo inorden.
   *
   * @param nodo Es el nodo actual que se evaluará. Al ser una función recursiva, avanza progresivamente llamándose a sí
   *             misma haciendo que este parámetro sea alguno de sus hijos.
   */
  public void recorrerInorden(Nodo nodo) {
    if (nodo != null) {
      recorrerInorden(nodo.getHijoIzquierdo());
      System.out.print(nodo.getDato());
      recorrerInorden(nodo.getHijoDerecho());
    }
  }

  /**
   * Función recursiva que recorre y muestra cada Nodo del árbol binario en el protocolo inorden. (la misma cosa que el
   * primero. xd...)
   *
   * @param nodo Es el nodo actual que se evaluará. Al ser una función recursiva, avanza progresivamente llamándose a sí
   *             misma haciendo que este parámetro sea alguno de sus hijos.
   */
  public void recorrerPreorden(Nodo nodo) {
    if (nodo != null) {
      recorrerPreorden(nodo.getHijoIzquierdo());
      recorrerPreorden(nodo.getHijoDerecho());
      System.out.print(nodo.getDato());
    }
  }

  /**
   * Función recursiva que recorre y muestra cada Nodo del árbol binario en el protocolo inorden. (la misma cosa que los
   * anteriores. xd...)
   *
   * @param nodo Es el nodo actual que se evaluará. Al ser una función recursiva, avanza progresivamente llamándose a sí
   *             misma haciendo que este parámetro sea alguno de sus hijos.
   */
  public void recorrerPosorden(Nodo nodo) {
    if (nodo != null) {
      System.out.print(nodo.getDato());
      recorrerPosorden(nodo.getHijoIzquierdo());
      recorrerPosorden(nodo.getHijoDerecho());
    }
  }

  private void StringBuilderInorden(Nodo nodo, StringBuilder sb) {
    if (nodo != null) {
      StringBuilderInorden(nodo.getHijoIzquierdo(), sb);
      sb.append(nodo.getDato());
      StringBuilderInorden(nodo.getHijoDerecho(), sb);
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    StringBuilderInorden(this.raiz, sb);
    return sb.toString();
  }

}
