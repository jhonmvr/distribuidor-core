package com.relative.midas.repository.imp;

import java.util.List;

import javax.ejb.Stateless;

import com.relative.core.exception.RelativeException;
import com.relative.core.persistence.GeneralRepositoryImp;
import com.relative.core.util.main.Constantes;
import com.relative.midas.model.TbMiCuenta;
import com.relative.midas.repository.CuentaRepository;
import com.relative.midas.repository.spec.CuentaByCodigo;

@Stateless(mappedName = "cuentaRepository")
public class CuentaRepositoryImp extends GeneralRepositoryImp<Long, TbMiCuenta> implements CuentaRepository {

	@Override
	public TbMiCuenta findCuentaByCodigo(String cuenta) throws RelativeException {
		List<TbMiCuenta> tmp;

		try {
			tmp = this.findAllBySpecification(new CuentaByCodigo(cuenta));
			if (tmp != null && !tmp.isEmpty()) {
				return tmp.get(0);
			}
		} catch (Exception e) {
			throw new RelativeException(Constantes.ERROR_CODE_READ, "NO SE PUEDE LEER LA INFORMACION DE LA CUENTA");
		}
		return null;

	}
}
