package org.example.demobookinghotel.controller;

import jakarta.validation.Valid;
import org.example.demobookinghotel.model.Role;
import org.example.demobookinghotel.model.Room;
import org.example.demobookinghotel.model.User;
import org.example.demobookinghotel.repository.RoleRepository;
import org.example.demobookinghotel.service.BookedRoomService;
import org.example.demobookinghotel.service.RoleService;
import org.example.demobookinghotel.service.RoomService;
import org.example.demobookinghotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoomService roomService;
    @Autowired
    BookedRoomService bookedRoomService;
    @Autowired
    RoleRepository roleRepository;

    @Value("${file-upload}")
    private String upload;

    @GetMapping
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("user",  new User());
        return modelAndView;
    }
    @GetMapping("/showAllRoom")
    public ModelAndView showAllRoom(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/user/list/listRoom");
        modelAndView.addObject("listRoom", roomService.findAll(pageable));
        return modelAndView;
    }
    @PostMapping("/addNewUser")
    public ModelAndView addNewUser(@Valid User user, BindingResult result){
        ModelAndView modelAndView;
        if (result.hasErrors()){

        }
        modelAndView = new ModelAndView("/login");
        for (Role role : roleRepository.findAll()){
            if (role.getId() == 2){
                user.setRole(role);
                userService.save(user);
                return modelAndView;
            }
        }
       return null;
    }


}
