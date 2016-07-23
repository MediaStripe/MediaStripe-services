package com.imie.services;

import java.util.List;

public interface BasicCRUDOperations<T> {

	public List<T> findAll();
	public T findById(Integer id);
	public void insert(T entity);
	public void update(T entity);
	public void delete(T entity);
}
