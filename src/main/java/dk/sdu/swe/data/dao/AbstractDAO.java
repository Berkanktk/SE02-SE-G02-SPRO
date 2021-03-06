package dk.sdu.swe.data.dao;

import dk.sdu.swe.data.DB;
import dk.sdu.swe.domain.persistence.IDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T> implements IDAO<T> {

    private Class<T> type;

    public AbstractDAO(Class<T> tClass) {
        this.type = tClass;
    }

    @Override
    public List<T> getAll() {
        Session session = DB.openSession();

        List<T> res = null;

        Transaction trans = session.beginTransaction();
        try {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
            Root<T> root = criteriaQuery.from(type);
            criteriaQuery.select(root);

            res = session.createQuery(criteriaQuery).getResultList();

        } finally {
            trans.commit();
            session.close();
        }

        return res;
    }

    @Override
    public Optional<T> getById(Long obj) {
        Session session = DB.openSession();

        T res = null;

        Transaction trans = session.beginTransaction();
        try {

            res = session.get(type, obj);

        } finally {
            trans.commit();
            session.close();
        }

        return Optional.ofNullable(res);
    }

    @Override
    public void save(T obj) {
        Session session = DB.openSession();
        Transaction trans = session.beginTransaction();
        try {
            session.save(obj);
        } finally {
            trans.commit();
            session.close();
        }
    }

    @Override
    public void update(T obj) {
        Session session = DB.openSession();
        Transaction trans = session.beginTransaction();
        try {
            session.update(obj);
        } finally {
            trans.commit();
            session.close();
        }
    }

    @Override
    public void delete(T obj) {
        Session session = DB.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(obj);
        } finally {
            transaction.commit();
            session.close();
        }
    }
}
