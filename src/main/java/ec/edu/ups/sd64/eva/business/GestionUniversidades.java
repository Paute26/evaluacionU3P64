package ec.edu.ups.sd64.eva.business;

import java.util.List;


import ec.edu.ups.sd64.eva.dao.UniversidadDao;
import ec.edu.ups.sd64.eva.model.Universidad;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUniversidades {
    
    @Inject
    private UniversidadDao daoUni;

    public void guardarUni(Universidad univ) {
    	Universidad uni = daoUni.read(univ.getCodigo());
        if (uni != null){
        	daoUni.update(univ);
        } else {
        	daoUni.insert(univ);
        }
    }
    
    public void actualizarUni(Universidad univ) throws Exception {
    	Universidad uni = daoUni.read(univ.getCodigo());
        if (uni != null){
        	daoUni.update(univ);
        } else {
            throw new Exception("Universidad no existe");
        }
    }
    
    public Universidad getUniPorSerial(String secuencial) throws Exception {
        if (secuencial == null || secuencial.isEmpty()) {
            throw new Exception("Secuencial incorrecto");
        }
        return daoUni.getUniPorSerial(secuencial);
    }
    
    public void borrarUni(int codigo) {
    	daoUni.remove(codigo);
    }
    
    public List<Universidad> getUniversidades() {
        return daoUni.getAll();
    }
}
