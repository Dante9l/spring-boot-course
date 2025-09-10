package top.zby.enums;

import lombok.Getter;

@Getter
public enum ExpressStatus {
    NOT_FOUND("未找到"),
    WAITING("待取件"),
    DELIVERING("配送中"),
    SUCCESS("签收");

    private final String label;
    ExpressStatus( String label) {
        this.label = label;
    }
}
