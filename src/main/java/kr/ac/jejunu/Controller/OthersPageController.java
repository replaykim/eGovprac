package kr.ac.jejunu.Controller;

import kr.ac.jejunu.entity.Friend;
import kr.ac.jejunu.entity.FriendRelation;
import kr.ac.jejunu.entity.User;
import kr.ac.jejunu.service.FriendService;
import kr.ac.jejunu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by replay on 2017. 6. 13..
 */
@Controller
public class OthersPageController {
    @Autowired
    UserService userService;
    @Autowired
    FriendService friendService;

    @GetMapping("/others/{no}")
    public String viewOthersPage(@PathVariable String no, HttpServletRequest request, ModelMap modelmap)  {

        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User myUser = userService.findOneByEmail(email);

        Long id = Long.valueOf(no);
        User user = userService.findOneById(id);


        if(id == myUser.getNo()){
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
        }else{
            //관계가 없을경우
            modelmap.addAttribute("relation", null);

        }

        modelmap.addAttribute("user", user);
        return "others";
    }

    @PostMapping("/others/{no}/friendrequest")
    public String friendRequest(@PathVariable String no, HttpServletRequest request, Friend friend){
        try {
            HttpSession session = request.getSession(false);
            String email = (String) session.getAttribute("email");
            User myUser = userService.findOneByEmail(email);

            Long id = Long.valueOf(no);
            User otherUser = userService.findOneById(id);

            friend.setFriend1No(myUser);
            friend.setFriend2No(otherUser);

            friendService.requestFriend(friend);
        }catch (Exception e){

        }

        return "redirect:/others/"+no;
    }

    @PostMapping("/others/{no}/friendconfirm")
    public String friendConfirm(@PathVariable String no, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        User myUser = userService.findOneByEmail(email);

        Long id = Long.valueOf(no);
        User user = userService.findOneById(id);

        Friend relation = friendService.getRelation(myUser.getNo(), id);
        relation.setFriendRelation(FriendRelation.full);

        friendService.confirmFriend(relation);

        return "redirect:/others/"+no;
    }

}
