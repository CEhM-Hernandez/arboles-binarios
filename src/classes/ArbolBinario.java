package classes;

public class ArbolBinario {

  private Nodo raiz;

  public ArbolBinario() {
    raiz = null;
  }

  public ArbolBinario(char[] vectorArbol) {
    raiz = null;

    if (vectorArbol != null) {
      for (int i = 0 ; i < vectorArbol.length ; i++) {
        insertar(vectorArbol[i]);
      }
    }
  }

  public Nodo getRaiz() {
    return raiz;
  }

  public void setRaiz(Nodo raiz) {
    this.raiz = raiz;
  }

  // =====================================================
  // INSERTAR CON BALANCEO AVL
  // =====================================================

  public void insertar(char dato) {
    raiz = insertarAVL(raiz, dato);
  }

  private Nodo insertarAVL(Nodo nodo, char dato) {
    if (nodo == null) {
      return new Nodo(dato);
    }

    if (dato < nodo.getDato()) {
      nodo.setHijoIzquierdo(insertarAVL(nodo.getHijoIzquierdo(), dato));
    } else if (dato > nodo.getDato()) {
      nodo.setHijoDerecho(insertarAVL(nodo.getHijoDerecho(), dato));
    } else {
      return nodo;
    }

    return balancear(nodo);
  }

  // =====================================================
  // ELIMINAR CON BALANCEO AVL
  // =====================================================

  public boolean eliminar(char dato) {
    if (!existeDato(dato)) {
      return false;
    }

    raiz = eliminarAVL(raiz, dato);
    return true;
  }

  private Nodo eliminarAVL(Nodo nodo, char dato) {
    if (nodo == null) {
      return null;
    }

    if (dato < nodo.getDato()) {
      nodo.setHijoIzquierdo(eliminarAVL(nodo.getHijoIzquierdo(), dato));
    } else if (dato > nodo.getDato()) {
      nodo.setHijoDerecho(eliminarAVL(nodo.getHijoDerecho(), dato));
    } else {
      if (nodo.getHijoIzquierdo() == null || nodo.getHijoDerecho() == null) {
        Nodo reemplazo;

        if (nodo.getHijoIzquierdo() != null) {
          reemplazo = nodo.getHijoIzquierdo();
        } else {
          reemplazo = nodo.getHijoDerecho();
        }

        if (reemplazo == null) {
          return null;
        } else {
          nodo = reemplazo;
        }
      } else {
        Nodo menor = obtenerMenor(nodo.getHijoDerecho());
        nodo.setDato(menor.getDato());
        nodo.setHijoDerecho(eliminarAVL(nodo.getHijoDerecho(), menor.getDato()));
      }
    }

    return balancear(nodo);
  }

  private Nodo obtenerMenor(Nodo nodo) {
    Nodo actual = nodo;

    while (actual.getHijoIzquierdo() != null) {
      actual = actual.getHijoIzquierdo();
    }

    return actual;
  }

  // =====================================================
  // BALANCEO AVL
  // Factor de balance = altura izquierda - altura derecha
  // =====================================================

  private Nodo balancear(Nodo nodo) {
    if (nodo == null) {
      return null;
    }

    actualizarAltura(nodo);

    int factorBalance = getFactorBalance(nodo);

    if (factorBalance > 1) {
      if (getFactorBalance(nodo.getHijoIzquierdo()) < 0) {
        nodo.setHijoIzquierdo(rotarIzquierda(nodo.getHijoIzquierdo()));
      }

      return rotarDerecha(nodo);
    }

    if (factorBalance < -1) {
      if (getFactorBalance(nodo.getHijoDerecho()) > 0) {
        nodo.setHijoDerecho(rotarDerecha(nodo.getHijoDerecho()));
      }

      return rotarIzquierda(nodo);
    }

    return nodo;
  }

  private Nodo rotarDerecha(Nodo nodo) {
    Nodo nuevaRaiz = nodo.getHijoIzquierdo();
    Nodo temporal = nuevaRaiz.getHijoDerecho();

    nuevaRaiz.setHijoDerecho(nodo);
    nodo.setHijoIzquierdo(temporal);

    actualizarAltura(nodo);
    actualizarAltura(nuevaRaiz);

    return nuevaRaiz;
  }

  private Nodo rotarIzquierda(Nodo nodo) {
    Nodo nuevaRaiz = nodo.getHijoDerecho();
    Nodo temporal = nuevaRaiz.getHijoIzquierdo();

    nuevaRaiz.setHijoIzquierdo(nodo);
    nodo.setHijoDerecho(temporal);

    actualizarAltura(nodo);
    actualizarAltura(nuevaRaiz);

    return nuevaRaiz;
  }

  private int getFactorBalance(Nodo nodo) {
    if (nodo == null) {
      return 0;
    }

    return getAlturaNodo(nodo.getHijoIzquierdo()) - getAlturaNodo(nodo.getHijoDerecho());
  }

