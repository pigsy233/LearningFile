package spring5.collectiontype;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student {

    private int data;

    private String[] courses;

    private List<String> list;

    private Map<String, String> maps;

    private Set<String> set;

    private List<Course> coursesList;

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public void test(){

        System.out.println(data);
        System.out.println(Arrays.toString(courses));
        System.out.println(list);
        System.out.println(set);
        System.out.println(maps);
    }
}
