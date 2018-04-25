/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ja09gestionficheros;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mati
 */
public class GestioFitxersImpl {

    protected File carpetaDeTreball = null;
    private int files, columnes;
    private Object[][] contingut;

    public GestioFitxersImpl() {
        carpetaDeTreball = File.listRoots()[0];
        actualitza();
    }

    public GestioFitxersImpl(int col) {
        carpetaDeTreball = File.listRoots()[0];
        this.columnes = col;

        actualitza();
    }

    public void actualitza() {
        String[] fitxers = null;
        fitxers = carpetaDeTreball.list();
        files = fitxers.length / columnes;
        if (files * columnes < fitxers.length) {
            files++;
        }
        contingut = new String[files][columnes];
        for (int i = 0; i < columnes; i++) {
            for (int j = 0; j < files; j++) {
                int ind = j * columnes + i;
                if (ind < fitxers.length) {
                    contingut[j][i] = fitxers[ind];
                } else {
                    contingut[j][i] = "";
                }
            }
        }
    }

    public void mostraContingut() {
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                System.out.print("\t" + contingut[i][j]);
            }
            System.out.print("\n");
        }
    }

    public String getInformacio(String nom) throws GestioFitxersException {
        // ByteFormat ByteFormat= new ByteFormat("#,####.0", ByteFormat.BYTE);
        StringBuilder strBuilder = new StringBuilder();
        File file = new File(carpetaDeTreball, nom);
        if (!file.exists()) {
            throw new GestioFitxersException("Error. No es pot obtenir " + " informacio " + "de " + nom + ", no existeix.");

        }
        if (!file.canRead()) {
            throw new GestioFitxersException("Alerta. No es pot accedir a " + file.getAbsolutePath() + ".No teniu prou permisos");
        }
        strBuilder.append("INFORMACIO DEL SISTEMA");
        strBuilder.append("\n\n");
        strBuilder.append("Nom: ");
        strBuilder.append(nom);
        strBuilder.append("\n");
        strBuilder.append("Tipus: ");
        if (file.isFile()) {
            strBuilder.append("fitxer");
            strBuilder.append("\n");
            strBuilder.append("Mida: ");
            //    strBuilder.append(byteFormat.format(file.length()));
            strBuilder.append("\n");
        } else {
            strBuilder.append("carpeta");
            strBuilder.append("\n");
            strBuilder.append("Contingut: ");
            strBuilder.append(file.list().length);
            strBuilder.append(" entrades\n");
            strBuilder.append("Ubicacion: ");
        }
        try {
            strBuilder.append(file.getCanonicalPath());
        } catch (IOException ex) {
            Logger.getLogger(GestioFitxersImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        strBuilder.append("\n");
        strBuilder.append("Ultima modificacion: ");
        Date date = new Date(file.lastModified());
        strBuilder.append(date.toString());
        strBuilder.append("\n");
        strBuilder.append("Ocult: ");
        strBuilder.append((file.isHidden()) ? "Si" : "No");
        strBuilder.append("\n");
        if (file.isDirectory()) {
            strBuilder.append("Espai lliure: ");
            //    strBuilder.append(byteFormat.format(file.getFreeSpace()));
            strBuilder.append("\n");
            strBuilder.append("Espai disponible");
            //    strBuilder.append(byteFormat.format(file.getUsableSpace()));
            strBuilder.append("\n");
            strBuilder.append("Espai total: ");
            //     strBuilder.append(byteFormat.format(file.getTotalSpace()));
            strBuilder.append("\n");
        }

        return strBuilder.toString();
    }

    public void creaCarpeta(String nomCarpeta) throws GestioFitxersException {
        File file = new File(carpetaDeTreball, nomCarpeta);
        if (!carpetaDeTreball.canWrite()) {
            throw new GestioFitxersException("Error. No s'ha pogut crear " + nomCarpeta + ".No teniu suficients permisos");
        }
        if (file.exists()) {
            throw new GestioFitxersException("Error. No s'ha pogut crear. Ja existeix un fitxer o carpeta amb el nom " + nomCarpeta);
        }

        if (!file.mkdir()) {
            throw new GestioFitxersException("Error. No s'ha pogut crear " + nomCarpeta + ".");
        }
        actualitza();
    }

    public void creaFitxer(String nomFitxer) throws GestioFitxersException {
        File file = new File(carpetaDeTreball, nomFitxer);
        if (!carpetaDeTreball.canWrite()) {
            throw new GestioFitxersException("Error. No s'ha pogut crear " + nomFitxer + " .No teniu suficients permisos");
        }
        if (file.exists()) {
            throw new GestioFitxersException("Error. No sha pogut crear. Ja existeix un fitxer o carpeta amb el nom " + nomFitxer);
        }

        try {
            if (!file.createNewFile()) {
                throw new GestioFitxersException("Error. No s'ha pogut crear " + nomFitxer + ".");

            }
        } catch (IOException ex) {
            throw new GestioFitxersException("S'ha profuit un error d'entrada o sortida: " + ex.getMessage() + ex);
        }
        actualitza();
    }

    public void setAdrecaCarpeta(String adreca) throws GestioFitxersException {
        File file = new File(adreca);
        if (!file.isDirectory()) {
            throw new GestioFitxersException("Error. S'esperaba un directori");

        }
        if (!file.canRead()) {
            throw new GestioFitxersException("Alerta. No es pot accedir a " + file.getAbsolutePath() + ".No teniu prou permisos");
        }
        carpetaDeTreball = file;
        actualitza();
    }

    public void entraA(String nomCarpeta) throws GestioFitxersException {
        File file = new File(carpetaDeTreball, nomCarpeta);
        if (!file.isDirectory()) {
            throw new GestioFitxersException("\n. S'esperava un directori, pero \n" + file.getAbsolutePath() + " no es un directori");
        }
        if (!file.canRead()) {
            throw new GestioFitxersException("Alerta. No es pot accedir a: " + file.getAbsolutePath() + ".No teniu permisos suficients");
        }
        carpetaDeTreball = file;
        actualitza();
    }

    public void elimina(String nom) throws GestioFitxersException {
        File file = new File(carpetaDeTreball, nom);
        if (!carpetaDeTreball.canWrite()) {
            throw new GestioFitxersException("Error. No s'ha pogut eliminar " + nom + " no teniu suficients permisos");

        }
        if (!file.exists()) {
            throw new GestioFitxersException("Error. S'intenta eliminar " + nom + " pero no existeix");
        }
        if (!file.delete()) {
            if (file.isDirectory() && file.list().length > 0) {
                throw new GestioFitxersException("Error. No s'ha pogut elimina. La carpeta " + nom + " no esta buida");
            } else {
                throw new GestioFitxersException("Error. No s'ha pogut eliminar " + nom);
            }
        }
        actualitza();
    }

    public void reanomena(String nom, String nomNou) throws GestioFitxersException {
        File file = new File(carpetaDeTreball, nom);
        File fileNou = new File(carpetaDeTreball, nomNou);
        if (!carpetaDeTreball.canWrite()) {
            throw new GestioFitxersException("Error. No s'ha pogut eliminar " + nom + " no teniu suficients permisos");

        }
        if (!file.exists()) {
            throw new GestioFitxersException("Error. No es pot fer el canvi de nom a " + nom + " perque no existeix");
        }
        if (!file.renameTo(fileNou)) {

            throw new GestioFitxersException("Error. No s'ha pogut canviar de nom a " + nom);

        }
        actualitza();

    }

    
    public void setUltimaModificacio(String nom,long dataIHora) throws GestioFitxersException{
        File file= new File (carpetaDeTreball,nom);
        if(!file.exists()){
            throw new GestioFitxersException("Error. No es pot modificar "+nom+" ,no existeix");
        }
        file.setLastModified(dataIHora);
        System.out.println("Fecha modificada correctamente");
    }
    
    
    
    public void amunt() {
        if (carpetaDeTreball.getParentFile() != null) {
            carpetaDeTreball = carpetaDeTreball.getParentFile();
            actualitza();
        }
    }

    public String getAdrecaCarpeta() {
        return carpetaDeTreball.getAbsolutePath();
    }

    public String getNomCareta() {
        return carpetaDeTreball.getName();
    }

    public int getColumnes() {
        return columnes;
    }

    public void setColumnes(int columnes) {
        this.columnes = columnes;
    }

    public void setCarpetaDeTreball(File carpetaDeTreball) {
        this.carpetaDeTreball = carpetaDeTreball;
    }

}
