/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja09gestionficheros;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
public class Aplicacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String info;
        int numColumnas;
        int opcion = 0;
        System.out.println("Introduce el numero de columnas");
        numColumnas = teclado.nextInt();
        GestioFitxersImpl gf = new GestioFitxersImpl(numColumnas);
        gf.mostraContingut();

        do {
            opcion = verMenu();
            switch (opcion) {
                case 1:
                    System.out.println("1.- Entrar en carpeta");
                    System.out.println("Selecciona la carpeta que quieres ir");
                    String adreca = teclado.next();
                    try {
                        gf.setAdrecaCarpeta(adreca);
                    } catch (GestioFitxersException ex) {
                        Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gf.mostraContingut();

                    break;
                case 2:
                    System.out.println("2.- Ir a carpeta anterior");
                    gf.amunt();
                    gf.mostraContingut();
                    break;
                case 3:
                    System.out.println("3.- Ver informacion detallada del fichero");
                    System.out.println("Introduce la ruta del archivo");
                    teclado.nextLine();
                    adreca = teclado.nextLine();

                    try {
                        info = gf.getInformacio(adreca);
                        System.out.println(info);
                    } catch (GestioFitxersException ex) {
                        Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    break;
                case 4:
                    System.out.println("4.- Ver informacion detallada de carpeta");
                    System.out.println("Indica de la carpeta");
                    adreca = teclado.nextLine();
                    try {
                        info = gf.getInformacio(adreca);
                        System.out.println(info);
                    } catch (GestioFitxersException ex) {
                        Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 5:
                    System.out.println("5.- Crear carpeta");
                    System.out.println("Introduce nombre de la carpeta");
                    teclado.nextLine();
                    adreca = teclado.nextLine();

                    try {
                        gf.creaCarpeta(adreca);
                        System.out.println("Carpeta creada correctamente");
                    } catch (GestioFitxersException ex) {
                        Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                case 6:
                    System.out.println("6.- Crear fichero");
                    System.out.println("Introduce nombre del fichero");
                    teclado.nextLine();
                    adreca = teclado.nextLine();

                    try {
                        gf.creaFitxer(adreca);
                        System.out.println("Fitxer creat correctament");
                    } catch (GestioFitxersException ex) {
                        Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 7:
                    System.out.println("7.- Renombrar fichero");
                    System.out.println("Introduce el fichero a renombrar");
                    teclado.nextLine();
                    adreca = teclado.nextLine();
                    System.out.println("Introduce el nuevo nombre");
                    String novaAdreca = teclado.nextLine();
                     {
                        try {
                            gf.reanomena(adreca, novaAdreca);
                            System.out.println("Renombrado correctamente");
                        } catch (GestioFitxersException ex) {
                            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    break;
                case 8:
                    System.out.println("8.- Cambiar fecha/hora modificacio");
                    System.out.println("Introduce el fichero a modificar");
                    teclado.nextLine();
                    adreca=teclado.nextLine();
                
                 
                    Calendar c= Calendar.getInstance();
                    
                    
            {
                try {
                    gf.setUltimaModificacio(adreca, c.getTimeInMillis());
                    System.out.println("Fecha modificada correctamente");
                } catch (GestioFitxersException ex) {
                    Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                    
                    
                    
                    
                    break;
                case 9:
                    System.out.println("9.- Borrar fichero");
                    System.out.println("Introduce el nombre del fichero");
                    teclado.nextLine();
                    adreca = teclado.nextLine();
                     {
                        try {
                            gf.elimina(adreca);
                            System.out.println("Borrado correctamente");
                        } catch (GestioFitxersException ex) {
                            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    break;
            }

        } while (opcion != 0);
    }

    public static int verMenu() {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduzca una de la siguientes opciones");
        System.out.println("1.- Entrar en carpeta");
        System.out.println("2.- Ir a carpeta anterior");
        System.out.println("3.- Ver informacion detallada del fichero");
        System.out.println("4.- Ver informacion detallada de carpeta");
        System.out.println("5.- Crear carpeta");
        System.out.println("6.- Crear fichero");
        System.out.println("7.- Renombrar fichero");
        System.out.println("8.- Cambiar fecha/hora modificacion");
        System.out.println("9.- Borrar fichero");
        System.out.println("0.- Salir");
        return teclado.nextInt();
    }
}
