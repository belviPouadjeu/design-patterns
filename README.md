# Design Patterns in Software Development

Design patterns are reusable solutions to common problems in software design. They aren't finished code, but **templates** to organize code in a way that promotes flexibility, reusability, and maintainability. By following these patterns, developers avoid reinventing the wheel and build robust systems more efficiently.


## Why Are Design Patterns Important?

### Solve Recurring Problems
Design patterns address (solve, manage) common software development issues, such as:
- Object creation and management.
- Communication between components.
- Structuring and organizing code efficiently.

### Improve Communication
Using design pattern names (e.g., "Singleton," "Observer") allows developers to communicate complex concepts concisely and effectively.

### Promote Best Practices
Design patterns encapsulate decades of collective programming experience and wisdom, ensuring code is:
- Scalable (adaptable, modular)
- Maintainable
- Reusable

By applying design patterns, developers can create software that is both robust and adaptable to future requirements.

## Classification of Design Patterns

Design patterns are grouped into three categories:

### Creational Patterns
Creational design patterns deal with object creation mechanisms, trying to create objects in a manner suitable to the situation. Some common examples include:
- **Singleton**: Ensures a class has only one instance and provides a global point of access to it.
- **Factory Method**: Defines an interface for creating objects but allows subclasses to alter the type of objects that will be created.
- **Abstract Factory**: Provides an interface for creating families of related or dependent objects without specifying their concrete classes.
- **Builder**: Separates object construction from its representation to create complex objects step by step.
- **Prototype**: Creates objects based on a template of an existing object through cloning.

### Structural Patterns
Structural design patterns focus on class and object composition. These patterns help ensure that if one part of a system changes, the entire system doesn’t need to do the same. Some examples include:
- **Adapter**: Allows incompatible interfaces to work together.
- **Bridge**: Separates abstraction from implementation so they can evolve independently.
- **Composite**: Composes objects into tree structures to represent part-whole hierarchies.
- **Decorator**: Adds new functionality to an object dynamically.
- **Facade**: Provides a simplified interface to a larger body of code.
- **Flyweight**: Minimizes memory usage by sharing data between similar objects.
- **Proxy**: Provides a surrogate or placeholder for another object to control access to it.

### Behavioral Patterns
Behavioral design patterns deal with communication between objects, ensuring that the interactions are flexible and maintainable. Some examples include:
- **Observer**: Defines a dependency between objects so that when one object changes state, all dependents are notified automatically.
- **Strategy**: Defines a family of algorithms, encapsulates each one, and makes them interchangeable.
- **Command**: Encapsulates a request as an object, thereby allowing parameterization of clients with different requests.
- **Chain of Responsibility**: Passes a request along a chain of handlers.
- **Mediator**: Reduces dependencies between communicating objects by centralizing communication logic.
- **Memento**: Captures and restores an object's state without violating encapsulation.
- **State**: Allows an object to alter its behavior when its internal state changes.
- **Template Method**: Defines the skeleton of an algorithm, allowing subclasses to redefine certain steps.
- **Visitor**: Represents an operation to be performed on elements of an object structure without modifying the objects.

Understanding these patterns helps in designing scalable, maintainable, and efficient software systems.

## Creational Pattern: Singleton

### Purpose
Ensure a class has only one instance and provide global access to it.

### Real-World Example
A printer spooler (only one instance should manage print jobs).

### Java Code (Thread-safe using static inner class)
```java
public class Singleton {
    private Singleton() {
        // Private constructor prevents instantiation
    }
    
    private static class SingletonHelper {
        private static final Singleton INSTANCE = new Singleton();
    }
    
    public static Singleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```

### Explanation
- **Private Constructor**: Prevents direct instantiation.
- **Static Inner Class**: Ensures lazy initialization and thread safety.
- **Global Access**: `getInstance()` provides the single instance.

### Why Use It?

- **Saves memory** by reusing a single instance.
- **Controls access** to shared resources (e.g., databases).

## 2. Creational Pattern: Factory Method

### Purpose
Delegate object creation to subclasses instead of using `new` directly.

### Real-World Example
A pizza shop where `PizzaFactory` creates "cheese" or "pepperoni" pizzas based on customer orders.

