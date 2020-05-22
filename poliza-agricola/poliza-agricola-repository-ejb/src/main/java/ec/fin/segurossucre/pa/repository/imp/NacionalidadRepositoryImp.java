package ec.fin.segurossucre.pa.repository.imp;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.pa.model.Nacionalidad;
import ec.fin.segurossucre.pa.repository.NacionalidadRepository;
@Stateless(mappedName = "nacionalidadRepository")
public class NacionalidadRepositoryImp extends GeneralRepositoryImp<String, Nacionalidad> implements NacionalidadRepository {

}
