package training360.activitytracker;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Service
public class ActivityService {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
    private final EntityManager manager = factory.createEntityManager();

    public Activity createActivity(Activity activity) {
        manager.getTransaction().begin();
        manager.persist(activity);
        manager.getTransaction().commit();
        return activity;
    }
}
