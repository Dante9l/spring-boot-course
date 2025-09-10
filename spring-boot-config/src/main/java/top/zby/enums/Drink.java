package top.zby.enums;

import lombok.Getter;

/**
 * @author ASUS
 */

@Getter
public enum Drink {
    TEA("茶", 15.0),
    COFFEE("咖啡", 25.0),
    WATER("水", 5.0),
    BEER("啤酒", 30.0),
    UNKNOWN("未知", 0.0);

    private final String label;
    private final double price;
    
    Drink(String label, double price) {
        this.label = label;
        this.price = price;
    }

}
