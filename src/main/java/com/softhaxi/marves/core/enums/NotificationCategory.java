package com.softhaxi.marves.core.enums;

public enum NotificationCategory {
    GENERAL("Pesan Umum"),
    GOODNEWS("Kabar Gembira"),
    BADNEWS("Kabar Dukacita"),
    EMERGENCY("Darurat"),
    OTHERS("Informasi");

    private String value;

    NotificationCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
