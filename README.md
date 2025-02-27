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


---

## Creational Pattern

**Creational design patterns** focus on **object creation mechanisms**, ensuring that objects are created in a way that promotes flexibility, reusability, and maintainability. They abstract the instantiation process, making systems independent of how objects are created, composed, and represented.

## 1. Creational Pattern: Singleton

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

**Usage**
```java
Singleton instance = Singleton.getInstance();
```

### Explanation
- **Private Constructor**: Prevents direct instantiation.
- **Static Inner Class**: Ensures lazy initialization and thread safety.
- **Global Access**: `getInstance()` provides the single instance.

### Why Use It?

- **Saves memory** by reusing a single instance.
- **Controls access** to shared resources (e.g., databases).

### Best Use Cases:

- Managing shared resources (e.g., database connections).
- Logging or configuration management.

### When to Avoid:

- When you need multiple instances of a class.
- In distributed systems (use dependency injection instead).

### Common Mistakes:

- Not making the constructor private.
- Ignoring thread safety in multi-threaded environments.

### Best Practices:

- Use lazy initialization for resource efficiency.
- Consider dependency injection frameworks for better testability.


---

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

### Best Use Cases:

- When the exact type of object isn’t known until runtime.
- Frameworks where subclasses define their own object types.

### When to Avoid:

- When object creation is simple and doesn’t require abstraction.

### Common Mistakes:

- Overcomplicating the factory hierarchy.
- Mixing factory logic with business logic.

### Best Practices:

- Use dependency injection for better flexibility.
- Keep factory methods focused on creation only.



---

## 3. Creational Pattern: Abstract Factory

### Purpose
Provide an interface for creating families of related or dependent objects without specifying their concrete classes.

### Real-World Example
A pizza shop that has different pizza styles (e.g., **New York Style** and **Chicago Style**), where each style has its own types of pizza (cheese, pepperoni). An **Abstract Factory** ensures that when we create a pizza, we get the correct style and type.

### Java Code

```java
// Step 1: Define the Abstract Product
abstract class Pizza {
    abstract void prepare();
}

// Step 2: Concrete Products for New York Style
class NYStyleCheesePizza extends Pizza {
    @Override
    void prepare() {
        System.out.println("Preparing New York Style Cheese Pizza");
    }
}

class NYStylePepperoniPizza extends Pizza {
    @Override
    void prepare() {
        System.out.println("Preparing New York Style Pepperoni Pizza");
    }
}

// Step 3: Concrete Products for Chicago Style
class ChicagoStyleCheesePizza extends Pizza {
    @Override
    void prepare() {
        System.out.println("Preparing Chicago Style Cheese Pizza");
    }
}

class ChicagoStylePepperoniPizza extends Pizza {
    @Override
    void prepare() {
        System.out.println("Preparing Chicago Style Pepperoni Pizza");
    }
}

// Step 4: Define the Abstract Factory
interface PizzaFactory {
    Pizza createCheesePizza();
    Pizza createPepperoniPizza();
}

// Step 5: Concrete Factories
class NYPizzaFactory implements PizzaFactory {
    @Override
    public Pizza createCheesePizza() {
        return new NYStyleCheesePizza();
    }

    @Override
    public Pizza createPepperoniPizza() {
        return new NYStylePepperoniPizza();
    }
}

class ChicagoPizzaFactory implements PizzaFactory {
    @Override
    public Pizza createCheesePizza() {
        return new ChicagoStyleCheesePizza();
    }

    @Override
    public Pizza createPepperoniPizza() {
        return new ChicagoStylePepperoniPizza();
    }
}

// Step 6: Client Code
public class PizzaStore {
    public static void main(String[] args) {
        PizzaFactory nyFactory = new NYPizzaFactory();
        Pizza nyCheesePizza = nyFactory.createCheesePizza();
        nyCheesePizza.prepare();

        PizzaFactory chicagoFactory = new ChicagoPizzaFactory();
        Pizza chicagoPepperoniPizza = chicagoFactory.createPepperoniPizza();
        chicagoPepperoniPizza.prepare();
    }
}
```

### Explanation
- The `Pizza` class is the abstract product.
- `NYStyleCheesePizza`, `NYStylePepperoniPizza`, `ChicagoStyleCheesePizza`, and `ChicagoStylePepperoniPizza` are concrete products.
- `PizzaFactory` defines an abstract factory interface.
- `NYPizzaFactory` and `ChicagoPizzaFactory` implement the factory interface to create the right pizza style.
- The `PizzaStore` (client) uses the factory without depending on specific pizza classes.

### Why Use It?
- **Encapsulates object creation**: The client doesn’t need to know which concrete class to instantiate.
- **Promotes consistency**: Ensures related products are used together (e.g., New York-style pizzas are created together).
- **Improves flexibility**: Easy to add new product families (e.g., ItalianStylePizzaFactory) without modifying existing code.

---

### Best Use Cases:

- When you need to create families of related objects.
- GUI libraries (e.g., creating matching buttons, windows, and menus).

### When to Avoid:

- When the system only needs a single type of object.

### Common Mistakes:

- Creating too many factories for small variations.
- Tightly coupling factories with product classes.

### Best Practices:

- Use dependency injection to manage factories.
- Keep the factory hierarchy simple and focused.



## 4. Creational Pattern: Builder

### Purpose
Separate the construction of a complex object from its representation, allowing step-by-step creation with different configurations.

### Real-World Example
Building a burger where different ingredients (bun, patty, toppings, sauce) are added progressively using a `BurgerBuilder`.

### Java Code

```java
// Step 1: Product Class
class Burger {
    private String bun;
    private String patty;
    private String sauce;
    private boolean cheese;
    private boolean lettuce;

    // Private Constructor
    private Burger(BurgerBuilder builder) {
        this.bun = builder.bun;
        this.patty = builder.patty;
        this.sauce = builder.sauce;
        this.cheese = builder.cheese;
        this.lettuce = builder.lettuce;
    }

    // Step 2: Builder Class
    public static class BurgerBuilder {
        private String bun;
        private String patty;
        private String sauce;
        private boolean cheese;
        private boolean lettuce;

        public BurgerBuilder(String bun, String patty) {
            this.bun = bun;
            this.patty = patty;
        }

        public BurgerBuilder addSauce(String sauce) {
            this.sauce = sauce;
            return this;
        }

        public BurgerBuilder addCheese(boolean cheese) {
            this.cheese = cheese;
            return this;
        }

        public BurgerBuilder addLettuce(boolean lettuce) {
            this.lettuce = lettuce;
            return this;
        }

        public Burger build() {
            return new Burger(this);
        }
    }

    @Override
    public String toString() {
        return "Burger with " + bun + " bun, " + patty + " patty, " +
               (sauce != null ? " sauce: " + sauce + "," : "") +
               (cheese ? " cheese, " : "") +
               (lettuce ? " lettuce" : "");
    }
}

// Step 3: Client Code
public class BuilderPatternDemo {
    public static void main(String[] args) {
        Burger burger = new Burger.BurgerBuilder("Sesame", "Beef")
                .addSauce("BBQ")
                .addCheese(true)
                .addLettuce(true)
                .build();
        
        System.out.println(burger);
    }
}
```

### Explanation
- `Burger` is the complex object being constructed.
- `BurgerBuilder` provides step-by-step methods to configure the burger.
- The `build()` method creates the final `Burger` object.
- The client (`BuilderPatternDemo`) uses the builder to create customized burger instances.

### Why Use It?
- **Improves readability** by providing a clear step-by-step way to construct an object.
- **Encapsulates construction logic** to avoid large constructors.
- **Flexible object creation** by allowing selective parameter initialization.

### Best Use Cases:

- When constructing objects with many optional parameters.
- Immutable objects with complex initialization.

### When to Avoid:

- When objects are simple and don’t require many steps to construct.

### Common Mistakes:

- Overcomplicating the builder for simple objects.
- Not providing a clear separation between the builder and the director.

### Best Practices:

- Use fluent interfaces for better readability.
- Keep the builder focused on construction logic.

---

## 5. Creational Pattern: Prototype

### Purpose
The **Prototype Pattern** is used to create new objects by copying an existing object (a prototype) instead of instantiating new ones. This is useful when object creation is expensive or complex.

### Real-World Example
A game where different enemy characters are cloned instead of being created from scratch every time.

### Java Code

