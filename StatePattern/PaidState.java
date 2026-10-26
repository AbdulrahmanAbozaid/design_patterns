
public class PaidState implements OrderState {

    @Override
    public void pay(Order order) throws IllegalTransitionException {
        throw new IllegalTransitionException("Cannot pay: order is " + order.getStatus().getLabel());
    }

    @Override
    public void ship(Order order) {
        System.out.println("[" + order.getOrderId() + "] Order shipped");
        order.setState(new ShippedState());
    }

    @Override
    public void deliver(Order order) throws IllegalTransitionException {
        throw new IllegalTransitionException("Cannot deliver: order is " + order.getStatus().getLabel());
    }

    @Override
    public void cancel(Order order) {
        System.out.println("[" + order.getOrderId() + "] Order cancelled, payment refunded");
        order.setState(new CancelledState());
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.PAID;
    }
}
