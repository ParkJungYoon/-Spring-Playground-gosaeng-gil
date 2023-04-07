package jpabook.jpashop.exception;

public class NotEnoughStockException extends RuntimeException{
    // cmd + N 으로 필요한거 다 오버라이딩 시킴
    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }
}
