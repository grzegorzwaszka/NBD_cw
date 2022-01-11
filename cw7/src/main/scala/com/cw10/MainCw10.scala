package com.cw10

object MainCw10 {

  class Maybe[A] (x:A){
    def Map[B](fun: A=>B):B= {
      x match {
        case None => fun()
        case Some(x) => fun(x)
      }
    }

  }

  def generateDivides(dividersLimit: Int) = {
    for (a <- 1 until dividersLimit;
         b <- 1 until a if a % b == 0)
      yield (a, b)
  }

  def main(args: Array[String]): Unit = {

    println("Zadanie 1")
    var itCol = generateDivides(100).take(20).iterator
    while(itCol.hasNext){
      println(itCol.next())
    }



  }
}
