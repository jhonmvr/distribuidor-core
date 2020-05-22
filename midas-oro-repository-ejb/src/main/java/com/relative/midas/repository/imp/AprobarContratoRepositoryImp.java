package com.relative.midas.repository.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoAprobacionEnum;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.model.TbMiAprobarContrato;
import com.relative.midas.repository.AprobarContratoRepository;
import com.relative.midas.repository.spec.AprobarContratoByParamsSpec;

@Stateless(mappedName = "abonoRepository")
public class AprobarContratoRepositoryImp extends GeneralRepositoryImp<Long, TbMiAprobarContrato> implements AprobarContratoRepository{

	@Inject
	Logger log;
	
	@Override
	public List<TbMiAprobarContrato> findAprobarContratoByParams(int startRecord, Integer pageSize, String sortFields,
			String sortDirections, String identificacion, EstadoAprobacionEnum estado, String nivelAprobacion, String rolAprobacion 
			,String rolAprobacionDos, String usuarioCreacion) throws RelativeException {
		try {
			log.info("===>entra a findAprobarContratoByParams repository paged" );
			List<TbMiAprobarContrato> tmp;
			tmp = this.findAllBySpecificationPaged(new AprobarContratoByParamsSpec(identificacion, estado, nivelAprobacion, 
					rolAprobacion,rolAprobacionDos,usuarioCreacion), startRecord, pageSize, sortFields, sortDirections);
			
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"Al buscar aprobarContratos por parametros: "+identificacion);
		}
	}

	@Override
	public List<TbMiAprobarContrato> findAprobarContratoByParams(String identificacion, EstadoAprobacionEnum estado, String nivelAprobacion, 
			String rolAprobacion,String rolAprobacionDos, String usuarioCreacion) throws RelativeException {
		log.info("===>entra a findAprobarContratoByParams repository " );
		List<TbMiAprobarContrato> tmp;
		try {
			tmp = this.findAllBySpecification(new AprobarContratoByParamsSpec(identificacion, estado, 
					nivelAprobacion, rolAprobacion,rolAprobacionDos,usuarioCreacion));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"Al buscar aprobarContratos por parametros: "+identificacion);
			
		}
		return tmp;
	}

	@Override
	public Long countAprobarContratoByParams(String identificacion, EstadoAprobacionEnum estado, String nivelAprobacion, 
			String rolAprobacion,String rolAprobacionDos, String usuarioCreacion) throws RelativeException {
		try {
			log.info("===>entra a findAprobarContratoByParams repository buscar" );
			return this.countBySpecification(new AprobarContratoByParamsSpec(identificacion,estado, nivelAprobacion, 
					rolAprobacion,rolAprobacionDos,usuarioCreacion));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"Al buscar aprobarContratos por parametros: "+identificacion);
			
		}
	}
	
	/**
	 * Valida sumatorio de los contratos vigente para un cliente
	 */
	@Override
	public BigDecimal validarValorContratos(String identificacion, List<EstadoContratoEnum> estados) throws RelativeException{
		try {
			List<BigDecimal> tmp;
			StringBuilder str =new StringBuilder("select SUM(contrato.valorContrato) from TbMiContrato contrato ");
			str.append("inner join TbMiCliente cliente on contrato.tbMiCliente.id=cliente.id ");
			str.append("where cliente.identificacion =:identificacion and contrato.estado in (:estados)");
			Query q = this.getEntityManager().createQuery(str.toString());
			q.setParameter("estados", estados);
			q.setParameter("identificacion", identificacion);
			tmp = (List<BigDecimal>) q.getResultList();
			if(tmp != null && !tmp.isEmpty() && tmp.get(0) != null) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL VALIDAR EL VALOR DE LOS CONTRATOS VIGENTE PARA: "+ identificacion);
		}
		return BigDecimal.ZERO;
	}

	
}
