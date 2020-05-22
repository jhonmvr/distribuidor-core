package com.relative.midas.repository.imp;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.enums.TipoAgenciaEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.repository.AgenciaRepository;
import com.relative.midas.repository.spec.AgenciaByCodigoAndNombreSpec;
import com.relative.midas.repository.spec.AgenciaByEstadoSpec;
import com.relative.midas.repository.spec.AgenciaByTipoAgenciaSpec;

/**
 * Session Bean implementation class AgenciaRepository
 */
@Stateless(mappedName = "agenciaRepository")
public class AgenciaRepositoryImp extends GeneralRepositoryImp<Long, TbMiAgencia> implements AgenciaRepository {

	@Inject
	Logger log;

	/**
	 * Default constructor.
	 */
	public AgenciaRepositoryImp() {
	}

	/**
	 * Lista todas las agencias por joyas estado
	 * 
	 * @author Kevin Chamorro
	 */
	@Override
	public List<TbMiAgencia> findAllBySpecificationPaged(Class<TbMiAgencia> class1, Integer currentPage,
			Integer pageSize, String sortFields, String sortDirections) throws RelativeException {
		List<TbMiAgencia> tmp;
		try {
			tmp = this.findAllBySpecificationPaged(TbMiAgencia.class, currentPage, pageSize, sortFields,
					sortDirections);
			if (tmp != null && !tmp.isEmpty()) {
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException("Error al buscar inventario por id joya" + e);
		}
		return null;
	}

	@Override
	public TbMiAgencia add(TbMiAgencia entity) throws RelativeException {
		try {
			Query seqCd = this.getEntityManager().createNativeQuery("CREATE SEQUENCE " + entity.getSeqCd());
			Query seqCp = this.getEntityManager().createNativeQuery("CREATE SEQUENCE " + entity.getSeqCp());
			Query seqVl = this.getEntityManager().createNativeQuery("CREATE SEQUENCE " + entity.getSeqVl());
			Query seqIE = this.getEntityManager().createNativeQuery("CREATE SEQUENCE " + entity.getSeqIE());
			seqCd.executeUpdate();
			seqCp.executeUpdate();
			seqVl.executeUpdate();
			seqIE.executeUpdate();
			System.out.println("===inicio persiste");
			getEntityManager().persist(entity);
			System.out.println("===termino persiste");
			getEntityManager().flush();
			getEntityManager().refresh(entity);
			return entity;
		} catch (Exception e) {
			log.log(Level.SEVERE, "ERROR EN LA CREACION DE " + entity.getClass().getName(), e);
			throw new RelativeException(Constantes.ERROR_CODE_CREATE, "ERROR EN LA CREACION DE "
					+ entity.getClass().getName() + Constantes.MSG_CON_ERROR + e.getMessage());
		}
	}

	/**
	 * Metodo que obtiene la sencuencia definida
	 * 
	 * @return secuencia generada
	 * @throws RelativeException
	 */
	@Override
	public BigInteger generateCodigoAgencia() throws RelativeException {
		try {
			Query query = this.getEntityManager().createNativeQuery("select last_value from seq_agencia");
			BigInteger  result = (BigInteger) query.getSingleResult();
			if(result != null) {
				return result.add(new BigInteger("1"));
			}
			return null;

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,
					"GENERAR SECUENCIA " + ex.getMessage());
		}
	}
	
	@Override
	public List<TbMiAgencia> findByCodigoAndNombre(String codigo, String nombre, int startRecord, Integer pageSize,
			String sortFields, String sortDirections) throws RelativeException {
		try {
			return this.findAllBySpecificationPaged(new AgenciaByCodigoAndNombreSpec(codigo, nombre), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "En agencia" + e.getMessage());
		}
	}

	@Override
	public List<TbMiAgencia> findByCodigoAndNombre(String codigo, String nombre) throws RelativeException {
		try {
			return this.findAllBySpecification(new AgenciaByCodigoAndNombreSpec(codigo, nombre));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "En agencia" + e.getMessage());
		}
	}

	@Override
	public Long countByCodigoAndNombre(String codigo, String nombre) throws RelativeException {
		try {
			return this.countBySpecification(new AgenciaByCodigoAndNombreSpec(codigo, nombre));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "En agencia" + e.getMessage());
		}
	}

	@Override
	public List<TbMiAgencia> findByEstado(EstadoMidasEnum estado) throws RelativeException {
		try {
			return this.findAllBySpecification(new AgenciaByEstadoSpec(estado));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Error al leer agencias por estado");
		}
	}

	@Override
	public List<TbMiAgencia> finByTipoAgencia(TipoAgenciaEnum tipoAgencia) throws RelativeException {
		try {
			List<TbMiAgencia> agencias = this.findAllBySpecification(new AgenciaByTipoAgenciaSpec(tipoAgencia));
			
				return agencias;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "En agencia" + e.getMessage());
		}
	}

	@Override
	public BigInteger generateCodigoIngresoEgreso() throws RelativeException {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * Metodo que obtiene la sencuencia definida
	 * 
	 * @return secuencia generada
	 * @throws RelativeException
	 */
	/*@Override
	
	public BigInteger generateCodigoIngresoEgreso() throws RelativeException {
		TbMiAgencia agencia;
		String seq;
		
		tipoC = parametroRepository.findByNombre(tipoCompra).getValor();
		agencia = agenciaRepository.findById(idAgencia);
		
		seq = agenciaRepository.get(generateCodigoAgencia.getSeqCd()).toString();
		codigoCompra = codigoCompra.concat(tipoC).concat("-")
				.concat(String.valueOf(new Date().getYear() + 1900)).concat("-").concat(agencia.getCodigo())
				.concat("-").concat(seq);
	}
	*/
	
}
