package org.example.transaction;

import lombok.Getter;

@Getter
public class TransactionNode {

    private Transaction transaction;

    private TransactionNode next;

    public TransactionNode() {
    }

    public TransactionNode(Transaction transaction) {
        this.transaction = transaction;
    }

    public TransactionNode(Transaction transaction, TransactionNode next) {
        this.transaction = transaction;
        this.next = next;
    }

    public TransactionNode merge(TransactionNode node1, TransactionNode node2) {
        TransactionNode mergedHead = new TransactionNode();
        TransactionNode current = mergedHead;

        while (node1 != null && node2 != null) {
            if (node1.transaction.getDate().isBefore(node2.transaction.getDate())) {
                current.next = new TransactionNode(node1.transaction);
                node1 = node1.next;
            } else {
                current.next = new TransactionNode(node2.transaction);
                node2 = node2.next;
            }

            current = current.next;
        }

        if (node1 != null) {
            current.next = node1;
        } else if (node2 != null) {
            current.next = node2;
        }

        return mergedHead.next;
    }
}
