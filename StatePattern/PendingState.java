
public class PendingState implements OrderState {

    @Override
    public void pay(Order order) {
        System.out.println("[" + order.getOrderId() + "] Payment received");
        order.setState(new PaidState());
    }

    @Override
    public void ship(Order order) throws IllegalTransitionException {
        throw new IllegalTransitionException("Cannot ship: order is " + order.getStatus().getLabel());
    }

    @Override
    public void deliver(Order order) throws IllegalTransitionException {
        throw new IllegalTransitionException("Cannot deliver: order is " + order.getStatus().getLabel());
    }

    @Override
    public void cancel(Order order) {
        System.out.println("[" + order.getOrderId() + "] Order cancelled");
        order.setState(new CancelledState());
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.PENDING;
    }
}
