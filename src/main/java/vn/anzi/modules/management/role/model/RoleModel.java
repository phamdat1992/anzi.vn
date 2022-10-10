package vn.anzi.modules.management.role.model;

import java.util.HashMap;
import java.util.Map;

public enum RoleModel {
    STAFF(1),
    MANAGER(2);

    private static final Map map = new HashMap<>();

    static {
        for (RoleModel status : RoleModel.values()) {
            RoleModel.map.put(status.value, status);
        }
    }

    private final int value;

    RoleModel(int value) {
        this.value = value;
    }

    public static RoleModel valueOf(int contractUserRole) {
        return (RoleModel) RoleModel.map.get(contractUserRole);
    }

    public int getValue() {
        return value;
    }
}