### Java Code

```java
// Step 1: Define the Product (Pizza)
abstract class Pizza {
    abstract void prepare();
}

// Step 2: Concrete Products
class CheesePizza extends Pizza {
    @Override
    void prepare() {
        System.out.println("Preparing Cheese Pizza");
    }
}

class PepperoniPizza extends Pizza {
    @Override
    void prepare() {
        System.out.println("Preparing Pepperoni Pizza");
    }
}

// Step 3: Factory Method
abstract class PizzaFactory {
    abstract Pizza createPizza(String type);
}

// Step 4: Concrete Factory
class SimplePizzaFactory extends PizzaFactory {
    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new CheesePizza();
        } else if (type.equals("pepperoni")) {
            return new PepperoniPizza();
        } else {
            throw new IllegalArgumentException("Unknown pizza type: " + type);
        }
    }
}

// Step 5: Client Code
public class PizzaStore {
    public static void main(String[] args) {
        PizzaFactory factory = new SimplePizzaFactory();
        Pizza pizza = factory.createPizza("cheese");
        pizza.prepare();
    }
}
```

### Explanation
- The `Pizza` class is an abstract product.
- `CheesePizza` and `PepperoniPizza` are concrete implementations.
- `PizzaFactory` provides an abstraction for object creation.
- `SimplePizzaFactory` implements the Factory Method to create specific pizza types.
- `PizzaStore` (client) uses the factory to get an instance without directly calling `new`.

### Why Use It?

- **Loose coupling** by reusing a single instance.
- Dynamic subscription (add/remove observers at runtime).

### 3. Behavioral Pattern: Observer

**Purpose:** Notify multiple objects when a subject’s state changes (publish-subscribe model).

**Real-World Example:** Weather app sending alerts to users when temperature changes.

```java
import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(float temperature);
}

// Subject (Observable) interface
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class WeatherStation implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private float temperature;

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature);
        }
    }
}

// Concrete Observer
class WeatherApp implements Observer {
    private String name;

    public WeatherApp(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature) {
        System.out.println(name + " received temperature update: " + temperature + "°C");
    }
}

// Main class to demonstrate the Observer pattern
public class ObserverPatternDemo {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        
        Observer app1 = new WeatherApp("WeatherApp1");
        Observer app2 = new WeatherApp("WeatherApp2");
        
        weatherStation.addObserver(app1);
        weatherStation.addObserver(app2);
        
        weatherStation.setTemperature(25.5f);
        weatherStation.setTemperature(30.0f);
    }
}
```

**Explanation:**
- `Observer` is an interface that requires an `update()` method.
- `Subject` defines methods to add, remove, and notify observers.
- `WeatherStation` implements `Subject` and maintains a list of observers.
- `WeatherApp` implements `Observer` and receives updates when the temperature changes.
- `ObserverPatternDemo` simulates a weather station notifying multiple weather apps of temperature changes.

### Why Use It?

- **Loose coupling** by reusing a single instance.
- Dynamic subscription (add/remove observers at runtime).
---

### 4. Behavioral Pattern: Strategy

**Purpose:** Define a family of algorithms, encapsulate each, and make them interchangeable.

**Real-World Example:** Sorting algorithms (quick sort, merge sort) or payment methods (credit card, PayPal).

**Java Code:**

```java
// Step 1: Define the Strategy Interface
interface PaymentStrategy {
    void pay(int amount);
}

// Step 2: Implement Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card ending with " + cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    
    public PayPalPayment(String email) {
        this.email = email;
    }
    
    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal account: " + email);
    }
}

// Step 3: Context Class
class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    public void checkout(int amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }
        paymentStrategy.pay(amount);
    }
}

// Step 4: Usage Example
public class StrategyPatternDemo {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        
        // Pay using Credit Card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        cart.checkout(100);
        
        // Pay using PayPal
        cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(200);
    }
}
```
---

### Why Use It?

- Switch algorithms at runtime (e.g., payment method in checkout).
- Isolate algorithm code from the client.

### Key Takeaways

- **Creational Patterns** simplify object creation.
- **Structural Patterns** build flexible class/object structures.
- **Behavioral Patterns** manage communication between objects.



