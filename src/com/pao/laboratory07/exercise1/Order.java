package com.pao.laboratory07.exercise1;

import com.pao.laboratory07.exercise1.exceptions.OrderIsAlreadyFinalException;
import java.util.Stack;

public class Order {
    private Stack<OrderState> history;

    public Order(OrderState initialState) {
        history = new Stack<>();
        history.push(initialState);
    }

    public OrderState getCurrentState() {
        return history.peek();
    }

    public boolean isFinalState() {
        OrderState current = getCurrentState();
        return current == OrderState.DELIVERED || current == OrderState.CANCELED;
    }

    public void nextState() throws OrderIsAlreadyFinalException {
        if (isFinalState()) {
            throw new OrderIsAlreadyFinalException("Order is already in a final state.");
        }
        
        OrderState current = getCurrentState();
        OrderState next = current;
        
        switch (current) {
            case PLACED -> next = OrderState.PROCESSED;
            case PROCESSED -> next = OrderState.SHIPPED;
            case SHIPPED -> next = OrderState.DELIVERED;
            default -> {} 
        }
        history.push(next);
    }

    public void cancel() throws OrderIsAlreadyFinalException {
        if (isFinalState()) {
            throw new OrderIsAlreadyFinalException("Cannot cancel a final state order.");
        }
        history.push(OrderState.CANCELED);
    }

    public boolean undoState() {
        if (history.size() <= 1) {
            return false;
        }
        history.pop();
        return true;
    }
}