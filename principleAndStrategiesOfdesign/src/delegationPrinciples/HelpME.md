# Delegation Principle in Java

## Introduction
The **Delegation Principle** is a fundamental concept in object-oriented design that promotes **code reuse, flexibility, and separation of concerns**. It allows an object (delegator) to delegate a task to another object (delegate) instead of handling it directly.

This approach enhances maintainability and promotes the **Single Responsibility Principle (SRP)** by keeping different concerns separate.

## Example Implementation
Below is an example demonstrating the **Delegation Principle** in Java:

### 1. Define an Interface for the Delegate
We create a `PrinterService` interface to define the printing behavior.
```java
interface PrinterService {
    void print();
}
```

### 2. Implement the Delegate Class
The `RealPrinter` class implements the `PrinterService` interface and provides the actual printing functionality.
```java
class RealPrinter implements PrinterService {
    @Override
    public void print() {
        System.out.println("The Delegate: Printing the document.");
    }
}
```

### 3. Create the Delegator Class
The `Printer` class **delegates** the `print` method to `PrinterService`. It does not implement the printing itself but relies on the `RealPrinter` class.
```java
class Printer {
    private final PrinterService printerService;

    // Constructor Injection
    public Printer(PrinterService printerService) {
        this.printerService = printerService;
    }

    // Delegating the task
    public void print() {
        printerService.print();
    }
}
```

### 4. Test the Delegation
The `Tester` class demonstrates delegation by using `Printer` to perform the task, which is actually handled by `RealPrinter`.
```java
public class Tester {
    public static void main(String[] args) {
        // Create the delegate
        PrinterService realPrinter = new RealPrinter();

        // Inject the delegate into the delegator
        Printer printer = new Printer(realPrinter);

        // Call the print method
        printer.print();
    }
}
```

## Key Benefits of Delegation Principle
### ✅ Separation of Concerns
- The **Printer** class does not handle printing itself but **delegates** it to `PrinterService`. This makes the code modular and easier to maintain.

### ✅ Code Reusability
- Different implementations of `PrinterService` can be created (e.g., `NetworkPrinter`, `FilePrinter`), and `Printer` can use any of them **without modification**.

### ✅ Improves Flexibility
- The behavior of the system can be changed at runtime by **injecting** different implementations of `PrinterService`.

### ✅ Follows the Open/Closed Principle
- We can introduce new `PrinterService` implementations **without modifying** existing code, adhering to SOLID design principles.

## Conclusion
The **Delegation Principle** is a powerful design concept that allows better **code organization, maintainability, and flexibility**. It is widely used in frameworks like **Spring** (for dependency injection) and **Java’s I/O classes** (like `InputStreamReader` delegating to `InputStream`).

By using delegation, we promote **loose coupling** and make our applications **more scalable and extensible**! 🚀