## 1. Factory Method Pattern
The Factory Method pattern is used to create objects without specifying the exact class of object that will be created. This pattern is useful when you need to
decouple the creation of an object from its implementation.

### When to Use the Factory Method Design Pattern?

Use the **Factory Method Design Pattern** in the following scenarios:

- **Encapsulating Object Creation:** When object creation is complex, involves multiple steps, or varies based on conditions, a factory method helps simplify client code, improve maintainability, and enhance reusability.
- **Decoupling Client Code from Concrete Classes:** Instead of directly instantiating objects, the factory method allows you to create them through an interface or an abstract class. This abstraction promotes loose coupling, making it easier to modify or extend the system without affecting existing client code.

## Components of Factory Method Design Pattern

The **Factory Method Design Pattern** consists of several key components that work together to enable flexible and scalable object creation. Below are the main components:

### 1. **Product (Interface or Abstract Class)**
   - Defines the interface or abstract class that the concrete products must implement.
   - Ensures that all products follow a common structure.

   ```java
   interface Product {
       void use();
   }
   ```

### 2. **Concrete Product (Implementation of Product)**
   - Implements the interface or extends the abstract class.
   - Represents different variations of the product that the factory method can create.

   ```java
   class ConcreteProductA implements Product {
       public void use() {
           System.out.println("Using ConcreteProductA");
       }
   }
   
   class ConcreteProductB implements Product {
       public void use() {
           System.out.println("Using ConcreteProductB");
       }
   }
   ```

### 3. **Creator (Abstract Class or Interface with Factory Method)**
   - Declares the factory method that returns objects of type `Product`.
   - Typically provides a default implementation or enforces subclasses to implement the factory method.

   ```java
   abstract class Creator {
       abstract Product createProduct();
   }
   ```

### 4. **Concrete Creator (Implements Factory Method)**
   - Implements the factory method to return an instance of a specific `ConcreteProduct`.
   - Each subclass is responsible for creating a particular type of product.

   ```java
   class ConcreteCreatorA extends Creator {
       @Override
       Product createProduct() {
           return new ConcreteProductA();
       }
   }
   
   class ConcreteCreatorB extends Creator {
       @Override
       Product createProduct() {
           return new ConcreteProductB();
       }
   }
   ```

### 5. **Client (Uses Factory Method)**
   - Calls the factory method instead of directly instantiating objects.
   - Works with `Product` objects without depending on their concrete implementations.

   ```java
   public class FactoryMethodExample {
       public static void main(String[] args) {
           Creator creator = new ConcreteCreatorA();
           Product product = creator.createProduct();
           product.use();
       }
   }
   ```

## Summary
| Component          | Description |
|-------------------|-------------|
| **Product** | Defines the interface or abstract class for products. |
| **Concrete Product** | Implements the `Product` interface or extends the abstract class. |
| **Creator** | Declares the factory method to create products. |
| **Concrete Creator** | Implements the factory method to create a specific product. |
| **Client** | Calls the factory method and works with the product through its interface. |

By using the **Factory Method Design Pattern**, we achieve loose coupling, better maintainability, and scalability in object creation.

## Challenge Factory method
## E-Commerce Platform Payment Methods in Cameroon

You are building an e-commerce platform for Cameroon that supports multiple payment methods, including:

1️⃣ **Credit Card Payments** (Visa, MasterCard)  
2️⃣ **PayPal Payments**  
3️⃣ **Crypto Payments** (Bitcoin, Ethereum)  
4️⃣ **Mobile Money Payments** (MTN MoMo, Orange Money)
---

# Singleton Design Pattern in Java

## 🔹 What is the Singleton Pattern?

The **Singleton Design Pattern** is a **creational design pattern** that ensures a class has only **one instance** and provides a **global point of access** to that instance. It is commonly used when exactly one object is needed to coordinate actions across a system.

---

## 🔹 When to Use Singleton?

- **Database connections** (e.g., single instance of a database connection manager)
- **Logging** (ensuring a single log file handler)
- **Configuration settings** (loading config settings once)
- **Caching** (centralized data caching)
- **Thread pools** (managing a single pool of threads)
- **Hardware access** (e.g., single access point to a hardware resource like a printer)

