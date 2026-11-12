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

        this.origen = origen;
        this.desplazamientoFila = desplazamientoFila;
        this.desplazamientoColumna = desplazamientoColumna;
    }

    public Posicion getOrigen() {
        return origen;
    }

    public Posicion destino() {
        return new Posicion(
            origen.getFila() + desplazamientoFila,
            origen.getColumna() + desplazamientoColumna
        );
    }
}