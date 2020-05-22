package ec.com.def.pa.repository.imp;

import javax.ejb.Stateless;

import ec.com.def.core.persistence.GeneralRepositoryImp;
import ec.com.def.pa.model.TbPaPredio;
import ec.com.def.pa.repository.PredioRepository;
@Stateless(mappedName = "predioRepository")
public class PredioRepositoryImp extends GeneralRepositoryImp<Long, TbPaPredio> implements PredioRepository {

}
