
public class Order {

    private final String orderId;
    private OrderState state;

    public Order(String orderId) {
        this.orderId = orderId;
        this.state = new PendingState();
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public OrderStatus getStatus() {
        return this.state.getStatus();
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void pay() throws IllegalTransitionException {
        this.state.pay(this);
    }

    public void ship() throws IllegalTransitionException {
        this.state.ship(this);
    }

    public void deliver() throws IllegalTransitionException {
        this.state.deliver(this);
    }

    public void cancel() throws IllegalTransitionException {
        this.state.cancel(this);
    }
}
