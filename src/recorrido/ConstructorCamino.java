package recorrido;
import java.util.Stack;
import modelo.Laberinto;
import modelo.Posicion;
import portales.GestorPortales;

public class ConstructorCamino {
    public Stack<Posicion> construir(Laberinto l,long semilla) {
        //pila para guardar el recorrido.
        Stack<Posicion> camino=new Stack<>();
        // calcula la cantidad  de movimientos 
        int mejor=resolver(l,camino,0,0,l.getFilas()*l.getColumnas(),false);
        /// uso de la misma
        if(mejor<l.getFilas()*l.getColumnas()) resolver(l,camino,0,0,mejor+1,true);
        return camino;
    }
    
    // se recorrena las opciones de forma recursiva y se descartan las que no mejoran la longitud y se conserva la longitud minima 
    
    private int resolver(Laberinto l,Stack<Posicion> pila,int f,int c,int mejor,boolean conservar) {
    
        // si hay una pared se detiene, sale de la matriz y se repite la casilla
        if(!l.transitable(f,c) || (l.obtener(f,c)&0x8000)!=0 || pila.size()>=mejor) return mejor;
        
        // se guarda el valor original para restaurarlo cuando termine la llamada
        char original=l.obtener(f,c);
        // La cantidad de posiciones anteriores equivale a los movimientos 
        int longitud=pila.size();
        // agrega  la pocicion actual de la pila
        pila.push(new Posicion(f,c));
        //se marca la visita con el bit alto del carácter para no crear otra matriz.
        l.colocar(f,c,(char)(original|0x8000));
        int resultado=mejor;
        
        // si esta en el final, registra la longitud de recorrido
        if(original=='F') {
            resultado=longitud;
        } else {

            // si se encuentra de un portal, se calcula el destino antes de continuar 
            Posicion destino=l.esPortal(original)?GestorPortales.destino(f,c,original):null;
            
            // si el destino esta dentro del laberinto y no es una pared ocurre el salto 
            if(destino!=null && l.transitable(destino.getFila(),destino.getColumna())) {
                resultado=resolver(l,pila,destino.getFila(),destino.getColumna(),resultado,conservar);
            } else {
                // Si no hay un portal válido, intento abajo, derecha, arriba e izquierda.
                resultado=resolver(l,pila,f+1,c,resultado,conservar);
                // se retira la posición al retroceder.
        if(!conservar || resultado==mejor) resultado=resolver(l,pila,f,c+1,resultado,conservar);
                if(!conservar || resultado==mejor) resultado=resolver(l,pila,f-1,c,resultado,conservar);
                if(!conservar || resultado==mejor) resultado=resolver(l,pila,f,c-1,resultado,conservar);
            }
        }
        
        l.colocar(f,c,original);
        if(!conservar || resultado==mejor) pila.pop();
        return resultado;
    }
}
