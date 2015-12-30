package at.hf.stopwatch.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import at.hf.stopwatch.dao.EntityDao;
import at.hf.stopwatch.model.BaseEntity;

public abstract class EntityService<T extends BaseEntity> implements Serializable {

    protected abstract EntityDao<T> getDao();

    @Transactional(TxType.MANDATORY)
    public T save(T entity) {
        if (entity.getId() > 0) {
            return getDao().update(entity);
        }
        return getDao().create(entity);
    }

    @Transactional(TxType.MANDATORY)
    public void delete(T entity) {
        getDao().delete(entity);
    }

    public T findById(int id) {
        return getDao().findById(id);
    }

    public List<T> findAll() {
        return getDao().findAll();
    }

}