```java
// Step 1: Create the Prototype Interface
interface Enemy extends Cloneable {
    Enemy clone();
    void attack();
}

// Step 2: Concrete Prototype Implementations
class Orc implements Enemy {
    private String weapon;
    
    public Orc(String weapon) {
        this.weapon = weapon;
    }
    
    @Override
    public Enemy clone() {
        return new Orc(this.weapon);
    }
    
    @Override
    public void attack() {
        System.out.println("Orc attacks with " + weapon);
    }
}

class Goblin implements Enemy {
    private int power;
    
    public Goblin(int power) {
        this.power = power;
    }
    
    @Override
    public Enemy clone() {
        return new Goblin(this.power);
    }
    
    @Override
    public void attack() {
        System.out.println("Goblin attacks with power level " + power);
    }
}

// Step 3: Client Code
public class Game {
    public static void main(String[] args) {
        Enemy orcPrototype = new Orc("Axe");
        Enemy clonedOrc = orcPrototype.clone();
        clonedOrc.attack();
        
        Enemy goblinPrototype = new Goblin(10);
        Enemy clonedGoblin = goblinPrototype.clone();
        clonedGoblin.attack();
    }
}
```

### Explanation
- The **Enemy** interface extends `Cloneable` and defines a `clone` method.
- **Orc** and **Goblin** implement `Enemy` and override `clone()` to return a copy of themselves.
- In the **Game** class, we create prototype objects and clone them instead of instantiating new ones.

### Why Use It?
- **Improves performance** by avoiding costly object creation.
- **Encapsulates object creation logic**, making it reusable and flexible.
- **Reduces dependency on constructors**, allowing dynamic object creation.

### Best Use Cases:

- When constructing objects with many optional parameters.
- Immutable objects with complex initialization.

### When to Avoid:

- When objects are simple and don’t require many steps to construct.

### Common Mistakes:

- Overcomplicating the builder for simple objects.
- Not providing a clear separation between the builder and the director.

### Best Practices:

- Use fluent interfaces for better readability.
- Keep the builder focused on construction logic.

---

## Comparison of Creational Patterns

| Pattern         | Purpose                                    | When to Use                                     |
|----------------|--------------------------------------------|-------------------------------------------------|
| **Singleton**  | Ensure one instance of a class.           | Shared resources, logging.                     |
| **Factory Method** | Delegate object creation to subclasses. | Frameworks, runtime object creation.           |
| **Abstract Factory** | Create families of related objects.  | GUI libraries, product families.               |
| **Builder**    | Construct complex objects step-by-step.   | Immutable objects, optional parameters.        |
| **Prototype**  | Clone existing objects.                   | Expensive object creation, shared config.      |

### Summary

Creational patterns simplify object creation and promote flexibility. Use them to:

- **Control object instantiation** (*Singleton*).
- **Delegate creation logic** (*Factory Method, Abstract Factory*).
- **Construct complex objects** (*Builder*).
- **Clone existing objects** (*Prototype*).

---


## Structural design patterns

Structural design patterns focus on how classes and objects are composed to form larger structures. They help ensure that changes in one part of the system don’t require changes throughout the entire system, promoting flexibility, reusability, and maintainability.

### Why Use Structural Patterns?

- **Simplify Relationships**: Make interactions between objects easier to understand.
- **Improve Flexibility**: Allow systems to adapt to changes without major redesigns.
- **Promote Reusability**: Enable components to be reused in different contexts.



## 1. Structural Pattern: Adapter Pattern

### Purpose  
The **Adapter Pattern** allows incompatible interfaces to work together by acting as a bridge between them. It is useful when integrating legacy code or third-party libraries that have different interfaces.  

### Real-World Example  
Imagine you have an **old charging port** but a **new charger with a different plug**. Instead of replacing everything, you use an **adapter** to connect them.  

### Java Code  

```java
// Step 1: Define the Target Interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Step 2: Create an Adaptee Class (Incompatible Interface)
class AdvancedMediaPlayer {
    void playMp4(String fileName) {
        System.out.println("Playing MP4 file: " + fileName);
    }
    
    void playVlc(String fileName) {
        System.out.println("Playing VLC file: " + fileName);
    }
}

// Step 3: Implement the Adapter
class MediaAdapter implements MediaPlayer {
    private AdvancedMediaPlayer advancedMediaPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer = new AdvancedMediaPlayer();
        } else if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new AdvancedMediaPlayer();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(fileName);
        } else if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVlc(fileName);
        }
    }
}

// Step 4: Create a Client Class that Uses the Adapter
class AudioPlayer implements MediaPlayer {
    private MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing MP3 file: " + fileName);
        } else if (audioType.equalsIgnoreCase("mp4") || audioType.equalsIgnoreCase("vlc")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media type: " + audioType);
        }
    }
}

// Step 5: Test the Adapter Pattern
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();

        player.play("mp3", "song.mp3");
        player.play("mp4", "video.mp4");
        player.play("vlc", "movie.vlc");
        player.play("avi", "unknown.avi");
    }
}
```

### Explanation  
- The **`MediaPlayer`** interface is the expected interface.  
- The **`AdvancedMediaPlayer`** is the existing class with a different interface.  
- The **`MediaAdapter`** bridges the gap by translating `MediaPlayer` requests to `AdvancedMediaPlayer`.  
- The **`AudioPlayer`** client uses the adapter to handle different media formats.  

### Why Use It?  
- Enables **integration of incompatible interfaces** without modifying existing code.  
- **Improves reusability** by allowing old and new components to work together.  
- **Encapsulates conversion logic** in a separate adapter class.  

### Best Use Cases:  
- When working with **legacy systems** that have different interfaces.  
- When using **third-party libraries** with mismatched interfaces.  
- When needing to **extend functionality** without modifying existing code.  

### When to Avoid:  
- When direct modification of the code is possible and does not break dependencies.  
- If the **adaptation logic is too complex**, consider refactoring instead.  

### Common Mistakes:  
- **Overusing adapters** instead of refactoring the original code.  
- Making the **adapter too complex**, leading to performance issues.  

### Best Practices:  
- **Keep the adapter simple** and only handle interface mismatches.  
- **Favor composition over inheritance** for better flexibility.  
- **Use adapters only when necessary**, not as a default approach.

---

## 2. Structural Pattern: Bridge Pattern 

### Purpose
The **Bridge Pattern** is a structural design pattern that separates abstraction from implementation, allowing them to evolve independently. This helps in reducing coupling and makes the code more flexible.

### Real-World Example
Imagine a **remote control** that works with different types of **devices** (TV, Radio, etc.). Instead of tightly coupling remotes with specific devices, we create an abstraction for remotes that can work with any device implementation.

### Java Code

```java
// Step 1: Implementor Interface
interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int volume);
}

// Step 2: Concrete Implementations
class TV implements Device {
    private int volume;
    
    @Override
    public void turnOn() {
        System.out.println("TV is turned ON.");
    }
    
    @Override
    public void turnOff() {
        System.out.println("TV is turned OFF.");
    }
    
    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("TV volume set to " + volume);
    }
}

class Radio implements Device {
    private int volume;
    
    @Override
    public void turnOn() {
        System.out.println("Radio is turned ON.");
    }
    
    @Override
    public void turnOff() {
        System.out.println("Radio is turned OFF.");
    }
    
    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        System.out.println("Radio volume set to " + volume);
    }
}

// Step 3: Abstraction
abstract class RemoteControl {
    protected Device device;
    
    public RemoteControl(Device device) {
        this.device = device;
    }
    
    abstract void powerOn();
    abstract void powerOff();
    abstract void setVolume(int volume);
}

// Step 4: Refined Abstraction
class BasicRemote extends RemoteControl {
    public BasicRemote(Device device) {
        super(device);
    }
    
    @Override
    void powerOn() {
        device.turnOn();
    }
    
    @Override
    void powerOff() {
        device.turnOff();
    }
    
    @Override
    void setVolume(int volume) {
        device.setVolume(volume);
    }
}

// Step 5: Client Code
public class BridgePatternExample {
    public static void main(String[] args) {
        Device tv = new TV();
        RemoteControl remote = new BasicRemote(tv);
        remote.powerOn();
        remote.setVolume(10);
        remote.powerOff();
        
        System.out.println();
        
        Device radio = new Radio();
        remote = new BasicRemote(radio);
        remote.powerOn();
        remote.setVolume(5);
        remote.powerOff();
    }
}
```

