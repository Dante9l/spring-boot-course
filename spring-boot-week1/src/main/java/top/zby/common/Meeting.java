package top.zby.common;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Meeting {
    private long id;
    private LocalDate date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public boolean isOverlap(Meeting another) {
        return this.startTime.isBefore(another.endTime) && this.endTime.isAfter(another.startTime);
    }
}
