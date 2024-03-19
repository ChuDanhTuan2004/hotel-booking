package org.example.demobookinghotel.controller;

import jakarta.validation.Valid;
import org.example.demobookinghotel.model.Room;
import org.example.demobookinghotel.model.User;
import org.example.demobookinghotel.service.BookedRoomService;
import org.example.demobookinghotel.service.RoomService;
import org.example.demobookinghotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    RoomService roomService;
    @Autowired
    BookedRoomService bookedRoomService;

    @Value("${file-upload}")
    private String upload;

    @GetMapping
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("user",  new User());
        return modelAndView;
    }
    @GetMapping("/showAllUser")
    public ModelAndView showAllUser(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/admin/list/listUser");
        modelAndView.addObject("listUser", userService.findAll(pageable));
        return modelAndView;
    }
    @PostMapping("/deleteUser")
    public ModelAndView deleteUser(@RequestParam(value = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/showAllUser");
        userService.delete(id);
        return modelAndView;
    }
    @GetMapping("/showAllRoom")
    public ModelAndView showAllRoom(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/admin/list/listRoom");
        modelAndView.addObject("listRoom", roomService.findAll(pageable));
        return modelAndView;
    }
    @GetMapping("/{id}/showRoom")
    public ModelAndView showRoom(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/admin/view/room");
        modelAndView.addObject("room", roomService.findById(id).get());
        return modelAndView;
    }
    @GetMapping("/showAddRoom")
    public ModelAndView addNewRoom(){
        ModelAndView modelAndView = new ModelAndView("/admin/create/createRoom");
        modelAndView.addObject("room", new Room());
        return modelAndView;
    }
    @PostMapping("/addNewRoom")
    public ModelAndView addNewRoom(@RequestParam(value = "file", required = false) MultipartFile file,
                                   @Valid Room room,
                                   BindingResult result) throws IOException {
        ModelAndView modelAndView;
        String fileName = file.getOriginalFilename();
        if (result.hasErrors()){
            modelAndView = new ModelAndView("/admin/create/createRoom");
            modelAndView.addObject("listERR", result.getAllErrors());
            return modelAndView;
        } if (fileName.isEmpty()){
            modelAndView = new ModelAndView("redirect:/admin/showAllRoom");
            roomService.save(room);
            return modelAndView;
        }else {
            modelAndView = new ModelAndView("redirect:/admin/showAllRoom");
            FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
            room.setDirectionImage(fileName);
            roomService.save(room);
            return modelAndView;
        }
    }
    @GetMapping("/{id}/editRoom")
    public ModelAndView editRoom(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/admin/edit/editRoom");
        modelAndView.addObject("room", roomService.findById(id).get());
        return modelAndView;
    }
    @PostMapping("/editRoom")
    public ModelAndView editRoom(@RequestParam(value = "file", required = false) MultipartFile file,
                                   @Valid Room room,
                                   BindingResult result) throws IOException {
        ModelAndView modelAndView;
        String fileName = file.getOriginalFilename();
        if (result.hasErrors()){
            modelAndView = new ModelAndView("/admin/edit/editRoom");
            modelAndView.addObject("listERR", result.getAllErrors());
            if (fileName.isEmpty()){
                modelAndView.addObject("fileERR", "Vui long nhap anh");
            }
            return modelAndView;
        }if (fileName.isEmpty()){
            modelAndView = new ModelAndView("redirect:/admin/showAllRoom");
            roomService.save(room);
            return modelAndView;
        }else {
            modelAndView = new ModelAndView("redirect:/admin/showAllRoom");
            FileCopyUtils.copy(file.getBytes(), new File(upload + fileName));
            room.setDirectionImage(fileName);
            roomService.save(room);
            return modelAndView;
        }
    }
    @PostMapping("/deleteRoom")
    public ModelAndView deleteRoom(@RequestParam(value = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/showAllRoom");
        roomService.delete(id);
        return modelAndView;
    }
    @GetMapping("/showAllBooking")
    public ModelAndView showAllBooking(Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("/admin/list/listBooking");
        modelAndView.addObject("listBooking", bookedRoomService.findAll(pageable));
        return modelAndView;
    }
    @PostMapping("/deleteBooking")
    public ModelAndView deleteBooking(@RequestParam(value = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("redirect:/showAllBooking");
        bookedRoomService.delete(id);
        return modelAndView;
    }
}
