package generacion;
import java.util.Random;
import modelo.Laberinto;
public class GeneradorLaberinto {
    public void generar(Laberinto l, long semilla) {
        // La semilla controla dónde aparecen:
        // # Las paredes.
        //. Los pasillos.
        //P Los portales y sus desplazamientos.
        llenar(l, new Random(semilla), 0, 3);
    }
    private void llenar(Laberinto l, Random azar, int i, int reservas) {
        int total = l.getFilas() * l.getColumnas();
        // Caso base: Cuando llego al total de casillas, termina la recursividad.
        if (i == total) return;
        // Se obtiene la fila con la división y la columna con el residuo.
        int f = i / l.getColumnas(), c = i % l.getColumnas();
        // Dejo la primera casilla como inicio y la última como final.
        if (i == 0 || i == total-1) {
            l.colocar(f,c,i == 0 ? 'S' : 'F');
        } else {
            // Se reservan tres espacios libres para asegurar que pueda colocar los portales.
            boolean reservar = azar.nextInt(total-1-i) < reservas;
            l.colocar(f,c,reservar || azar.nextInt(100)<42 ? '.' : '#');
            if (reservar) reservas--;
        }
        // Caso recursivo: En este caso se vuelve a llamar a si mismo
        llenar(l,azar,i+1,reservas);
    }
}
