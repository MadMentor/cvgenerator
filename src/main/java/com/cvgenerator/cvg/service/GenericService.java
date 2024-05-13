package com.cvgenerator.cvg.service;

import java.util.List;

public interface GenericService<D, ID> {
    D save(D d);

    D findById(ID id);

    List<D> findAll();

    void deleteById(ID id);
}