### Explanation
- The **Device** interface defines operations that concrete devices must implement.
- **TV** and **Radio** are concrete implementations of `Device`.
- **RemoteControl** is an abstract class that bridges the device operations.
- **BasicRemote** extends `RemoteControl` and implements its operations.
- In the **client code**, a remote can control different devices independently.

### Why Use It?
- **Decouples abstraction from implementation**, allowing independent evolution.
- **Improves code maintainability** by reducing tight coupling.
- **Enhances flexibility** by allowing different remote types for various devices.

### Best Use Cases:
- When we have multiple variations of abstraction and implementation.
- When an abstraction and its implementation should be extensible independently.

### When to Avoid:
- When there's no need for independent abstraction and implementation.
- When the overhead of additional classes is unnecessary.

### Common Mistakes:
- Overcomplicating the design when a simple inheritance model suffices.
- Not correctly segregating abstraction and implementation.

### Best Practices:
- Keep the **bridge interface** minimal but extensible.
- Ensure that abstraction and implementation remain **loosely coupled**.


---
## 3. Structural Pattern: Composite Pattern

### Purpose
The **Composite Pattern** is used to treat individual objects and compositions of objects uniformly. It is particularly useful when dealing with tree structures, where individual elements and groups of elements need to be treated in the same way.

### Real-World Example
A **file system** where both files and folders should be accessed with the same interface. A folder can contain files or other folders, forming a tree structure.

### Java Code

```java
// Step 1: Define the Component Interface
interface FileSystemComponent {
    void showDetails();
}

// Step 2: Leaf Class (Individual Object)
class File implements FileSystemComponent {
    private String name;
    
    public File(String name) {
        this.name = name;
    }
    
    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}

// Step 3: Composite Class (Collection of Objects)
import java.util.ArrayList;
import java.util.List;

class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();
    
    public Folder(String name) {
        this.name = name;
    }
    
    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }
    
    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}

// Step 4: Client Code
public class FileSystemDemo {
    public static void main(String[] args) {
        FileSystemComponent file1 = new File("Document.txt");
        FileSystemComponent file2 = new File("Picture.jpg");
        
        Folder folder1 = new Folder("MyDocuments");
        folder1.addComponent(file1);
        folder1.addComponent(file2);
        
        FileSystemComponent file3 = new File("Music.mp3");
        Folder rootFolder = new Folder("Root");
        rootFolder.addComponent(folder1);
        rootFolder.addComponent(file3);
        
        rootFolder.showDetails();
    }
}
```

### Explanation
- The **FileSystemComponent** interface defines a common method `showDetails()`.
- The **File** class represents individual objects in the tree.
- The **Folder** class can contain multiple `FileSystemComponent` objects (both files and folders), making it a composite.
- The **Client (FileSystemDemo)** creates and manipulates the tree structure.

### Why Use It?
- **Simplifies client code** by treating individual and composite objects uniformly.
- **Improves flexibility** by allowing hierarchical structures.
- **Encourages reusability** by allowing new components to be easily added.

### Best Use Cases:
- Tree structures such as GUI components, file systems, or organizational charts.
- When client code should not differentiate between individual objects and compositions.

### When to Avoid:
- When hierarchy is simple and does not require uniform treatment.
- If objects need fundamentally different behaviors that don't fit into a common interface.

### Common Mistakes:
- Not ensuring that composite objects can contain both single elements and groups.
- Overcomplicating implementation with unnecessary abstractions.

### Best Practices:
- Use recursion for processing composite structures efficiently.
- Keep the component interface simple and consistent for both leaf and composite nodes.


---
## 4. Structural Pattern: Decorator Pattern

### Purpose
The **Decorator Pattern** is used to dynamically add behavior or responsibilities to objects without modifying their existing code. It follows the **Open-Closed Principle**, allowing for flexible and reusable code.

### Real-World Example
A coffee shop offers different types of coffee, and customers can add extra ingredients like milk, sugar, or whipped cream. Instead of modifying the base coffee class, we create decorators that wrap the coffee object and add new behavior.

### Java Code

```java
// Step 1: Create the Component Interface
interface Coffee {
    String getDescription();
    double cost();
}

// Step 2: Concrete Component (Base Coffee)
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
    
    @Override
    public double cost() {
        return 5.0;
    }
}

// Step 3: Abstract Decorator Class
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
    
    @Override
    public double cost() {
        return decoratedCoffee.cost();
    }
}

// Step 4: Concrete Decorators
class Milk extends CoffeeDecorator {
    public Milk(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Milk";
    }
    
    @Override
    public double cost() {
        return decoratedCoffee.cost() + 1.5;
    }
}

class Sugar extends CoffeeDecorator {
    public Sugar(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription() + ", Sugar";
    }
    
    @Override
    public double cost() {
        return decoratedCoffee.cost() + 0.5;
    }
}

// Step 5: Client Code
public class CoffeeShop {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " -> $" + coffee.cost());
        
        coffee = new Milk(coffee);
        System.out.println(coffee.getDescription() + " -> $" + coffee.cost());
        
        coffee = new Sugar(coffee);
        System.out.println(coffee.getDescription() + " -> $" + coffee.cost());
    }
}
```

### Explanation
- The **Coffee** interface defines the structure for all coffee types.
- **SimpleCoffee** is the base concrete component.
- **CoffeeDecorator** is an abstract class that wraps a `Coffee` object and forwards requests.
- **Milk** and **Sugar** are concrete decorators that enhance coffee dynamically.
- The **CoffeeShop** class demonstrates how to create coffee and wrap it with different decorators.

### Why Use It?
- **Adds new behavior dynamically** without modifying the original class.
- **Follows the Open-Closed Principle**, making it easier to extend functionality.
- **Encourages composition over inheritance**, reducing class explosion.

### Best Use Cases:
- GUI components (e.g., adding scrollbars, borders to windows).
- Text processing (e.g., adding bold, italic styles to text).
- Logging, encryption, and compression functionalities.

### When to Avoid:
- If modifications to the base class are possible and simpler.
- When too many decorators create complexity and performance issues.

### Common Mistakes:
- Overusing dec
---
## 5. Structural Pattern: Flyweight Pattern
### Purpose
The **Flyweight Pattern** is used to minimize memory usage or computational expenses by sharing as much data as possible with similar objects. This is useful when a program needs to create a large number of objects that share common properties.

### Real-World Example
A text editor where multiple instances of the same character share the same font and style instead of creating a new object for each character.

### Java Code

```java
// Step 1: Flyweight Interface
interface Character {
    void display(int x, int y);
}

// Step 2: Concrete Flyweight Implementation
class Letter implements Character {
    private final char symbol; // Intrinsic state (shared)
    
    public Letter(char symbol) {
        this.symbol = symbol;
    }
    
    @Override
    public void display(int x, int y) {
        System.out.println("Displaying " + symbol + " at (" + x + ", " + y + ")");
    }
}

// Step 3: Flyweight Factory
import java.util.HashMap;
import java.util.Map;

class CharacterFactory {
    private static final Map<Character, Letter> letterMap = new HashMap<>();
    
    public static Character getLetter(char symbol) {
        letterMap.putIfAbsent(symbol, new Letter(symbol));
        return letterMap.get(symbol);
    }
}

// Step 4: Client Code
public class TextEditor {
    public static void main(String[] args) {
        Character a1 = CharacterFactory.getLetter('A');
        Character a2 = CharacterFactory.getLetter('A');
        Character b1 = CharacterFactory.getLetter('B');
        
        // Display characters at different positions
        a1.display(10, 20);
        a2.display(30, 40);
        b1.display(50, 60);
        
        // Check if A instances are shared
        System.out.println("a1 and a2 are the same instance: " + (a1 == a2));
    }
}
```

### Explanation
- **Flyweight Interface (Character)**: Defines a method for displaying characters.
- **Concrete Flyweight (Letter)**: Stores the intrinsic state (symbol) that is shared across multiple instances.
- **Flyweight Factory (CharacterFactory)**: Ensures that identical characters are shared rather than created anew.
- **Client Code (TextEditor)**: Retrieves character objects from the factory and uses them efficiently.

### Why Use It?
- **Reduces memory usage** by sharing common objects instead of creating duplicates.
- **Improves performance** in applications that require a large number of similar objects.
- **Encapsulates shared state**, ensuring efficient object management.

### Best Use Cases:
- **Text rendering systems** (e.g., fonts, icons, glyphs).
- **Game development** (e.g., large-scale object rendering).
- **Data caching mechanisms**.

