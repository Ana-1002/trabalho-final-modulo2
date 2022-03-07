package com.dbc.enums;

public enum Categories {

    COMBATE_A_FOME("Combate a Fome"),
    CRIANÇAS("Crianças"),
    ENFERMOS("Enfermos"),
    COMBATE_A_COVID_19("Combate a COVID-19"),
    CAUSAS_AMBIENTAIS("Causas Ambientais"),
    SOBREVIVENTES_DE_GUERRA("Sobreviventes de Guerra"),
    ANIMAIS("Animais"),
    SONHOS("Sonhos"),
    POBREZA("Pobreza"),
    OUTROS("Outros");

    private String description;

    Categories(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public static void listCategories () {
        int count = 1;
        for (Categories cat:
             Categories.values()) {
            System.out.println(count++ + " - " + cat.getDescription());
        }
    }
}
