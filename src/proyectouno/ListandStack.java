package proyectouno;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.Scanner;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class ListandStack {
    
    Ventana vnt = new Ventana();
    //Stack<Character> pila = new Stack<>();
    String expression = "", reverse = "";
    String operador; 
    
    
    public ListandStack(){
        //pila = null;
       
    }
    
    public void capturarCadena(String expression){
               
        System.out.println(expression);
        for(int i = expression.length() - 1; i >= 0; i--){
            reverse += expression.charAt(i);
        }  
        
        System.out.println(reverse);
    }
    
    
    
    public void evaluarExpression(String expression) {
        // Cola para almacenar operandos y operadores en el orden de ejecución
        Queue<String> cola = new LinkedList<>();
        
        // Pila para almacenar los operadores
        Stack<Character> pilaOperadores = new Stack<>();
        
        // Map para almacenar los valores de las letras (variables)
        Map<Character, Integer> valores = new HashMap<>();
        
        // Leer cada carácter de la expresión
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (Character.isLetter(c)) {
                // Si es una letra, pedir el valor al usuario
                String valor = JOptionPane.showInputDialog("Ingresa el valor para la variable " + c + ":");
                int valorInt = Integer.parseInt(valor);
                valores.put(c, valorInt);
                // Almacenar el valor de la letra en la cola
                cola.add(String.valueOf(valorInt));
            } else if (esOperador(c)) {
                // Si es un operador, manejarlo con la pila
                while (!pilaOperadores.isEmpty() && precedencia(pilaOperadores.peek()) >= precedencia(c)) {
                    // Sacar operadores de la pila a la cola si tienen mayor o igual precedencia
                    cola.add(String.valueOf(pilaOperadores.pop()));
                }
                // Agregar el operador a la pila
                pilaOperadores.push(c);
            }
        }
        
        // Sacar los operadores restantes de la pila a la cola
        while (!pilaOperadores.isEmpty()) {
            cola.add(String.valueOf(pilaOperadores.pop()));
        }
        
        // Mostrar el contenido de la cola (expresión en orden infijo con operadores en su lugar)
        System.out.println("Contenido de la cola (expresión en orden de ejecución):");
        for (String elemento : cola) {
            System.out.print(elemento + " ");
        }
        
        // Mostrar el resultado en la interfaz
        JOptionPane.showMessageDialog(null, "Expresión procesada: " + cola.toString());
    }
    
    // Función para verificar si un carácter es un operador
    public boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    // Función que devuelve la precedencia de los operadores
    public int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1; // Precedencia baja
            case '*':
            case '/':
                return 2; // Precedencia alta
            default:
                return -1; // No es operador
        }
    }
    
    
    // Crear un árbol binario a partir de la cola (expresión en notación postfija)
    public Nodo crearArbolBinario(Queue<String> cola) {
        Stack<Nodo> pilaArbol = new Stack<>();
        
        while (!cola.isEmpty()) {
            String elemento = cola.poll();
            
            if (esNumero(elemento)) {
                Nodo nodo = new Nodo(Integer.parseInt(elemento));
                pilaArbol.push(nodo);
            } else if (esOperador(elemento.charAt(0))) {
                Nodo nodoDerecho = pilaArbol.pop();
                Nodo nodoIzquierdo = pilaArbol.pop();
                Nodo nodoOperador = new Nodo(elemento.charAt(0));
                nodoOperador.izquierda = nodoIzquierdo;
                nodoOperador.derecha = nodoDerecho;
                pilaArbol.push(nodoOperador);
            }
        }
        
        return pilaArbol.pop();
    }
    
    // Función para verificar si un elemento es un número
    public boolean esNumero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    // Recorrido Preorden
    public void preorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            preorden(nodo.izquierda);
            preorden(nodo.derecha);
        }
    }
    
    // Recorrido Postorden
    public void postorden(Nodo nodo) {
        if (nodo != null) {
            postorden(nodo.izquierda);
            postorden(nodo.derecha);
            System.out.print(nodo.valor + " ");
        }
    }
    
}