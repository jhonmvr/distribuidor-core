package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.midas.model.TbMiDetalleCaja;
import com.relative.midas.repository.DetalleCajaRepository;
import com.relative.midas.repository.spec.DetalleCajaByIdCorteCaja;

@Stateless(mappedName = "cajaRepository")
public class DetalleCajaRepositoryImp extends GeneralRepositoryImp<Long, TbMiDetalleCaja> implements DetalleCajaRepository{

	@Override
	public List<TbMiDetalleCaja> findByIdCorteCaja(Long idCorteCaja) throws RelativeException {
		List<TbMiDetalleCaja> tmp;
		try {
			tmp = this.findAllBySpecification(
					new DetalleCajaByIdCorteCaja(idCorteCaja));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException ("Error al buscar en TbMiDetalleCaja" + e);
		}
		return null;
	}

	@Override
	public Long countByIdCorteCaja(Long idCorteCaja)	throws RelativeException {
		try {
			return this.countBySpecification(new DetalleCajaByIdCorteCaja(idCorteCaja));
			
		} catch (Exception e) {
			e.getStackTrace();
			
			throw new RelativeException ("Error al buscar en TbMiDetalleCaja" + e);
		}
	}

	@Override
	public List<TbMiDetalleCaja> findByIdCorteCaja(Long idCorteCaja, int inicio, int tamanio) throws RelativeException {
		
		List<TbMiDetalleCaja> tmp;

		try {
			tmp = this.findAllBySpecificationPaged(
					new DetalleCajaByIdCorteCaja(idCorteCaja), inicio,
					tamanio);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			
			throw new RelativeException ("Error al buscar en TbMiDetalleCaja");
		}
		return null;
	}

	@Override
	public List<TbMiDetalleCaja> findByIdCorteCaja(Long idCorteCaja, int inicio, int tamanio, String sortField,
			String sortDirection) throws RelativeException {
		List<TbMiDetalleCaja> tmp;

		try {
			tmp = this.findAllBySpecificationPaged(
					new DetalleCajaByIdCorteCaja(idCorteCaja), inicio,
					tamanio, sortField, sortDirection);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			
			throw new RelativeException ("Error al buscar en TbMiDetalleCaja");
		}
		return null;
	}

	
	
}
