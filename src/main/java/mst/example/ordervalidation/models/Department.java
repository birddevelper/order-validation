package mst.example.ordervalidation.models;

import com.fasterxml.jackson.annotation.JsonValue;


public enum Department {

    GOOD_ANALYSIS_DEPARTMENT("GOoD analysis department"),
    GOOD_REPAIR_DEPARTMENT("GOoD repair department"),
    GOOD_REPLACEMENT_DEPARTMENT("GOoD replacement department");

    Department(String departmentName) {
        this.departmentName = departmentName;
    }

    private String departmentName;

    @JsonValue
    public String getDepartmentName() {
        return this.departmentName;
    }


    public static boolean exists(String departmentName)
    {
        for(Department department:values())
            if (department.departmentName.equals(departmentName))
                return true;
        return false;
    }
}
