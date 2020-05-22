package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiContactabilidad;
import com.relative.midas.repository.ContactabilidadRepository;
import com.relative.midas.repository.spec.ContactabilidadByParams;

@Stateless(mappedName = "contactabilidadRepository")
public class ContactabilidadRepositoryImp extends GeneralRepositoryImp<Long, TbMiContactabilidad> implements ContactabilidadRepository {

	@Override
	public List<TbMiContactabilidad> findByParams(PaginatedWrapper pw, Long idCliente, Long idAgente, Long idAgencia,
			Long idContrato, EstadoMidasEnum estado) throws RelativeException {
		try {
			if (pw == null) {
				return this.findAllBySpecification(new ContactabilidadByParams(idCliente, idAgente, idAgencia, 
						idContrato, estado));
			} else {
				if (pw.getIsPaginated() != null && pw.getIsPaginated().equalsIgnoreCase(PaginatedWrapper.YES)) {
					return this.findAllBySpecificationPaged(new ContactabilidadByParams(idCliente, idAgente, 
							idAgencia, idContrato, estado), pw.getStartRecord(), pw.getPageSize(),
							pw.getSortFields(), pw.getSortDirections());
				} else {
					return this.findAllBySpecification(new ContactabilidadByParams(idCliente, idAgente, idAgencia, 
							idContrato, estado));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Ocurrio un error al leer contactabilidad");
		}
	}

	@Override
	public void delete(Long id) throws RelativeException {
		try {
			TbMiContactabilidad movEntreCaja = this.findById(id);
			if(movEntreCaja != null) {
				this.remove(movEntreCaja);
			}else {
				throw new RelativeException(Constantes.ERROR_CODE_DELETE, "El contactabilidad entre cajas ya no existe");
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_DELETE, e.getMessage());
		}
	}

	
}
