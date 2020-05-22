package com.relative.midas.repository.imp;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.model.TbMiMovEntreCaja;
import com.relative.midas.repository.MovEntreCajaRepository;
import com.relative.midas.repository.spec.MovEntreCajaByParamsSpec;

@Stateless(mappedName = "movEntreCajaRepository")
public class MovEntreCajaRepositoryImp extends GeneralRepositoryImp<Long, TbMiMovEntreCaja> implements MovEntreCajaRepository {
	
	@Inject
	Logger log;
	
	/**
	 * Lista los Movimientos entre caja por agencia origen, agencia destino, fecha desde y fecha hasta en basea a fecha creacion
	 * @param pw
	 * @param idAgenciaOrigen
	 * @param idAgenciaDestino
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	@Override
	public List<TbMiMovEntreCaja> findByParams(PaginatedWrapper pw, Long idAgenciaOrigen, 
			Date fechaDesde, Date fechaHasta) throws RelativeException {
		try {
			if (pw == null) {
				return this.findAllBySpecification(new MovEntreCajaByParamsSpec(idAgenciaOrigen, 
						fechaDesde, fechaHasta));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.findAllBySpecificationPaged(new MovEntreCajaByParamsSpec(idAgenciaOrigen, 
							fechaDesde, fechaHasta), pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecification(new MovEntreCajaByParamsSpec(idAgenciaOrigen, 
							fechaDesde, fechaHasta));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer movimientos entre cajas");
		}
	}
	
	public void delete(Long id) throws RelativeException{
		try {
			TbMiMovEntreCaja movEntreCaja = this.findById(id);
			if(movEntreCaja != null) {
				this.remove(movEntreCaja);
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_DELETE, "El movimineto entre cajas ya no existe");
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_DELETE, e.getMessage());
		}
	}
}
