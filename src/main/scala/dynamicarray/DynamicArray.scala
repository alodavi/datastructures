package dynamicarray

import scala.reflect.ClassTag

class DynamicArray[T] extends Iterable[T]{

  private var arr: Array[T] = null
  private var len = 0 // length user thinks array is
  private var capacity = 0 // Actual array size

  override def size: Int = len

  override def isEmpty: Boolean = size == 0

  def get(index: Int): T = arr(index)

  def set(index: Int, elem: T): Unit = {
    arr(index) = elem
  }

  def clear(): Unit = {
    var i = 0
    while (i < len) {
      arr.drop(i)
      i += 1
    }
    len = 0
  }

  def add(elem: T)(implicit m: ClassTag[T]): Unit = { // Time to resize!
    if (len + 1 >= capacity) {
      if (capacity == 0) capacity = 1
      else capacity *= 2 // double the size
      val new_arr = new Array[T](capacity)
      var i = 0
      while (i < len) {
        new_arr(i) = arr(i)
        i += 1
      }
      arr = new_arr // arr has extra nulls padded

    }
    arr({
      len += 1; len - 1
    }) = elem
  }

  // Removes an element at the specified index in this array.
  def removeAt(rm_index: Int)(implicit m: ClassTag[T]): T = {
    if (rm_index >= len || rm_index < 0) throw new IndexOutOfBoundsException
    val data = arr(rm_index)
    val new_arr = new Array[T](len - 1)
    var i = 0
    var j = 0
    while ( i < len) {
      if (i == rm_index) j -= 1 // Skip over rm_index by fixing j temporarily
      else new_arr(j) = arr(i)
      i += 1
      j += 1
    }
    arr = new_arr
    capacity = {
      len -= 1; len
    }
    data
  }

  def indexOf(obj: T): Int = {
    var i = 0
    while (i < len) {
      if (obj == arr(i)) return i
      i += 1
    }
    -1
  }

  def remove(obj: T)(implicit m: ClassTag[T]): Boolean = {
    val index = indexOf(obj)
    if (index == -1) return false
    removeAt(index)
    true
  }

  def contains(obj: T): Boolean = indexOf(obj) != -1


  // Iterator is still fast but not as fast as iterative for loop
  def iterator: Iterator[T] = new Iterator[T]() {
    var index = 0

    def hasNext: Boolean = index < len

    def next: T = arr({
      index += 1; index - 1
    })

    def remove(): Unit = {
      throw new UnsupportedOperationException
    }
  }

  override def toString: String = if (len == 0) "[]"
  else {
    val sb = new StringBuilder(len).append("[")
    var i = 0
    while (i < len - 1) {
      sb.append(arr(i) + ", ")
      i += 1
    }
    sb.append(arr(len - 1) + "]").toString
  }

}
