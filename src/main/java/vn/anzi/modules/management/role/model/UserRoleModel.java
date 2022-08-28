package vn.anzi.modules.management.role.model;

import java.util.HashMap;
import java.util.Map;

public enum UserRoleModel {
    STAFF(1),
    MANAGER(2);

    private int value;
    private static Map map = new HashMap<>();

    UserRoleModel(int value) {
        this.value = value;
    }

    static {
        for (UserRoleModel status : UserRoleModel.values()) {
            UserRoleModel.map.put(status.value, status);
        }
    }

    public static UserRoleModel valueOf(int contractUserRole) {
        return (UserRoleModel) UserRoleModel.map.get(contractUserRole);
    }

    public int getValue() {
        return value;
    }
}
