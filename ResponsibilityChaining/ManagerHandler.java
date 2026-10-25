
public class ManagerHandler extends ApprovalHandler {

    @Override
    public void handle(ExpenseRequest request) {
        if (request.getAmount() <= 2000) {
            System.out.println("Manager approved: " + request.getDescription() + " ($" + request.getAmount() + ")");
            // stopping the chain
            return;
        }
        forward(request);
    }
}
