package proyectouno;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;
import java.util.Scanner;
import java.util.Set;


public class ListandStack {
    
    Ventana vnt = new Ventana();
    Stack<Character> pila = new Stack<>();
    String expression = "", reverse = "";
    String operador;
    
    
    
    public ListandStack(){
        pila = null;
       
    }
    
    public void capturarCadena(String expression){
               
        System.out.println(expression);
        for(int i = expression.length() - 1; i >= 0; i--){
            reverse += expression.charAt(i);
        }  
        
        System.out.println(reverse);
    }
    
    
        
}