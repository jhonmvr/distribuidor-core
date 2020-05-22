package com.relative.midas.repository;

import javax.ejb.Local;

import com.relative.core.persistence.CrudRepository;
import com.relative.midas.model.Provincia;
@Local
public interface ProvinciaRepository   extends CrudRepository<String,Provincia> {

}
