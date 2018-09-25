package com.demo.music.persistence;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.bean.Scope;
import org.apache.aries.blueprint.annotation.service.Service;

import com.demo.music.model.ServiceDAOGenneric;
import com.demo.music.model.Song;

@Bean(scope = Scope.PROTOTYPE, id = "genneric-dao", initMethod="init")
/*
 * @Service(classes = ServiceDAOGenneric.class, properties = {
 * 
 * @ServiceProperty(name = "music.genneric.dao", values = "true") })
 */
@Service
public class GennericDAOImpl<T> implements ServiceDAOGenneric<T> {

	@PersistenceContext(unitName = "managed-jpa")
	private EntityManager m_entityManager;
	private Class<T> m_type;
	private int i;

	public GennericDAOImpl() {
		i = (int) (Math.random() * 100);
	}

	public void init() {
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
	public void add(T item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int tId, T item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int tId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<T> getAll() {
		System.out.println("ID=" + i);
		String hql = "from " + m_type.getName();
		System.out.println("HQL Query: " + hql);
		Query query = m_entityManager.createQuery(hql);
		return new HashSet(query.getResultList());
	}

	@Override
	public T findById(int id) {
		return null;
	}

	@Override
	public void setClazz(Class<T> class1) {
		this.m_type = class1;

	}

	public static void main(String[] args) {
		Song song = new Song();
		System.out.println(song.getClass().getName());
	}

}
