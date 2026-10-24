public class CsvDataImporter extends AbstractDataImporter{
    protected void openSource(String source) {
        System.out.println("Opening CSV file: "+ source);
    }

    protected void readRecords() {
        System.out.println("Reading CSV Records...");
    }

    protected void closeSource() {
        System.out.println("Closing CSV file.");
    }
}