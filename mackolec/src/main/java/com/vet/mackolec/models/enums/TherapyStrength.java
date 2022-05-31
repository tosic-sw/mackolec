package com.vet.mackolec.models.enums;

import java.util.HashMap;
import java.util.Map;

public enum TherapyStrength {
    MG100(0), MG200(1), MG300(2), MG400(3), MG500(4), UNDEFINED(5);
    
    private int value;
    private static Map<Integer, TherapyStrength> map = new HashMap<Integer, TherapyStrength>();

    private TherapyStrength(int value) {
        this.value = value;
    }

    static {
        for (TherapyStrength pageType : TherapyStrength.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static TherapyStrength valueOf(int pageType) {
        return (TherapyStrength) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
