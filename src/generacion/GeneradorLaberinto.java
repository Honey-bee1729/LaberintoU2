package generacion;

import java.util.Random;
import modelo.Laberinto;

public class GeneradorLaberinto {

    public void generar(Laberinto laberinto, long semilla) {
        llenar(laberinto, new Random(semilla), 0);
    }

    private void llenar(Laberinto laberinto,
                        Random azar, int indice) {

        int total = laberinto.getFilas()
                  * laberinto.getColumnas();

        if (indice == total) {
            return;
        }

        int fila = indice / laberinto.getColumnas();
        int columna = indice % laberinto.getColumnas();

        laberinto.colocar(
            fila,
            columna,
            azar.nextInt(4) == 0 ? '.' : '#'
        );

        llenar(laberinto, azar, indice + 1);
    }
}