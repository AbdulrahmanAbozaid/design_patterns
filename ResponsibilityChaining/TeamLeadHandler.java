
public class TeamLeadHandler extends ApprovalHandler {

    @Override
    public void handle(ExpenseRequest request) {
        if (request.getAmount() <= 500) {
            System.out.println("Team Lead approved: " + request.getDescription() + " ($" + request.getAmount() + ")");
            // stopping the chain
            return;
        }
        forward(request);
    }
}
