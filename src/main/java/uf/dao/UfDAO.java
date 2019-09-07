package uf.dao;

import uf.modelo.Uf;
import uf.util.Hibernate;

import javax.persistence.EntityManager;
import java.util.List;

public class UfDAO {

    private static UfDAO instance;
    protected EntityManager entityManager;

    private UfDAO() {
        entityManager = Hibernate.getEntityManager();
    }

    public static UfDAO getInstance() {
        if (instance == null) {
            instance = new UfDAO();
        }

        return instance;
    }

    public Uf getById(final int id) {
        return entityManager.find(Uf.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Uf> findAll() {
        return entityManager.createQuery("FROM " +
                Uf.class.getName()).getResultList();
    }

    public void persist(Uf uf) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(uf);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void merge(Uf uf) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(uf);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(Uf uf) {
        try {
            entityManager.getTransaction().begin();
            uf = entityManager.find(Uf.class, uf.getUuid());
            entityManager.remove(uf);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(final int uuid) {
        try {
            Uf uf = getById(uuid);
            remove(uf);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}