package com.example.webtaskgfl.controllers;

import com.example.webtaskgfl.entities.Address;
import com.example.webtaskgfl.entities.User;
import com.example.webtaskgfl.repositories.AddressRepository;
import com.example.webtaskgfl.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String addExpression(@RequestParam User user) {
        try {
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
}
