package edu.neu.csye7374.stockflow.designpattern.command;

interface OrderCommand {
    void execute();
    void undo();
}