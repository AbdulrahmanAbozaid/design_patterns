
public class ExpenseRequest {

    private final int amount;
    private final String description;

    public ExpenseRequest(int amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }
}
