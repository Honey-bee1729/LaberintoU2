package portales;

import java.util.Random;
import modelo.Laberinto;
import modelo.Posicion;

public class GestorPortales {

    // Inicia la colocación de 3 portales cambiando la semilla para que no choque con las paredes
    public void colocarTres(Laberinto l, long semilla) {
        seleccionar(l, new Random(semilla ^ 0x4F1BBCDCL), 0, 3, contar(l, 0));
    }

    // Cuenta recursivamente cuántos pasillos '.' hay en todo el laberinto
    private int contar(Laberinto l, int i) {
        if (i == l.getFilas() * l.getColumnas()) return 0;
        return (l.obtener(i / l.getColumnas(), i % l.getColumnas()) == '.' ? 1 : 0) + contar(l, i + 1);
    }

    // Recorre recursivamente el laberinto buscando dónde poner los 3 portales
    private void seleccionar(Laberinto l, Random azar, int i, int pendientes, int libres) {
        
        // Si ya puse los 3 portales o acabé el tablero, salgo
        if (pendientes == 0 || i == l.getFilas() * l.getColumnas()) return;
        
        // Convierte el número de paso 'i' en coordenadas de Fila (f) y Columna (c)
        int f = i / l.getColumnas();
        int c = i % l.getColumnas();
        
        // Si es un pasillo '.', decide por probabilidad si coloca un portal
        if (l.obtener(f, c) == '.') {
            if (azar.nextInt(libres) < pendientes) {
                
                // Elige un código al azar del 0 al 7 (saltándose el 4 para no quedarse en el mismo lugar)
                int codigo = azar.nextInt(8);
                if (codigo >= 4) codigo++; 
                
                // Guarda una letra (a, b, c...) en la matriz que representa el salto del portal
                l.colocar(f, c, (char) ('a' + codigo));
                
              
                pendientes--;
            }
           
            libres--;
        }
        
        // Llama a la siguiente casilla (i + 1) 
        seleccionar(l, azar, i + 1, pendientes, libres);
    }
    // Calcula a qué casilla exacta te manda el portal al pisarlo
    public static Posicion destino(int f, int c, char codigo) {
        int n = codigo - 'a';
        return new Portal(new Posicion(f, c), n / 3 - 1, n % 3 - 1).destino();
    }
}
