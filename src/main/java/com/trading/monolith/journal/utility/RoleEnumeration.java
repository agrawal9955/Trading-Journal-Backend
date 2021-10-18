package com.trading.monolith.journal.utility;

public enum RoleEnumeration {
    ADMIN("admin"), USER("user"), MODERATOR("moderator");

    private String role;

    RoleEnumeration(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
