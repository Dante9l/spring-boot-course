package top.zby.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zby.Service.MeetingService;
import top.zby.common.Meeting;

@RestController
@RequestMapping("/meetings")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    @RequestMapping("/check")
    public ResponseEntity<String> check(@RequestBody Meeting meeting) {
        if (meetingService.isRoomAvailable(meeting)) {
            meetingService.addMeeting(meeting);
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.status(409).body("fail");
        }
    }
}