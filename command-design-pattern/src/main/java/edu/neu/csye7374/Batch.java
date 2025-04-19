package edu.neu.csye7374;

import java.util.ArrayList;
import java.util.List;

public class Batch {

    private List<Command> commands;

    public Batch() {
        commands = new ArrayList<>();
    }

    public void compute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    public void addCommand(Command command) {
        commands.add(command);
    }

    public static void demo(){
        System.out.println("Executing Batch demo...\n");
        CalculatorAPI calculator = new Calculator();
        Batch batch = new Batch();
        //Adding commands to the batch
        batch.addCommand(new AddCommand(calculator, 6, 3));
        batch.addCommand(new SubCommand(calculator, 6, 3));
        batch.addCommand(new MulCommand(calculator, 6, 3));
        batch.addCommand(new DivCommand(calculator, 6, 3));
        batch.addCommand(new SumCommand(calculator, new double[]{1, 2, 3,4}));
        //Executing the batch
        batch.compute();
    }

}