  private void actualizarAltura(Nodo nodo) {
    if (nodo == null) {
      return;
    }

    int alturaIzquierda = getAlturaNodo(nodo.getHijoIzquierdo());
    int alturaDerecha = getAlturaNodo(nodo.getHijoDerecho());

    if (alturaIzquierda > alturaDerecha) {
      nodo.setAltura(alturaIzquierda + 1);
    } else {
      nodo.setAltura(alturaDerecha + 1);
    }
  }

  private int getAlturaNodo(Nodo nodo) {
    if (nodo == null) {
      return 0;
    }

    return nodo.getAltura();
  }

  public int getAlturaArbol() {
    return getAlturaNodo(raiz);
  }

  public int getAlturaDato(char dato) {
    Nodo nodo = buscarNodo(dato);

    if (nodo == null) {
      return -1;
    }

    return getAlturaNodo(nodo);
  }

  // =====================================================
  // RECORRIDOS
  // =====================================================

  public String recorridoInorden() {
    StringBuilder sb = new StringBuilder();
    recorridoInorden(raiz, sb);
    return formatearRecorrido(sb);
  }

  private void recorridoInorden(Nodo nodo, StringBuilder sb) {
    if (nodo != null) {
      recorridoInorden(nodo.getHijoIzquierdo(), sb);
      sb.append(nodo.getDato()).append(" ");
      recorridoInorden(nodo.getHijoDerecho(), sb);
    }
  }

  public String recorridoPreorden() {
    StringBuilder sb = new StringBuilder();
    recorridoPreorden(raiz, sb);
    return formatearRecorrido(sb);
  }

  private void recorridoPreorden(Nodo nodo, StringBuilder sb) {
    if (nodo != null) {
      sb.append(nodo.getDato()).append(" ");
      recorridoPreorden(nodo.getHijoIzquierdo(), sb);
      recorridoPreorden(nodo.getHijoDerecho(), sb);
    }
  }

  public String recorridoPostorden() {
    StringBuilder sb = new StringBuilder();
    recorridoPostorden(raiz, sb);
    return formatearRecorrido(sb);
  }

  private void recorridoPostorden(Nodo nodo, StringBuilder sb) {
    if (nodo != null) {
      recorridoPostorden(nodo.getHijoIzquierdo(), sb);
      recorridoPostorden(nodo.getHijoDerecho(), sb);
      sb.append(nodo.getDato()).append(" ");
    }
  }

  private String formatearRecorrido(StringBuilder sb) {
    if (sb.length() == 0) {
      return "El árbol está vacío.";
    }

    return sb.toString().trim();
  }

  // =====================================================
  // MOSTRAR ÁRBOL GRÁFICO
  // =====================================================

  public String mostrarArbol() {
    if (raiz == null) {
      return "El árbol está vacío.";
    }

    StringBuilder sb = new StringBuilder();

    sb.append("Árbol binario balanceado:\n\n");
    sb.append("└── Raíz: ").append(raiz.getDato()).append("\n");

    construirHijos(raiz, "    ", sb);

    return sb.toString();
  }

  private void construirHijos(Nodo nodo, String prefijo, StringBuilder sb) {
    if (nodo == null) {
      return;
    }

    Nodo izquierdo = nodo.getHijoIzquierdo();
    Nodo derecho = nodo.getHijoDerecho();

    if (izquierdo != null && derecho != null) {
      sb.append(prefijo).append("├── Izq: ").append(izquierdo.getDato()).append("\n");
      construirHijos(izquierdo, prefijo + "│   ", sb);

      sb.append(prefijo).append("└── Der: ").append(derecho.getDato()).append("\n");
      construirHijos(derecho, prefijo + "    ", sb);
    } else if (izquierdo != null) {
      sb.append(prefijo).append("└── Izq: ").append(izquierdo.getDato()).append("\n");
      construirHijos(izquierdo, prefijo + "    ", sb);
    } else if (derecho != null) {
      sb.append(prefijo).append("└── Der: ").append(derecho.getDato()).append("\n");
      construirHijos(derecho, prefijo + "    ", sb);
    }
  }

  // =====================================================
  // CONTADORES
  // =====================================================

  public int contarHojas() {
    return contarHojas(raiz);
  }

  private int contarHojas(Nodo nodo) {
    if (nodo == null) {
      return 0;
    }

    if (nodo.getHijoIzquierdo() == null && nodo.getHijoDerecho() == null) {
      return 1;
    }

    return contarHojas(nodo.getHijoIzquierdo()) + contarHojas(nodo.getHijoDerecho());
  }

  public int contarPadres() {
    return contarPadres(raiz);
  }

  private int contarPadres(Nodo nodo) {
    if (nodo == null) {
      return 0;
    }

    int contador = 0;

    if (nodo.getHijoIzquierdo() != null || nodo.getHijoDerecho() != null) {
      contador = 1;
    }

    return contador + contarPadres(nodo.getHijoIzquierdo()) + contarPadres(nodo.getHijoDerecho());
  }

