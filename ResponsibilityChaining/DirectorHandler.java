
public class DirectorHandler extends ApprovalHandler {

    @Override
    public void handle(ExpenseRequest request) {
        if (request.getAmount() <= 10_000) {
            System.out.println("Director approved: " + request.getDescription() + " ($" + request.getAmount() + ")");
            // stopping the chain
            return;
        }
        forward(request);
    }
}
