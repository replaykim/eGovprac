package kr.ac.jejunu.Controller;

import kr.ac.jejunu.entity.Content;
import kr.ac.jejunu.entity.Friend;
import kr.ac.jejunu.entity.FriendRelation;
import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.ContentService;
import kr.ac.jejunu.service.FriendService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by replay on 2017. 6. 13..
 */
@Controller
public class OthersPageController {
    @Autowired
    UserService userService;
    @Autowired
    FriendService friendService;
    @Autowired
    ContentService contentService;

    @RequestMapping("/others/{no}")
    public String viewOthersPage(@PathVariable String no, HttpServletRequest request, ModelMap modelmap) {

        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User myUser = userService.findOneByEmail(email);

        Long id = Long.valueOf(no);
        User user = userService.findOneById(id);

        //내 이름이면 mypage로
        if (id == myUser.getNo()) {
            return "redirect:/mypage";
        }

        Friend relation = friendService.getRelation(myUser.getNo(), id);


        if (relation != null) {
            //누구의 요청인지 체크
            if (relation.getFriendRelation().toString().equals("half")) {
                if (myUser.getNo() == relation.getFriend1No().getNo()) {
                    modelmap.addAttribute("relation", "request");

                } else {
                    modelmap.addAttribute("relation", "confirm");
                }
            } else {
                //full 일경우
                modelmap.addAttribute("relation", relation.getFriendRelation().toString());
            }
        } else {
            //관계가 없을경우
            modelmap.addAttribute("relation", null);

        }
        PageRequest pageRequest = new PageRequest(0, 100, new Sort(Sort.Direction.DESC, "registDate"));
        Page<Content> result = contentService.findByWallNosort(user, pageRequest);

        List<Content> contents = result.getContent();
//        List<Content> contents = contentService.findAllByWall(user);
        modelmap.addAttribute("requestFrom", request.getRequestURI());
        modelmap.addAttribute("result", contents);
        modelmap.addAttribute("user", user);
        return "others";
    }

    @PostMapping("/others/{no}/friendrequest")
    public String friendRequest(@PathVariable String no, HttpServletRequest request, Friend friend) {
        try {
            HttpSession session = request.getSession(false);
            String email = (String) session.getAttribute("email");
            User myUser = userService.findOneByEmail(email);

            Long id = Long.valueOf(no);
            User otherUser = userService.findOneById(id);

            friend.setFriend1No(myUser);
            friend.setFriend2No(otherUser);

            friendService.requestFriend(friend);
        } catch (Exception e) {

        }

        return "redirect:/others/" + no;
    }

    @PostMapping("/others/{no}/friendconfirm")
    public String friendConfirm(@PathVariable String no, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User myUser = userService.findOneByEmail(email);

        Long id = Long.valueOf(no);
        User user = userService.findOneById(id);

        Friend relation = friendService.getRelation(myUser.getNo(), id);
        relation.setFriendRelation(FriendRelation.full);

        friendService.confirmFriend(relation);

        return "redirect:/others/" + no;
    }


    @PostMapping("/others/{no}/savecontentinothers")
    public String saveContent(@PathVariable String no, HttpServletRequest request, @RequestParam(name = "content") String contents, ModelMap modelMap) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User myUser = userService.findOneByEmail(email);

        Long id = Long.valueOf(no);
        User wallUser = userService.findOneById(id);

        //관계 조사
        Friend relation = friendService.getRelation(myUser.getNo(), id);

        if (relation == null) {
            modelMap.addAttribute("message", "친구가 아닙니다.");

            return "forward:/others/" + no;
        } else if (relation.getFriendRelation().toString().equals("half")) {
            modelMap.addAttribute("message", "친구요청을 아직 받지 않았습니다.");

            return "forward:/others/" + no;
        } else {
            Content content = new Content();
            content.setContents(contents);
            content.setUser(myUser);

            content.setWall(wallUser);

            contentService.save(content);

            return "redirect:/others/" + no;
        }

    }
}
