package com.cw8

import sun.awt.image.OffScreenImageSource

object Main {

  trait KontoBankoweT { def stanKonta: Double }

  class KontoBankowe (var stanKonta:Double) extends KontoBankoweT{

    def this(){
      this(0)
    }

    def wplata(ilosc:Double): Boolean ={
      stanKonta += ilosc
      true
    }

    def wyplata(ilosc:Double):Boolean ={
      if(stanKonta-ilosc>=0){
        stanKonta -= ilosc
        true
      }else {
        false

      }

    }

  }

  class Osoba (val imie:String, val nazwisko:String, val podatek : Double){


  }

  trait Pracownik  extends Osoba {
    def pensja : Double
    override val podatek : Double = 20
  }

  trait Student extends Osoba {
    override val podatek : Double = 0
  }

  trait Nauczyciel extends Pracownik {
    override val podatek : Double = this.pensja * 0.10
  }




  def main(args: Array[String]): Unit = {

    print("Zadanie 1 \n")
    print(checkIfWeekend("Wtorek") + "\n")
    print(checkIfWeekend("Sobota") + "\n")
    print(checkIfWeekend("Niedzielnik") + "\n")

    print("Zadanie 2 \n")
    var kontoA = new KontoBankowe(20.00)
    var kontoB = new KontoBankowe()
    print(kontoA.stanKonta + "\n")
    print(kontoB.stanKonta + "\n")

    print("Zadanie 3 \n")
    var osobaA = new Osoba("Bob", "Budowniczy", 10)
    var osobaB = new Osoba("Ania", "Annowska", 20)
    var osobaC = new Osoba("Krzysztof", "Nowak", 23)
    var osobaD = new Osoba("Beata", "Kowalska", 37)
    print(greetPeople(osobaA) + "\n")
    print(greetPeople(osobaB) + "\n")
    print(greetPeople(osobaC) + "\n")
    print(greetPeople(osobaD) + "\n")

    print("Zadanie 4 \n")
    def sumwith5(y:Int) = y+5
    def multiplyBy9(y:Int) = y*9
    print(performCaluclation(4,sumwith5) + "\n")
    print(performCaluclation(5,multiplyBy9) + "\n")

    print("Zadanie 5 \n")
    var nauczycielA = new Osoba("Andrzej", "Andrzejowski", 23) with Nauczyciel {
      override def pensja: Double = 3000
    }
    print("Nauczyciel A pensja: " + nauczycielA.pensja + " podatek: " + nauczycielA.podatek + "\n")
    var pracownikA = new Osoba("Jan", "Janowski", 25) with Pracownik {
      override def pensja: Double = 6080
    }
    print("Pracownik A pensja: " + pracownikA.pensja + " podatek: " + pracownikA.podatek + "\n")
    var studentA  = new Osoba("Zbigniew", "Zbigniew", 60) with Student
    print("Student A podatek: " + studentA.podatek + "\n")
    var randomPerson = new Osoba("Zosia", "Zosia", 67) with Student with Pracownik {
      override def pensja: Double = 6900
    }
    print("Osoba pensja: " + randomPerson.pensja + " podatek: " + randomPerson.podatek + "\n")
    var randomPerson2 = new Osoba("Zosia", "Zosia", 67) with Pracownik with Student {
      override def pensja: Double = 8879
    }
    print("Osoba2 pensja: " + randomPerson2.pensja + " podatek: " + randomPerson2.podatek + "\n")

  }

  def checkIfWeekend (day:String) : String = {
    day match {
      case ("Poniedziałek" | "Wtorek" | "Środa" | "Czwartek" | "Piątek") => "Praca"
      case ("Sobota" | "Niedziela") => "Weekend"
      case _ => "Nie ma takiego dnia"
    }
  }



  def greetPeople (osoba: Osoba) :String = {
    osoba match {
      case x if x.nazwisko == "Budowniczy" => "Dzień dobry Panie Budowniczy"
      case x if x.imie == "Ania" => "Witam Pani Anno!"
      case x if x.imie.endsWith("a") => "Dzień dobry Pani"
      case _ => "Ahoj!"
    }
  }

  def performCaluclation (x: Int, call: Int => Int): Int = {
    call(call(call(x)))
  }




}
