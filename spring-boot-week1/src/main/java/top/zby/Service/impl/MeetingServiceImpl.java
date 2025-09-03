package top.zby.Service.impl;

import org.springframework.stereotype.Service;
import top.zby.Service.MeetingService;
import top.zby.common.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ASUS
 */
@Service
public class MeetingServiceImpl implements MeetingService {
    private final List<Meeting> meetings = new ArrayList<>();
    @Override
    public boolean isRoomAvailable(Meeting newMeeting) {
        return meetings.stream().noneMatch(meeting -> meeting.isOverlap(newMeeting));
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }
}
