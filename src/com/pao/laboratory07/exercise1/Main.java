package com.pao.laboratory07.exercise1;

import com.pao.laboratory07.exercise1.exceptions.OrderIsAlreadyFinalException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // part A
        // load intial state
        if (!scanner.hasNext()) return;

        OrderState initialState = OrderState.valueOf(scanner.next());
        Order order = new Order(initialState);
        
        System.out.println("Initial order state: " + initialState);

        while (scanner.hasNext()) {
            OrderCommand orderCommand = OrderCommand.valueOf(scanner.next());
            switch (orderCommand) {
                case next -> {
                    try {
                        order.nextState();
                        System.out.println("Order state updated to: " + order.getCurrentState());
                    } catch (OrderIsAlreadyFinalException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case cancel -> {
                    try {
                        order.cancel();
                        System.out.println("Order has been canceled.");
                    } catch (OrderIsAlreadyFinalException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case undo -> {
                    boolean success = order.undoState();
                    if (success) {
                        System.out.println("Order state reverted to: " + order.getCurrentState());
                    } else {
                        System.out.println("Cannot undo the initial order state.");
                    }
                }
                case QUIT -> {
                    System.out.println("User quit the program.");
                    scanner.close();
                    return;
                }
            }
        }
        scanner.close();
    }
}