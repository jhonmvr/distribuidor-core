package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.model.TbMiJoyaLote;
import com.relative.midas.repository.JoyaLoteRepository;
import com.relative.midas.repository.spec.AgenciaByCodigoAndNombreSpec;
import com.relative.midas.repository.spec.JoyaLoteByIdLoteSpec;
import com.relative.midas.repository.spec.JoyaLoteByJoyaSpec;

@Stateless(mappedName = "joyaLoteRepository")
public class JoyaLoteRepositoryImp extends GeneralRepositoryImp<Long, TbMiJoyaLote> implements JoyaLoteRepository{
	
	@Override
	public List<TbMiJoyaLote> findJoyaLoteByIdLote(Long idLote) throws RelativeException{
		try {
			return this.findAllBySpecification(new JoyaLoteByIdLoteSpec(idLote));
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL BUSCAR JOYALOTE POR ID LOTE" + e.getMessage());
		}
	}
	
	@Override
	public void deleteByJoya(Long idJoya) throws RelativeException {
		try {
			List<TbMiJoyaLote> listJoyaLote = this.findAllBySpecification(new JoyaLoteByJoyaSpec(idJoya));
			if(listJoyaLote != null && !listJoyaLote.isEmpty()) {
				for (TbMiJoyaLote tbMiJoyaLote : listJoyaLote) {
					this.remove(tbMiJoyaLote);
				}
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_DELETE, "No se pudo eliminar los registros de Joya Lote asociados");
		}
	}

	@Override
	public TbMiJoyaLote findByIdJoya(Long idJoya) throws RelativeException {
		try {
			List<TbMiJoyaLote> tmp = this.findAllBySpecification(new JoyaLoteByJoyaSpec(idJoya));
			if(tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL BUSCAR JOYALOTE POR ID LOTE" + e.getMessage());
		}
		return null;
	}

	@Override
	public List<TbMiJoyaLote> findByIdLote(int startRecord, Integer pageSize, String sortFields, String sortDirections,
			Long idLote) throws RelativeException {
		try {
			return this.findAllBySpecificationPaged(new JoyaLoteByIdLoteSpec(idLote), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL BUSCAR JOYALOTE POR ID LOTE" + e.getMessage());
		}
	}

	@Override
	public List<TbMiJoyaLote> findByIdLote(Long idLote) throws RelativeException {

		try {
			return this.findAllBySpecification(new JoyaLoteByIdLoteSpec(idLote));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL BUSCAR JOYALOTE POR ID LOTE" + e.getMessage());
		}
	
	}

	@Override
	public Long countByIdLote(Long idLote) throws RelativeException {
		try {
			return this.countBySpecification(new JoyaLoteByIdLoteSpec(idLote));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL BUSCAR JOYALOTE POR ID LOTE" + e.getMessage());
		}
	}
	
}
