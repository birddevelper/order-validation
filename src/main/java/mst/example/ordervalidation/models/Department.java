package mst.example.ordervalidation.models;

import com.fasterxml.jackson.annotation.JsonValue;


public enum Department {

    GOOD_ANALYSIS_DEPARTMENT("GOoD analysis department"),
    GOOD_REPAIR_DEPARTMENT("GOoD repair department"),
    GOOD_REPLACEMENT_DEPARTMENT("GOoD replacement department"),
    IT_DEPARTMENT("IT department");

    Department(String departmentName) {
        this.departmentName = departmentName;
    }

    private String departmentName;

    @JsonValue
    public String getDepartmentName() {
        return this.departmentName;
    }


    /**
     * This method checks the existence of the given department name in the valid department list
     * @param departmentName
     * @return boolean
     */
    public static boolean exists(String departmentName)
    {
        // iterate over department list
        for(Department department:values())
            if (department.departmentName.equals(departmentName))
                return true;
        // if no department is found, return false
        return false;
    }
}
