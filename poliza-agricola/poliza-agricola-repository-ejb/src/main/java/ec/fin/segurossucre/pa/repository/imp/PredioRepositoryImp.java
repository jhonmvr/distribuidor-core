package ec.fin.segurossucre.pa.repository.imp;

import javax.ejb.Stateless;

import ec.fin.segurossucre.core.persistence.GeneralRepositoryImp;
import ec.fin.segurossucre.pa.model.TbPaPredio;
import ec.fin.segurossucre.pa.repository.PredioRepository;
@Stateless(mappedName = "predioRepository")
public class PredioRepositoryImp extends GeneralRepositoryImp<Long, TbPaPredio> implements PredioRepository {

}
