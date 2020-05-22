package ec.com.def.pa.repository.imp;

import javax.ejb.Stateless;

import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.pa.model.Nacionalidad;
import ec.com.def.pa.repository.NacionalidadRepository;
@Stateless(mappedName = "nacionalidadRepository")
public class NacionalidadRepositoryImp extends GeneralRepositoryImp<String, Nacionalidad> implements NacionalidadRepository {

}
