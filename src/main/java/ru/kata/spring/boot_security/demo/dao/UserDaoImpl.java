package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Users;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Users findById(Long id) {
        return entityManager.find(Users.class, id);
    }

    @Override
    public List<Users> getAllUsers() {
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u", Users.class);
        return query.getResultList();
    }

    public boolean create(Users user) {
        entityManager.persist(user);
        return true;
    }

    public Users findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM Users u JOIN FETCH u.roles WHERE u.email = :id", Users.class)
                .setParameter("id", email)
                .getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(Users.class, id));
    }

    @Override
    public void updateUser(Users user) {
        entityManager.merge(user);
    }
}
