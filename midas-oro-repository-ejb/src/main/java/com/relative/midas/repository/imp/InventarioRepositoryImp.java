package com.relative.midas.repository.imp;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.enums.EstadoJoyaEnum;
import com.relative.midas.model.TbMiInventario;
import com.relative.midas.repository.InventarioRepository;
import com.relative.midas.repository.spec.InventarioByJoyaSpec;
import com.relative.midas.util.MidasOroUtil;
import com.relative.midas.wrapper.TrazabilidadWrapper;

/**
 * Session Bean implementation class InventarioRepositoryImp
 */
@Stateless(mappedName = "inventarioRepository")
public class InventarioRepositoryImp extends GeneralRepositoryImp<Long, TbMiInventario> implements InventarioRepository {
	/**
     * Default constructor. 
     */
    public InventarioRepositoryImp() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public TbMiInventario findByJoya(Long idJoya) throws RelativeException{
    	List<TbMiInventario> tmp;
    	try {
			tmp = this.findAllBySpecification(new InventarioByJoyaSpec(idJoya));
			if( tmp != null && !tmp.isEmpty() ) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			throw new RelativeException ("Error al buscar inventario por id joya" + e);
		}
    	return null;
    	
    }
    /**
     * Metodo que cambia el estado del inventario pertenecientes a una joya
     * @param idJoya
     * @param estado
     * @param usuario
     * @return
     * @throws RelativeException
     * @author kevin chamorro
     */
    @Override
    public List<TbMiInventario> changeEstadoInventarioByIdJoya(Long idJoya, EstadoJoyaEnum estado, String usuario) throws RelativeException{
    	List<TbMiInventario> tmp;
    	try {
			tmp = this.findAllBySpecification(new InventarioByJoyaSpec(idJoya));
			if( tmp != null && !tmp.isEmpty() ) {
				for (TbMiInventario i : tmp) {
					i.setEstado(estado);
					i.setUsuarioActualizacion(usuario);
					try {
						this.update(i);
					} catch (Exception e) {
						throw new RelativeException (Constantes.ERROR_CODE_UPDATE, "Error al actualizar el inventario id: " + i.getId());
					}
				}
				return tmp;
			}
		} catch (Exception e) {
			throw new RelativeException (Constantes.ERROR_CODE_READ, "Error al buscar inventario por id joya" + e.getMessage());
		}
    	return null;
    	
    }

	@Override
	public List<TrazabilidadWrapper> findTrazabilidad(Long idJoya) throws RelativeException {
		try {
			List<TrazabilidadWrapper> list;
			StringBuilder strQry = new StringBuilder("select distinct  hj.id as idHistoricoJoya , "
					+ " j.codigoJoya  as codigoJoya, COALESCE(l.nombreLote,'') as  nombreLote, hj.proceso  as proceso, b.descripcion  as ubicacion, "
					+ "f.codigo  as codigoFunda, hj.estado  as estadoJoya, hj.usuarioCreacion  as responsable, "
					+ " hj.fechaCreacion  as fechaActualizacion, COALESCE(vl.codigo,'')  as codigoVentaLote "
					+ "from TbMiHistoricoJoya  hj "
					+ "inner join TbMiJoya j on j.id = hj.tbMiJoya.id   "
					+ "inner join TbMiFunda f on f.id = j.tbMiFunda.id "
					+ "inner join TbMiInventario i on i.tbMiJoya.id = j.id "	
					+ "inner join TbMiMovInventario mv on mv.tbMiInventario.id = i.id  "
					+ "inner join TbMiBodega b on b.id = mv.tbMiBodega.id  "
					+ "left outer join TbMiJoyaLote jl on jl.tbMiJoya.id = j.id "
					+ "left outer join TbMiLote l on l.id = jl.tbMiLote.id "
					+ "left outer join TbMiVentaLote vl on l.tbMiVentaLote.id = vl.id "
					+ "where 1=1 and  hj.proceso = mv.proceso ");
			if(idJoya !=null) {
				strQry.append(" and j.id = :idJoya");
				
			}
			Query q = this.getEntityManager().createQuery(strQry.toString());
			if(idJoya !=null) {
				q.setParameter("idJoya",idJoya);
			}
			
			list = MidasOroUtil.getResultList( q.getResultList(),TrazabilidadWrapper.class);
			if(list != null && !list.isEmpty()) {
				return list;
			}
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RelativeException(Constantes.ERROR_CODE_CUSTOM,"AL BUSCAR TRAZABILIDAD");
		}
	}
}



