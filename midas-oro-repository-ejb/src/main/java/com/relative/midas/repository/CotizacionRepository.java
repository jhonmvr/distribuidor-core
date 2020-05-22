package com.relative.midas.repository;
import java.util.List;
import javax.ejb.Local;
import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.CrudRepository;
import com.relative.midas.enums.EstadoMidasEnum;
import com.relative.midas.model.TbMiCotizacion;
@Local
public interface CotizacionRepository  extends CrudRepository<Long,TbMiCotizacion> {
	public Long countfindByAll(EstadoMidasEnum estado) throws RelativeException;
	public List<TbMiCotizacion> findByAllPaged(EstadoMidasEnum estado,int page, int pageSize);
	public List<TbMiCotizacion> findByAll(EstadoMidasEnum estado) throws RelativeException;
}



