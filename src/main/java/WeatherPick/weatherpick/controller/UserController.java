package WeatherPick.weatherpick.controller;


import WeatherPick.weatherpick.domain.user.dto.*;
import WeatherPick.weatherpick.domain.user.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import WeatherPick.weatherpick.domain.user.repository.UserRepository;




@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    //바로 로그인 사이트로 보내기
    @GetMapping("/")
    public String home() {
        return "redirect:/login"; // /login 페이지로 리디렉트
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

// RestAPI
//    @PostMapping("/login")
//    public String loginProcess(UserLoginDto dto) {
//        userService.checkPassword(dto.getUsername(),dto.getPassword());
//
//        return "redirect:/login";
//    }


    @GetMapping("/main")
    public String mainPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        return "main";
    }

    //회원가입 사이트
    @GetMapping("/user/join")
    public String joinPage(){
        return "join";
    }

    @PostMapping("/user/join")
    public String joinProcess(SignRequestDto dto){
        userService.createOneUser(dto);

        return "redirect:/login";
    }


//    // 회원 수정
//    @GetMapping("/user/update/{username}")
//    public String updatePage(@PathVariable("username") String username, Model model) {
//
//        // 본인 또는 ADMIN 권한만 접근 가능
//        if (userService.isAccess(username)) {
//            UserResponseDto dto = userService.readOneUser(username);
//            model.addAttribute("USER", dto);
//            return "update";
//        }
//
//        return "redirect:/login";
//    }

//    // 회원 수정
//    @PostMapping("/user/update/{username}")
//    public String updateProcess(@PathVariable("username") String username, UpdateUserRequestDto dto) {
//
//        // 본인 또는 ADMIN 권한만 접근 가능
//        if (userService.isAccess(username)) {
//            userService.updateOneUser(dto, username);
//        }
//
//        return "redirect:/user/update/" + username;
//    }
//    // 회원 삭제
//    @PostMapping("/user/delete/{username}")
//    public String deleteProcess(@PathVariable("username") String username){
//        // 본인 또는 ADMIN 권한만 접근 가능
//        if (userService.isAccess(username)) {
//            userService.deleteOneUser(username);
//        }
//        else {
//            return "redirect:/user/update/" + username;
//        }
//        return "redirect:/login";
//
//    }


    // 아이디 중복 확인
    @GetMapping("/user/check-username")
    @ResponseBody
    public Map<String, Boolean> checkUsername(@RequestParam("username") String username) {
        boolean exists = userRepository.existsByUsername(username);
        return Collections.singletonMap("exists", exists);
    }

    // 이메일 중복 확인
    @GetMapping("/user/check-email")
    @ResponseBody
    public Map<String, Boolean> checkEmail(@RequestParam("email") String email) {
        boolean exists = userRepository.existsByEmail(email);
        return Collections.singletonMap("exists", exists);
    }

}