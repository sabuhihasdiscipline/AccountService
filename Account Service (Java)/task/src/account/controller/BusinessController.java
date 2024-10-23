package account.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class BusinessController {
    @GetMapping("/empl/payment")
    public ResponseEntity<?> getEmployeesPayrolls() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/acct/payments")
    public ResponseEntity<?> uploadEmployeesPayrolls() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/acct/payments")
    public ResponseEntity<?> updatePaymentInformation() {
        return ResponseEntity.ok().build();
    }
}
