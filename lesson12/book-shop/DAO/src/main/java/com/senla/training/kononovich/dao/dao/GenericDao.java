package com.senla.training.kononovich.dao.dao;

import java.io.Serializable;
import java.util.List;

import com.senla.training.kononovich.entity.Identified;

/**
 * ��������������� ��������� ���������� ������������� ���������� ��������
 * @param <T> ��� ������� ������������
 * @param <PK> ��� ���������� �����
 */
public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {

    /** ������� ����� ������ � ��������������� �� ������ */
    public T create() throws PersistException;

    /** ������� ����� ������, ��������������� ������� object */
    public T persist(T object)  throws PersistException;

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null */
    public T getByPK(PK key) throws PersistException;

    /** ��������� ��������� ������� group � ���� ������ */
    public void update(T object) throws PersistException;

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(int id) throws PersistException;

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<T> getAll() throws PersistException;
}
