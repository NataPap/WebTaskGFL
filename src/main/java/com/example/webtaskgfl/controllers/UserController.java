package com.example.webtaskgfl.controllers;

import com.example.webtaskgfl.entities.Address;
import com.example.webtaskgfl.entities.User;
import com.example.webtaskgfl.repositories.AddressRepository;
import com.example.webtaskgfl.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
@Autowired
    public UserController(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
    this.addressRepository = addressRepository;
}

    @GetMapping("/list")
    public String showAll(Model model) {
       model.addAttribute("users", userRepository.findAll());
        return "resultList";
    }
    @PostMapping("/add")
    public String addUser (@ModelAttribute User user, Model model) {
        try {
            model.addAttribute("user", user);
            User userNew = new User();
            userNew.setId(user.getId());
            userNew.setName(user.getName());
            userNew.setEmail(user.getEmail());
            Address addressNew = new Address();
            addressNew.setCity(user.getAddress().getCity());
            addressNew.setStreet(user.getAddress().getStreet());
            addressNew.setSuite(user.getAddress().getSuite());
            addressNew.setZipcode(user.getAddress().getZipcode());
            addressNew.setId(user.getId());
            addressRepository.save(addressNew);
            userNew.setAddress(addressNew);
            userNew.setPhone(user.getPhone());
            userRepository.save(userNew);
        } catch (Exception ignored) {}
            return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid user id"));
        userRepository.delete(user);

        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String showUpdatePage(@PathVariable("id") int id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid user id"));
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, User user) {
        try {
            userRepository.save(user);
        } catch (Exception ignored){}

        return "redirect:/list";
    }
}
