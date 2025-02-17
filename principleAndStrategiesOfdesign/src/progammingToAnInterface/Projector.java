package progammingToAnInterface;

public class Projector implements DisplayModule {
    @Override
    public void display() {

        System.out.println("Display through Projector");
    }
}