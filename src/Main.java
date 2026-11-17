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

        // Se utiliza scanner para recibir los datos desde la terminal
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

            // se crea la matriz con las dimensiones que ingresamos
            Laberinto laberinto =
                new Laberinto(filas, columnas);

            // primero se generan las paredes y los pasillos usando la semilla
            new GeneradorLaberinto().generar(
                laberinto, semilla
            );

            // se colocan los tres portales en los espacios libres
            new GestorPortales().colocarTres(
                laberinto, semilla
            );

            // se guarda en una pila el camino que encuentra la función recursiva
            Stack<Posicion> camino =
                new ConstructorCamino().construir(
                    laberinto, semilla
                );

            Visualizador vista = new Visualizador();

            vista.mostrar(laberinto);
            // si la pila queda vacía, podemos decir que no se encontro una salida
            if (camino.empty()) {
                System.out.println("El laberinto no tiene solucion.");
            } else {
                vista.mostrarCamino(camino);
            }

        // se controlan las dimensiones incorrectas para que no se cierre con un error
        } catch (IllegalArgumentException ex) {

            System.err.println(
                "Datos invalidos: " + ex.getMessage()
            );
        }
    }
}
