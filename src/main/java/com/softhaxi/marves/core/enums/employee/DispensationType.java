package com.softhaxi.marves.core.enums.employee;

public enum DispensationType {
    SICK("Sakit"),
    LEAVE("Cuti"),
    ASSIGNMENT("Tugas"),
    OTHERS("Lainnya");

    private String value;

    DispensationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
