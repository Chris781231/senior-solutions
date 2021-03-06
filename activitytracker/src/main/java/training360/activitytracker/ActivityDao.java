package training360.activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
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
        activity.setCreatedAt(LocalDateTime.now());
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

    @PreUpdate
    public void updateActivity(Long id, String desc) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Activity result = manager.find(Activity.class, id);
        result.setDesc(desc);
        result.setUpdatedAt(LocalDateTime.now());
        manager.getTransaction().commit();
        manager.close();
    }

    public Activity findActivityByIdWithLabels(Long id) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Activity result = manager.createQuery("select a from Activity a join fetch a.labels where a.id = :id",
                Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        manager.close();
        return result;
    }

    public void addTrackPoint(Long id, TrackPoint trackPoint) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Activity activity = manager.getReference(Activity.class, id);
        trackPoint.setActivity(activity);
        manager.persist(trackPoint);
        manager.getTransaction().commit();
        manager.close();
    }

    public Activity findActivityByIdWithTrackPoints(Long id) {
        EntityManager manager = factory.createEntityManager();
        Activity activity = manager.createQuery("select a from Activity a join fetch a.trackPoints where a.id = :id",
                Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        manager.close();
        return activity;
    }
}
