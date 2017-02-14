package com.coolnimesh43.persistence.rest.service;

public interface AbstractService<T, R> {

    /**
     * Find entity by primary key.
     * 
     * @author coolnimesh43
     * @param id
     *            primary key
     * @return
     */
    public T findOne(R id);

    /**
     * Save entity s.
     * 
     * @author coolnimesh43
     * @param s
     *            Entity to be saved.
     * @return T
     */
    public T save(T s);

    /**
     * Delete entity by id.
     * 
     * @author coolnimesh43
     * @param id
     *            The id
     */
    public void delete(R id);
}
