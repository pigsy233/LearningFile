package spring5.bean;

public class Orders {

    private String oName;

    public Orders() {
        System.out.println("第一步");
    }

    public void setoName(String oName) {
        System.out.println("第二步");
    }

    public void initMethod(){
        System.out.println("第三步");
    }

    public void destroyMethod(){
        System.out.println("第五步");
    }
}
