package meetengroomsjpa;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class MeetingRoomDao implements MeetingRoomRepository {

    private EntityManagerFactory factory;


    @Override
    public MeetingRoom save(String name, int width, int length) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        MeetingRoom meetingRoom = new MeetingRoom(name, width, length);
        manager.persist(meetingRoom);
        manager.getTransaction().commit();
        manager.close();
        return meetingRoom;
    }

    @Override
    public List<String> getMeetingRoomsOrderedByName() {
        EntityManager manager = factory.createEntityManager();
        List<String> meetingRooms = manager.createQuery("select m.name from MeetingRoom m order by m.name",
                String.class)
                .getResultList();
        manager.close();
        return meetingRooms;
    }

    @Override
    public List<String> getEverySecondMeetingRoom() {
        EntityManager manager = factory.createEntityManager();
        List<String> meetingRooms = manager.createQuery("select m.name from MeetingRoom m order by m.name",
                String.class)
                .getResultList();
        List<String> everySecondMeetingRooms = new ArrayList<>();
        for (int i = 1; i < meetingRooms.size(); i += 2) {
            everySecondMeetingRooms.add(meetingRooms.get(i));
        }
        manager.close();
        return everySecondMeetingRooms;
    }

    @Override
    public List<MeetingRoom> getMeetingRooms() {
        EntityManager manager = factory.createEntityManager();
        List<MeetingRoom> meetingRooms = manager.createQuery("select m from MeetingRoom m",
                MeetingRoom.class)
                .getResultList();
        manager.close();
        return meetingRooms;
    }

    @Override
    public MeetingRoom getExactMeetingRoomByName(String name) {
        EntityManager manager = factory.createEntityManager();
        MeetingRoom meetingRoom = manager.createQuery("select m from MeetingRoom m where m.name = :name order by m.name",
                MeetingRoom.class)
                .setParameter("name", name)
                .getSingleResult();
        manager.close();
        return meetingRoom;
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsByPrefix(String prefixOfName) {
        EntityManager manager = factory.createEntityManager();
        List<MeetingRoom> meetingRooms = manager.createQuery("select m from MeetingRoom m where m.name like :prefixOfName order by m.name",
                MeetingRoom.class)
                .setParameter("prefixOfName", prefixOfName)
                .getResultList();
        manager.close();
        return meetingRooms;
    }

    @Override
    public void deleteAll() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.createQuery("delete from MeetingRoom").executeUpdate();
        manager.getTransaction().commit();
        manager.close();
    }
}
