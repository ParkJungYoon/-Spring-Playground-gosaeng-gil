package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드

    // 상태를 유지하기 때문에 이렇게 하면 안된다.
//    public void order(String name, int price) {
//        System.out.println("name = " + name + "price = " + price);
//        this.price = price;
//    }

    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        return price;
    }
//    public int getPrice() {
//        return price;
//    }
}

