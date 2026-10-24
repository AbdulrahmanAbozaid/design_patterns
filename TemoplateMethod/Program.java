public class Program {

    public static void main(String[] args) {

        // Import a CSV file
        // Declared as AbstractDataImporter because it is the super type and all its methods will be defined
        AbstractDataImporter csvImporter = new CsvDataImporter();
        csvImporter.importData("customers.csv");

        System.out.println();

        // Import a JSON file
        AbstractDataImporter jsonImporter = new JsonDataImporter();
        jsonImporter.importData("orders.json");
    }
}