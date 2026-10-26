
public enum OrderStatus {
    PENDING("Pending"),
    PAID("Paid"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private final String label;

    private OrderStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }
}