  // =====================================================
  // BÚSQUEDA
  // =====================================================

  public boolean existeDato(char dato) {
    return buscarNodo(dato) != null;
  }

  public Nodo buscarNodo(char dato) {
    Nodo actual = raiz;

    while (actual != null) {
      if (dato == actual.getDato()) {
        return actual;
      } else if (dato < actual.getDato()) {
        actual = actual.getHijoIzquierdo();
      } else {
        actual = actual.getHijoDerecho();
      }
    }

    return null;
  }

  private Nodo buscarPadre(char dato) {
    Nodo actual = raiz;
    Nodo padre = null;

    while (actual != null) {
      if (dato == actual.getDato()) {
        return padre;
      }

      padre = actual;

      if (dato < actual.getDato()) {
        actual = actual.getHijoIzquierdo();
      } else {
        actual = actual.getHijoDerecho();
      }
    }

    return null;
  }

  // =====================================================
  // HERMANO
  // =====================================================

  public String obtenerHermano(char dato) {
    if (!existeDato(dato)) {
      return "El dato '" + dato + "' no existe en el árbol.";
    }

    Nodo padre = buscarPadre(dato);

    if (padre == null) {
      return "El dato '" + dato + "' es la raíz y no tiene hermano.";
    }

    Nodo hermano;

    if (padre.getHijoIzquierdo() != null && padre.getHijoIzquierdo().getDato() == dato) {
      hermano = padre.getHijoDerecho();
    } else {
      hermano = padre.getHijoIzquierdo();
    }

    if (hermano == null) {
      return "El dato '" + dato + "' no tiene hermano.";
    }

    return "El hermano de '" + dato + "' es: " + hermano.getDato();
  }

  // =====================================================
  // NIVEL
  // Raíz = nivel 0
  // =====================================================

  public int getNivelDato(char dato) {
    Nodo actual = raiz;
    int nivel = 0;

    while (actual != null) {
      if (dato == actual.getDato()) {
        return nivel;
      } else if (dato < actual.getDato()) {
        actual = actual.getHijoIzquierdo();
      } else {
        actual = actual.getHijoDerecho();
      }

      nivel++;
    }

    return -1;
  }

  // =====================================================
  // PRIMOS HERMANOS
  // Hijos del tío del nodo
  // =====================================================

  public String obtenerPrimosHermanos(char dato) {
    if (!existeDato(dato)) {
      return "El dato '" + dato + "' no existe en el árbol.";
    }

    Nodo padre = buscarPadre(dato);

    if (padre == null) {
      return "La raíz no tiene primos hermanos.";
    }

    Nodo abuelo = buscarPadre(padre.getDato());

    if (abuelo == null) {
      return "El dato '" + dato + "' no tiene primos hermanos.";
    }

    Nodo tio;

    if (abuelo.getHijoIzquierdo() == padre) {
      tio = abuelo.getHijoDerecho();
    } else {
      tio = abuelo.getHijoIzquierdo();
    }

    if (tio == null) {
      return "El dato '" + dato + "' no tiene primos hermanos.";
    }

    StringBuilder sb = new StringBuilder();

    if (tio.getHijoIzquierdo() != null) {
      sb.append(tio.getHijoIzquierdo().getDato()).append(" ");
    }

    if (tio.getHijoDerecho() != null) {
      sb.append(tio.getHijoDerecho().getDato()).append(" ");
    }

    if (sb.length() == 0) {
      return "El dato '" + dato + "' no tiene primos hermanos.";
    }

    return "Primos hermanos de '" + dato + "': " + sb.toString().trim();
  }

  // =====================================================
  // ANCESTROS
  // =====================================================

  public String obtenerAncestros(char dato) {
    if (!existeDato(dato)) {
      return "El dato '" + dato + "' no existe en el árbol.";
    }

    if (raiz != null && raiz.getDato() == dato) {
      return "La raíz no tiene ancestros.";
    }

    Nodo actual = raiz;
    StringBuilder sb = new StringBuilder();

    while (actual != null && actual.getDato() != dato) {
      sb.append(actual.getDato()).append(" ");

      if (dato < actual.getDato()) {
        actual = actual.getHijoIzquierdo();
      } else {
        actual = actual.getHijoDerecho();
      }
    }

    return "Ancestros de '" + dato + "': " + sb.toString().trim();
  }

  @Override
  public String toString() {
    return recorridoInorden();
  }

  // =====================================================
  // Quiz - Contar y mostrar los descendentes de un dato
  // =====================================================

  public int mostrarDescendentes(Nodo nodo, int count, char DatoInicial) {
    if (nodo != null) {
      count = mostrarDescendentes(nodo.getHijoIzquierdo(), count, DatoInicial) + mostrarDescendentes(nodo.getHijoDerecho(),
          count, DatoInicial);
      if (nodo.getDato() != DatoInicial) {
        System.out.println(nodo.getDato());
        count++;
      }
    }
    return count;
  }
}
