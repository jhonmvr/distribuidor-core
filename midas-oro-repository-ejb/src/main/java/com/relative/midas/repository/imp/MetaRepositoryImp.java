package com.relative.midas.repository.imp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoContratoEnum;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiMeta;
import com.relative.midas.repository.MetaRepository;
import com.relative.midas.repository.spec.MetaByCodigoAndNombre;

@Stateless(mappedName = "metaRepository")
public class MetaRepositoryImp extends GeneralRepositoryImp<Long, TbMiMeta> implements MetaRepository{

	@Override
	public List<TbMiMeta> findByCodigoAndNombre(Date fechaDesde, Date fechaHasta, String nombre, int startRecord,
			Integer pageSize, String sortFields, String sortDirections) throws RelativeException {
		try {
			return this.findAllBySpecificationPaged(new MetaByCodigoAndNombre(fechaDesde, fechaHasta,nombre), startRecord,
					pageSize, sortFields, sortDirections);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "En agencia" + e.getMessage());
		}
	}

	@Override
	public List<TbMiMeta> findByCodigoAndNombre(Date fechaDesde, Date fechaHasta, String nombre)
			throws RelativeException {
		try {
			return this.findAllBySpecification(new MetaByCodigoAndNombre(fechaDesde, fechaHasta,nombre));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "En agencia" + e.getMessage());
		}
	}

	@Override
	public Long countByCodigoAndNombre(Date fechaDesde, Date fechaHasta, String nombre) throws RelativeException {
		try {
			return this.countBySpecification(new MetaByCodigoAndNombre(fechaDesde, fechaHasta,nombre));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_READ, "En agencia" + e.getMessage());
		}
	}

	@Override
	public List<TbMiMeta> metaActiva(EstadoMidasEnum estado) throws RelativeException {
		try {
			
			StringBuilder strQry = new StringBuilder("select m from TbMiMeta m where 1=1 ");
			
			if(estado != null) {
				strQry.append(" and  estado = :estado ");
			}			
			strQry.append(" order by fechaCreacion desc ");
			Query q = this.getEntityManager().createQuery(strQry.toString());
			if(estado != null) {
				q.setParameter("estado", estado);
			}
			q.setMaxResults(1);
			return q.getResultList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
}
