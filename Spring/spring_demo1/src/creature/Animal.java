package creature;

public class Animal {
    String name;

    String categories;

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void testAni(){
        System.out.println(name + categories);
    }
}