---

## 🔹 How to Implement Singleton in Java

Here’s a basic implementation of the Singleton pattern in Java:

```java
public class Singleton {
    // Private static instance of the class
    private static Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {
        // Initialization code (if needed)
    }

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    // Other methods of the Singleton class
    public void doSomething() {
        System.out.println("Singleton is doing something!");
    }
}
```
---
## Eager Initialization (Thread-Safe)

The instance is created at class loading time.
```java
class Singleton {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {}  // Private constructor

    public static Singleton getInstance() {
        return INSTANCE;
    }
}

```
---

## Lazy Initialization (Not Thread-Safe)

The instance is created only when needed.

```java
class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

```
---

## Thread-Safe Singleton (Using synchronized)
To make the Singleton pattern thread-safe, you can use the synchronized keyword:
```java
class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

```
---

## Bill Pugh Singleton (Best Approach)
Uses an inner static helper class for lazy loading and thread safety.
```java
public class Main {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        System.out.println(instance1 == instance2);  // Output: true (same instance)
    }
}

```
---

# Which Singleton Approach Should You Use?

Below is a comparison of different Singleton implementation approaches in Java, highlighting their **thread safety**, **lazy initialization**, and **performance** characteristics:

| **Method**               | **Thread-Safe** | **Lazy Initialization** | **Performance**                     |
|--------------------------|-----------------|-------------------------|-------------------------------------|
| **Eager Initialization** | ✅ Yes          | ❌ No                   | 🔥 Fast (always initialized)        |
| **Lazy Initialization**  | ❌ No           | ✅ Yes                  | 🐢 Slow in multithreading           |
| **Synchronized Method**  | ✅ Yes          | ✅ Yes                  | ⚠️ Slower due to synchronization   |
| **Double-Checked Locking** | ✅ Yes        | ✅ Yes                  | ✅ Good Performance                 |
| **Bill Pugh Singleton**  | ✅ Yes          | ✅ Yes                  | ✅ Best Performance                 |

---

## 🔹 Key Takeaways

1. **Eager Initialization**:
   - Simplest approach.
   - Instance is created at class-loading time.
   - Not suitable if the Singleton is resource-intensive and not always needed.

2. **Lazy Initialization**:
   - Instance is created only when needed.
   - Not thread-safe by default.

3. **Synchronized Method**:
   - Thread-safe but slower due to synchronization overhead.
   - Suitable for low-concurrency scenarios.

4. **Double-Checked Locking**:
   - Thread-safe with better performance than synchronized methods.
   - Uses `volatile` and `synchronized` blocks to minimize overhead.

5. **Bill Pugh Singleton**:
   - Uses a static inner helper class to create the Singleton instance.
   - Thread-safe, lazy initialization, and best performance.
   - No need for synchronization or `volatile` keywords.

---

## 🔹 Recommendation

- Use **Bill Pugh Singleton** for the best combination of **thread safety**, **lazy initialization**, and **performance**.
- Use **Double-Checked Locking** if you need fine-grained control over initialization.
- Use **Eager Initialization** for simple, low-resource scenarios where lazy initialization is not required.

# 🚀 Real-World Challenge: Singleton in a Logging System

## Scenario:
You are working on a web application that needs a **centralized logging system**. Your goal is to ensure that:
- ✅ Only one instance of the logger exists
- ✅ It is thread-safe
- ✅ It supports writing logs to a file

---

## 📝 Challenge Tasks

1️⃣ **Implement a thread-safe Singleton Logger in Java.**  
2️⃣ **The logger should have a method to write messages to a file (`log.txt`).**  
3️⃣ **Use multi-threading to test if multiple threads write to the same log file.**  
4️⃣ **Ensure only one instance of the logger is created.**

- Step 1: Implement Singleton Logger
- Step 2: Testing with Multiple Threads

---

## 📝 Challenge Tasks for abstract factory method
Imagine you’re managing a global car manufacturing company. You want to design a system to create cars with specific configurations for different
regions, such as North America and Europe. Each region may have unique requirements and regulations, and you want to ensure that cars produced for each region meet those standards.



