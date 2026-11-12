package portales;

import java.util.Random;
import modelo.Laberinto;
import modelo.Posicion;

public class GestorPortales {

    public void colocarTres(
            Laberinto laberinto, long semilla) {

        int d = Math.min(
            laberinto.getFilas(),
            laberinto.getColumnas()
        ) - 1;

        seleccionar(
            laberinto,
            new Random(semilla ^ 0x4F1BBCDCL),
            1, d, 3
        );
    }

    private void seleccionar(
            Laberinto laberinto,
            Random azar,
            int k,
            int limite,
            int pendientes) {

        if (pendientes == 0) {
            return;
        }

        if (k == limite) {
            throw new IllegalStateException(
                "Faltan portales por colocar"
            );
        }

        int candidatos = limite - k;

        boolean elegir =
            azar.nextInt(candidatos) < pendientes;

        if (elegir) {

            Portal portal = new Portal(
                new Posicion(k, k),
                1, 1
            );

            Posicion destino = portal.destino();

            if (!laberinto.esValida(
                    destino.getFila(),
                    destino.getColumna())) {

                throw new IllegalStateException(
                    "Destino de portal invalido"
                );
            }

            laberinto.colocar(k, k, 'P');
        }

        seleccionar(
            laberinto,
            azar,
            k + 1,
            limite,
            pendientes - (elegir ? 1 : 0)
        );
    }
}