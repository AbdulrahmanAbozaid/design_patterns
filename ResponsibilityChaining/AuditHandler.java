
public class AuditHandler extends ApprovalHandler {

    @Override
    public void handle(ExpenseRequest request) {
        System.out.println("[AUDIT] Request received: " + request.getDescription()
                + " ($" + request.getAmount() + ")");
        forward(request);
    }
}
