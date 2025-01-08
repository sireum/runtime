// #Sireum
/*
 Copyright (c) 2017-2025, Robby, Kansas State University
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

import ContractUtil._

@record trait CircularQueue[E] {

  @spec var rep: MSZ[E] = $

  @spec def repInv = Invariant(rep.size <= max)

  @strictpure def max: Z

  @strictpure def default: E

  @strictpure def scrub: B

  @strictpure def policy: CircularQueue.Policy.Type

  def size: Z = Contract.Only(Ensures(Res == rep.size))

  def isEmpty: B = Contract.Only(Ensures(Res == (rep.size == 0)))

  def nonEmpty: B = Contract.Only(Ensures(Res == (rep.size != 0)))

  def isFull: B = Contract.Only(Ensures(Res == (rep.size == max)))

  def enqueue(element: E): Unit = Contract.Only(
    Modifies(rep),
    Case("Non-full",
      Requires(rep.size < max),
      Ensures(rep == In(rep) :+ element)
    ),
    Case("Drop front policy and full",
      Requires(
        policy == CircularQueue.Policy.DropFront,
        rep.size == max
      ),
      Ensures(
        rep.size == In(rep).size,
        All(1 until rep.size)(i => rep(i - 1) == In(rep)(i)),
        rep(rep.size - 1) == element
      )
    ),
    Case("Drop rear policy and full",
      Requires(
        policy == CircularQueue.Policy.DropRear,
        rep.size == max
      ),
      Ensures(
        rep.size == In(rep).size,
        msEqualExcept(rep, In(rep), rep.size - 1),
        rep(rep.size - 1) == element
      )
    )
  )

  def dequeue(): E = Contract.Only(
    Requires(rep.size > 0),
    Modifies(rep),
    Ensures(
      rep.size == In(rep).size - 1,
      All(1 until In(rep).size)(i => rep(i - 1) == In(rep)(i)),
      Res == In(rep)(0)
    )
  )

  def elements: MSZ[E] = Contract.Only(Ensures(Res == rep))

  override def string: String = {
    return elements.string
  }
}

object CircularQueue {

  @enum object Policy {
    "NoDrop"
    "DropFront"
    "DropRear"
  }

  @strictpure def inv[@mut E](max: Z, default: E, scrub: B, queue: MSZ[E], front: Z, rear: Z, numOfElements: Z): B =
    max > 0 &
      max + 1 == queue.size &
      queue.isInBound(front) &
      queue.isInBound(rear) &
      0 <= numOfElements &
      numOfElements <= max &
      (rear >= front) == (numOfElements == rear - front) &
      (rear < front) == (numOfElements == rear + queue.size - front) &
      (scrub ->: All(0 until queue.size - numOfElements)(i => queue(modPos(rear + i, queue.size)) == default))

  @strictpure def refinement[@mut E](rep: MSZ[E], queue: MSZ[E], numOfElements: Z, front: Z): B =
    rep.size == numOfElements &
      All(rep.indices)(i => rep(i) == queue(modPos(front + i, queue.size)))

  @strictpure def createEnsures[@mut E](res: CircularQueue[E], max: Z, default: E, scrub: B, policy: Policy.Type): B =
    res.max == max &
      res.default == default &
      res.scrub == scrub &
      res.policy == policy

  object NoDrop {

    @pure def create[@mut E](max: Z, default: E, scrub: B): NoDrop[E] = {
      Contract(
        Ensures(
          createEnsures(Res, max, default, scrub, Policy.NoDrop) &
            additionalCreateEnsures(Res) &
            Res[NoDrop[E]].rep == MSZ[E]()
        )
      )

      return NoDrop(max, default, scrub, MS.create(max + 1, default), 0, 0, 0)
    }

    @strictpure def additionalCreateEnsures[@mut E](res: NoDrop[E]): B =
      res.front == 0 &
        res.rear == 0 &
        res.numOfElements == 0 &
        isAllMS(res.queue, res.default)
  }

  @record class NoDrop[E](val max: Z,
                          val default: E,
                          val scrub: B,
                          val queue: MSZ[E],
                          var front: Z,
                          var rear: Z,
                          var numOfElements: Z) extends CircularQueue[E] {

    @spec def invariant = Invariant(
      inv(max, default, scrub, queue, front, rear, numOfElements)
    )

    Contract(
      DataRefinement(rep)(queue, front, rear, numOfElements)(
        refinement(rep, queue, numOfElements, front)
      )
    )

    @strictpure def policy: Policy.Type = Policy.NoDrop

    override def size: Z = {
      Contract(Ensures(Res == numOfElements))

      return numOfElements
    }

    override def isEmpty: B = {
      Contract(Ensures(Res == (numOfElements == 0)))

      return numOfElements == 0
    }

    override def nonEmpty: B = {
      Contract(Ensures(Res == (numOfElements != 0)))

      return !isEmpty
    }

    override def isFull: B = {
      Contract(Ensures(Res == (numOfElements == max)))

      return numOfElements == max
    }

    override def enqueue(element: E): Unit = {
      Contract(
        Requires(numOfElements != max),
        Modifies(queue, rear, front, numOfElements),
        Ensures(
          queue(In(rear)) == element,
          rear == modPos(In(rear) + 1, queue.size),
          numOfElements == In(numOfElements) + 1,
          msEqualExcept(queue, In(queue), In(rear))
        )
      )

      queue(rear) = element
      rear = modPos(rear + 1, queue.size)
      numOfElements = numOfElements + 1
    }

    override def dequeue(): E = {
      Contract(
        Requires(numOfElements != 0),
        Modifies(queue, front, numOfElements),
        Ensures(
          numOfElements == In(numOfElements) - 1,
          front == modPos(In(front) + 1, queue.size),
          Res == queue(In(front))
        )
      )

      val r = queue(front)
      if (scrub) {
        queue(front) = default
      }
      front = modPos(front + 1, queue.size)
      numOfElements = numOfElements - 1
      return r
    }

    override def elements: MSZ[E] = {
      Contract(Ensures(refinement(Res, queue, numOfElements, front)))

      val r = MSZ.create(numOfElements, default)
      for (i <- 0 until numOfElements) {
        r(i) = queue(modPos(front + i, queue.size))
      }
      return r
    }
  }

  object DropFront {

    @pure def create[E](max: Z, default: E, scrub: B): DropFront[E] = {
      Contract(
        Ensures(
          createEnsures(Res, max, default, scrub, Policy.NoDrop) &
            additionalCreateEnsures(Res) &
            Res[DropFront[E]].rep == MSZ[E]()
        )
      )

      return DropFront(max, default, scrub, MS.create(max + 1, default), 0, 0, 0)
    }

    @strictpure def additionalCreateEnsures[E](res: DropFront[E]): B =
      res.front == 0 &
        res.rear == 0 &
        res.numOfElements == 0 &
        isAllMS(res.queue, res.default)
  }

  @record class DropFront[E](val max: Z,
                             val default: E,
                             val scrub: B,
                             val queue: MSZ[E],
                             var front: Z,
                             var rear: Z,
                             var numOfElements: Z) extends CircularQueue[E] {

    @spec def invariant = Invariant(
      inv(max, default, scrub, queue, front, rear, numOfElements)
    )

    Contract(
      DataRefinement(rep)(queue, front, rear, numOfElements)(
        refinement(rep, queue, numOfElements, front)
      )
    )

    @strictpure def policy: Policy.Type = Policy.DropFront

    override def size: Z = {
      Contract(Ensures(Res == numOfElements))

      return numOfElements
    }

    override def isEmpty: B = {
      Contract(Ensures(Res == (numOfElements == 0)))

      return numOfElements == 0
    }

    override def nonEmpty: B = {
      Contract(Ensures(Res == (numOfElements != 0)))

      return !isEmpty
    }

    override def isFull: B = {
      Contract(Ensures(Res == (numOfElements == max)))

      return numOfElements == max
    }

    override def enqueue(element: E): Unit = {
      Contract(
        Modifies(queue, rear, front, numOfElements),
        Ensures(
          queue(In(rear)) == element,
          rear == modPos(In(rear) + 1, queue.size),
          (In(numOfElements) < max) == (numOfElements == In(numOfElements) + 1),
          (In(numOfElements) == max) == (numOfElements == In(numOfElements)),
          msEqualExcept(queue, In(queue), In(rear))
        )
      )

      if (isFull) {
        dequeue()
      }
      queue(rear) = element
      rear = modPos(rear + 1, queue.size)
      numOfElements = numOfElements + 1
    }

    override def dequeue(): E = {
      Contract(
        Requires(numOfElements != 0),
        Modifies(queue, front, numOfElements),
        Ensures(
          numOfElements == In(numOfElements) - 1,
          front == modPos(In(front) + 1, queue.size),
          Res == queue(In(front))
        )
      )

      val r = queue(front)
      if (scrub) {
        queue(front) = default
      }
      front = modPos(front + 1, queue.size)
      numOfElements = numOfElements - 1
      return r
    }

    override def elements: MSZ[E] = {
      Contract(Ensures(refinement(Res, queue, numOfElements, front)))

      val r = MSZ.create(numOfElements, default)
      for (i <- 0 until numOfElements) {
        r(i) = queue(modPos(front + i, queue.size))
      }
      return r
    }
  }

  object DropRear {

    @pure def create[E](max: Z, default: E, scrub: B): DropRear[E] = {
      Contract(
        Ensures(
          createEnsures(Res, max, default, scrub, Policy.NoDrop) &
            additionalCreateEnsures(Res) &
            Res[DropRear[E]].rep == MSZ[E]()
        )
      )
      return DropRear(max, default, scrub, MS.create(max + 1, default), 0, 0, 0)
    }

    @strictpure def additionalCreateEnsures[E](res: DropRear[E]): B =
      res.front == 0 &
        res.rear == 0 &
        res.numOfElements == 0 &
        isAllMS(res.queue, res.default)
  }

  @record class DropRear[E](val max: Z,
                            val default: E,
                            val scrub: B,
                            val queue: MSZ[E],
                            var front: Z,
                            var rear: Z,
                            var numOfElements: Z) extends CircularQueue[E] {

    @spec def invariant = Invariant(
      inv(max, default, scrub, queue, front, rear, numOfElements)
    )

    Contract(
      DataRefinement(rep)(queue, front, rear, numOfElements)(
        refinement(rep, queue, numOfElements, front)
      )
    )

    @strictpure def policy: Policy.Type = Policy.DropRear

    override def size: Z = {
      Contract(Ensures(Res == numOfElements))

      return numOfElements
    }

    override def isEmpty: B = {
      Contract(Ensures(Res == (numOfElements == 0)))

      return numOfElements == 0
    }

    override def nonEmpty: B = {
      Contract(Ensures(Res == (numOfElements != 0)))

      return !isEmpty
    }

    override def isFull: B = {
      Contract(Ensures(Res == (numOfElements == max)))

      return numOfElements == max
    }

    override def enqueue(element: E): Unit = {
      Contract(
        Modifies(queue, rear, front, numOfElements),
        Case("Non-full and rear is not the last index",
          Requires(
            In(numOfElements) < max,
            rear < max
          ),
          Ensures(
            numOfElements == In(numOfElements) + 1,
            rear == In(rear) + 1,
            queue(In(rear)) == element,
            msEqualExcept(queue, In(queue), In(rear))
          )
        ),
        Case("Non-full and rear is the last index",
          Requires(
            In(numOfElements) < max,
            rear == max
          ),
          Ensures(
            numOfElements == In(numOfElements) + 1,
            rear == 0,
            queue(max) == element,
            msEqualExcept(queue, In(queue), max)
          )
        ),
        Case("Full and rear is the first index",
          Requires(
            numOfElements == max,
            rear == 0
          ),
          Ensures(
            numOfElements == In(numOfElements),
            rear == In(rear),
            queue(max) == element,
            msEqualExcept(queue, In(queue), max)
          )
        ),
        Case("Full and rear is not the first index",
          Requires(
            In(numOfElements) == max,
            rear > 0
          ),
          Ensures(
            numOfElements == In(numOfElements),
            rear == In(rear),
            queue(rear - 1) == element,
            msEqualExcept(queue, In(queue), rear - 1)
          )
        )
      )

      if (isFull) {
        queue(modNeg(rear - 1, queue.size)) = element
      } else {
        queue(rear) = element
        rear = modPos(rear + 1, queue.size)
        numOfElements = numOfElements + 1
      }
    }

    override def dequeue(): E = {
      Contract(
        Requires(numOfElements != 0),
        Modifies(queue, front, numOfElements),
        Ensures(
          numOfElements == In(numOfElements) - 1,
          front == modPos(In(front) + 1, queue.size),
          Res == queue(In(front))
        )
      )

      val r = queue(front)
      if (scrub) {
        queue(front) = default
      }
      front = modPos(front + 1, queue.size)
      numOfElements = numOfElements - 1
      return r
    }

    override def elements: MSZ[E] = {
      Contract(Ensures(refinement(Res, queue, numOfElements, front)))

      val r = MSZ.create(numOfElements, default)
      for (i <- 0 until numOfElements) {
        r(i) = queue(modPos(front + i, queue.size))
      }
      return r
    }
  }

  @pure def create[E](max: Z, default: E, scrub: B, policy: Policy.Type): CircularQueue[E] = {
    Contract(Ensures(createEnsures(Res, max, default, scrub, policy)))

    policy match {
      case Policy.NoDrop => return NoDrop.create(max, default, scrub)
      case Policy.DropFront => return DropFront.create(max, default, scrub)
      case Policy.DropRear => return DropRear.create(max, default, scrub)
    }
  }

}
