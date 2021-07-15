package training360.activitytracker;

import java.time.LocalDateTime;

public class ActivityTrackerMain {

    private final ActivityService service;

    public ActivityTrackerMain(ActivityService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        ActivityTrackerMain main = new ActivityTrackerMain(new ActivityService());
        System.out.println(main.service.createActivity(new Activity(LocalDateTime.now().minusDays(2), "Futás a hegyekben", Type.RUNNING)));
        System.out.println(main.service.createActivity(new Activity(LocalDateTime.now().minusDays(1), "Kosarazás a haverokkal", Type.BASKETBALL)));
        System.out.println(main.service.createActivity(new Activity(LocalDateTime.now(), "10 km biciklizés", Type.BIKING)));

    }
}
