package ec.edu.ups.sd64.eva.business;

import java.util.List;


import ec.edu.ups.sd64.eva.dao.UniversidadDao;
import ec.edu.ups.sd64.eva.model.Universidad;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class GestionDatos {

    @Inject
    private UniversidadDao daoUni;

    @PostConstruct
    public void init() {
        System.out.println("Inicializando datos...");

        Universidad U1 = new Universidad();
        U1.setNombre("espe");
        U1.setDescripcion("Universidad Central");
        U1.setDireccion("Quito");
        U1.setSerial("111");
        daoUni.insert(U1);
        
        Universidad u2 = new Universidad();
        u2.setNombre("UTPL");
        u2.setDescripcion("Universidad de Loja");
        u2.setDireccion("Loja");
        u2.setSerial("222");
        daoUni.insert(u2);

        

        // Imprimir los libros para verificar la inserción
        System.out.println("\n------------- Libros");
        List<Universidad> list = daoUni.getAll();
        for (Universidad uni : list) {
            System.out.println(uni);
        }
    }
}
