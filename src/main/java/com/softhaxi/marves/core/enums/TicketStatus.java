package com.softhaxi.marves.core.enums;

public enum TicketStatus {
    OPEN("Dikirim"),
    START("Dalam Proses Perbaikan"),
    FINISH("Perbaikan Selesai"),
    REOPEN("Dibuka Kembali"),
    CLOSED("Selesai");

    private String value;

    TicketStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
