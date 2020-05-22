package com.relative.midas.repository.imp;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.model.TbMiLote;
import com.relative.midas.model.TbMiMovEntreCaja;
import com.relative.midas.model.TbMiMovimientoCaja;
import com.relative.midas.repository.LoteRepository;
import com.relative.midas.repository.spec.JoyaByFundaId;
import com.relative.midas.repository.spec.LoteByIdVentaLoteSpec;
import com.relative.midas.repository.spec.LoteByNombreLoteSpec;
import com.relative.midas.repository.spec.MovEntreCajaByParamsSpec;
import com.relative.midas.repository.spec.MovimientoCajabyContratoandProcesoSpec;

@Stateless(mappedName = "loteRepository")
public class LoteRepositoryImp extends GeneralRepositoryImp<Long, TbMiLote> implements LoteRepository {

	public LoteRepositoryImp() {

	}
	
	@Override
	public List<TbMiLote> findByParams(String nombreLote, Date fechaDesde, Date fechaHasta,
			String usuarioCreacion, Long tipoOro, List<EstadoMidasEnum> estados, PaginatedWrapper pw) throws RelativeException {
		try {
			if (pw == null) {
				return this.findAllBySpecification(new LoteByNombreLoteSpec(nombreLote, usuarioCreacion, 
						tipoOro, fechaDesde, fechaHasta, estados));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.findAllBySpecificationPaged(new LoteByNombreLoteSpec(nombreLote, usuarioCreacion, 
							tipoOro, fechaDesde, fechaHasta, estados), pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecification(new LoteByNombreLoteSpec(nombreLote, usuarioCreacion, 
							tipoOro, fechaDesde, fechaHasta, estados));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer movimientos entre cajas");
		}
	}

	@Override
	public List<TbMiLote> findByVentaLoteId(Long idVentaLote) throws RelativeException {
		 List<TbMiLote> tmp;
			
			try {
				tmp = this.findAllBySpecification(new LoteByIdVentaLoteSpec(idVentaLote));
				if (tmp != null && !tmp.isEmpty()) {
					return tmp;
				}
			} catch (Exception e) {
				e.getStackTrace();
				
				throw new RelativeException ("Error al buscar en TbMi" + e);
			}
			return null;
	}
	
}
