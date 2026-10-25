
public abstract class ApprovalHandler {

    private ApprovalHandler next;

    public void setNext(ApprovalHandler next) {
        this.next = next;
    }

    public abstract void handle(ExpenseRequest request);

    protected void forward(ExpenseRequest request) {
        if (next != null) {
            next.handle(request);
        } else {
            System.out.println("Rejected: " + request.getDescription() + " ($" + request.getAmount() + ") exceeds all approval limits");
        }
    }
}
