package com.aegis.TechMarket.Enumerators;

public class Enums {

    public enum Status {
        Active("Active"),
        Cancelled("Cancelled"),
        Sold("Sold"),
        Expired("Expired");

        public final String label;

        Status(String label) {
            this.label = label;
        }

        public String getDisplayName() {
            return label;
        }

    }

    public enum Category {
        SOFTWARE("Software"),
        DESKTOP_PC("Desktop PC"),
        SERVERS ("Servers"),
        MONITORS("Monitors"),
        COMPONENTS ("Components"),
        NETWORK_AND_WIRELESS("Network and wireless"),
        PRINTERS ("Printers"),
        USB_STICKS("USB Sticks"),
        LAPTOPS("Laptops"),
        TABLETS("Tablets"),
        MEMORY_CARDS_AND_DEVICES("Memory cards and devices"),
        GAMING_CONSOLES("Gaming consoles"),
        DRONES("Drones"),
        OTHER("Other");

        public final String label;

        Category(String label) {
            this.label = label;
        }

        public String getDisplayName() {
            return label;
        }
    }
}
