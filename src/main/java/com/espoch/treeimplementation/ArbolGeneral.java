package com.espoch.treeimplementation;

public class ArbolGeneral<L extends Comparable<L>> {

    public Nodo<L> raiz;

    public ArbolGeneral() {
        this.raiz = null;
    }

    public Nodo<L> getRaiz() {
        return raiz;
    }

    public void insertar(L valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo<L> insertarRec(Nodo<L> actual, L valor) {
        if (actual == null)
            return new Nodo<>(valor);

        int comparacion = valor.compareTo(actual.valor);

        if (comparacion < 0) {
            actual.izquierdo = insertarRec(actual.izquierdo, valor);
        } else if (comparacion > 0) {
            actual.derecho = insertarRec(actual.derecho, valor);
        }

        return actual;
    }

    public void eliminar(L valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private Nodo<L> eliminarRec(Nodo<L> actual, L valor) {
        if (actual == null) {
            return null;
        }

        int comparacion = valor.compareTo(actual.valor);

        if (comparacion < 0) {
            actual.izquierdo = eliminarRec(actual.izquierdo, valor);
        } else if (comparacion > 0) {
            actual.derecho = eliminarRec(actual.derecho, valor);
        } else {
            if (actual.izquierdo == null) {
                return actual.derecho;
            } else if (actual.derecho == null) {
                return actual.izquierdo;
            }

            actual.valor = encontrarMin(actual.derecho);
            actual.derecho = eliminarRec(actual.derecho, actual.valor);
        }

        return actual;
    }

    private L encontrarMin(Nodo<L> actual) {
        L min = actual.valor;
        while (actual.izquierdo != null) {
            min = actual.izquierdo.valor;
            actual = actual.izquierdo;
        }
        return min;
    }

    public void mostrar() {
        mostrar(raiz);
    }

    public void mostrar(Nodo<L> actual) {
        if (actual == null)
            return;
        System.out.println(actual.valor);
        mostrar(actual.izquierdo);
        mostrar(actual.derecho);
    }
}
