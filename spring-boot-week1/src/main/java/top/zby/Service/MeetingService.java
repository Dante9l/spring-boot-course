package top.zby.Service;

import top.zby.common.Meeting;

public interface MeetingService {
    boolean isRoomAvailable(Meeting newMeeting);
    void addMeeting(Meeting meeting);
}
