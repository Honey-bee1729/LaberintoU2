import modelo.Posicion;

public class Visualizador {

    public void mostrar(Laberinto laberinto) {
        System.out.println(
            "\nLABERINTO: # pared, . pasillo, P portal, S inicio, F final"
        );

        // empieza a mostrarse la matriz desde la primera casilla 
        imprimir(laberinto, 0);
        System.out.println("\nPORTALES (origen -> destino):");
        mostrarPortales(laberinto, 0);
    }

    private void imprimir(
            Laberinto l, int indice) {

        //se termina de imprimir al llegar al final de la matriz.
        //CASO BASE 
        if (indice ==
            l.getFilas() * l.getColumnas()) {
            return;
        }

        // se convierte el índice en las coordenadas de la casilla.
        int fila = indice / l.getColumnas();
        int columna = indice % l.getColumnas();

        System.out.print(
            l.simbolo(fila, columna) + " "
        );

        if (columna == l.getColumnas() - 1) {
            System.out.println();
        }

        // LLAMADA RECURSIVA
        imprimir(l, indice + 1);
    }

    public void mostrarCamino(
            Stack<Posicion> camino) {

        System.out.println(
            "\nRECORRIDO (incluye los saltos de portales):"
        );

        // Mse muestran posiciones desde la base de la pila hasta el final.
        imprimirCamino(camino, 0);

        // Resta uno porque la posición inicial no cuenta como movimiento.
        System.out.println(
            "\nMovimientos: " + (camino.size() - 1)
        );
    }

    private void imprimirCamino(
            Stack<Posicion> camino,
            int indice) {

        
        //Se detiene cuando ya se mostraron todas las pociciones 
        if (indice == camino.size()) {
            return;
        }

        if (indice > 0) {
            System.out.print(" -> ");
        }

        System.out.print(camino.get(indice));

        // Contina con la siguiente posición sin utilizar ciclos.
        imprimirCamino(camino, indice + 1);
    }
    private void mostrarPortales(Laberinto l, int i) {
        // Recorre la matriz para mostrar el origen y destino de cada portal.
        if (i == l.getFilas() * l.getColumnas()) return;
        int f = i / l.getColumnas(), c = i % l.getColumnas();
        char codigo = l.obtener(f,c);
        if (l.esPortal(codigo)) {
            // Calcula el destino y avisa si el portal no tiene efecto.
            Posicion d = portales.GestorPortales.destino(f,c,codigo);
            System.out.println(new Posicion(f,c) + " -> " + d +
                (l.transitable(d.getFila(),d.getColumna()) ? " (valido)" : " (sin efecto)"));
        }
        mostrarPortales(l,i+1);
    }
}
