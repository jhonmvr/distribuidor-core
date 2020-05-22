package com.relative.midas.repository.imp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.core.util.main.PaginatedWrapper;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiAgencia;
import com.relative.midas.model.TbMiAgente;
import com.relative.midas.model.TbMiBodega;
import com.relative.midas.model.TbMiContrato;
import com.relative.midas.model.TbMiFunda;
import com.relative.midas.model.TbMiJoya;
import com.relative.midas.repository.AgenteRepository;
import com.relative.midas.repository.spec.AgenteByNombreUsuarioSpec;
import com.relative.midas.repository.spec.AgenteByParamsSpec;
import com.relative.midas.wrapper.RetazarContratos;

/**
 * Session Bean implementation class AgenteRepositoryImp
 */
@Stateless(mappedName = "agenteRepository")
public class AgenteRepositoryImp extends GeneralRepositoryImp<Long, TbMiAgente> implements AgenteRepository {

	/**
	 * Default constructor.
	 */
	public AgenteRepositoryImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TbMiAgente> agenteSinAsignar() throws RelativeException {
		List<TbMiAgente> listAgente = new ArrayList<TbMiAgente>();
		try {
			StringBuilder strQry = new StringBuilder("select a from TbMiAgente a "
					+ "left join TbMiAgencia c on a.id =c.tbMiAgente or a.id =c.tbMiAgenteSupervisor "
					+ "where c.tbMiAgente is null and c.tbMiAgenteSupervisor is null");
			Query q = this.getEntityManager().createQuery(strQry.toString());
			listAgente = q.getResultList();
			if (listAgente != null && !listAgente.isEmpty()) {
				return listAgente;

			}
			return null;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM, "En Al buscar agente sin asignar TbMiAgente");
		}
	}

	/**
	 * Busca agente por nombre de usuario y devuelve el primero que encuentre
	 * 
	 * @param nombreUsuario
	 * @return
	 * @throws RelativeException
	 * @author Kevin Chamorro
	 */
	public TbMiAgente findByNombreUsuario(String nombreUsuario) throws RelativeException {
		try {
			List<TbMiAgente> agentes = this.findAllBySpecification(new AgenteByNombreUsuarioSpec(nombreUsuario));
			if (agentes != null && !agentes.isEmpty()) {
				return agentes.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Busqueda de agente por nombre de usuario");
		}
	}

	@Override
	public List<TbMiAgente> findByParams(String nombre, String apellido, String identificacion)
			throws RelativeException {
		try {
			return this.findAllBySpecification(new AgenteByParamsSpec(nombre, apellido, identificacion));

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Busqueda de agente por nombre de usuario");
		}
	}

	@Override
	public List<TbMiAgente> findByParams(String nombre, String apellido, String identificacion, int startRecord,
			Integer pageSize, String sortFields, String sortDirections) throws RelativeException {
		try {
			return this.findAllBySpecificationPaged(new AgenteByParamsSpec(nombre, apellido, identificacion),
					startRecord, pageSize, sortFields, sortDirections);

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Busqueda de agente por nombre de usuario");
		}
	}

	@Override
	public Long countByParams(String nombre, String apellido, String identificacion) throws RelativeException {
		try {
			return this.countBySpecification(new AgenteByParamsSpec(nombre, apellido, identificacion));

		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "Busqueda de agente por nombre de usuario");
		}
	}

	@Override
	public TbMiAgente findAgenteOrSupervisorByNombreUsuario(String username) throws RelativeException {
		try {
			List<TbMiAgente> tmp;
			StringBuilder strQry = new StringBuilder("select a from TbMiAgente a "
					+ " join TbMiAgencia ag on ag.tbMiAgente = a"
					//+ " or   ag.tbMiAgenteSupervisor = a "
					+ " where a.nombreUsuario =:username");
			Query q = this.getEntityManager().createQuery(strQry.toString());
			q.setParameter("username", username);
			tmp = q.getResultList();
			if (tmp != null && !tmp.isEmpty()) {				
				return tmp.get(0);
			}			
			strQry = new StringBuilder("select a from TbMiAgente a "
					+ " join TbMiAgencia ag on ag.tbMiAgenteSupervisor = a"						
					+ " where a.nombreUsuario =:username");
			q = this.getEntityManager().createQuery(strQry.toString());
			q.setParameter("username", username);
			tmp = q.getResultList();
			if (tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "AL BUSCAR AGENTE - SUPERVISOR");
		}
	}
}
