// #Sireum
/*
 Copyright (c) 2019, Robby, Kansas State University
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
    list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.sireum


@record trait CircularQueue[E] {
  def size: Z

  def isEmpty: B

  def nonEmpty: B

  def isFull: B

  def enqueue(element: E): Unit

  def dequeue(): E

  def elements: MSZ[E]

  override def string: String = {
    return elements.string
  }
}

object CircularQueue {

  @enum object Policy {
    'NoDrop
    'DropFront
    'DropRear
  }

  @record class NoDrop[E](max: Z,
                          default: E,
                          clean: B,
                          queue: MSZ[E],
                          var front: Z,
                          var rear: Z,
                          var numOfElements: Z) extends CircularQueue[E] {

    def size: Z = {
      return numOfElements
    }

    def isEmpty: B = {
      return numOfElements == 0
    }

    def nonEmpty: B = {
      return !isEmpty
    }

    def isFull: B = {
      return numOfElements == max
    }

    def enqueue(element: E): Unit = {
      assert(!isFull)
      queue(rear) = element
      rear = (rear + 1) % queue.size
      numOfElements = numOfElements + 1
    }

    def dequeue(): E = {
      assert(!isEmpty)
      val r = queue(front)
      if (clean) {
        queue(front) = default
      }
      front = (front + 1) % queue.size
      numOfElements = numOfElements - 1
      return r
    }

    def elements: MSZ[E] = {
      val r = MS.create(numOfElements, default)
      for (i <- 0 until numOfElements) {
        r(i) = queue((front + i) % queue.size)
      }
      return r
    }
  }

  @record class DropFront[E](max: Z,
                             default: E,
                             clean: B,
                             queue: MSZ[E],
                             var front: Z,
                             var rear: Z,
                             var numOfElements: Z) extends CircularQueue[E] {

    def size: Z = {
      return numOfElements
    }

    def isEmpty: B = {
      return numOfElements == 0
    }

    def nonEmpty: B = {
      return !isEmpty
    }

    def isFull: B = {
      return numOfElements == max
    }

    def enqueue(element: E): Unit = {
      if (isFull) {
        dequeue()
      }
      queue(rear) = element
      rear = (rear + 1) % queue.size
      numOfElements = numOfElements + 1
    }

    def dequeue(): E = {
      assert(!isEmpty)
      val r = queue(front)
      if (clean) {
        queue(front) = default
      }
      front = (front + 1) % queue.size
      numOfElements = numOfElements - 1
      return r
    }

    def elements: MSZ[E] = {
      val r = MS.create(numOfElements, default)
      for (i <- 0 until numOfElements) {
        r(i) = queue((front + i) % queue.size)
      }
      return r
    }
  }

  @record class DropRear[E](max: Z,
                            default: E,
                            clean: B,
                            queue: MSZ[E],
                            var front: Z,
                            var rear: Z,
                            var numOfElements: Z) extends CircularQueue[E] {

    def size: Z = {
      return numOfElements
    }

    def isEmpty: B = {
      return numOfElements == 0
    }

    def nonEmpty: B = {
      return !isEmpty
    }

    def isFull: B = {
      return numOfElements == max
    }

    def enqueue(element: E): Unit = {
      if (isFull) {
        queue((rear - 1) % queue.size) = element
      } else {
        queue(rear) = element
        rear = (rear + 1) % queue.size
        numOfElements = numOfElements + 1
      }
    }

    def dequeue(): E = {
      assert(!isEmpty)
      val r = queue(front)
      if (clean) {
        queue(front) = default
      }
      front = (front + 1) % queue.size
      numOfElements = numOfElements - 1
      return r
    }

    def elements: MSZ[E] = {
      val r = MS.create(numOfElements, default)
      for (i <- 0 until numOfElements) {
        r(i) = queue((front + i) % queue.size)
      }
      return r
    }
  }

  def create[E](max: Z, default: E, clean: B, policy: Policy.Type): CircularQueue[E] = {
    policy match {
      case Policy.NoDrop => return NoDrop(max, default, clean, MS.create(max + 1, default), 0, 0, 0)
      case Policy.DropFront => return DropFront(max, default, clean, MS.create(max + 1, default), 0, 0, 0)
      case Policy.DropRear => return DropRear(max, default, clean, MS.create(max + 1, default), 0, 0, 0)
    }
  }

}

