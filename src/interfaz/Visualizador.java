package interfaz;

import java.util.Stack;
import modelo.Laberinto;
import modelo.Posicion;

public class Visualizador {

    public void mostrar(Laberinto laberinto) {
        System.out.println(
            "\nLABERINTO: # pared, . pasillo, P portal"
        );

        imprimir(laberinto, 0);
    }

    private void imprimir(
            Laberinto l, int indice) {

        if (indice ==
            l.getFilas() * l.getColumnas()) {
            return;
        }

        int fila = indice / l.getColumnas();
        int columna = indice % l.getColumnas();

        System.out.print(
            l.obtener(fila, columna) + " "
        );

        if (columna == l.getColumnas() - 1) {
            System.out.println();
        }

        imprimir(l, indice + 1);
    }

    public void mostrarCamino(
            Stack<Posicion> camino) {

        System.out.println(
            "\nRECORRIDO (incluye los saltos de portales):"
        );

        imprimirCamino(camino, 0);

        System.out.println(
            "\nMovimientos: " + (camino.size() - 1)
        );
    }

    private void imprimirCamino(
            Stack<Posicion> camino,
            int indice) {

        if (indice == camino.size()) {
            return;
        }

        if (indice > 0) {
            System.out.print(" -> ");
        }

        System.out.print(camino.get(indice));

        imprimirCamino(camino, indice + 1);
    }
}

