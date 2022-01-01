package creature.firm;

public class Employee {
    private String ename;
    private String gender;
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void test(){
        System.out.println(ename+"::"+gender+"::"+department);
    }
}
