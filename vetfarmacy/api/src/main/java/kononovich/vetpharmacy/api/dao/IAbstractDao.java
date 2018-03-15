package kononovich.vetpharmacy.api.dao;


public interface IAbstractDao<T> {
    void addEntity(T entity);
    void updateEntity(T entity);
    void deleteEntity(Long id);
    T getEntityById(Long id);


}