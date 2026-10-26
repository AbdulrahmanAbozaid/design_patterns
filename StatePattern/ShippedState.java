
public class ShippedState implements OrderState {

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
        System.out.println("[" + order.getOrderId() + "] Order delivered");
        order.setState(new DeliveredState());
    }

    @Override
    public void cancel(Order order) throws IllegalTransitionException {
        throw new IllegalTransitionException("Cannot cancel: order is " + order.getStatus().getLabel());
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.SHIPPED;
    }
}
