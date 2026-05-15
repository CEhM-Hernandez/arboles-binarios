package classes;

public class Nodo {
  private Nodo hijoIzquierdo;
  private Nodo hijoDerecho;
  private char dato;
  private int altura;

  public Nodo(char dato) {
    this.dato = dato;
    this.hijoIzquierdo = null;
    this.hijoDerecho = null;
    this.altura = 1;
  }

  public Nodo getHijoIzquierdo() {
    return hijoIzquierdo;
  }

  public void setHijoIzquierdo(Nodo hijoIzquierdo) {
    this.hijoIzquierdo = hijoIzquierdo;
  }

  public Nodo getHijoDerecho() {
    return hijoDerecho;
  }

  public void setHijoDerecho(Nodo hijoDerecho) {
    this.hijoDerecho = hijoDerecho;
  }

  public char getDato() {
    return dato;
  }

  public void setDato(char dato) {
    this.dato = dato;
  }

  public int getAltura() {
    return altura;
  }

  public void setAltura(int altura) {
    this.altura = altura;
  }
}