### When to Avoid:
- When objects don’t have enough shared properties.
- When managing the shared state becomes complex.

### Common Mistakes:
- **Forgetting to use the factory**, leading to duplicate objects.
- **Not handling thread safety** when multiple threads access the factory.

### Best Practices:
- Use **immutable intrinsic states** to prevent unintended modifications.
- Ensure **proper caching** for efficient object reuse.
---
## 6. Structural Pattern:  Proxy Pattern
### Purpose
The **Proxy Pattern** is a structural design pattern that provides an object (proxy) as a substitute for another object to control access to it. This is useful when adding additional functionality, like security, logging, or lazy initialization, without modifying the actual object.

### Real-World Example
A banking system where a **proxy** controls access to a **real bank account** to ensure security checks before performing operations.

### Java Code

```java
// Step 1: Create the Subject Interface
interface BankAccount {
    void withdraw(double amount);
}

// Step 2: Implement the Real Object
class RealBankAccount implements BankAccount {
    private double balance;

    public RealBankAccount(double balance) {
        this.balance = balance;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | Remaining balance: " + balance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
}

// Step 3: Create the Proxy Class
class BankAccountProxy implements BankAccount {
    private RealBankAccount realAccount;
    private String password;

    public BankAccountProxy(double balance, String password) {
        this.realAccount = new RealBankAccount(balance);
        this.password = password;
    }

    private boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    @Override
    public void withdraw(double amount) {
        if (authenticate("secure123")) {  // Simulating password check
            realAccount.withdraw(amount);
        } else {
            System.out.println("Access Denied: Incorrect password!");
        }
    }
}

// Step 4: Client Code
public class ProxyPatternDemo {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccountProxy(1000, "secure123");
        myAccount.withdraw(500); // Successful transaction
        myAccount.withdraw(600); // Insufficient funds scenario
    }
}
```

### Explanation
- **BankAccount** is the common interface for both the real object and the proxy.
- **RealBankAccount** implements `BankAccount` and handles withdrawals.
- **BankAccountProxy** controls access to `RealBankAccount` by checking authentication before performing any operation.
- In the **ProxyPatternDemo**, the client interacts with `BankAccountProxy`, which manages security and forwards valid requests to `RealBankAccount`.

### Why Use It?
- **Access Control**: Restricts access to an object with authentication mechanisms.
- **Lazy Initialization**: Defers the creation of an object until it is needed.
- **Logging & Monitoring**: Adds logging without modifying the real object.
- **Security**: Protects sensitive operations by enforcing authorization.

### Best Use Cases
- Implementing virtual proxies for **lazy loading**.
- Using protection proxies for **security enforcement**.
- Creating logging or caching layers without modifying the real object.

### When to Avoid
- When there is no additional functionality needed between the client and the real object.
- If the proxy adds unnecessary complexity or performance overhead.

### Common Mistakes
- Overcomplicating the proxy with excessive validation.
- Using a proxy when a simple wrapper class would suffice.

### Best Practices
- Keep the proxy **lightweight** to avoid performance issues.
- Ensure that proxies do not introduce unnecessary **bottlenecks**.
- Use proxies to **extend functionality** rather than duplicate it.
---

### Comparison of Structural Patterns

| Pattern   | Purpose                          | When to Use                            |
|-----------|----------------------------------|----------------------------------------|
| Adapter   | Convert interfaces.             | Integrating incompatible systems.      |
| Bridge    | Separate abstraction from implementation. | Supporting multiple platforms. |
| Composite | Represent part-whole hierarchies. | Hierarchical structures (e.g., menus). |
| Decorator | Add behavior dynamically.       | Extending functionality at runtime.    |
| Facade    | Simplify complex subsystems.    | Providing a unified interface.        |
| Flyweight | Minimize memory usage.          | Managing large numbers of objects.    |
| Proxy     | Control access to objects.      | Lazy initialization, access control.  |

### Summary
Structural patterns help organize classes and objects into larger structures. Use them to:

- **Simplify interactions** (Adapter, Facade).
- **Separate concerns** (Bridge, Proxy).
- **Manage hierarchies** (Composite).
- **Optimize resource usage** (Flyweight).
- **Add flexibility** (Decorator).

---

## Behavioral design patterns

**Behavioral design patterns** focus on how **objects interact and communicate with each other**. They help manage responsibilities, algorithms, and workflows in a flexible and reusable way. These patterns are particularly useful when you want to decouple objects, simplify complex interactions, or make systems more maintainable.


### 1. Behavioral Pattern: Observer

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

### 2. Behavioral Pattern: Strategy

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
### Why Use It?

- Switch algorithms at runtime (e.g., payment method in checkout).
- Isolate algorithm code from the client.

---
### 3. Behavioral Pattern: State
### Purpose
The **State Pattern** is a behavioral design pattern that allows an object to change its behavior when its internal state changes. Instead of using multiple `if-else` conditions, this pattern organizes state-specific logic into separate classes.

### Real-World Example
A **traffic light system** where the light transitions between different states (**Red, Green, Yellow**) with specific behaviors for each.

### Java Code

```java
// Step 1: Define the State Interface
interface TrafficLightState {
    void changeLight(TrafficLightContext context);
    void displayState();
}

// Step 2: Concrete State Implementations
class RedLight implements TrafficLightState {
    @Override
    public void changeLight(TrafficLightContext context) {
        System.out.println("Changing from RED to GREEN");
        context.setState(new GreenLight());
    }
    
    @Override
    public void displayState() {
        System.out.println("The traffic light is RED. Please stop.");
    }
}

class GreenLight implements TrafficLightState {
    @Override
    public void changeLight(TrafficLightContext context) {
        System.out.println("Changing from GREEN to YELLOW");
        context.setState(new YellowLight());
    }
    
    @Override
    public void displayState() {
        System.out.println("The traffic light is GREEN. You can go.");
    }
}

class YellowLight implements TrafficLightState {
    @Override
    public void changeLight(TrafficLightContext context) {
        System.out.println("Changing from YELLOW to RED");
        context.setState(new RedLight());
    }
    
    @Override
    public void displayState() {
        System.out.println("The traffic light is YELLOW. Prepare to stop.");
    }
}

// Step 3: Context Class that Maintains the Current State
class TrafficLightContext {
    private TrafficLightState state;
    
    public TrafficLightContext() {
        // Initial state
        state = new RedLight();
    }
    
    public void setState(TrafficLightState state) {
        this.state = state;
    }
    
    public void changeLight() {
        state.changeLight(this);
    }
    
    public void displayState() {
        state.displayState();
    }
}

// Step 4: Client Code
public class TrafficLightSystem {
    public static void main(String[] args) {
        TrafficLightContext trafficLight = new TrafficLightContext();
        
        // Simulate traffic light changes
        for (int i = 0; i < 3; i++) {
            trafficLight.displayState();
            trafficLight.changeLight();
        }
    }
}
```

### Explanation
- **State Interface (`TrafficLightState`)**: Defines methods `changeLight()` and `displayState()`.
- **Concrete States (`RedLight`, `GreenLight`, `YellowLight`)**: Implement the behavior for each state.
- **Context (`TrafficLightContext`)**: Maintains the current state and delegates state-specific behavior.
- **Client (`TrafficLightSystem`)**: Simulates traffic light transitions.

### Why Use It?
- **Encapsulates state-specific behavior** in separate classes.
- **Eliminates complex `if-else` or `switch` statements** for state transitions.
- **Improves maintainability** by promoting the Open-Closed Principle.

### Best Use Cases
- Finite state machines (e.g., vending machines, elevators, game AI).
- Workflow or process management (e.g., document approvals, order processing).
- UI components with dynamic behavior (e.g., button states, animations).

### When to Avoid
- If the number of states is fixed and simple (`if-else` might suffice).
- When additional classes for each state would add unnecessary complexity.

### Common Mistakes
- Not making states immutable (avoid modifying the same instance for different states).
- Forgetting to delegate state changes via the context class.

### Best Practices
- Use dependency injection to manage state transitions dynamically.
- Keep state logic encapsulated within state classes.
- Ensure state transitions follow a clear, logical order to prevent inconsistencies.


---
### 4. Behavioral Pattern: Command
### Purpose
The **Command Pattern** is a behavioral design pattern that turns a request into a stand-alone object. This allows the request to be parameterized, queued, and executed at different times. It promotes loose coupling between the sender and receiver of a request.

