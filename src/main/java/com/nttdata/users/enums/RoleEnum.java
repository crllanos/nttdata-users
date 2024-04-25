package com.nttdata.users.enums;

public enum RoleEnum {
    USER("USER"),
    ADMIN("ADMIN"),
    SUPER_ADMIN("SUPER_ADMIN");
    private String name;

    RoleEnum(String name) {
        this.name = name;
    }
}