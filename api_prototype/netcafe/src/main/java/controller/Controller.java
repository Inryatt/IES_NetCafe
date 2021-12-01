package controller;

import model.Machine;
import model.MachineUsage;
import model.Software;
import model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/machines")
    public List<Machine> machines() {
        return StaticData.machines;
    }

    @GetMapping("/users")
    public List<User> users() {
        return StaticData.users;
    }

    @GetMapping("/softwares")
    public List<Software> softwares() {
        return StaticData.softwares;
    }

    @GetMapping("/usages")
    public List<MachineUsage> usages() {
        return StaticData.currentMachineUsages;
    }
}