### Real-World Example
A **remote control** for a smart home system where each button press is a command (e.g., turn on lights, turn off TV), and the remote can undo or redo actions.

### Java Code

```java
// Step 1: Command Interface
interface Command {
    void execute();
    void undo();
}

// Step 2: Concrete Commands
class LightOnCommand implements Command {
    private Light light;
    
    public LightOnCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOn();
    }
    
    @Override
    public void undo() {
        light.turnOff();
    }
}

class LightOffCommand implements Command {
    private Light light;
    
    public LightOffCommand(Light light) {
        this.light = light;
    }
    
    @Override
    public void execute() {
        light.turnOff();
    }
    
    @Override
    public void undo() {
        light.turnOn();
    }
}

// Step 3: Receiver
class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }
    public void turnOff() {
        System.out.println("Light is OFF");
    }
}

// Step 4: Invoker
class RemoteControl {
    private Command command;
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public void pressButton() {
        command.execute();
    }
    
    public void pressUndo() {
        command.undo();
    }
}

// Step 5: Client Code
public class SmartHome {
    public static void main(String[] args) {
        Light light = new Light();
        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        
        RemoteControl remote = new RemoteControl();
        
        remote.setCommand(lightOn);
        remote.pressButton();  // Output: Light is ON
        remote.pressUndo();     // Output: Light is OFF
        
        remote.setCommand(lightOff);
        remote.pressButton();  // Output: Light is OFF
        remote.pressUndo();     // Output: Light is ON
    }
}
```

### Explanation
- **Command Interface**: Defines the execute and undo operations.
- **Concrete Commands**: Implement the Command interface and encapsulate the actions.
- **Receiver (Light)**: Performs the actual operations.
- **Invoker (RemoteControl)**: Stores and triggers commands.
- **Client (SmartHome)**: Configures and executes commands dynamically.

### Why Use It?
- **Decouples sender and receiver**: The client code doesn't need to know how requests are handled.
- **Supports undo/redo functionality**: Makes it easy to revert actions.
- **Allows dynamic command configuration**: Commands can be assigned at runtime.

### Best Use Cases
- Implementing **undo/redo** operations.
- Creating **macro commands** (batching multiple actions).
- **Queueing and scheduling** operations (e.g., job scheduling systems).

### When to Avoid
- When a **simple method call** is sufficient (overhead may not be justified).
- When **command logic is trivial** and doesn’t require encapsulation.

### Common Mistakes
- **Not implementing undo functionality** when required.
- **Tightly coupling** commands to specific receivers.

### Best Practices
- **Use a command history** to support undo/redo.
- **Keep commands lightweight** and focused on a single action.
- **Consider using the Command pattern with a queue** for delayed execution.


---
### 5. Behavioral Pattern: Chain of Responsibility
### Purpose
The **Chain of Responsibility Pattern** is used to process a request through a chain of handlers. Each handler decides either to process the request or pass it to the next handler in the chain. This decouples senders from receivers and provides flexibility in handling requests dynamically.

### Real-World Example
A **customer support system** where different levels of support (e.g., Basic Support, Supervisor, Manager) handle issues based on severity. If a handler cannot resolve the request, it forwards it to the next level.

### Java Code

```java
// Step 1: Define the Handler Interface
abstract class SupportHandler {
    protected SupportHandler nextHandler;

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void handleRequest(String requestType);
}

// Step 2: Concrete Handlers
class BasicSupport extends SupportHandler {
    @Override
    public void handleRequest(String requestType) {
        if (requestType.equals("Basic")) {
            System.out.println("Basic Support: Issue resolved at basic level.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

class SupervisorSupport extends SupportHandler {
    @Override
    public void handleRequest(String requestType) {
        if (requestType.equals("Intermediate")) {
            System.out.println("Supervisor Support: Issue resolved at supervisor level.");
        } else if (nextHandler != null) {
            nextHandler.handleRequest(requestType);
        }
    }
}

class ManagerSupport extends SupportHandler {
    @Override
    public void handleRequest(String requestType) {
        if (requestType.equals("Advanced")) {
            System.out.println("Manager Support: Issue resolved at manager level.");
        } else {
            System.out.println("No handler available for request: " + requestType);
        }
    }
}

// Step 3: Client Code
public class CustomerSupportSystem {
    public static void main(String[] args) {
        // Creating handlers
        SupportHandler basic = new BasicSupport();
        SupportHandler supervisor = new SupervisorSupport();
        SupportHandler manager = new ManagerSupport();

        // Setting up chain
        basic.setNextHandler(supervisor);
        supervisor.setNextHandler(manager);

        // Sending requests
        System.out.println("Customer issues:");
        basic.handleRequest("Basic");       // Handled by Basic Support
        basic.handleRequest("Intermediate"); // Handled by Supervisor Support
        basic.handleRequest("Advanced");    // Handled by Manager Support
        basic.handleRequest("Unknown");     // No handler available
    }
}
```

### Explanation
- The **SupportHandler** class defines the structure for handling requests and passing them along the chain.
- **BasicSupport**, **SupervisorSupport**, and **ManagerSupport** handle different levels of requests.
- The **CustomerSupportSystem** sets up the chain and processes requests step by step.

### Why Use It?
- **Reduces coupling** between sender and receiver.
- **Improves flexibility** in request handling.
- **Follows open/closed principle**, allowing new handlers to be added easily.

### Best Use Cases:
- Logging frameworks where logs pass through different levels.
- Event handling systems.
- Workflow approvals (e.g., leave requests in a company).

### When to Avoid:
- When there is always a single, fixed handler.
- When the chain can become too long, affecting performance.

### Common Mistakes:
- Not defining a proper stopping condition, leading to infinite loops.
- Making handlers too dependent on specific types of requests.

### Best Practices:
- Ensure each handler has a well-defined responsibility.
- Keep the chain flexible for easy modifications.
- Avoid long chains that can degrade performance.


---
### 6. Behavioral Pattern: Template
### Purpose
The **Template Pattern** is a behavioral design pattern that defines the skeleton of an algorithm in a base class but lets subclasses alter specific steps without changing the overall structure.

### Real-World Example
A meal preparation process where the overall steps remain the same (e.g., preparing ingredients, cooking, serving), but specific implementations vary (e.g., different cooking techniques for different cuisines).

### Java Code

```java
// Step 1: Define the Abstract Class with a Template Method
abstract class Meal {
    // Template method defining the skeleton of the algorithm
    public final void prepareMeal() {
        prepareIngredients();
        cook();
        serve();
    }

    protected abstract void prepareIngredients();
    protected abstract void cook();
    protected void serve() {
        System.out.println("Serving the meal.");
    }
}

// Step 2: Concrete Implementations
class PastaMeal extends Meal {
    @Override
    protected void prepareIngredients() {
        System.out.println("Preparing pasta, tomatoes, and cheese.");
    }
    
    @Override
    protected void cook() {
        System.out.println("Cooking pasta and making sauce.");
    }
}

class SaladMeal extends Meal {
    @Override
    protected void prepareIngredients() {
        System.out.println("Chopping vegetables and preparing dressing.");
    }
    
    @Override
    protected void cook() {
        System.out.println("Mixing ingredients (no cooking required).");
    }
}

// Step 3: Client Code
public class TemplatePatternDemo {
    public static void main(String[] args) {
        Meal pasta = new PastaMeal();
        pasta.prepareMeal();

        System.out.println();

        Meal salad = new SaladMeal();
        salad.prepareMeal();
    }
}
```

### Explanation
- The **Meal** abstract class defines the `prepareMeal()` template method, which outlines the meal preparation steps.
- Subclasses **PastaMeal** and **SaladMeal** implement specific steps (`prepareIngredients()` and `cook()`) while keeping the `serve()` method generic.
- The **final** modifier on `prepareMeal()` prevents subclasses from modifying the overall structure.

### Why Use It?
- **Ensures a consistent algorithm structure** while allowing flexibility in specific steps.
- **Promotes code reuse** by defining common logic in a base class.
- **Encapsulates variations** in subclasses without affecting the main algorithm.

### Best Use Cases:
- When multiple classes share a similar workflow but have different implementations for some steps.
- When you want to enforce a structure for algorithms but allow variations in execution.

### When to Avoid:
- If each subclass requires significant customization, making the template too rigid.
- When the common structure is minimal, making the pattern unnecessary.

