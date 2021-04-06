package modelos;

import java.io.Serializable;

/**
 *
 * @author jorge
 */
public class ObjetoGenerico <T> implements Serializable {
   
    private ObjetoGenerico anterior;
    private ObjetoGenerico siguiente;
    private T t;
    
    
    public ObjetoGenerico(T t){
        this.t = t;
    }
    

    public ObjetoGenerico getAnterior() {
        return anterior;
    }

    public void setAnterior(ObjetoGenerico anterior) {
        this.anterior = anterior;
    }

    public ObjetoGenerico getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(ObjetoGenerico siguiente) {
        this.siguiente = siguiente;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
