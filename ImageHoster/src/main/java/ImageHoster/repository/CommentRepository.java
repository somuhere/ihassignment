package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

//The annotation is a special type of @Component annotation which describes that the class defines a data repository
@Repository
public class CommentRepository {

    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method receives the comment object to be persisted in the comments table of the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public Comment addComment(Comment comment) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tr = em.getTransaction();

        try {
            tr.begin();
            em.persist(comment);
            tr.commit();
        }
        catch(Exception e) {
            tr.rollback();
        }

        return comment;
    }
}