### Common Mistakes:
- Allowing subclasses to override the template method, breaking the intended structure.
- Defining too many abstract methods, making the base class hard to maintain.

### Best Practices:
- Keep the template method `final` to prevent unintended modifications.
- Use protected methods for steps to enforce encapsulation while allowing subclass modifications.
- Avoid making the base class too complex; only include essential common logic.


---
### 7. Behavioral Pattern: Interpreter
### Purpose
The **Interpreter Pattern** is used to define a grammatical representation of a language and provide an interpreter to process sentences in that language. This pattern is useful when dealing with complex parsing problems, such as mathematical expressions, programming languages, or query interpreters.

### Real-World Example
A simple arithmetic expression evaluator that interprets expressions like `5 + 3 - 2`.

### Java Code

```java
// Step 1: Define the Expression Interface
interface Expression {
    int interpret();
}

// Step 2: Concrete Implementations for Numbers
class NumberExpression implements Expression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret() {
        return number;
    }
}

// Step 3: Concrete Implementations for Operations
class AdditionExpression implements Expression {
    private Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }
}

class SubtractionExpression implements Expression {
    private Expression left, right;

    public SubtractionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() - right.interpret();
    }
}

// Step 4: Client Code
public class InterpreterPatternDemo {
    public static void main(String[] args) {
        // Construct the expression: 5 + (3 - 2)
        Expression five = new NumberExpression(5);
        Expression three = new NumberExpression(3);
        Expression two = new NumberExpression(2);
        Expression subtraction = new SubtractionExpression(three, two);
        Expression addition = new AdditionExpression(five, subtraction);

        // Interpret the expression
        System.out.println("Result: " + addition.interpret());
    }
}
```

### Explanation
- The **Expression** interface defines a method `interpret()`.
- **NumberExpression** represents numbers in the expression.
- **AdditionExpression** and **SubtractionExpression** perform arithmetic operations.
- The **InterpreterPatternDemo** constructs and evaluates the expression `5 + (3 - 2)`.

### Why Use It?
- Useful for parsing expressions or DSLs (Domain-Specific Languages).
- Encapsulates complex grammar parsing logic into objects.
- Makes the system extensible by adding new rules without modifying existing code.

### Best Use Cases:
- Compilers or interpreters for scripting languages.
- SQL query evaluators.
- Mathematical expression solvers.
- Chatbot commands and automation scripts.

### When to Avoid:
- When the grammar is too complex; using parser generators like ANTLR may be better.
- If performance is critical, as multiple objects are created during parsing.

### Common Mistakes:
- Making the grammar too complex, leading to unmanageable code.
- Inefficient recursive interpretation that can slow down execution.

### Best Practices:
- Keep grammar rules simple and modular.
- Use caching or optimization strategies to improve interpretation performance.
- Consider alternatives like state machines for very complex grammars.


---
### 8. Behavioral Pattern: Visitor
### Purpose
The **Visitor Pattern** is used to separate algorithms from the objects on which they operate. It allows adding new operations to a set of classes without modifying them, promoting **open/closed principle**.

### Real-World Example
A **shopping cart** where different types of items (books, electronics) apply different tax calculations or discounts without modifying the item classes themselves.

### Java Code

```java
// Step 1: Create the Visitor Interface
interface ShoppingCartVisitor {
    int visit(Book book);
    int visit(Electronic electronic);
}

// Step 2: Concrete Visitor Implementation
class ShoppingCartVisitorImpl implements ShoppingCartVisitor {
    @Override
    public int visit(Book book) {
        int cost = book.getPrice();
        System.out.println("Book: " + book.getTitle() + " costs " + cost);
        return cost;
    }

    @Override
    public int visit(Electronic electronic) {
        int cost = electronic.getPrice() + 5; // Additional tax
        System.out.println("Electronic: " + electronic.getName() + " costs " + cost);
        return cost;
    }
}

// Step 3: Element Interface
interface Item {
    int accept(ShoppingCartVisitor visitor);
}

// Step 4: Concrete Elements
class Book implements Item {
    private String title;
    private int price;
    
    public Book(String title, int price) {
        this.title = title;
        this.price = price;
    }
    
    public String getTitle() { return title; }
    public int getPrice() { return price; }
    
    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}

class Electronic implements Item {
    private String name;
    private int price;
    
    public Electronic(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() { return name; }
    public int getPrice() { return price; }
    
    @Override
    public int accept(ShoppingCartVisitor visitor) {
        return visitor.visit(this);
    }
}

// Step 5: Client Code
public class ShoppingCart {
    public static void main(String[] args) {
        Item[] items = new Item[]{
            new Book("Design Patterns", 50),
            new Electronic("Headphones", 100)
        };
        
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        int totalCost = 0;
        for (Item item : items) {
            totalCost += item.accept(visitor);
        }
        
        System.out.println("Total Cost: " + totalCost);
    }
}
```

### Explanation
- **Visitor Interface (`ShoppingCartVisitor`)** defines visit methods for different object types.
- **Concrete Visitor (`ShoppingCartVisitorImpl`)** implements logic for each type of object.
- **Element Interface (`Item`)** allows objects to accept visitors.
- **Concrete Elements (`Book`, `Electronic`)** implement `accept()` to call the visitor’s method.
- **Client (`ShoppingCart`)** iterates over items and applies the visitor pattern for price calculation.

### Why Use It?
- **Extends functionality** without modifying existing classes.
- **Encapsulates operations** outside the object structure.
- **Follows Open/Closed Principle**.

### Best Use Cases
- When new operations need to be added to existing classes without altering them.
- When multiple objects share similar behaviors that require different implementations.

### When to Avoid
- If the class hierarchy frequently changes, modifying all visitors can become cumbersome.
- When adding new types of elements, as every visitor must implement methods for new types.

### Common Mistakes
- Violating encapsulation by exposing too much internal state.
- Making visitors too complex with excessive conditional logic.

### Best Practices
- Keep visitor methods focused on specific tasks.
- Minimize the impact on the element classes by making visitor logic self-contained.
---
### 9. Behavioral Pattern: Mediator
### Purpose
The **Mediator Pattern** is used to reduce the dependencies between components (objects) by introducing a mediator that handles communication between them. This promotes loose coupling and makes the system easier to maintain.

### Real-World Example
A **chatroom system**, where multiple users communicate with each other. Instead of users directly sending messages to each other, a central chatroom (mediator) manages message delivery.

### Java Code

```java
// Step 1: Define the Mediator Interface
interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

// Step 2: Implement the Concrete Mediator
import java.util.ArrayList;
import java.util.List;

class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();
    
    @Override
    public void addUser(User user) {
        users.add(user);
    }
    
    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            if (user != sender) {
                user.receiveMessage(message);
            }
        }
    }
}

// Step 3: Create the Colleague Class
abstract class User {
    protected ChatMediator mediator;
    protected String name;
    
    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }
    
    abstract void send(String message);
    abstract void receiveMessage(String message);
}

// Step 4: Concrete User Implementations
class ChatUser extends User {
    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }
    
    @Override
    void send(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }
    
    @Override
    void receiveMessage(String message) {
        System.out.println(name + " received: " + message);
    }
}

// Step 5: Client Code
public class MediatorPatternDemo {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();
        
        User user1 = new ChatUser(chatRoom, "Alice");
        User user2 = new ChatUser(chatRoom, "Bob");
        User user3 = new ChatUser(chatRoom, "Charlie");
        
        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);
        
        user1.send("Hello, everyone!");
    }
}
```

### Explanation
- The **ChatMediator** interface defines methods for message sending and user management.
- **ChatRoom** is the concrete mediator that handles communication between users.
- **User** is an abstract class representing a participant in the chatroom.
- **ChatUser** extends `User` and implements message sending and receiving.
- In the **MediatorPatternDemo**, users send messages through the mediator instead of directly communicating.

### Why Use It?
- **Reduces direct dependencies** between components.
- **Enhances code maintainability** by centralizing communication logic.
- **Improves flexibility** as components can be modified independently.

### Best Use Cases:
- When multiple components interact frequently.
- When you need a centralized communication mechanism.
- GUI applications where buttons, sliders, and input fields interact.

### When to Avoid:
- When only a few objects interact directly.
- When communication logic is simple and doesn’t need centralization.

### Common Mistakes:
- Making the mediator too complex, leading to a **God Object**.
- Not properly defining interactions, which can lead to miscommunication.

