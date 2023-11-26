package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Roles;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    public boolean addRole(Roles role) {
        entityManager.persist(role);
        return true;
    }

    public Roles findByName(String name) {
        return entityManager.createQuery("select xxx FROM Roles xxx WHERe xxx.role = :id", Roles.class)
                .setParameter("id", name)
                .getResultList().stream().findAny().orElse(null);
    }

    public List<Roles> listByName(List<String> name) {
        return entityManager.createQuery("select xxx FROM Roles xxx WHERe xxx.role in (:id)", Roles.class)
                .setParameter("id", name)
                .getResultList();
    }
}
