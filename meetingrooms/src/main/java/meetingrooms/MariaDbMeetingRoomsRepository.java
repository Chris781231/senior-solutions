package meetingrooms;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MariaDbMeetingRoomsRepository implements MeetingRoomsRepository {

    private final JdbcTemplate jdbcTemplate;

    public MariaDbMeetingRoomsRepository() {
        try {
            MariaDbDataSource dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3308/meetingrooms?useUnicode=true");
            dataSource.setUser("meetingrooms");
            dataSource.setPassword("meetingrooms");

            Flyway flyway = Flyway.configure().dataSource(dataSource).load();
            flyway.migrate();

            this.jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot create datasource", e);
        }
    }

    @Override
    public void saveMeetingRooms(String name, double width, double length) {
        jdbcTemplate.update("INSERT INTO `meetingrooms` (`name`, `length`, `width`) VALUES (?, ?, ?)", name, length, width);
    }

    @Override
    public List<String> listMeetingRoomsByName() {
        return jdbcTemplate.query("SELECT `name` FROM `meetingrooms` ORDER BY `name`",
                (rs, i) -> rs.getString("name"));
    }

    @Override
    public List<String> listMeetingRoomsByNameReversed() {
        return jdbcTemplate.query("SELECT `name` FROM `meetingrooms` ORDER BY `name` DESC",
                (rs, i) -> rs.getString("name"));
    }

    @Override
    public List<String> listMeetingRoomsByNameEverySecond() {
        return jdbcTemplate.query("SELECT `name` FROM" +
                        "(SELECT `name`, row_number() over (ORDER BY `name`) as `rn` FROM `meetingrooms`) as `w_rownum`" +
                        "WHERE w_rownum.rn % 2 = 0",
                (rs, i) -> rs.getString("name"));
    }

    @Override
    public List<MeetingRoom> listAreas() {
        return jdbcTemplate.query("SELECT `id`, `name`, `length`, `width` FROM `meetingrooms` ORDER BY `width` * `length` DESC, `name`",
                (rs, i) -> new MeetingRoom(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getDouble("width"),
                        rs.getDouble("length")));
    }

    @Override
    public MeetingRoom findByName(String name) {
        List<MeetingRoom> result = jdbcTemplate.query("SELECT `id`, `name`, `length`, `width` FROM `meetingrooms` WHERE `name` = ?",
                (rs, i) -> new MeetingRoom(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getDouble("width"),
                        rs.getDouble("length")),
                name);
        return result.size() != 0 ? result.get(0) : null;
    }

    @Override
    public List<MeetingRoom> findByNamePart(String namePart) {
        return jdbcTemplate.query("SELECT `id`, `name`, `length`, `width` FROM `meetingrooms` WHERE `name` LIKE ? ORDER BY `name`",
                (rs, i) -> new MeetingRoom(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getDouble("width"),
                        rs.getDouble("length")),
                "%" + namePart + "%");
    }

    @Override
    public List<MeetingRoom> findByAreaLargerThan(int area) {
        return jdbcTemplate.query("SELECT `id`, `name`, `length`, `width` FROM `meetingrooms` WHERE `width` * `length` > ?",
                (rs, i) -> new MeetingRoom(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getDouble("width"),
                        rs.getDouble("length")),
                area);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM `meetingrooms`");
    }
}
