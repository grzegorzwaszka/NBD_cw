package com.cw9

object MainCw9 {

  class Container[A,R](private val x : A){

    def getContent(): A = {
      return x
    }

    def applyFunction(fun: A => R):R = {
      fun(x)
    }

  }

  trait Maybe[A]

  class Yes[A,R] (var x:A) extends Maybe[A] {
    def applyFunction(fun: A => R):R = {
      fun(x)
    }
    def getOrElse[D](y:D): A ={
      this.x
    }
  }

  class No[A,R] extends Maybe[Nothing] {
    def applyFunction(fun: A => R):No[A,R]= {
      this
    }
    def getOrElse[D](y:D): D = {
      y
    }
  }



  def main(args: Array[String]): Unit = {


    print("Zadanie 1 \n")
    var cont = new Container[String, String]("Testowy")
    print(cont.getContent() + "\n")
    def funfun(x:String) = x + " Test"
    print(cont.applyFunction(funfun) + "\n")

    print("Zadanie 2 \n")
    var objNo = new No
    var objYes = new Yes[String,Object]("Hello")
    print("ObjNo instance: " + objNo.isInstanceOf[Maybe[Any]] + "\n")
    print("ObjYes instance: " + objYes.isInstanceOf[Maybe[Any]] + "\n")

    print("Zadanie 3 \n")
    def applyNothing(x:Nothing) = {
      x
    }
    def applyFun(y:String) = y + "Neighbour"
    print("ObjNo returns type: " + objNo.applyFunction(applyNothing).getClass() + "\n")
    print("ObjYes reurns: " + objYes.applyFunction(applyFun) + "\n")

    print("Zadanie 3 \n")
    print("ObjNo getOrElse: " + objNo.getOrElse("test") + "\n")
    print("ObjYes getOrElse: " + objYes.getOrElse("test") + "\n")

  }

}