### Best Practices:
- Keep the mediator focused on communication logic.
- Avoid adding unnecessary responsibilities to the mediator.
- Use logging or debugging tools to track mediator interactions.
---

### 10. Behavioral Pattern: Memento
### Purpose
The **Memento Pattern** is used to capture and restore an object's state without violating encapsulation. This is useful when implementing undo/redo functionality in applications.

### Real-World Example
A text editor where users can undo and redo their actions by saving and restoring previous states of a document.

### Java Code

```java
// Step 1: Create the Memento Class
class TextEditorMemento {
    private final String content;

    public TextEditorMemento(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// Step 2: Create the Originator
class TextEditor {
    private String content;

    public void write(String newText) {
        content = newText;
    }

    public String read() {
        return content;
    }

    public TextEditorMemento save() {
        return new TextEditorMemento(content);
    }

    public void restore(TextEditorMemento memento) {
        content = memento.getContent();
    }
}

// Step 3: Create the Caretaker
import java.util.Stack;

class EditorHistory {
    private final Stack<TextEditorMemento> history = new Stack<>();

    public void save(TextEditorMemento memento) {
        history.push(memento);
    }

    public TextEditorMemento undo() {
        if (!history.isEmpty()) {
            return history.pop();
        }
        return null;
    }
}

// Step 4: Client Code
public class MementoPatternExample {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        EditorHistory history = new EditorHistory();

        editor.write("Hello, World!");
        history.save(editor.save());
        System.out.println("Current Content: " + editor.read());

        editor.write("Design Patterns in Java");
        history.save(editor.save());
        System.out.println("Updated Content: " + editor.read());

        editor.restore(history.undo());
        System.out.println("After Undo: " + editor.read());
    }
}
```

### Explanation
- The **Memento (TextEditorMemento)** stores the state of an object.
- The **Originator (TextEditor)** creates and restores mementos.
- The **Caretaker (EditorHistory)** keeps track of mementos and allows undo operations.
- The **Client** interacts with these components to save and restore states.

### Why Use It?
- Enables undo/redo functionality.
- Preserves encapsulation by storing state externally.
- Useful for applications that require historical states.

### Best Use Cases
- Text editors, drawing applications, and games with checkpoints.
- Systems requiring version control or rollback features.

### When to Avoid
- When storing large objects frequently, as it may cause high memory usage.

### Common Mistakes
- Storing unnecessary states, leading to excessive memory consumption.
- Allowing external modification of the memento, violating encapsulation.

### Best Practices
- Keep memento immutable to prevent unintended modifications.
- Use a limited history size to manage memory efficiently.
---

### 11. Behavioral Pattern: Iterator

### Purpose  
The **Iterator Pattern** provides a way to **access elements** of a collection **sequentially** without exposing its underlying structure. This pattern is useful when working with complex data structures like lists, trees, or graphs.  

### Real-World Example  
A **playlist** in a music player allows users to navigate through songs **one by one** without directly accessing the internal data structure of the playlist.  

### Java Code  

```java
// Step 1: Create the Iterator Interface
interface SongIterator {
    boolean hasNext();
    String next();
}

// Step 2: Implement Concrete Iterator
import java.util.List;

class PlaylistIterator implements SongIterator {
    private List<String> songs;
    private int position = 0;

    public PlaylistIterator(List<String> songs) {
        this.songs = songs;
    }

    @Override
    public boolean hasNext() {
        return position < songs.size();
    }

    @Override
    public String next() {
        if (hasNext()) {
            return songs.get(position++);
        }
        return null;
    }
}

// Step 3: Create the Aggregate Interface
interface Playlist {
    SongIterator createIterator();
}

// Step 4: Implement Concrete Aggregate
import java.util.ArrayList;
import java.util.List;

class MusicPlaylist implements Playlist {
    private List<String> songs = new ArrayList<>();

    public void addSong(String song) {
        songs.add(song);
    }

    @Override
    public SongIterator createIterator() {
        return new PlaylistIterator(songs);
    }
}

// Step 5: Client Code
public class IteratorPatternExample {
    public static void main(String[] args) {
        MusicPlaylist playlist = new MusicPlaylist();
        playlist.addSong("Song 1");
        playlist.addSong("Song 2");
        playlist.addSong("Song 3");

        SongIterator iterator = playlist.createIterator();

        System.out.println("Playlist:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```  

---  

### Explanation  
- **Iterator Interface (SongIterator):** Defines methods to traverse a collection (`hasNext()` and `next()`).  
- **Concrete Iterator (PlaylistIterator):** Implements the iterator logic for traversing a **list of songs**.  
- **Aggregate Interface (Playlist):** Defines the method `createIterator()` to return an iterator.  
- **Concrete Aggregate (MusicPlaylist):** Stores a collection of songs and returns an iterator for traversal.  
- **Client Code:** Uses the iterator to **access songs one by one** without exposing the playlist’s internal structure.  

### Why Use It?  
- **Encapsulates traversal logic** outside the collection class.  
- **Supports multiple iteration strategies** without modifying the collection.  
- **Simplifies navigation** through complex data structures like trees, graphs, and composite objects.  

### Best Use Cases  
- Navigating elements in **lists, sets, trees, or graphs**.  
- Implementing **playlist navigation, database cursors, or social media feeds**.  
- **Decoupling iteration logic** from the collection itself.  

### When to Avoid  
- When simple `for-each` loops or Java's built-in `Iterator` are sufficient.  
- If direct access to the collection does not break encapsulation.  

### Common Mistakes  
- **Skipping the `hasNext()` check**, which can cause `NullPointerException`.  
- **Tightly coupling** the iterator to a specific collection implementation.  
- **Modifying the collection while iterating**, leading to `ConcurrentModificationException`.  

### Best Practices  
- **Keep iterators lightweight** and **separate from the collection**.  
- **Use built-in Java iterators** (like `Iterator<E>`) where possible.  
- **Implement different iteration strategies** (e.g., reverse iteration, filtering).  

---

### Key Takeaways

- **Creational Patterns** simplify object creation.
- **Structural Patterns** build flexible class/object structures.
- **Behavioral Patterns** manage communication between objects.


# CHALLENGES

### Challenge Part 1

# Challenging Coding Exercises: Design Patterns in Java

Here’s a collection of challenging coding exercises to practice Design Patterns in Java, categorized into Creational, Structural, and Behavioral patterns. Each challenge is designed to be practical, real-world-oriented, and progressively challenging.

## 1️⃣ Creational Patterns

### 1. Singleton: Global Configuration Manager
**Problem:** Create a Configuration Manager that reads configuration settings from a file and ensures only one instance of the manager exists throughout the application.

**Requirements:**
- Use the Singleton pattern to ensure a single instance.
- Load configuration settings (e.g., database URL, API keys) from a file (e.g., `config.properties`).
- Provide methods to access configuration values.

**Hints:**
- Use lazy initialization and thread safety.
- Use a static inner class or enum for the Singleton implementation.

**Difficulty:** Beginner

### 2. Factory Method: Payment Gateway Integration
**Problem:** Implement a Payment Gateway Factory that creates different payment processors (e.g., PayPal, Stripe, Credit Card) based on user input.

**Requirements:**
- Use the Factory Method pattern to create payment processors.
- Each payment processor should have a `processPayment(double amount)` method.
- The factory should decide which processor to instantiate based on user input.

**Hints:**
- Define an abstract `PaymentProcessor` class or interface.
- Create concrete classes for each payment processor.
- Use a factory method to instantiate the appropriate processor.

**Difficulty:** Intermediate

### 3. Abstract Factory: UI Theme System
**Problem:** Build a UI Theme System that creates consistent light and dark themes for buttons, text fields, and dialogs.

**Requirements:**
- Use the Abstract Factory pattern to create families of UI components.
- Define factories for `LightTheme` and `DarkTheme`.
- Each theme should produce matching buttons, text fields, and dialogs.

**Hints:**
- Create abstract classes for buttons, text fields, and dialogs.
- Implement concrete classes for each theme.
- Use an abstract factory to create the components.

**Difficulty:** Intermediate

### 4. Builder: Customizable Pizza Ordering System
**Problem:** Design a Pizza Ordering System where users can customize their pizza with toppings, crust type, and size.

**Requirements:**
- Use the Builder pattern to construct `Pizza` objects.
- Allow users to add toppings, choose crust type (thin, thick), and select size (small, medium, large).
- Provide a `PizzaBuilder` class to build the pizza step-by-step.

