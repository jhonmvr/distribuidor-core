package ec.com.def.pa.repository.imp;

import javax.ejb.Stateless;

import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.pa.model.Provincia;
import ec.com.def.pa.repository.ProvinciaRepository;

/**
 * Session Bean implementation class ProvinciaRepositoryImp
 */
@Stateless(mappedName = "provinciaRepository")
public class ProvinciaRepositoryImp extends GeneralRepositoryImp<String, Provincia> implements ProvinciaRepository {

    /**
     * Default constructor. 
     */
    public ProvinciaRepositoryImp() {
        // TODO Auto-generated constructor stub
    }

}
