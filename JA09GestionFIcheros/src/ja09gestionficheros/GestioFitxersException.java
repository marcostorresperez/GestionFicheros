/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja09gestionficheros;

/**
 *
 * @author mati
 */
public class GestioFitxersException extends Exception {
    String m;
    public GestioFitxersException (String mensaje){
        m=mensaje;
    }

    @Override
    public String toString() {
       return m; 
    }
    
}
