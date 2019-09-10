package uf.dao;

import uf.excecoes.PersistenciaExcecao;
import uf.modelo.Uf;
import uf.util.Acao;
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

    public Uf getById(String id) {
        return entityManager.find(Uf.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Uf> findAll() {
        return entityManager.createQuery("FROM " +
                Uf.class.getName()).getResultList();
    }

    public void persist(Uf uf) throws Exception {
        uf.valida(Acao.tipo.INCLUI);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(uf);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new PersistenciaExcecao();
        }
    }

    public void merge(Uf uf) throws Exception {
        uf.valida(Acao.tipo.ALTERA);
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(uf);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new PersistenciaExcecao();
        }
    }

    public void remove(Uf uf) throws Exception {
        uf.valida(Acao.tipo.DELETA);
        try {
            entityManager.getTransaction().begin();
            uf = entityManager.find(Uf.class, uf.getUuid());
            entityManager.remove(uf);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
            throw new PersistenciaExcecao();
        }
    }

    public Uf removeById(String uuid) throws PersistenciaExcecao {
        try {
            Uf uf = getById(uuid);
            remove(uf);
            return uf;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new PersistenciaExcecao();
        }
    }

}