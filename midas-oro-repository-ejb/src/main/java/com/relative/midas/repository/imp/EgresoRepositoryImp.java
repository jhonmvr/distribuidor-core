package com.relative.midas.repository.imp;

import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.midas.model.TbMiEgreso;
import com.relative.midas.model.TbMiJoyaSim;
import com.relative.midas.repository.EgresoRepository;
import com.relative.midas.repository.JoyaSimRepository;

/**
 * Session Bean implementation class HistoricoJoyaImp
 */
@Stateless(mappedName = "egresoRepository")
public class EgresoRepositoryImp  extends GeneralRepositoryImp<Long, TbMiEgreso> implements EgresoRepository {

	  /**
     * Default constructor. 
     */
    public EgresoRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
}

