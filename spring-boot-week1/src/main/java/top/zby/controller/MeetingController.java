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

    /**
     * 检查会议室是否可用并添加会议
     *
     * @param meeting 会议信息对象，包含会议室、时间等信息
     * @return ResponseEntity<String> 响应实体，成功时返回"success"和200状态码，
     *         失败时返回"fail"和409状态码
     */
    @RequestMapping("/check")
    public ResponseEntity<String> check(@RequestBody Meeting meeting) {
        // 检查会议室是否可用，如果可用则添加会议
        if (meetingService.isRoomAvailable(meeting)) {
            meetingService.addMeeting(meeting);
            return ResponseEntity.ok("success");
        } else {
            // 会议室不可用时返回冲突状态
            return ResponseEntity.status(409).body("fail");
        }
    }

}