package training360.activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
}
