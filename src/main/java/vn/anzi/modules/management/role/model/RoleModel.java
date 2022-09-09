package vn.anzi.modules.management.role.model;

import java.util.HashMap;
import java.util.Map;

public enum RoleModel {
    STAFF(1),
    MANAGER(2);

    private int value;
    private static Map map = new HashMap<>();

    RoleModel(int value) {
        this.value = value;
    }

    static {
        for (RoleModel status : RoleModel.values()) {
            RoleModel.map.put(status.value, status);
        }
    }

    public static RoleModel valueOf(int contractUserRole) {
        return (RoleModel) RoleModel.map.get(contractUserRole);
    }

    public int getValue() {
        return value;
    }
}
