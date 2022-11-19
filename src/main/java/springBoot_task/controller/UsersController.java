package springBoot_task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springBoot_task.service.UserService;
import springBoot_task.model.User;


@Controller
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String print(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/users")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping(value = "/users")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }



    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(name = "id") int id) {
        model.addAttribute("user", userService.get(id));
        return "edit";
    }
    @PostMapping(value = "/users/")
    public String update(@ModelAttribute("user") User user, @RequestParam(name = "id") int id) {
        userService.edit(id, user);
        return "redirect:/users";
    }
    @PostMapping(value = "/delete/")
    public String delete(@RequestParam(name = "id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping(value = "{/delete")
    public String isExistById(@RequestParam User user) {
        userService.delete(user.getId());
        return "redirect:/users";
    }


}

