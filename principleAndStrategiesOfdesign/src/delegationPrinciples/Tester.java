package delegationPrinciples;

// Step 1: Define an interface for the delegate
interface PrinterService {
    void print();
}

// Step 2: Implement the interface in the delegate
class RealPrinter implements PrinterService {
    @Override
    public void print() {
        System.out.println("The Delegate: Printing the document.");
    }
}

// Step 3: The delegator class
class Printer {
    // Hold a reference to the delegate
    private PrinterService printerService;

    // Constructor to inject the delegate (dependency injection)
    public Printer(PrinterService printerService) {
        this.printerService = printerService;
    }

    // Delegate the task to the PrinterService
    public void print() {
        printerService.print();
    }
}

// Step 4: Tester class
public class Tester {
    public static void main(String[] args) {
        // Create the delegate
        PrinterService realPrinter = new RealPrinter();

        // Create the delegator and inject the delegate
        Printer printer = new Printer(realPrinter);

        // To the outside world, it looks like Printer is doing the work
        printer.print();
    }
}