package org.anstreth.pcl

/**
  * Generic string parser trait.
  *
  * @tparam T the type of the parsing.
  */
trait Parser[T] {
  /**
    * Method to parse string and into something and return the result
    *
    * @param s String to parse
    * @return (String, T) tuple, in which T is a parse result, and String is remained string
    */
  def parse(s: String): (String, T)
}