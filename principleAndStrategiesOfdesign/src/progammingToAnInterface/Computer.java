package progammingToAnInterface;

public class Computer {
    private final DisplayModule displayModule; // Dependency is final

    // Constructor Injection
    public Computer(DisplayModule displayModule) {
        this.displayModule = displayModule;
    }

    public void display() {
        displayModule.display();
    }

    public static void main(String[] args) {
        DisplayModule monitor = new Monitor();
        DisplayModule projector = new Projector();

        // Creating Computer objects with different display modules
        Computer computerWithMonitor = new Computer(monitor);
        Computer computerWithProjector = new Computer(projector);

        // Display through different modules
        computerWithMonitor.display();   // Output: Display through Monitor
        computerWithProjector.display(); // Output: Display through Projector
    }
}
