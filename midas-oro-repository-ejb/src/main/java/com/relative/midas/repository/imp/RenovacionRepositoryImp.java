package com.relative.midas.repository.imp;
import javax.ejb.Stateless;

import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.midas.model.TbMiRenovacion;
import com.relative.midas.repository.RenovacionRepository;

/**
 * Session Bean implementation class RenovacionRepositoryImp
 */
@Stateless(mappedName = "renovacionRepository")
public class RenovacionRepositoryImp extends GeneralRepositoryImp<Long, TbMiRenovacion> implements RenovacionRepository  {
	  /**
     * Default constructor. 
     */
    public RenovacionRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
}
