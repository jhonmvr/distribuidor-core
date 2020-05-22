package ec.fin.segurossucre.pa.repository.imp;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.pa.model.Provincia;
import ec.fin.segurossucre.pa.repository.ProvinciaRepository;

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
