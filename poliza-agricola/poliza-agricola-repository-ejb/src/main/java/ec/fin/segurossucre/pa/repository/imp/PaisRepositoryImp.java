package ec.fin.segurossucre.pa.repository.imp;
import javax.ejb.Stateless;
import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.pa.model.Pais;
import ec.fin.segurossucre.pa.repository.PaisRepository;
@Stateless(mappedName = "paisRepository")
public class PaisRepositoryImp extends GeneralRepositoryImp<String, Pais> implements PaisRepository {


}
