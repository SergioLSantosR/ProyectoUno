
package proyectouno;


public class Nodo {
    
    
    String valor;
    Nodo izquierda, derecha;
    
    public Nodo(){
//        valor = "";
        izquierda = null;
        derecha = null;
    
    }
    
    Nodo(int valor) {
        this.valor = String.valueOf(valor);
    }
    
    Nodo(char valor) {
        this.valor = String.valueOf(valor);
    }
    
    Nodo(String valor) {
        this.valor = valor;
    }

    
}
