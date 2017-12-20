package com.senla.training.kononovich.entity;

import java.io.Serializable;

/**
 * Интерфейс идентифицируемых объектов.
 */
public interface Identified<PK extends Serializable> {

    /** Возвращает идентификатор объекта */
    public PK getId();
}
