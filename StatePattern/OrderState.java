
public interface OrderState {

    public void pay(Order order) throws IllegalTransitionException;

    public void ship(Order order) throws IllegalTransitionException;

    public void deliver(Order order) throws IllegalTransitionException;

    public void cancel(Order order) throws IllegalTransitionException;

    public OrderStatus getStatus();
}
