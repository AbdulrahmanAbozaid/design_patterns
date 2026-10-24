public class JsonDataImporter extends AbstractDataImporter {
    protected void openSource(String source) {
        System.out.println("Opening JSON file: "+ source);
    }

    protected void readRecords() {
        System.out.println("Reading JSON Records...");
    }

    protected void closeSource() {
        System.out.println("Closing JSON file.");
    }

    protected boolean shouldValidate() {
        return true;
    }
}
