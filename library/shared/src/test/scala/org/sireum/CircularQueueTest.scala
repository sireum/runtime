/*
 * Copyright (c) 2017-2024, Robby, Kansas State University
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sireum

import org.sireum.test._

class CircularQueueTest extends TestSuite {

  val tests = Tests {

    "NoDrop" - {

      def create: CircularQueue[Z] = CircularQueue.create(3, z"0", T, CircularQueue.Policy.NoDrop)

      * - {
        val cq3 = create

        val x1: Z = Z.random
        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        val r1: Z = cq3.dequeue()
        assert(r1 == x1)
      }


      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x1, x2))

        val r1: Z = cq3.dequeue()
        assert(r1 == x1 && cq3.elements == MSZ(x2))

        val r2: Z = cq3.dequeue()
        assert(r2 == x2 && cq3.elements == MSZ())
      }


      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random
        val x3: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x1, x2))

        cq3.enqueue(x3)
        assert(cq3.elements == MSZ(x1, x2, x3))

        val r1: Z = cq3.dequeue()
        assert(r1 == x1 && cq3.elements == MSZ(x2, x3))

        val r2: Z = cq3.dequeue()
        assert(r2 == x2 && cq3.elements == MSZ(x3))

        val r3: Z = cq3.dequeue()
        assert(r3 == x3 && cq3.elements == MSZ())
      }

    }

    "DropFront" - {

      def create: CircularQueue[Z] = CircularQueue.create(3, z"0", T, CircularQueue.Policy.DropFront)

      * - {
        val cq3 = create

        val x1: Z = Z.random
        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        val r1: Z = cq3.dequeue()
        assert(r1 == x1)
      }


      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x1, x2))

        val r1: Z = cq3.dequeue()
        assert(r1 == x1 && cq3.elements == MSZ(x2))

        val r2: Z = cq3.dequeue()
        assert(r2 == x2 && cq3.elements == MSZ())
      }


      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random
        val x3: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x1, x2))

        cq3.enqueue(x3)
        assert(cq3.elements == MSZ(x1, x2, x3))

        val r1: Z = cq3.dequeue()
        assert(r1 == x1 && cq3.elements == MSZ(x2, x3))

        val r2: Z = cq3.dequeue()
        assert(r2 == x2 && cq3.elements == MSZ(x3))

        val r3: Z = cq3.dequeue()
        assert(r3 == x3 && cq3.elements == MSZ())
      }


      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random
        val x3: Z = Z.random
        val x4: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x1, x2))

        cq3.enqueue(x3)
        assert(cq3.elements == MSZ(x1, x2, x3))

        cq3.enqueue(x4)
        assert(cq3.elements == MSZ(x2, x3, x4))

        val r1: Z = cq3.dequeue()
        assert(r1 == x2 && cq3.elements == MSZ(x3, x4))

        val r2: Z = cq3.dequeue()
        assert(r2 == x3 && cq3.elements == MSZ(x4))

        val r3: Z = cq3.dequeue()
        assert(r3 == x4 && cq3.elements == MSZ())
      }


      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random
        val x3: Z = Z.random
        val x4: Z = Z.random
        val x5: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x1, x2))

        cq3.enqueue(x3)
        assert(cq3.elements == MSZ(x1, x2, x3))

        cq3.enqueue(x4)
        assert(cq3.elements == MSZ(x2, x3, x4))

        cq3.enqueue(x5)
        assert(cq3.elements == MSZ(x3, x4, x5))

        val r1: Z = cq3.dequeue()
        assert(r1 == x3 && cq3.elements == MSZ(x4, x5))

        val r2: Z = cq3.dequeue()
        assert(r2 == x4 && cq3.elements == MSZ(x5))

        val r3: Z = cq3.dequeue()
        assert(r3 == x5 && cq3.elements == MSZ())
      }


      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random
        val x3: Z = Z.random
        val x4: Z = Z.random
        val x5: Z = Z.random
        val x6: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x1, x2))

        cq3.enqueue(x3)
        assert(cq3.elements == MSZ(x1, x2, x3))

        cq3.enqueue(x4)
        assert(cq3.elements == MSZ(x2, x3, x4))

        cq3.enqueue(x5)
        assert(cq3.elements == MSZ(x3, x4, x5))

        cq3.enqueue(x6)
        assert(cq3.elements == MSZ(x4, x5, x6))

        val r1: Z = cq3.dequeue()
        assert(r1 == x4 && cq3.elements == MSZ(x5, x6))

        val r2: Z = cq3.dequeue()
        assert(r2 == x5 && cq3.elements == MSZ(x6))

        val r3: Z = cq3.dequeue()
        assert(r3 == x6 && cq3.elements == MSZ())
      }

    }

    "DropRear" - {

      def create: CircularQueue[Z] = CircularQueue.create(3, z"0", T, CircularQueue.Policy.DropRear)

      * - {
        val cq3 = create

        val x1: Z = Z.random
        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        val r1: Z = cq3.dequeue()
        assert(r1 == x1)
      }

      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x1, x2))

        val r1: Z = cq3.dequeue()
        assert(r1 == x1 && cq3.elements == MSZ(x2))

        val r2: Z = cq3.dequeue()
        assert(r2 == x2 && cq3.elements == MSZ())
      }


      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random
        val x3: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x1, x2))

        cq3.enqueue(x3)
        assert(cq3.elements == MSZ(x1, x2, x3))

        val r1: Z = cq3.dequeue()
        assert(r1 == x1 && cq3.elements == MSZ(x2, x3))

        val r2: Z = cq3.dequeue()
        assert(r2 == x2 && cq3.elements == MSZ(x3))

        val r3: Z = cq3.dequeue()
        assert(r3 == x3 && cq3.elements == MSZ())
      }


      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random
        val x3: Z = Z.random
        val x4: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x1, x2))

        cq3.enqueue(x3)
        assert(cq3.elements == MSZ(x1, x2, x3))

        cq3.enqueue(x4)
        assert(cq3.elements == MSZ(x1, x2, x4))

        val r1: Z = cq3.dequeue()
        assert(r1 == x1 && cq3.elements == MSZ(x2, x4))

        val r2: Z = cq3.dequeue()
        assert(r2 == x2 && cq3.elements == MSZ(x4))

        val r3: Z = cq3.dequeue()
        assert(r3 == x4 && cq3.elements == MSZ())
      }


      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random
        val x3: Z = Z.random
        val x4: Z = Z.random
        val x5: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x1, x2))

        cq3.enqueue(x3)
        assert(cq3.elements == MSZ(x1, x2, x3))

        cq3.enqueue(x4)
        assert(cq3.elements == MSZ(x1, x2, x4))

        cq3.enqueue(x5)
        assert(cq3.elements == MSZ(x1, x2, x5))

        val r1: Z = cq3.dequeue()
        assert(r1 == x1 && cq3.elements == MSZ(x2, x5))

        val r2: Z = cq3.dequeue()
        assert(r2 == x2 && cq3.elements == MSZ(x5))

        val r3: Z = cq3.dequeue()
        assert(r3 == x5 && cq3.elements == MSZ())
      }


      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random
        val x3: Z = Z.random
        val x4: Z = Z.random
        val x5: Z = Z.random
        val x6: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x1, x2))

        cq3.enqueue(x3)
        assert(cq3.elements == MSZ(x1, x2, x3))

        cq3.enqueue(x4)
        assert(cq3.elements == MSZ(x1, x2, x4))

        cq3.enqueue(x5)
        assert(cq3.elements == MSZ(x1, x2, x5))

        cq3.enqueue(x6)
        assert(cq3.elements == MSZ(x1, x2, x6))

        val r1: Z = cq3.dequeue()
        assert(r1 == x1 && cq3.elements == MSZ(x2, x6))

        val r2: Z = cq3.dequeue()
        assert(r2 == x2 && cq3.elements == MSZ(x6))

        val r3: Z = cq3.dequeue()
        assert(r3 == x6 && cq3.elements == MSZ())
      }

      * - {
        val cq3 = create

        val x1: Z = Z.random
        val x2: Z = Z.random
        val x3: Z = Z.random
        val x4: Z = Z.random
        val x5: Z = Z.random
        val x6: Z = Z.random

        cq3.enqueue(x1)
        assert(cq3.elements == MSZ(x1))

        val r1 = cq3.dequeue()
        assert(cq3.elements == MSZ() && r1 == x1)

        cq3.enqueue(x2)
        assert(cq3.elements == MSZ(x2))

        cq3.enqueue(x3)
        assert(cq3.elements == MSZ(x2, x3))

        cq3.enqueue(x4)
        assert(cq3.elements == MSZ(x2, x3, x4))

        cq3.enqueue(x5)
        assert(cq3.elements == MSZ(x2, x3, x5))

        cq3.enqueue(x6)
        assert(cq3.elements == MSZ(x2, x3, x6))

        val r2 = cq3.dequeue()
        assert(cq3.elements == MSZ(x3, x6) && r2 == x2)

        val r3 = cq3.dequeue()
        assert(cq3.elements == MSZ(x6) && r3 == x3)

        val r4 = cq3.dequeue()
        assert(cq3.elements == MSZ() && r4 == x6)
      }

    }

  }

}
