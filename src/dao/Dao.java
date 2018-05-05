
package dao;

import controller.exceptions.NonexistentEntityException;
import entities.Adherent;
import entities.Emprunter;
import entities.Livre;
import test.GestionJpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PostPersist;
import javax.persistence.Query;


/**
 *
 * @author Olga
 */
public class Dao {
    
        /**
         * Créer une Entité Livre
         * @param titre
         * @param auteur
         * @return 
         */
        public boolean createLivre(String titre, String auteur, String langage){
            
            System.out.println("GestionJpa::createLivre()");
            boolean creation = false ;
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
            EntityManager em = emf.createEntityManager();
            Livre unLivre = new Livre();
        
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            unLivre.setTitreLivre(titre);
            unLivre.setAuteurLivre(auteur);
            unLivre.setLangage(langage);
        
            em.persist(unLivre);
            creation = true ;
            
            tx.commit();
        
            if(em != null){
            
                em.close();
            }
            
            if(emf != null){
                
                emf.close();
            }
        
        return creation ;
        
        }
    
        /**
         * Modifier Une Entite Livre
         * @param idLivre
         * @param titre
         * @param auteur
         * @return 
         */
        public boolean updateLivre(int idLivre, String titre, String auteur){
        
        System.out.println("Modele::updateLivre()");
        boolean modification = false ;
        
        try {

              EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
              EntityManager em = emf.createEntityManager();
              
              Livre unLivre = em.find(Livre.class, idLivre);
              
              EntityTransaction transaction = em.getTransaction();
              transaction.begin();
              
              if(unLivre == null){
                  
                  System.out.println(unLivre+ " Non trouvée");
                  
              }
              
              else{
                  
                  unLivre.setTitreLivre(titre);
                  unLivre.setAuteurLivre(auteur);
                  
                  //Enregistrer les Modifications
                  em.flush();
              }
              
              //Valider la Transaction
              transaction.commit();
              
              //Fermer l'Entity Mananger
              if(em != null){
              
                  em.close();
              }
              //Fermer l'Entity Mananger Factory
              if(emf != null){
                  
                  emf.close();
              }

        } 
        
        catch (Exception ex) {
            
            Logger.getLogger(GestionJpa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return modification ;
        
    }
    
    /**
     * Supprimer une Entité Livre
     * @param idLivre
     * @return 
     */
    public boolean removeLivre(int idLivre){
        
        System.out.println("Modele::removeLivre()");
        boolean suppression = false ;
        
        try {

              EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
              EntityManager em = emf.createEntityManager();
              
              Livre unLivre = em.find(Livre.class, idLivre);
              
              EntityTransaction transaction = em.getTransaction();
              transaction.begin();
              
              if(unLivre == null){
                  
                  System.out.println(unLivre+ " Non trouvée");  
              }
              
              else{
                  
                  //Suppression du Livre
                  em.remove(unLivre);
                  
                  suppression = true ;
              }
              
              //Terminer la Transaction
              transaction.commit();
              
              //Fermer l'Entity Mananger
              if(em != null){
                  
                  em.close();
              }
              
              //Fermer l'Entity Mananger Factory
              if(emf != null){
                  
                  emf.close();
              }  
        } 
        
        catch (Exception ex) {
            
            Logger.getLogger(GestionJpa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return suppression ;
    }  
    
    /**
     * Retourne la Liste des Livres
     * @return 
     */
    public List<Livre> listesDesLivres(){
        
        System.out.println("Modele::listeDesLivres()");
        
        List<Livre> liste = null;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
        EntityManager em = emf.createEntityManager();
        
        String requete = "SELECT * FROM livre order by etatLivre asc";
        Query query = em.createNativeQuery(requete,entities.Livre.class);
        
        //Récupération du Résultat
        liste = query.getResultList();
        
        for(Livre l : liste){
            
            System.out.println(l.toString()+"\n");
        }
        
        if(em != null){
            
            em.close();
        }
        
        if(emf != null){
            
            emf.close();
        }
        
        return liste ;
        
    }

    /**
     * Retourne un Livre(1ère méthode)
     * @param idLivre
     * @return 
     * @throws controller.exceptions.NonexistentEntityException 
     */
    public Livre getLivre(int idLivre) throws NonexistentEntityException{
        
        System.out.println("Modele::getLivre()");
        
        Livre livre = null ;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
        EntityManager em = emf.createEntityManager();
        
        String requete = "SELECT * FROM livre WHERE idLivre =  ?";
        Query query = em.createNativeQuery(requete,entities.Livre.class);
        
        query.setParameter(1, idLivre);
        
        //Récupération du Résultat concerné(Un seul resultat)
        livre = (Livre) query.getSingleResult();

        System.out.println(" Le Livre Rechecher : "+livre.toString());
        
        if(em != null){
            
            em.close();
        }
        
        if(emf != null){
            
            emf.close();
        }
        
        return livre ;
        
    }
    
    /**
     * Rechercher un Livre(2ème méthode)
     * @param idLivre
     * @return 
     */
    public Livre rechercherUnLivre(int idLivre){
        
        System.out.println("Modele::rechercherUnLivre()");
        
        Livre livre = null ;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
        EntityManager em = emf.createEntityManager();
        
        livre = em.getReference(entities.Livre.class, idLivre);
        
        if(em != null){
            
            em.close();
        }
        
        if(emf != null){
            
            emf.close();
        }
        
        System.out.println(" Le Livre que vous Recherchez : "+livre.toString());
        return livre ;

    }
    
//    /**
//     * Créer une Entité Adhérent en fonction d'un Livre
//     * @param nom
//     * @param prenom
//     * @param idLivre
//     * @return 
//     */
//    public boolean createAdherent(String nom, String prenom ,int idLivre){
//            
//        System.out.println("GestionJpa::createAdherent()");
//        boolean creation = false ;
//        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
//        EntityManager em = emf.createEntityManager();
//
//        Adherent unAdherent = new Adherent();
//        Livre unLivre = null ;
//        
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        unLivre = em.getReference(entities.Livre.class, idLivre);
//        
//        unAdherent.setNom(nom);
//        unAdherent.setPrenom(prenom);
//        unAdherent.setIdLivre(unLivre);
//
//        em.persist(unAdherent);
//        creation = true ;
//        System.out.println(unAdherent.toString());
//            
//        tx.commit();
//        
//        if(em != null){
//            
//             em.close();
//        }
//            
//        if(emf != null){
//                
//             emf.close();
//        }
//        
//        return creation ;
//        
//        } 
    
    /**
     * Créer une Entité Adhérent
     * @param nom
     * @param prenom
     * @return 
     */
    public boolean createAdherent(String nom, String prenom){
            
        System.out.println("GestionJpa::createAdherent()");
        boolean creation = false ;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
        EntityManager em = emf.createEntityManager();

        Adherent unAdherent = new Adherent();
        
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        unAdherent.setNom(nom);
        unAdherent.setPrenom(prenom);

        em.persist(unAdherent);
        creation = true ;
        System.out.println(unAdherent.toString());
            
        tx.commit();
        
        if(em != null){
            
             em.close();
        }
            
        if(emf != null){
                
             emf.close();
        }
        
        return creation ;
        
    }
    
    /**
     * Emprunter Un Livre
     * @param idLivre
     * @param idAdherent
     * @return 
     */
    public boolean emprunterUnLivre(int idAdherent, int idLivre){
            
            System.out.println("GestionJpa::emprunterUnLivre()");
            boolean creation = false ;
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
            EntityManager em = emf.createEntityManager();
            Livre unLivre = null;
            Adherent unAdherent = null ;
            Emprunter unEmprunt = new Emprunter();
            
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            
            unLivre = em.getReference(entities.Livre.class, idLivre);
            unAdherent = em.getReference(entities.Adherent.class, idAdherent);
            
            while(!unLivre.getEtatLivre().equalsIgnoreCase("Emprunter")){
	            
            	unEmprunt.setIdLivre(unLivre);
	            unEmprunt.setIdAdherent(unAdherent);
	            
	            unLivre.setEtatLivre("Emprunter");
	
	            em.persist(unEmprunt);
	            
	            creation = true ;
	            
	            this.incrementerNbLivreEmprunter(idAdherent, 
	                    unAdherent.incrementerVariableNbLivreEmprunter());
	            
	            em.flush();
	            
	            tx.commit();
            
            }
        
            if(em != null){
            
                em.close();
            }
            
            if(emf != null){
                
                emf.close();
            }
        
        return creation ;
        
        }
    
    /**
     * Rendre un Livre
     * @param idEmprunt
     * @param idAdherent
     * @param idLivre
     * @return 
     */
    public boolean rendreUnLivre(int idEmprunt,int idAdherent, int idLivre){
            
            System.out.println("GestionJpa::rendreUnLivre()");
            boolean suppression = false ;
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
            EntityManager em = emf.createEntityManager();

            Emprunter unEmprunt = null ;
            Adherent unAdherent = null ;
            Livre unLivre = null ;
            
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            unEmprunt = em.getReference(entities.Emprunter.class, idEmprunt);
            unAdherent = em.getReference(entities.Adherent.class, idAdherent);
            unLivre = em.getReference(entities.Livre.class, idLivre);

            if(unEmprunt == null){
                
                System.out.println(unEmprunt + " Non trouvée");
            }
            
            else{
                
                em.remove(unEmprunt);
                suppression = true ;
                
                unLivre.setEtatLivre("Disponible");
                
                //Décrémente le Nombre de Livres emprunter par un Adhérent lorsqu'il emprunte un Livre
                unAdherent.setNbLivreEmprunter(unAdherent.getNbLivreEmprunter() - 1);
                
//                this.decrementerNbLivreEmprunter(idAdherent, 
//                        unAdherent.decrementerVariableNbLivreEmprunter());
                
                em.flush();
                
                tx.commit();
            }
        
            if(em != null){
            
                em.close();
            }
            
            if(emf != null){
                
                emf.close();
            }
        
        return suppression ;
        
        }
    
    /**
     * Incrémente le nombre de Livres Emprunter par un Adherent
     * @param idAdherent
     * @param nbLivreEmprunter 
     */
    @PostPersist
    public void incrementerNbLivreEmprunter(int idAdherent, int nbLivreEmprunter){
        
        System.out.println("modele::incrementerNbLivreEmprunter()");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
        EntityManager em = emf.createEntityManager();
              
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        
        Adherent unAdherent = new Adherent() ;
        unAdherent  = em.getReference(entities.Adherent.class, idAdherent);
        unAdherent.setNbLivreEmprunter(unAdherent.incrementerVariableNbLivreEmprunter());
                  
        //Enregistrer les Modifications
        em.flush();
     
        //Valider la Transaction
        transaction.commit();
              
        //Fermer l'Entity Mananger
        if(em != null){
              
            em.close();
        }
        
        //Fermer l'Entity Mananger Factory
        if(emf != null){
                  
            emf.close();
        }

    }
    
//    @PreRemove
//    public void decrementerNbLivreEmprunter(int idAdherent, int nbLivreEmprunter){
//        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
//        EntityManager em = emf.createEntityManager();
//              
//        EntityTransaction transaction = em.getTransaction();
//        transaction.begin();
//        
//        Adherent unAdherent = new Adherent() ;
//        unAdherent  = em.getReference(entities.Adherent.class, idAdherent);
//        unAdherent.setNbLivreEmprunter(unAdherent.incrementerVariableNbLivreEmprunter());
//                  
//        //Enregistrer les Modifications
//        em.flush();
//     
//        //Valider la Transaction
//        transaction.commit();
//              
//        //Fermer l'Entity Mananger
//        if(em != null){
//              
//            em.close();
//        }
//        
//        //Fermer l'Entity Mananger Factory
//        if(emf != null){
//                  
//            emf.close();
//        }
//
//    }
    
    /**
     * Retourne la Liste des Livres Emprunter
     * @return 
     */
    public List listeDesLivresEmprunter(){
        
        System.out.println("Modele::listeDesLivresEmprunter()");
        
        List<Emprunter> listeDesLivresEmprunter = null;
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
        EntityManager em = emf.createEntityManager();
              
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createNamedQuery("Emprunter.findAll",entities.Emprunter.class);
        
        //Récupération du Résultat
        listeDesLivresEmprunter = query.getResultList();
        
        for(Emprunter l : listeDesLivresEmprunter){
            
            System.out.println(l.toString()+"\n");
        }
        
        if(em != null){
            
            em.close();
        }
        
        if(emf != null){
            
            emf.close();
        }

        return listeDesLivresEmprunter ;
    }
    
    /**
     * Emprunter Un  ou plusieurs Livres
     * @param idLivre
     * @param idAdherent
     * @return 
     */
    public boolean emprunterDesLivres(int idAdherent, int idLivre){
            
            System.out.println("GestionJpa::emprunterDesLivres()");
            boolean creation = false ;
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("GestionJpaPU");
            EntityManager em = emf.createEntityManager();
            Livre unLivre = null;
            Adherent unAdherent = null ;
            
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            
            unLivre = em.getReference(entities.Livre.class, idLivre);
            unAdherent = em.getReference(entities.Adherent.class, idAdherent);
            
            Set<Livre> lesLivres = new HashSet();
            lesLivres.add(unLivre);
            
	        unLivre.setEtatLivre("Encore Disponible");
	        
	        unAdherent.setLesLivres(lesLivres);
	
	        em.persist(unAdherent);
	            
	        creation = true ;
	        
	        this.incrementerNbLivreEmprunter(idAdherent,unAdherent.incrementerVariableNbLivreEmprunter());
	       
	        em.flush();
	            
	        tx.commit();

            if(em != null){
            
                em.close();
            }
            
            if(emf != null){
                
                emf.close();
            }
        
        return creation ;
        
        }
}