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



