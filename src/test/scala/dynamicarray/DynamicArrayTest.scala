package dynamicarray

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class DynamicArrayTest {

  @Test def testEmptyList(): Unit = {
    val list = new DynamicArray[Integer]
    assertTrue(list.isEmpty)
  }

  @Test(expected = classOf[Exception]) def testRemovingEmpty(): Unit = {
    val list = new DynamicArray[Integer]
    list.removeAt(0)
  }

  import dynamicarray.DynamicArray
  import org.junit.Test

  @Test(expected = classOf[Exception]) def testIndexOutOfBounds(): Unit = {
    val list = new DynamicArray[Integer]
    list.add(-56)
    list.add(-53)
    list.add(-55)
    list.removeAt(3)
  }


  @Test(expected = classOf[Exception]) def testIndexOutOfBounds2(): Unit = {
    val list = new DynamicArray[Integer]
    for (i <- 0 until 1000) {
      list.add(789)
    }
    list.removeAt(1000)
  }

  import dynamicarray.DynamicArray
  import org.junit.Test

  @Test def testRemovingAt(): Unit = {
    val list = new DynamicArray[String]
    val strs = Array("a", "b", "c")
    for (s <- strs) {
      list.add(s)
    }

    list.removeAt(0)

    assertEquals(2, list.size)

  }

  @Test def testRemoving(): Unit = {
    val list = new DynamicArray[String]
    val strs = Array("a", "b", "c", "d", "e", null, "g", "h")
    for (s <- strs) {
      list.add(s)
    }
    var ret = list.remove("c")
    assertEquals(7, list.size)

    assertTrue(ret)
    ret = list.remove("c")
    assertFalse(ret)
    ret = list.remove("h")
    assertTrue(ret)
    ret = list.remove(null)
    assertTrue(ret)
    ret = list.remove("a")
    assertTrue(ret)
    ret = list.remove("a")
    assertFalse(ret)
    ret = list.remove("h")
    assertFalse(ret)
    ret = list.remove(null)
    assertFalse(ret)

  }

  import dynamicarray.DynamicArray
  import org.junit.Test

  @Test def testRemoving2(): Unit = {
    val list = new DynamicArray[String]
    val strs = Array("a", "b", "c", "d")
    for (s <- strs) {
      list.add(s)
    }
    assertTrue(list.remove("a"))
    assertTrue(list.remove("b"))
    assertTrue(list.remove("c"))
    assertTrue(list.remove("d"))
    assertFalse(list.remove("a"))
    assertFalse(list.remove("b"))
    assertFalse(list.remove("c"))
    assertFalse(list.remove("d"))
  }

  @Test def testIndexOfNullElement(): Unit = {
    val list = new DynamicArray[String]
    val strs = Array("a", "b", null, "d")
    for (s <- strs) {
      list.add(s)
    }
    assertTrue(list.indexOf(null) == 2)
  }

  import dynamicarray.DynamicArray
  import org.junit.Test

  @Test def testAddingElements(): Unit = {
    val list = new DynamicArray[Integer]
    val elems = Array(1, 2, 3, 4, 5, 6, 7)
    for (i <- 0 until elems.length) {
      list.add(elems(i))
    }
    for (i <- 0 until elems.length) {
      assertEquals(list.get(i).intValue, elems(i))
    }
  }

  @Test def testAddAndRemove(): Unit = {
    val list = new DynamicArray[Long]()
    for (i <- 0 until 55) {
      list.add(44)
    }
    for (i <- 0 until 55) {
      list.remove(44)
    }
    assertTrue(list.isEmpty)
    for (i <- 0 until 55) {
      list.add(44)
    }
    for (i <- 0 until 55) {
      list.removeAt(0)
    }
    assertTrue(list.isEmpty)
    for (i <- 0 until 155) {
      list.add(44)
    }
    for (i <- 0 until 155) {
      list.remove(44)
    }
    assertTrue(list.isEmpty)
    for (i <- 0 until 155) {
      list.add(44)
    }
    for (i <- 0 until 155) {
      list.removeAt(0)
    }
    assertTrue(list.isEmpty)
  }

  @Test def testAddSetRemove(): Unit = {
    val list = new DynamicArray[Long]()
    for (i <- 0 until 55) {
      list.add(44)
    }
    for (i <- 0 until 55) {
      list.set(i, 33)
    }
    for (i <- 0 until 55) {
      list.remove(33L)
    }
    assertTrue(list.isEmpty)
    for (i <- 0 until 55) {
      list.add(44)
    }
    for (i <- 0 until 55) {
      list.set(i, 33)
    }
    for (i <- 0 until 55) {
      list.removeAt(0)
    }
    assertTrue(list.isEmpty)
    for (i <- 0 until 155) {
      list.add(44L)
    }
    for (i <- 0 until 155) {
      list.set(i, 33)
    }
    for (i <- 0 until 155) {
      list.remove(33)
    }
    assertTrue(list.isEmpty)
    for (i <- 0 until 155) {
      list.add(44L)
    }
    for (i <- 0 until 155) {
      list.removeAt(0)
    }
    assertTrue(list.isEmpty)
  }

  @Test def testSize(): Unit = {
    val list = new DynamicArray[Integer]
    val elems = Array[Integer](-76, 45, 66, 3, null, 54, 33)
    var i = 0
    var sz = 1
    while ( {
      i < elems.length
    }) {
      list.add(elems(i))
      assertEquals(list.size, sz)

      i += 1
      sz += 1
    }
  }
}
