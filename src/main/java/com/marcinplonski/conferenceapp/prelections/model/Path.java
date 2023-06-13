package com.marcinplonski.conferenceapp.prelections.model;

public enum Path {
    BEZPIECZENSTWO_SYSTEMOW("Bezpieczeństwo systemów"),
    MARKETING_INTERNETOWY("Marketing Internetowy"),
    PROJEKTOWANIE_USER_EXPIRIENCE("Projekotwanie UX");

    private String desc;

    Path(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
