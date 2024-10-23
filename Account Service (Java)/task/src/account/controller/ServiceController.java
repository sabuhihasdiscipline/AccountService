package account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/admin")
public class ServiceController {
    @PutMapping("/user/role")
    public ResponseEntity<?> changeUserRole() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsersInformation() {
        return ResponseEntity.ok().build();
    }
}
