package lt.vu.persistence;

import lt.vu.entities.Director;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class DirectorsDAO {

    @Inject
    private EntityManager em;

    public List<Director> loadAll() {
        return em.createNamedQuery("Director.findAll", Director.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Director director){
        this.em.persist(director);
    }

    public Director findOne(Integer id) {
        return em.find(Director.class, id);
    }
    public Director findAllDirectors(Integer id) {
        return em.find(Director.class, id);
    }
}

