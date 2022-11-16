package web.dao;

import web.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(long id) {
        return entityManager.createQuery(
                        "SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void deleteUser(long id) {
        entityManager.createQuery(
                        "DELETE FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void updateUser(User user) {
        entityManager.createQuery(
                        "UPDATE User u " +
                                "SET u.name = :name, " +
                                "u.lastName = :last_name, " +
                                "u.email = :email, " +
                                "u.age = :age " + "WHERE u.id = :id")
                .setParameter("id", user.getId())
                .setParameter("name", user.getName())
                .setParameter("last_name", user.getLastName())
                .setParameter("email", user.getEmail())
                .setParameter("age", user.getAge())
                .executeUpdate();
    }
}
