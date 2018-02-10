package bmroz.calc.dao;

import bmroz.calc.entities.PostEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PostDAO {

    @PersistenceContext(unitName = "calculatorPU")
    EntityManager entityManager;

    public void create(PostEntity post) {
        entityManager.persist(post);
        // em.flush();
    }

    public PostEntity merge(PostEntity post) {
        return entityManager.merge(post);
    }

    public void remove(PostEntity post) {
        entityManager.remove(entityManager.merge(post));
    }

    public PostEntity find(Object id) {
        return entityManager.find(PostEntity.class, id);
    }

    public List<PostEntity> getPostsWhereAuthorId(int idAuthor) {
        List<PostEntity> list = null;
        Query query = entityManager.createQuery("select p from PostEntity p where p.userByAuthorId.idUser=:id");
        query.setParameter("id", idAuthor);
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<PostEntity> getPostsWhereOwnerId(int idOwner) {
        List<PostEntity> list = null;
        Query query = entityManager.createQuery("select p from PostEntity p where p.userByOwnerId.idUser=:id");
        query.setParameter("id", idOwner);
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


}
