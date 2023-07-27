package org.example.transaction

import spock.lang.Specification

import java.time.LocalDate

class TransactionNodeSpec extends Specification {

    def transactionNode = new TransactionNode();

    def "should merge transactions"() {

        given: 'transaction nodes'
        def node1 = new TransactionNode(
                transaction: new Transaction(id: '1', date: LocalDate.of(2031, 2, 15)),
                next: new TransactionNode(
                        transaction: new Transaction(id: '6', date: LocalDate.of(2031, 9, 20)
                        )))


        def node2 = new TransactionNode(
                transaction: new Transaction(id: '2', date: LocalDate.of(2031, 3, 10)),
                next: new TransactionNode(
                        transaction: new Transaction(id: '3', date: LocalDate.of(2031, 4, 12)),
                        next: new TransactionNode(
                                transaction: new Transaction(id: '4', date: LocalDate.of(2031, 5, 12)),
                                next: new TransactionNode(
                                        transaction: new Transaction(id: '5', date: LocalDate.of(2031, 6, 12)
                                        )))))

        when: 'merge transaction nodes'
        def result = transactionNode.merge(node1, node2)

        then: 'return correct results'
        result != null
        result.transaction.date == LocalDate.of(2031, 2, 15)
        result.transaction.id == '1'
        getLastNode(result).transaction.date == LocalDate.of(2031, 9, 20)
        getLastNode(result).transaction.id == '6'


        and: 'no more invokes'
        0 * _
    }

    private def getLastNode(TransactionNode node) {

        while (node.next != null) {
            node = node.next;
        }
        return node;
    }
}
