package training360.activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

public class ActivityDaoIT {

    private ActivityDao dao;

    // with JPA-Hibernate
    @BeforeEach
    void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        dao = new ActivityDao(factory);
    }

    // with Flyway
//    @BeforeEach
//    void init() throws SQLException {
//        MariaDbDataSource dataSource = new MariaDbDataSource();
//        dataSource.setUrl("jdbc:mariadb://localhost:3308/activitytracker_jpa?useUnicode=true");
//        dataSource.setUser("activitytracker_jpa");
//        dataSource.setPassword("activitytracker_jpa");
//
//        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
//        flyway.clean();
//        flyway.migrate();
//
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
//        dao = new ActivityDao(factory);
//    }

    @Test
    void saveThenFindTest() {
        Activity activity = new Activity(LocalDateTime.now().minusHours(2), "Futás a hegyekben", Type.RUNNING);
        dao.saveActivity(activity);

        Long id = activity.getId();
        Activity result = dao.findActivityById(id);
        assertEquals(Type.RUNNING, result.getType());
    }

    @Test
    void saveThenFindActivityCreatedAtTest() {
        Activity activity = new Activity(LocalDateTime.now().minusHours(2), "Futás a hegyekben", Type.RUNNING);
        dao.saveActivity(activity);

        assertEquals(LocalDateTime.now().withSecond(0).withNano(0), activity.getCreatedAt().withSecond(0).withNano(0));
    }

    @Test
    void saveThenListAllTest() {
        dao.saveActivity(new Activity(LocalDateTime.now().minusHours(2), "Futás a hegyekben", Type.RUNNING));
        dao.saveActivity(new Activity(LocalDateTime.now().minusHours(1), "10km bringázás", Type.BIKING));

        List<Activity> result = dao.listActivities();

        assertThat(result)
                .hasSize(2)
                .extracting(Activity::getType)
                .containsExactly(Type.RUNNING, Type.BIKING);
    }

    @Test
    void updateActivityTest() throws InterruptedException {
        Activity activity = new Activity(LocalDateTime.now().minusHours(2), "Futás a hegyekben", Type.RUNNING);
        dao.saveActivity(activity);
        assertNull(activity.getUpdatedAt());


        dao.updateActivity(activity.getId(), "3km futás aszfalton");
        Activity updatedActivity = dao.findActivityById(activity.getId());

        assertNotNull(updatedActivity.getUpdatedAt());
        assertEquals("3km futás aszfalton", updatedActivity.getDesc());
    }

    @Test
    void labelsTest() {
        Activity activity = new Activity(LocalDateTime.now().minusHours(2), "Futás a hegyekben", Type.RUNNING);
        activity.setLabels(Set.of("Running", "5km", "Hill"));
        dao.saveActivity(activity);

        Activity anotherActivity = dao.findActivityByIdWithLabels(activity.getId());
        assertEquals(Set.of("Running", "5km", "Hill"), anotherActivity.getLabels());
    }

    @Test
    void trackPointsTest() {
        TrackPoint trackPoint = new TrackPoint(LocalDateTime.now().minusHours(1), 47.123103, 19.17625);
        TrackPoint trackPoint2 = new TrackPoint(LocalDateTime.now().minusHours(2), 47.123101, 19.17623);

        Activity activity = new Activity(LocalDateTime.now().minusHours(4), "Futás a hegyekben", Type.RUNNING);
        activity.addTrackPoint(trackPoint);
        activity.addTrackPoint(trackPoint2);
        dao.saveActivity(activity);

        Activity anotherActivity = dao.findActivityByIdWithTrackPoints(activity.getId());
        assertEquals(2, anotherActivity.getTrackPoints().size());
        assertEquals(47.123101, anotherActivity.getTrackPoints().get(0).getLat());
    }

    @Test
    void trackPointsAddTest() {
        Activity activity = new Activity(LocalDateTime.now().minusHours(2), "Futás a hegyekben", Type.RUNNING);
        dao.saveActivity(activity);

        dao.addTrackPoint(activity.getId(), new TrackPoint(LocalDateTime.now().minusHours(2), 47.123103, 19.17625));
        dao.addTrackPoint(activity.getId(), new TrackPoint(LocalDateTime.now().minusHours(1), 47.123101, 19.17623));

        Activity anotherActivity = dao.findActivityByIdWithTrackPoints(activity.getId());
        assertThat(anotherActivity.getTrackPoints())
                .hasSize(2)
                .extracting(TrackPoint::getLat, TrackPoint::getLon)
                .containsExactly(tuple(47.123103, 19.17625), tuple(47.123101, 19.17623));
    }
}
