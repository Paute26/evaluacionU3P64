package ec.edu.ups.sd64.eva.dao;

import java.util.List;

import ec.edu.ups.sd64.eva.model.Universidad;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UniversidadDao {

    @PersistenceContext
    private EntityManager em;

    public void insert(Universidad uni) {
        em.persist(uni);
    }

    public void update(Universidad uni) {
        em.merge(uni);
    }

    public void remove(int codigo) {
        Universidad uni = em.find(Universidad.class, codigo);
        if (uni != null) {
            em.remove(uni);
        }
    }

    public Universidad read(int codigo) {
        return em.find(Universidad.class, codigo);
    }

    public List<Universidad> getAll() {
        String jpql = "SELECT u FROM Universidad u";
        Query q = em.createQuery(jpql, Universidad.class);
        return q.getResultList();
    }

    public Universidad getUniPorSerial(String serial) {
        String jpql = "SELECT u FROM Universidad u WHERE u.serial = :serial";
        Query q = em.createQuery(jpql, Universidad.class);
        q.setParameter("serial", serial);
        List<Universidad> universidades = q.getResultList();
        if (!universidades.isEmpty()) {
            return universidades.get(0);
        }
        return null;
    }
}
