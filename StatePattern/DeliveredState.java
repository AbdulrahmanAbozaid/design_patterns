
public class DeliveredState implements OrderState {

    @Override
    public void pay(Order order) throws IllegalTransitionException {
        throw new IllegalTransitionException("Cannot pay: order is " + order.getStatus().getLabel());
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
    public void cancel(Order order) throws IllegalTransitionException {
        throw new IllegalTransitionException("Cannot cancel: order is " + order.getStatus().getLabel());
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.DELIVERED;
    }
}
