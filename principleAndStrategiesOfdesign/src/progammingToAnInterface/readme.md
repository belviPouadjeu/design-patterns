# Programming to an Interface - Example

## Introduction
Programming to an interface is a key principle in object-oriented design that promotes flexibility and maintainability. Instead of depending on concrete implementations, a class interacts with an interface, making it easier to extend and modify the code without altering existing implementations.

## Code Explanation
This example demonstrates the principle using a `DisplayModule` interface, which is implemented by `Monitor` and `Projector` classes. The `Computer` class depends on `DisplayModule` rather than a specific display type.

### **Key Components:**
1. **`DisplayModule` Interface** - Defines a common method `display()`, which concrete classes must implement.
2. **`Monitor` and `Projector` Classes** - Implement the `DisplayModule` interface with specific display behaviors.
3. **`Computer` Class** - Uses `DisplayModule` instead of a concrete display class, allowing flexibility to switch display types at runtime.

## Benefits of Programming to an Interface
- **Encapsulation**: Bundles data and methods into a single unit and restricts direct access to internal details to protect data integrity.
- **Flexibility**: Easily switch or extend implementations without modifying existing code.
- **Testability**: Enables unit testing using mock objects.
- **Scalability**: New display types can be added without modifying the `Computer` class.

## Example Code
### **Interface: DisplayModule.java**
```java
public interface DisplayModule {
    void display();
}
```

### **Implementations: Monitor.java & Projector.java**
```java
public class Monitor implements DisplayModule {
    @Override
    public void display() {
        System.out.println("Display through Monitor");
    }
}
```
```java
public class Projector implements DisplayModule {
    @Override
    public void display() {
        System.out.println("Display through Projector");
    }
}
```

### **Main Class: Computer.java**
```java
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

```

## Conclusion
This approach ensures that the `Computer` class remains loosely coupled to specific display implementations. If new display modules are added in the future, no changes are required in the `Computer` class, making the design more maintainable and extensible.

