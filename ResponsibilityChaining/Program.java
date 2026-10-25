
public class Program {

    public static void main(String[] args) {

        // Create the handlers
        ApprovalHandler audit = new AuditHandler();
        ApprovalHandler teamLead = new TeamLeadHandler();
        ApprovalHandler manager = new ManagerHandler();
        ApprovalHandler director = new DirectorHandler();

        // Build the chain: audit -> teamLead -> manager -> director
        audit.setNext(teamLead);
        teamLead.setNext(manager);
        manager.setNext(director);

        // Every request starts at the beginning of the chain
        audit.handle(new ExpenseRequest(120, "Team lunch"));
        System.out.println();

        audit.handle(new ExpenseRequest(1500, "Conference tickets"));
        System.out.println();

        audit.handle(new ExpenseRequest(8000, "New server"));
        System.out.println();

        audit.handle(new ExpenseRequest(25000, "Office renovation"));
    }
}
