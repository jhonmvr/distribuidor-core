package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.NotificacionEnum;
import com.relative.midas.model.TbMiJoyaLote;
import com.relative.midas.model.TbMiNotificacion;
import com.relative.midas.repository.NotificacionRepository;
import com.relative.midas.repository.spec.JoyaLoteByJoyaSpec;
import com.relative.midas.repository.spec.NotificacionByMultiSpec;

@Stateless(mappedName = "notificacionRepository")
public class NotificacionRepositoryImp extends GeneralRepositoryImp<Long, TbMiNotificacion>	implements NotificacionRepository {

	@Override
	public TbMiNotificacion findByMulti(NotificacionEnum tipo, EstadoMidasEnum estado, Long idAgencia,
			Long idReferencia) throws RelativeException {

		try {
			List<TbMiNotificacion> tmp = this
					.findAllBySpecification(new NotificacionByMultiSpec(tipo, estado, idAgencia, idReferencia));
			if (tmp != null && tmp.size() != 0) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			e.getStackTrace();
			throw new RelativeException("Error al buscar en TbMiNotificacion"+e);
		}
		return null;
	}

	@Override
	public List<TbMiNotificacion> findByIdAgencia(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, Long idAgencia) throws RelativeException {
		try {
			List<TbMiNotificacion> tmp = this.findAllBySpecificationPaged(new NotificacionByMultiSpec(null, null, idAgencia, null), startRecord, pageSize, sortFields, sortDirections);
			if(tmp != null && tmp.size() != 0) {
				return tmp;
			}
			return null;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR NOTIFICACIONES POR ID AGENCIA");
		}
	}

	@Override
	public List<TbMiNotificacion> findByIdAgencia(Long idAgencia) throws RelativeException {
		try {
			List<TbMiNotificacion> tmp = this.findAllBySpecification(new NotificacionByMultiSpec(null, null, idAgencia, null));
			if(tmp != null && tmp.size() != 0) {
				return tmp;
			}
			return null;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR NOTIFICACIONES POR ID AGENCIA");
		}
	}

	@Override
	public Long countByIdAgencia(Long idAgencia) throws RelativeException {
		try {
			return countBySpecification(new NotificacionByMultiSpec(null, null, idAgencia, null));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR NOTIFICACIONES POR ID AGENCIA");
		}
	}

	@Override
	public void deleteEntity(Long id) throws RelativeException {
		try {
			TbMiNotificacion entity = findById(id);
			if(entity != null ) {				
			this.remove(entity);
				
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_DELETE, "No se pudo eliminar los registros de Joya Lote asociados");
		}
	}
}
