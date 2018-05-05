
package test;

import controller.exceptions.NonexistentEntityException;
import dao.Dao;

/**
 *
 * @author Olga
 */
public class GestionJpa {
    
    private final Dao modele;
    
    /**
     * Tester les Fonctionnalités
     * @throws NonexistentEntityException 
     */
    public GestionJpa() throws NonexistentEntityException{
        this.modele = new Dao();

//        modele.createLivre("Persistance des Données avec JPA", "Fabien Luciel");
//        modele.createLivre("Symfony2/3", "Fabien Luciel");
//        modele.createLivre("Android Java", "Henricks Drake");
//        modele.createLivre("JavaFX", "Matthieu Rolland");
//        modele.createLivre("Développement avec le Framework .NET", "Tom Hanks");

//        modele.createAdherent("WELLE", "Guillaume");
//        modele.createAdherent("WELLE", "Benjamin");
//        modele.createAdherent("SOBELE", "Victorien");
//        modele.createAdherent("WELLE", "Guillaume");
//        modele.createAdherent("FUREAU", "Guillaume");

        //modele.emprunterUnLivre(1, 1);
        //modele.emprunterUnLivre(2, 1 );
//        modele.emprunterUnLivre(2, 3);
        //modele.rendreUnLivre(9, 1, 1);
        
          //modele.listeDesLivresEmprunter();
        
        //modele.emprunterDesLivres(1, 1);
        //modele.emprunterDesLivres(2, 1);
        //modele.emprunterDesLivres(1, 3);
        modele.emprunterDesLivres(1, 1);

        
        } 

    /**
     * @param args the command line arguments 
     * @throws controller.exceptions.NonexistentEntityException
     */
    public static void main(String[] args) throws NonexistentEntityException {

        new GestionJpa();
        
    }
}