package com.demo.music.model;

import java.util.Set;

public interface ServiceDAOGenneric<T> {
	void add(T item);

	void update(int tId, T item);

	void delete(int tId);

	Set<T> getAll();

	T findById(int iTd);

	void setClazz(Class<T> class1);

}
