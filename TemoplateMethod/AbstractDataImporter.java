public abstract class AbstractDataImporter {

    /**
     * importData - final to not be overriden
     * If a new Importer added it, it will need to just override the abstract ones 
     */
    public final void importData(String source) {
        openSource(source);
        readRecords();
        if (shouldValidate()) validateRecords();
        saveToDatabase();
        closeSource();
    }

    protected abstract void openSource(String source);

    protected abstract void readRecords();

    protected void validateRecords() {
        System.out.println("Validating Records...");
    }
    
    protected void saveToDatabase() {
        System.out.println("Saving Records To Database...");
    }

    protected abstract void closeSource();

    // Hook method that optionally can be overriden to adjust its behavior
    protected boolean shouldValidate() {
        return false;
    }
}