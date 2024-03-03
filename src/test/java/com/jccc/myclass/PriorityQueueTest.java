package com.jccc.myclass;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.PriorityQueue;

import org.junit.jupiter.api.Test;

/**
 * This is not a test class for epic battle. Its a test class for
 * PriorityQueuePractice.
 *
 * @author Stephen Schroer
 *
 */
public class PriorityQueueTest {

  /**
   * This method checks for correct ordering.
   */
  @Test
  public void isQueueOneAlaphbetical() throws IOException, ClassNotFoundException {

    FileInputStream in = new FileInputStream("sortPriorityQueue1.dat");
    ObjectInputStream input = new ObjectInputStream(in);

    @SuppressWarnings("unchecked")
    PriorityQueue<String> queue1 = (PriorityQueue<String>) input.readObject();
    input.close();

    PriorityQueue<String> original = queue1;

    PriorityQueue<String> order = new PriorityQueue<>();
    while (!(queue1.isEmpty())) {
      order.add(queue1.poll());
    }
    assertTrue(order.toString().equals(original));
  }

  /**
   * This method checks for correct ordering.
   */
  @Test
  public void isQueueTwoAlaphbetical() throws IOException, ClassNotFoundException {

    FileInputStream in2 = new FileInputStream("sortPriorityQueue2.dat");
    ObjectInputStream input2 = new ObjectInputStream(in2);

    @SuppressWarnings("unchecked")
    PriorityQueue<String> queue2 = (PriorityQueue<String>) input2.readObject();

    input2.close();

    PriorityQueue<String> original = queue2;

    PriorityQueue<String> order2 = new PriorityQueue<>();
    while (!(queue2.isEmpty())) {
      order2.add(queue2.poll());
    }

    assertTrue(original.equals(order2.toString()));

  }
}
