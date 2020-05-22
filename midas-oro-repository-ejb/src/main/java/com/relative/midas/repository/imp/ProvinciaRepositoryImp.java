package com.relative.midas.repository.imp;
import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.midas.model.Provincia;
import com.relative.midas.repository.ProvinciaRepository;

/**
 * Session Bean implementation class RenovacionRepositoryImp
 */
@Stateless(mappedName = "provinciaRepository")
public class ProvinciaRepositoryImp extends GeneralRepositoryImp<String, Provincia> implements ProvinciaRepository  {
	 /**
     * Default constructor. 
     */
    public ProvinciaRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
}

