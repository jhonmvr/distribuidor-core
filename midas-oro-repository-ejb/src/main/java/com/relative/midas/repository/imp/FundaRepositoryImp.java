package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoFundaEnum;
import com.relative.midas.model.TbMiFunda;
import com.relative.midas.repository.FundaRepository;
import com.relative.midas.repository.spec.FundaByIdRangoSpec;

/**
 * Session Bean implementation class FundaRepositoryImp
 */
@Stateless(mappedName = "fundaRepository")
public class FundaRepositoryImp extends GeneralRepositoryImp<Long, TbMiFunda> implements FundaRepository {

	public FundaRepositoryImp() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public TbMiFunda reservarFunda(Long idAgencia) throws RelativeException {
		TbMiFunda persisted = new TbMiFunda();
		List<TbMiFunda> listFunda = null;
		try {
			StringBuilder strQry = new StringBuilder(
					"select f from TbMiFunda f where f.estado =:estado "
					+ "and tbMifundaRango.tbMiAgencia.id =:idAgencia order by f.id asc");
			Query q = this.getEntityManager().createQuery(strQry.toString());
			q.setParameter("estado", EstadoFundaEnum.CREADA);
			q.setParameter("idAgencia", idAgencia);
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
	public Integer countFunda(Long idAgencia) throws RelativeException {
		try {
			StringBuilder strQry = new StringBuilder(
					"select f from TbMiFunda f where f.estado =:estado "
					+ "and tbMifundaRango.tbMiAgencia.id =:idAgencia order by f.id asc");
			Query q = this.getEntityManager().createQuery(strQry.toString());
			q.setParameter("estado", EstadoFundaEnum.CREADA);
			q.setParameter("idAgencia", idAgencia);
			return q.getResultList().size();			
		} catch (Exception e) {
			//e.getStackTrace();
			
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, e.getMessage());
		}
	}
	
	
	public List<TbMiFunda> findByRango(Long idRango,  int page, int pageSize,String order, String direction) throws RelativeException {
		List<TbMiFunda> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(new FundaByIdRangoSpec( idRango,null,null,null ), page,
					pageSize, order, direction);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar contrato por identificacion de cliente" + e);
		}
		return null;
	}
	
	
	
	public List<TbMiFunda> findByRango(Long idRango) throws RelativeException {
		List<TbMiFunda> tmp;
		try {
			tmp = this.findAllBySpecification(new FundaByIdRangoSpec( idRango,null,null,null ));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar contrato por identificacion de cliente" + e);
		}
		return null;
	}
	
	public Long countFindByRango(Long idRango) throws RelativeException {
		Long tmp;
		try {
			tmp = this.countBySpecification(new FundaByIdRangoSpec(idRango,null,null,null));
			if (tmp != null) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error no se encontraron fundas para id rango: " + idRango + e.getMessage());
		}
		return null;
	}

	
}
