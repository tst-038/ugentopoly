package be.ugent.objprog.ugentopoly.model;

public enum TransactionPriority {
    HIGH,   // For transactions that must be completed, such as paying rent
    LOW     // For optional transactions, such as buying properties
}