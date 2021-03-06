package uff.ic.lleme.tcc00174.exercicios.datastructures.linkedQueue;

import uff.ic.lleme.tcc00174.exercicios.datastructures.linkedQueue.linkedlist.LinkedList;
import uff.ic.lleme.tcc00174.exercicios.datastructures.linkedQueue.linkedlist.LinkedListException;

public class LikedQueue implements Queue {

  private LinkedList queueList;

  public LikedQueue() {
    queueList = new LinkedList();
  }

  @Override
  public void enqueue(Object object) {
    queueList.insertAtBack(object);
  }

  @Override
  public Object dequeue() throws LinkedListException {
    return queueList.removeFromFront();
  }

  @Override
  public boolean isEmpty() {
    return queueList.isEmpty();
  }

  public void print() {
    queueList.print();
  }

  @Override
  public Object getFront() {
    return queueList.getFront();
  }

  @Override
  public void makeEmpty() {
    queueList.makeEmpty();
  }
}