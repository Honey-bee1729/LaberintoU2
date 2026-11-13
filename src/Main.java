import generacion.GeneradorLaberinto;
import interfaz.Visualizador;
import modelo.Laberinto;
import modelo.Posicion;
import portales.GestorPortales;
import recorrido.ConstructorCamino;

import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        try (Scanner entrada = new Scanner(System.in)) {

            System.out.println(
                "=== LABERINTO RECURSIVO ==="
            );

            System.out.print("Filas (5 a 30): ");
            int filas = entrada.nextInt();

            System.out.print("Columnas (5 a 30): ");
            int columnas = entrada.nextInt();

            System.out.print("Semilla (numero entero): ");
            long semilla = entrada.nextLong();

            Laberinto laberinto =
                new Laberinto(filas, columnas);

            new GeneradorLaberinto().generar(
                laberinto, semilla
            );

            new GestorPortales().colocarTres(
                laberinto, semilla
            );

            Stack<Posicion> camino =
                new ConstructorCamino().construir(
                    laberinto, semilla
                );

            Visualizador vista = new Visualizador();

            vista.mostrar(laberinto);
            vista.mostrarCamino(camino);

            System.out.println(
                "Minimo garantizado: " +
                (filas + columnas - 2 - 3) +
                " movimientos."
            );

        } catch (IllegalArgumentException ex) {

            System.err.println(
                "Datos invalidos: " + ex.getMessage()
            );
        }
    }
}
