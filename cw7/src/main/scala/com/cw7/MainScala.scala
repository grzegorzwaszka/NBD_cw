package com.cw7

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object MainScala {
  def main(args: Array[String]): Unit = {
   //Zadanie 1
    print("Zadanie 1a \n")
    val daysOfWeek = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
    var concatDays = ""
    for(day <- daysOfWeek){
      concatDays += day + ","
    }
    print(concatDays + "\n")

    print("Zadanie 1b \n")
    concatDays = ""
    for(day <- daysOfWeek){
      if(day.startsWith("P")){
        concatDays += day + ","
      }
    }
    print(concatDays + "\n")

    print("Zadanie 1c \n")
    concatDays = "" + daysOfWeek.head + ","
    var i = 1
    var daysOfWeekCopy = daysOfWeek
    while(daysOfWeekCopy.length > 1){
      daysOfWeekCopy = daysOfWeekCopy.tail
      concatDays += daysOfWeekCopy.head + ","
    }
    print(concatDays + "\n")

    //Zadanie 2
    print("Zadanie 2a \n")
    print(concatListDays(daysOfWeek, ""))

    print("\n")
    print("Zadanie 2b \n")
    print(concatListDaysReverse(daysOfWeek, "", 0))


    //Zadanie 3
    print("\n")
    print("Zadanie 3 \n")
    print(tailRecDays(daysOfWeek, ""))

    //Zadanie 4
    print("\n")
    print("Zadanie 4a \n")
    print(daysOfWeek.foldLeft("")(_+_+","))
    print("\n")
    print("Zadanie 4b \n")
    print(daysOfWeek.foldRight("")(_+"," +_))
    print("\n")
    print("Zadanie 4c \n")
    print(daysOfWeek.foldLeft(""){(x:String,y:String) =>
      if(y.startsWith("P")) x.concat(y).concat(",") else x
    })
    print("\n")

    //Zadanie 5
    print("Zadanie 5 \n")
    var products = Map("Bułka" -> 1.29, "Ser" -> 4.20, "Ogórek" -> 3.45, "Mleko" -> 3.49, "Masło" -> 6.89, "Chipsy" -> 7.69)
    print("Products before: \n")
    print(products + "\n")
    products = products.map(kv => (kv._1, kv._2*0.9))
    print("Products after: \n")
    print(products + "\n")

    //Zadanie 6
    print("Zadanie 6 \n")
    printTuple(("Test", 1, 2.4))
    print("\n")

    //Zadanie 7
    //Reusing products from previous task
    //Ser is an existing key in Map products
    print("Zadanie 7 \n")
    print("Ser: " + products.get("Ser").getOrElse("Not in a map") + "\n")
    //Szynka is not an existing key and should return None
    print("Szynka: " + products.get("Szynka").getOrElse("Not in a map") + "\n")

    //Zadanie 8
    print("Zadanie 8 \n")
    print(removeZeros(List(1,0,5,6,0,7,8), new ListBuffer[Int]()))

    //Zadanie 9
    print("\n")
    print("Zadanie 9 \n")
    print(increaseby1(List(1,2,3,4,5,6,7,8,9)))

    //Zadanie 10
    print("\n")
    print("Zadanie 10 \n")
    print(filterListandApplyCond(List(-8, -10, -4, -5, -1, 10, 16, 8, 2)))



  }

  def concatListDays(lst: List[String], concatString: String): String ={
    lst.length match {
      case x if x > 0 => concatListDays(lst.tail, concatString + lst.head + ",")
      case 0 => concatString
    }
  }

  def concatListDaysReverse(lst: List[String], concatString: String, state: Int): String ={
    lst.length match {
      case e if state == 0 => concatListDaysReverse(lst.reverse, concatString, state+1)
      case x if x > 0 => concatListDaysReverse(lst.tail, concatString + lst.head + ",", state+1)
      case 0 => concatString
    }
  }

  @tailrec
  def tailRecDays(list: List[String], concatString: String): String = list match {
    case Nil => concatString
    case head :: tail => tailRecDays(tail, concatString + head + ",")
  }

  def printTuple (t:(String, Int, Double)){
    print("First : " + t._1 + " second: " + t._2 + " third: " + t._3)
  }

  def removeZeros(lst:List[Int], newlst: ListBuffer[Int]): List[Int] ={
    //print("Current head is: "+ lst.head)
    if(lst.head != 0){
      newlst += lst.head
    }
    lst.length match {
      case x if x > 1 => removeZeros(lst.tail, newlst)
      case 1 => newlst.toList
    }

  }

  def increaseby1 (lst:List[Int]):List[Int] ={
    lst.map(k=> k+1)
  }

  def filterListandApplyCond(lst:List[Double]): List[Double]={
    lst.filter(_>=(-5)).filter(_<=12).map(k => k.abs)
  }

}
