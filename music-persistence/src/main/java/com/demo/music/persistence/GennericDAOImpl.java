package com.demo.music.persistence;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.demo.music.model.ServiceDAOGenneric;

public class GennericDAOImpl<T> implements ServiceDAOGenneric<T> {
	@PersistenceUnit(unitName = "managed-jpa")
	private EntityManagerFactory m_factory;
	private EntityManager m_entityManager;
	private Class<T> m_type;

	public GennericDAOImpl() {
		init();
	}

	private void loadEntityManager() {
		if (m_entityManager == null)
			m_entityManager = m_factory.createEntityManager();
	}

	@SuppressWarnings("unchecked")
	private void init() {
		try {
			Type type = getClass().getGenericSuperclass();

			while (!(type instanceof ParameterizedType)
					|| ((ParameterizedType) type).getRawType() != GennericDAOImpl.class) {
				if (type instanceof ParameterizedType) {
					type = ((Class<?>) ((ParameterizedType) type).getRawType()).getGenericSuperclass();
				} else {
					type = ((Class<?>) type).getGenericSuperclass();
				}
			}
			this.m_type = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
		} catch (Exception e) {
			System.out.println("NPE");
		}
		System.out.println("init-methodDAO");
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void add(T item) {
		loadEntityManager();
		m_entityManager.getTransaction().begin();
		m_entityManager.persist(item);
		m_entityManager.getTransaction().commit();
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void update(T item) {
		loadEntityManager();
		m_entityManager.merge(item);
		m_entityManager.flush();
	}

	@Override
	@Transactional(value = TxType.REQUIRES_NEW)
	public void delete(int tId) {
		loadEntityManager();
		T t = findById(tId);
		m_entityManager.remove(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<T> getAll() {
		loadEntityManager();
		String hql = "from " + m_type.getName();
		Query query = m_entityManager.createQuery(hql);
		return new HashSet<T>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(int id) {
		loadEntityManager();
		try {
			System.out.println(m_type.getClassLoader().loadClass(m_type.getName()));
			return (T) m_entityManager.find(m_type.getClassLoader().loadClass(m_type.getName()), id);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

}
