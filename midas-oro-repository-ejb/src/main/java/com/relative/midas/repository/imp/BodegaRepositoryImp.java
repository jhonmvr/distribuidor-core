package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.model.TbMiBodega;
import com.relative.midas.repository.BodegaRepository;
import com.relative.midas.repository.spec.BodegaByAgenciaAndNombreSpec;

/**
 * Session Bean implementation class InventarioRepositoryImp
 */
@Stateless(mappedName = "bodegaRepository")
public class BodegaRepositoryImp extends GeneralRepositoryImp<Long, TbMiBodega> implements BodegaRepository {
	/**
	 * Default constructor.
	 */
	public BodegaRepositoryImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TbMiBodega findByAgenciaAndNombre(Long idAgencia, String nombreBodega) throws RelativeException {
		List<TbMiBodega> tmp;
		try {
			tmp = this.findAllBySpecification(new BodegaByAgenciaAndNombreSpec(idAgencia, nombreBodega));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "NO SE PUEDE LEER LA INFORMACION DE LA BODEGA" + e.getMessage());
		}
		return null;

	}
}
