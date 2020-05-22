package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiTipoJoya;
import com.relative.midas.repository.TipoJoyaRepository;
import com.relative.midas.repository.spec.TipoJoyaByDetalleEstado;
import com.relative.midas.repository.spec.TipoJoyaByEstadoSpec;

/**
 * Session Bean implementation class ContratoRepositoryImpl
 */
@Stateless(mappedName = "tipojoyaRepository")
public class TipoJoyaRepositoryImp extends GeneralRepositoryImp<Long, TbMiTipoJoya> implements TipoJoyaRepository {
	/**
	 * Default constructor.
	 */
	public TipoJoyaRepositoryImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TbMiTipoJoya> findByAllPaged(EstadoMidasEnum estado, int page, int pageSize) {
		List<TbMiTipoJoya> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(new TipoJoyaByEstadoSpec(estado), page, pageSize);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			// log.info("NO EXISTE REGISTROS PARA cotizacion" +e);
		}
		return null;
	}

	@Override
	public List<TbMiTipoJoya> findByAll(EstadoMidasEnum estado) throws RelativeException {
		List<TbMiTipoJoya> tmp;
		try {
			tmp = this.findAllBySpecification(new TipoJoyaByEstadoSpec(estado));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			// log.info("NO EXISTE REGISTROS PARA PROVEEDOR" +e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: NO EXISTE INFORMACION DE tipo Joya PARA ID " + estado);

		}
		return null;

	}

	@Override
	public Long countfindByAll(EstadoMidasEnum estado) throws RelativeException {
		Long tmp = null;
		try {
			tmp = this.countBySpecification(new TipoJoyaByEstadoSpec(estado));

			return tmp;

		} catch (Exception e) {
			// log.info("NO EXISTE REGISTROS PARA cotizacion " +e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: NO EXISTE INFORMACION TIPO JOYA PARA ID " + estado);

		}

	}

	@Override
	public List<TbMiTipoJoya> findByDetalleEstado(String detalle, EstadoMidasEnum estado, int startRecord,
			Integer pageSize, String sortFields, String sortDirections) throws RelativeException {
		List<TbMiTipoJoya> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(new TipoJoyaByDetalleEstado(detalle,estado), startRecord, pageSize, sortFields, sortDirections);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			// log.info("NO EXISTE REGISTROS PARA PROVEEDOR" +e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: NO EXISTE INFORMACION DE tipo Joya PARA ID " + estado);

		}
		return null;

	}

	@Override
	public List<TbMiTipoJoya> findByDetalleEstado(String detalle, EstadoMidasEnum estado) throws RelativeException {
		List<TbMiTipoJoya> tmp;
		try {
			tmp = this.findAllBySpecification(new TipoJoyaByDetalleEstado(detalle,estado));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			// log.info("NO EXISTE REGISTROS PARA PROVEEDOR" +e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: NO EXISTE INFORMACION DE tipo Joya PARA ID " + estado);

		}
		return null;

	}

	@Override
	public Long countfindByDetalleEstado(String detalle, EstadoMidasEnum estado) throws RelativeException {
		try {
			return this.countBySpecification(new TipoJoyaByDetalleEstado(detalle,estado));

		} catch (Exception e) {
			// log.info("NO EXISTE REGISTROS PARA cotizacion " +e);
			throw new RelativeException(Constantes.ERROR_CODE_READ,
					"ERROR: NO EXISTE INFORMACION TIPO JOYA PARA ID " + estado);

		}

	}

}
