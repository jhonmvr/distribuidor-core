package com.relative.midas.repository.imp;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoFundaEnum;
import com.relative.midas.model.TbMiLiquidacion;
import com.relative.midas.repository.LiquidacionRepository;
import com.relative.midas.repository.spec.LiquidacionByFolletoLiquidacionSpec;

/**
 * Session Bean implementation class FundaRepositoryImp
 */
@Stateless(mappedName = "fundaRepository")
public class LiquidacionRepositoryImp extends GeneralRepositoryImp<Long, TbMiLiquidacion> implements LiquidacionRepository {

	public LiquidacionRepositoryImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TbMiLiquidacion reservarLiquidacion(Long idAgencia) throws RelativeException {
		TbMiLiquidacion persisted = new TbMiLiquidacion();
		List<TbMiLiquidacion> listFunda = null;
		try {
			Date fecha = new Date();
			StringBuilder strQry = new StringBuilder(
					"select f from TbMiLiquidacion f where f.estado =:estado "
					+ "and f.tbMiFolletoLiquidacion.tbMiAgencia.id =:idAgencia "
					+ "and f.tbMiFolletoLiquidacion.fechaVigencia >=:fecha order by f.id asc");
			Query q = this.getEntityManager().createQuery(strQry.toString());
			q.setParameter("estado", EstadoFundaEnum.CREADA);
			q.setParameter("idAgencia", idAgencia);
			q.setParameter("fecha", fecha);
			listFunda = q.getResultList();
			if (listFunda != null && !listFunda.isEmpty()) {
				persisted = listFunda.get(0);
				persisted.setEstado(EstadoFundaEnum.RESERVADA);
				return this.update(persisted);
				
			}
			return null;
		} catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL RESERVAR FUNDA " + e);
		}
	}
	
	@Override
	public Integer countLiquidacion(Long idAgencia) throws RelativeException {		
		try {
			Date fecha = new Date();
			StringBuilder strQry = new StringBuilder(
					"select f from TbMiLiquidacion f where f.estado =:estado "
					+ "and f.tbMiFolletoLiquidacion.tbMiAgencia.id =:idAgencia "
					+ "and f.tbMiFolletoLiquidacion.fechaVigencia >=:fecha order by f.id asc");
			Query q = this.getEntityManager().createQuery(strQry.toString());
			q.setParameter("estado", EstadoFundaEnum.CREADA);
			q.setParameter("idAgencia", idAgencia);
			q.setParameter("fecha", fecha);
			return q.getResultList().size();			
		} catch (Exception e) {
			//e.getStackTrace();
			
			throw new RelativeException(Constantes.ERROR_CODE_READ, e.getMessage());
		}
	}
	
	
	
	/**
	 * Busqueda de liquidaciones por id folleto
	 * @param pw
	 * @param idFolletoLiquidacion
	 * @return
	 * @throws RelativeException
	 * @author Kevin chamorro
	 */
	@Override
	public List<TbMiLiquidacion> findByFolletoLiquidacion(PaginatedWrapper pw, 
			Long idFolletoLiquidacion) throws RelativeException {		
		try {
			if (pw == null) {
				return this.findAllBySpecification(new LiquidacionByFolletoLiquidacionSpec(idFolletoLiquidacion));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.findAllBySpecificationPaged(new LiquidacionByFolletoLiquidacionSpec(idFolletoLiquidacion), pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecification(new LiquidacionByFolletoLiquidacionSpec(idFolletoLiquidacion));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"Busqueda liquidaciones por folleto " + e.getMessage());
		}
	}


}
