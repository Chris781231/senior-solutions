package training360.activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityDao {

    private EntityManagerFactory factory;

    public ActivityDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveActivity(Activity activity) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(activity);
        manager.getTransaction().commit();
        manager.close();
    }

    public Activity findActivityById(Long id) {
        EntityManager manager = factory.createEntityManager();
        Activity result = manager.find(Activity.class, id);
        manager.close();
        return result;
    }

    public List<Activity> listActivities() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        List<Activity> result = manager.createQuery("select a from Activity a order by a.id", Activity.class)
                .getResultList();
        manager.getTransaction().commit();
        manager.close();
        return result;
    }
}
