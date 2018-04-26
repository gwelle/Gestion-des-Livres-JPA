
package controller;

import controller.exceptions.NonexistentEntityException;
import entities.Livre;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Olga
 */
public class LivreJpaController implements Serializable {
    
    private EntityManagerFactory emf = null;

    /**
     * Construit un Objet Controleur JPA pour l'Objet Livre
     * @param emf 
     */
    public LivreJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    

    /**
     * Retourne l'Entity Manager
     * @return 
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Créer un Livre
     * @param livre 
     */
    public void create(Livre livre) {
        
        System.out.println("LivreJpaController::create()");
        
        EntityManager em = null;
        
        try {
            
            //Récupère l'Entity Manager
            em = getEntityManager();
            
            //Débute la Transaction
            em.getTransaction().begin();
            
            //Persiste l'Ojet Livre dans la base de donnees
            em.persist(livre);
            
            //Valide la Transaction
            em.getTransaction().commit();
        } 
        
        finally {
            
            if (em != null) {
                
                //Fermeture de l'Entity Manager
                em.close();
            }
            
            if(emf != null){
                
                //Fermeture de l'Entity Manager Factory
                emf.close();
            }
        }
    }

    /**
     * Modifier un Objet Livre
     * @param livre
     * @throws NonexistentEntityException
     * @throws Exception 
     */
    public void edit(Livre livre) throws NonexistentEntityException, Exception {
        
        System.out.println("LivreJpaController::edit()");
        
        EntityManager em = null;
        
        try {
            
            //Récupère l'Entity Manager
            em = getEntityManager();
            
            //Débute la Transaction
            em.getTransaction().begin();
            
            //Modification des Données de l'Entité Livre
            livre = em.merge(livre);
            
            //Valide la Transaction
            em.getTransaction().commit();
        } 
        
        catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                
                int id = livre.getIdLivre();
                
                System.out.println(id);
                
                if (findLivre(id) == null) {
                    throw new NonexistentEntityException("The livre with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } 
        
        finally {
            
            if (em != null) {
                
                em.close();
            }
            
            if(emf != null){
                
                emf.close();
            }
        }
    }

    /**
     * 
     * @param id
     * @throws NonexistentEntityException 
     */
    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Livre livre;
            try {
                livre = em.getReference(Livre.class, id);
                livre.getIdLivre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The livre with id " + id + " no longer exists.", enfe);
            }
            em.remove(livre);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
            
            if(emf !=null){
                
                emf.close();
            }
        }
    }

    public List<Livre> findLivreEntities() {
        return findLivreEntities(true, -1, -1);
    }

    public List<Livre> findLivreEntities(int maxResults, int firstResult) {
        return findLivreEntities(false, maxResults, firstResult);
    }

    private List<Livre> findLivreEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Livre.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Trouver un Livre en fonction de son Id
     * @param id
     * @return 
     */
    public Livre findLivre(int id) {
        
        System.out.println("System.out.println::findLivre()");
        
        EntityManager em = getEntityManager();
        
        try {
            
            return em.find(Livre.class, id);
        } 
        
        finally {
            
            em.close();
        }
    }

    public int getLivreCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Livre> rt = cq.from(Livre.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
