package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventCreator {
    public static Event createEvent(String start, String end, String name, String location, String description, int duration, int dayOfWeek, List<Integer> daysOfWeek) throws Exception {
        // start and end should both be in the format of 16:00 or null
        // dayOfWeek should be integers 1: Sunday, 2: Monday: 3 Tuesday;
        // TODO recurring event
        if (start == null && end == null) {
            if (dayOfWeek > 0 && dayOfWeek < 8) {
                return EventCreator.createEvent(duration, name, location, description, dayOfWeek);
            } else if (duration > 0) {
                return EventCreator.createEvent(duration, name, location, description);
            }
        } else if (start != null && end != null) {
            if (daysOfWeek == null) {
                return EventCreator.createEvent(Util.getNewNextWeekCalendar(dayOfWeek, start), Util.getNewNextWeekCalendar(dayOfWeek, end), name, location, description);
            }
            return EventCreator.createEvent(start, end, name, location, description, daysOfWeek);
        }
        throw new Exception("Incorrect input");
    }

    public static RecurringEvent createEvent(String start, String end, String name, String location, String description, List<Integer> daysOfWeek) {
        return new RecurringEvent(name, location, description, Util.getNewNextWeekCalendar(1, start), Util.getNewNextWeekCalendar(1, end), daysOfWeek);
    }

    public static IndividualEvent createEvent(Calendar start, Calendar end, String name, String location, String description) throws Exception {
        EventCreator.isEndAfterStart(start, end);
        EventCreator.isEventOnDifferentDay(start, end);
        return new IndividualEvent(start, end, name, location, description);
    }

    public static FlexibleEventWithDayField createEvent(int duration, String name, String location, String description, int dayOfWeek) {
        return new FlexibleEventWithDayField(duration, name, location, description, dayOfWeek);
    }

    public static FlexibleEvent createEvent(int duration, String name, String location, String description) {
        return new FlexibleEvent(duration, name, location, description);
    }

    public static GroupEvent createEvent(String name, String location, String description) {
        return new GroupEvent(name, location, description);
    }

    public static boolean isEndAfterStart(Calendar start, Calendar end) throws Exception {
        if (start.before(end)) {
            throw new Exception("Event ends before it starts");
        }
        return true;
    }

    public static boolean isEventOnDifferentDay(Calendar start, Calendar end) throws Exception {
        if (start.get(Calendar.DATE) == end.get(Calendar.DATE)) {
            return false;
        }
        throw new Exception("Same event can't happen on different days");
    }
}
