package ec.com.def.pa.repository.imp;
import javax.ejb.Stateless;

import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.pa.model.Pais;
import ec.com.def.pa.repository.PaisRepository;
@Stateless(mappedName = "paisRepository")
public class PaisRepositoryImp extends GeneralRepositoryImp<String, Pais> implements PaisRepository {


}
