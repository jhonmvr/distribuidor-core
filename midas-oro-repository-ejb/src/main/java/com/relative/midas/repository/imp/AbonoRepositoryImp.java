package com.relative.midas.repository.imp;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiAbono;
import com.relative.midas.repository.AbonoRepository;
import com.relative.midas.repository.spec.AbonoByClienteIdentificacion;
import com.relative.midas.repository.spec.AbonoByEstadoIdentificacionSpec;
import com.relative.midas.repository.spec.AbonoByIdClienteSpec;

@Stateless(mappedName = "abonoRepository")
public class AbonoRepositoryImp extends GeneralRepositoryImp<Long, TbMiAbono> implements AbonoRepository{
	
	/**
	 * Metodo que busca la entidad por identificacion cliente
	 * 
	 * @param id Pk de la entidad
	 * @return Entidad encontrada
	 * @author KEVIN CHAMORRO - Relative Engine
	 * @throws RelativeException
	 */
	@Override
    public List<TbMiAbono> getAbonoPorIdentificacionCliente(String identificacion) throws RelativeException{
    	List<TbMiAbono> tmp;
    	try {
    		tmp = this.findAllBySpecification(new AbonoByClienteIdentificacion(identificacion));
    		if( tmp != null && !tmp.isEmpty() ) {
				return tmp;
			}
    	}catch(Exception e){
    		throw new RelativeException("Error no se encontraron Abonos con el cliente: " + identificacion);
    	}
		return null;
    }

	@Override
	public List<TbMiAbono> findByIdClient(Long idCliente) throws RelativeException {
		List<TbMiAbono> tmp;
    	try {
    		tmp = this.findAllBySpecification(new AbonoByIdClienteSpec(idCliente));
    		if( tmp != null && !tmp.isEmpty() ) {
				return tmp;
			}
    	}catch(Exception e){
    		throw new RelativeException("Error no se encontraron Abonos con el cliente: ");
    	}
		return null;
	}

	@Override
	public List<TbMiAbono> findByEstadoAndIdentificacion(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, EstadoMidasEnum pendienteHabilitante, String identificacion)
			throws RelativeException {
		try {
			return findAllBySpecificationPaged(new AbonoByEstadoIdentificacionSpec(pendienteHabilitante,identificacion), startRecord, pageSize, sortFields, sortDirections);
			// TODO Auto-generated method stub
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR ABONO POR ESTADO E IDENTIFICACION");
		}
	}

	@Override
	public List<TbMiAbono> findByEstadoAndIdentificacion(EstadoMidasEnum pendienteHabilitante, String identificacion)
			throws RelativeException {
		try {
			return findAllBySpecification(new AbonoByEstadoIdentificacionSpec(pendienteHabilitante,identificacion));
			// TODO Auto-generated method stub
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR ABONO POR ESTADO E IDENTIFICACION");
		}
	}

	@Override
	public Long countByEstadoAndIdentificacion(EstadoMidasEnum pendienteHabilitante, String identificacion)
			throws RelativeException {
		try {
			return countBySpecification(new AbonoByEstadoIdentificacionSpec(pendienteHabilitante,identificacion));
			// TODO Auto-generated method stub
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR ABONO POR ESTADO E IDENTIFICACION");
		}
	}


	
	
}
