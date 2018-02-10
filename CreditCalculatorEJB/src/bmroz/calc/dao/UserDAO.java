package bmroz.calc.dao;

import bmroz.calc.entities.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDAO {

    @PersistenceContext(unitName = "calculatorPU")
    EntityManager entityManager;

    public void create(UserEntity user) {
        entityManager.persist(user);
        // em.flush();
    }

    public UserEntity merge(UserEntity user) {
        return entityManager.merge(user);
    }

    public void remove(UserEntity user) {
        entityManager.remove(entityManager.merge(user));
    }

    public UserEntity find(Object id) {
        return entityManager.find(UserEntity.class, id);
    }

    public List<UserEntity> getUsersListWhereEmail(String email) {
        List<UserEntity> list = null;
        Query query = entityManager.createQuery("select u from UserEntity u where u.email=:email");
        query.setParameter("email", email);
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<UserEntity> getUsersListWhereUsername(String username) {
        List<UserEntity> list = null;
        Query query = entityManager.createQuery("select u from UserEntity u where u.username=:username");
        query.setParameter("username", username);
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<UserEntity> getUsersListWhereEmailAndPassword(String email, String password) {
        List<UserEntity> list = null;
        Query query = entityManager.createQuery("select u from UserEntity u where u.email=:email and u.password=:password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        list = query.getResultList();
        System.out.println(list.size());

        return list;
    }


}
