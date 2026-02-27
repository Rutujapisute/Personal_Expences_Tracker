package com.example.Expences_TrackerUsingJWT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Expences_TrackerUsingJWT.Dao.Dao;
import com.example.Expences_TrackerUsingJWT.Model.users;

@RestController
public class LogoutController {

    @Autowired
    private Dao dao;

    @PostMapping("logoutAndDelete")
    public ResponseEntity<String> logoutAndDelete() {

        // 1️⃣ Get logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        // 2️⃣ Get user entity
        users usr = dao.getUserByEmail(email);

        if (usr != null) {
            // 3️⃣ Delete user by ID
            String msg = dao.deleteUserById(usr.getUid());

            // 4️⃣ Clear security context
            SecurityContextHolder.clearContext();

            return ResponseEntity.ok(msg);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}