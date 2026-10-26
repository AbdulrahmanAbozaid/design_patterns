
public class Program {

    public static void main(String[] args) {

        // Scenario 1: the normal life of an order
        Order order1 = new Order("A100");
        printStatus(order1);
        try {
            order1.pay();
            order1.ship();
            order1.deliver();
            printStatus(order1);
            order1.cancel();            // not allowed once delivered
        } catch (IllegalTransitionException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();

        // Scenario 2: cancelled before shipping
        Order order2 = new Order("B200");
        try {
            order2.pay();
            order2.cancel();
            printStatus(order2);
            order2.pay();               // not allowed once cancelled
        } catch (IllegalTransitionException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println();

        // Scenario 3: trying to skip a step
        Order order3 = new Order("C300");
        try {
            order3.ship();              // not allowed while pending
        } catch (IllegalTransitionException e) {
            System.out.println("Error: " + e.getMessage());
        }
        printStatus(order3);
    }

    private static void printStatus(Order order) {
        System.out.println("Order " + order.getOrderId()
                + " status: " + order.getStatus().getLabel());
    }
}
