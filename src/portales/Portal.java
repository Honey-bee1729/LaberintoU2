package portales;

import modelo.Posicion;

public class Portal {
    private final Posicion origen;
    private final int desplazamientoFila;
    private final int desplazamientoColumna;

    public Portal(
            Posicion origen,
            int desplazamientoFila,
            int desplazamientoColumna) {

        // Guardo posición del portal y cuánto se mueve en cada dirección.
        this.origen = origen;
        this.desplazamientoFila = desplazamientoFila;
        this.desplazamientoColumna = desplazamientoColumna;
    }

    public Posicion getOrigen() {
        return origen;
    }

    public Posicion destino() {
        // Sumo los desplazamientos al origen para obtener el destino (px+x, py+y).
        return new Posicion(
            origen.getFila() + desplazamientoFila,
            origen.getColumna() + desplazamientoColumna
        );
    }
}