**Hints:**
- Use a `Pizza` class with attributes like `toppings`, `crustType`, and `size`.
- Implement a `PizzaBuilder` class with methods like `addTopping()`, `setCrustType()`, and `setSize()`.
- Use a `Director` class to manage the building process.

**Difficulty:** Intermediate

### 5. Prototype: Game Character Cloning
**Problem:** Implement a Game Character System where characters can be cloned to create new instances with similar attributes.

**Requirements:**
- Use the Prototype pattern to clone game characters.
- Each character should have attributes like `name`, `health`, and `weapon`.
- Allow deep cloning of characters to create independent copies.

**Hints:**
- Implement the `Cloneable` interface in the `Character` class.
- Override the `clone()` method to perform deep cloning.
- Use a `CharacterRegistry` to store and retrieve prototypes.

**Difficulty:** Advanced

## 2️⃣ Structural Patterns

### 1. Adapter: Legacy System Integration
**Problem:** Integrate a Legacy Payment System that uses an incompatible interface with a modern payment processing system.

**Requirements:**
- Use the Adapter pattern to make the legacy system compatible with the modern system.
- The legacy system has a method `processLegacyPayment(double amount)`.
- The modern system expects a method `processPayment(double amount)`.

**Hints:**
- Create an adapter class that implements the modern interface.
- Use composition to delegate calls to the legacy system.

**Difficulty:** Beginner

### 2. Bridge: Remote Control for Devices
**Problem:** Design a Remote Control System that works with different devices (e.g., TV, Radio) and supports multiple remote types (e.g., Basic, Advanced).

**Requirements:**
- Use the Bridge pattern to separate the remote control abstraction from the device implementation.
- Support basic operations like `turnOn()`, `turnOff()`, and `setChannel()`.
- Allow adding advanced features like volume control for advanced remotes.

**Hints:**
- Define an abstract `RemoteControl` class and a `Device` interface.
- Implement concrete classes for TV and Radio.
- Use composition to link the remote control with the device.

**Difficulty:** Intermediate

## 3️⃣ Behavioral Patterns

### 1. Strategy: Sorting Algorithm Selector
**Problem:** Implement a Sorting System that allows users to choose between different sorting algorithms (e.g., Bubble Sort, Quick Sort, Merge Sort).

**Requirements:**
- Use the Strategy pattern to encapsulate each sorting algorithm.
- Allow users to select and change the sorting strategy at runtime.
- Provide a method to sort an array of integers.

**Hints:**
- Define a `SortingStrategy` interface with a `sort(int[] array)` method.
- Implement concrete strategies for each algorithm.
- Use a context class to manage the selected strategy.

**Difficulty:** Beginner

### 2. Observer: Weather Station
**Problem:** Build a Weather Station that notifies multiple displays (e.g., Phone, TV) when the weather changes.

**Requirements:**
- Use the Observer pattern to notify displays about weather updates.
- The weather station should support adding, removing, and notifying observers.
- Each display should update its content when notified.

**Hints:**
- Define a `Subject` interface and an `Observer` interface.
- Implement the weather station as the subject and displays as observers.
- Use a list to manage observers in the subject.

**Difficulty:** Intermediate

## Summary

These challenges are designed to help you practice and master Design Patterns in Java. Start with simpler patterns like Singleton and Strategy, and gradually move to more complex patterns like State and Visitor. Each challenge reinforces best practices and real-world application of design patterns.

Happy coding! 🚀


---


### Challenge Part 2 

# Design Patterns Coding Challenges in Java

## **1️⃣ Creational Patterns**

### **Challenge 1: Singleton - Logger System**
**Problem:**  
Implement a thread-safe logging system using the Singleton pattern. The logger should support writing logs to a file and ensure that only one instance of the logger is used throughout the application.

**Hints/Requirements:**  
- Use `synchronized` or `Double-Checked Locking` to make it thread-safe.  
- Ensure logs are written to a file in an append mode.  
- Provide methods like `logInfo()`, `logWarning()`, and `logError()`.

**Difficulty:** ⭐⭐☆☆☆ (Beginner)

---

### **Challenge 2: Factory Method - Payment Processing System**
**Problem:**  
Create a payment processing system that supports multiple payment methods (Credit Card, PayPal, Bitcoin) using the Factory Method pattern.

**Hints/Requirements:**  
- Define an abstract `PaymentProcessor` class.  
- Implement `CreditCardProcessor`, `PayPalProcessor`, and `BitcoinProcessor` subclasses.  
- Create a `PaymentFactory` to instantiate the correct processor dynamically.

**Difficulty:** ⭐⭐⭐☆☆ (Intermediate)

---

### **Challenge 3: Builder - Configurable Computer Builder**
**Problem:**  
Design a `Computer` class that allows customers to build customized computers (CPU, RAM, Storage, GPU) using the Builder pattern.

**Hints/Requirements:**  
- Use method chaining to construct the `Computer` object.  
- Ensure immutability of the `Computer` class.  
- Add a `build()` method that returns a `Computer` instance.

**Difficulty:** ⭐⭐⭐⭐☆ (Advanced)

---

## **2️⃣ Structural Patterns**

### **Challenge 4: Adapter - Legacy System Integration**
**Problem:**  
A new application needs to interact with an old XML-based service, but the application uses JSON. Implement an Adapter pattern to bridge this gap.

**Hints/Requirements:**  
- Define a `JsonService` interface.  
- Implement an `XmlToJsonAdapter` that adapts the old XML system to JSON format.  
- Demonstrate the integration in a main method.

**Difficulty:** ⭐⭐⭐☆☆ (Intermediate)

---

### **Challenge 5: Decorator - Coffee Shop Customization**
**Problem:**  
Implement a coffee ordering system where users can dynamically add ingredients (milk, sugar, caramel, etc.) to their coffee using the Decorator pattern.

**Hints/Requirements:**  
- Create a `Coffee` interface with a `cost()` method.  
- Implement a `BasicCoffee` class.  
- Implement decorators such as `MilkDecorator`, `SugarDecorator`, etc., to modify the coffee’s cost.

**Difficulty:** ⭐⭐⭐⭐☆ (Advanced)

---

### **Challenge 6: Proxy - Secure File Access**
**Problem:**  
Develop a system where access to files is restricted based on user roles using the Proxy pattern.

**Hints/Requirements:**  
- Implement an `ActualFileReader` class.  
- Create a `FileProxy` class that verifies if the user has permission before accessing the file.  
- Demonstrate role-based access control.

**Difficulty:** ⭐⭐⭐☆☆ (Intermediate)

---

## **3️⃣ Behavioral Patterns**

### **Challenge 7: Strategy - Dynamic Sorting Algorithm**
**Problem:**  
Create a system where a list of numbers can be sorted using different strategies (Bubble Sort, Quick Sort, Merge Sort).

**Hints/Requirements:**  
- Define a `SortingStrategy` interface.  
- Implement `BubbleSort`, `QuickSort`, and `MergeSort` classes.  
- Allow users to switch sorting algorithms dynamically at runtime.

**Difficulty:** ⭐⭐⭐☆☆ (Intermediate)

---

### **Challenge 8: Observer - Stock Market Notification System**
**Problem:**  
Implement a stock price tracker where multiple users (observers) are notified when stock prices change.

**Hints/Requirements:**  
- Create a `Stock` class that maintains the stock price.  
- Implement an `Observer` interface.  
- Implement different observers like `MobileApp`, `EmailNotifier`, etc.  

**Difficulty:** ⭐⭐⭐⭐☆ (Advanced)

---

### **Challenge 9: Chain of Responsibility - Customer Support System**
**Problem:**  
Develop a customer support system where requests are handled in a chain (Basic Support → Technical Support → Manager).

**Hints/Requirements:**  
- Implement a `SupportHandler` abstract class.  
- Implement concrete handlers (`BasicSupport`, `TechnicalSupport`, `ManagerSupport`).  
- Ensure requests escalate when necessary.

**Difficulty:** ⭐⭐⭐⭐☆ (Advanced)

---

### **Challenge 10: Command - Smart Home System**
**Problem:**  
Design a smart home automation system where devices (lights, fans, thermostats) can be controlled using commands.

**Hints/Requirements:**  
- Create an `ICommand` interface.  
- Implement concrete commands like `TurnOnLightCommand`, `TurnOffFanCommand`.  
- Implement an `Invoker` that executes commands.

**Difficulty:** ⭐⭐⭐⭐☆ (Advanced)


