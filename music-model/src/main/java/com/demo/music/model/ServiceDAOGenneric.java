package com.demo.music.model;

import java.util.Set;

public interface ServiceDAOGenneric<T> {
	void add(T item);

	void update(T item);

	void delete(int tId);

	Set<T> getAll();

	T findById(int iTd);
	

}
