package com.spidermanteam.spiderpuppies.security.models;

import com.spidermanteam.spiderpuppies.models.User;

public class JwtUser {
        private String token;
        private int id;
        private String username;
        private String role;

        public JwtUser() {

        }

        public JwtUser(User user, String token, String role) {
            this.setId(user.getId());
            this.setToken(token);
            this.setUsername(user.getUsername());
            this.setRole(role);
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }