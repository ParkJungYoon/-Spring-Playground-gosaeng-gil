package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    // 이 객체 인스턴스는 오직 이 메서드를 통해서만 조회할 수 있다.
    // (중요) 딱 1개의 객체 인스턴스만 존재해야 하므로, private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
    public static SingletonService getInstance() {
        return instance;
    }

    // private 생성자를 만들어서 외부에서 new해서 생성할 수 없도록 한다. -> 싱글톤
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
