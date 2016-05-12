package at.hf.stopwatch.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import at.hf.stopwatch.model.BaseEntity;

public abstract class EntityDao<T extends BaseEntity> implements Serializable {
    @Inject
    private EntityManager entityManager;

    @Transactional(TxType.MANDATORY)
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional(TxType.MANDATORY)
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Transactional(TxType.MANDATORY)
    public void delete(T entity) {
        T toDelete = findById(entity.getId());
         entityManager.remove(toDelete);
    }

    public T findById(int id) {
        return entityManager.find(getResponseClass(), id);
    }

    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(getResponseClass());
        query.from(getResponseClass());
        return entityManager.createQuery(query).getResultList();
    }

    protected TypedQuery<T> createNamedQuery(String query) {
        return entityManager.createNamedQuery(query, getResponseClass());
    }

    protected <U> TypedQuery<U> createNamedQuery(String query, Class<U> responseClass) {
        return getEntityManager().createNamedQuery(query, responseClass);
    }

    protected <U> U getSingleResult(TypedQuery<U> query) {
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    protected abstract Class<T> getResponseClass();

    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
