package training360.activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AreaDao {

    private EntityManagerFactory factory;

    public AreaDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveArea(Area area) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(area);
        manager.getTransaction().commit();
        manager.close();
    }

    public Area findAreaByName(String name) {
        EntityManager manager = factory.createEntityManager();
        Area area = manager.createQuery("select a from Area a join fetch a.activities where a.name = :name",
                Area.class)
                .setParameter("name", name)
                .getSingleResult();
        manager.close();
        return area;
    }
}
