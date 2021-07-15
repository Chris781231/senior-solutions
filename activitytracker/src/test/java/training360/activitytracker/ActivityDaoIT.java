package training360.activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void saveThenListAllTest() {
        dao.saveActivity(new Activity(LocalDateTime.now().minusHours(2), "Futás a hegyekben", Type.RUNNING));
        dao.saveActivity(new Activity(LocalDateTime.now().minusHours(1), "10km bringázás", Type.BIKING));

        List<Activity> result = dao.listActivities();

        assertThat(result)
                .hasSize(2)
                .extracting(Activity::getType)
                .containsExactly(Type.RUNNING, Type.BIKING);
    }
}
