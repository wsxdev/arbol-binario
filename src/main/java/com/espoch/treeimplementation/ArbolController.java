package com.espoch.treeimplementation;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ArbolController {

    @FXML
    private TextField txtValor;

    @FXML
    private Canvas canvas;

    @FXML
    private ScrollPane scrollPane;

    private ArbolGeneral<Integer> arbol;
    private static final double NODE_RADIUS = 20;
    private static final double VERTICAL_GAP = 60;

    public void initialize() {
        arbol = new ArbolGeneral<>();
        limpiarCanvas();
    }

    @FXML
    void onAgregar() {
        try {
            int valor = Integer.parseInt(txtValor.getText());
            arbol.insertar(valor);
            txtValor.clear();
            dibujarArbol();
        } catch (NumberFormatException e) {
        }
    }

    @FXML
    void onEliminar() {
        try {
            int valor = Integer.parseInt(txtValor.getText());
            arbol.eliminar(valor);
            txtValor.clear();
            dibujarArbol();
        } catch (NumberFormatException e) {
        }
    }

    @FXML
    void onLimpiar() {
        arbol = new ArbolGeneral<>();
        limpiarCanvas();
    }

    private void limpiarCanvas() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void dibujarArbol() {
        limpiarCanvas();
        if (arbol.getRaiz() != null) {
            dibujarNodo(arbol.getRaiz(), canvas.getWidth() / 2, 50, canvas.getWidth() / 4);
        }
    }

    private void dibujarNodo(Nodo<Integer> nodo, double x, double y, double hGap) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        if (nodo.izquierdo != null) {
            gc.setStroke(Color.web("#34495e"));
            gc.setLineWidth(2);
            gc.strokeLine(x, y, x - hGap, y + VERTICAL_GAP);
            dibujarNodo(nodo.izquierdo, x - hGap, y + VERTICAL_GAP, hGap / 2);
        }

        if (nodo.derecho != null) {
            gc.setStroke(Color.web("#34495e"));
            gc.setLineWidth(2);
            gc.strokeLine(x, y, x + hGap, y + VERTICAL_GAP);
            dibujarNodo(nodo.derecho, x + hGap, y + VERTICAL_GAP, hGap / 2);
        }

        gc.setFill(Color.web("#3498db"));
        gc.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

        gc.setStroke(Color.web("#2980b9"));
        gc.setLineWidth(2);
        gc.strokeOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

        gc.setFill(Color.WHITE);
        gc.setFont(new Font("System Bold", 14));
        String texto = String.valueOf(nodo.valor);

        double textWidth = gc.getFont().getSize() * 0.6 * texto.length();
        gc.fillText(texto, x - (textWidth / 2) + 2, y + 5);
    }
}
