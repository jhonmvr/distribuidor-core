package com.relative.midas.repository.imp;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiCorteCaja;
import com.relative.midas.repository.CorteCajaRepository;
import com.relative.midas.repository.spec.CorteCajaByMultiSpec;

@Stateless(mappedName = "corteCajaRepository")
public class CorteCajaRepositoryImp extends GeneralRepositoryImp<Long, TbMiCorteCaja> implements CorteCajaRepository {
	
	
	@Override
	public List<TbMiCorteCaja> findByParam(Date fechaCreacion, Date fechaActualizacion, EstadoMidasEnum estado, Long idAgencia) throws RelativeException {
		List<TbMiCorteCaja> tmp;
		try {
			tmp = this.findAllBySpecification(
					new CorteCajaByMultiSpec(null,fechaCreacion, fechaActualizacion, estado,idAgencia));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException ("Error al buscar en TbMiCorteCaja" + e);
		}

		return null;

	}


	@Override
	public List<TbMiCorteCaja> findByParamPaged(Date fechaCreacion, Date fechaActualizacion, EstadoMidasEnum estado,
			Long idAgencia, int inicio, int tamanio) throws RelativeException {

		List<TbMiCorteCaja> tmp;

		try {
			tmp = this.findAllBySpecificationPaged(
					new CorteCajaByMultiSpec(null,fechaCreacion, fechaActualizacion, estado,idAgencia), inicio,
					tamanio);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			
			throw new RelativeException ("Error al buscar en TbMiCorteCaja");
		}

		return null;

	}



	@Override
	public Long countByParamPaged(Date fechaCreacion, Date fechaActualizacion, EstadoMidasEnum estado, Long idAgencia)
			throws RelativeException {
		
		try {
			return this.countBySpecification(new CorteCajaByMultiSpec(null,fechaCreacion, fechaActualizacion, estado,idAgencia));
			
		} catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException ("Error al buscar en TbMiCorteCaja" + e);
		}

		

	}

	@Override
	public List<TbMiCorteCaja> findByParamPaged(Date fechaCreacion, Date fechaActualizacion, EstadoMidasEnum estado,Long idAgencia, int inicio, int tamanio, String sortField,
			String sortDirection) throws RelativeException {

		List<TbMiCorteCaja> tmp;

		try {
			tmp = this.findAllBySpecificationPaged(
					new CorteCajaByMultiSpec(null,fechaCreacion, fechaActualizacion, estado,idAgencia), inicio,
					tamanio, sortField, sortDirection);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			
			throw new RelativeException ("Error al buscar en TbMiCorteCaja");
		}

		return null;

	}


	
	
	@Override
	public TbMiCorteCaja reservarCaja(Long idAgencia) throws RelativeException {
		TbMiCorteCaja persisted = new TbMiCorteCaja();
		List<TbMiCorteCaja> listCorte = null;
		try {
			StringBuilder strQry = new StringBuilder(
					"select f from TbMiCorteCaja f where f.estado in ('CERRADO','PENDIENTE_HABILITANTE') "
					+ "and tbMiAgencia.id =:idAgencia order by f.id desc");
			Query q = this.getEntityManager().createQuery(strQry.toString());
			q.setParameter("idAgencia", idAgencia);
			listCorte = q.getResultList();		
			if(listCorte != null && !listCorte.isEmpty()) {
				return listCorte.get(0);
			}
		} catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL OBTENER CORTE " + e);
		}
		return null;
	}
	
	@Override
	public TbMiCorteCaja reservarCaja(Long idAgencia,EstadoMidasEnum estado) throws RelativeException {
		TbMiCorteCaja persisted = new TbMiCorteCaja();
		List<TbMiCorteCaja> listCorte = null;
		try {
			StringBuilder strQry = new StringBuilder(
					"select f from TbMiCorteCaja f where 1=1 "
					+ "and tbMiAgencia.id =:idAgencia");
			
			if(estado != null) {
				strQry.append(" and f.estado =:estado");
			}
			strQry.append(" order by f.fechaCreacion desc");
			Query q = this.getEntityManager().createQuery(strQry.toString());
			if(estado != null) {
				q.setParameter("estado", estado);
			}
			q.setParameter("idAgencia", idAgencia);
			listCorte = q.getResultList();		
			if(listCorte != null && !listCorte.isEmpty()) {
				return listCorte.get(0);
			}
		} catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException(Constantes.ERROR_CODE_READ, "ERROR AL OBTENER CORTE " + e);
		}
		return null;
	}


	@Override
	public Long countByIdAndEstado(Long id, EstadoMidasEnum estado,Long idAgencia) throws RelativeException {
		
		try {
			return this.countBySpecification(new CorteCajaByMultiSpec(id,null, null, estado,idAgencia));
			
		} catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException ("Error al buscar en TbMiCorteCaja" + e);
		}

		

	}


	@Override
	public List<TbMiCorteCaja> findByIdAndEstado(Long id, EstadoMidasEnum estado, Long idAgencia) throws RelativeException {
		List<TbMiCorteCaja> tmp;
		try {
			tmp = this.findAllBySpecification(new CorteCajaByMultiSpec(id,null, null, estado,idAgencia));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {			
			throw new RelativeException ("AL BUSCAR CORTE CAJA" + e);
		}

		return null;

	}


	@Override
	public List<TbMiCorteCaja> findByIdAndEstado(Long id, EstadoMidasEnum estado, Long idAgencia, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException {
		List<TbMiCorteCaja> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(
					new CorteCajaByMultiSpec(id,null, null, estado,idAgencia),startRecord,pageSize,sortFields,sortDirections);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			
			throw new RelativeException ("AL BUSCAR CORTE CAJA" + e);
		}

		return null;

	}




}
