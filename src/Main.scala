object Main {
    def main(args:Array[String]) = {
        println("Hello World!!")
        syntax1()
        syntax2()
        syntax3()
        syntax4()
        syntax5()
        syntax6()
        val clsPtrn = new ClassAndPattern
        clsPtrn.syntax1()
        clsPtrn.syntax2()
        clsPtrn.syntax3()
        clsPtrn.syntax4()
        clsPtrn.syntax5()
        val t = new Trait
        t.syntax1()
        t.syntax2()
        t.syntax3()
        t.syntax4()
        val ai = new AbstractImplicit
        ai.syntax1()
        ai.syntax2()
    }

    println("||||||||||||||||||||||||||||||| Main |||||||||||||||||||||||||||||||")
    def syntax1() =  {
        println("----------- 変数 ---------------")
        var num:Int = 10
        num = 20
        var n1, n2, n3 = 1
        println("----------- 型推論 ---------------")
        //型推論
        var message = "Hello"
//        val message = "Hello"
        message = "Scala"
        
//        var longNumber:Long = _
        //型推論
        var longNumber:Long = 0
        //文字列に代入
        val str = num.toString()
        println("--------- 浮動小数点リテラル etc -------------")
        //Double
        val double1 = 0.1
        //Float(with f)
        val float1 = 0.1F
        val double2 = 0.13+3f
        val t = true
        println("-------　文字列リテラル、文字コード　---------------")
        // 文字リテラル
        val a = 'A'
        // 「\\u」に続いて4個の16進数値を並べたUnicode文字  
        val b = '\u0041' //-> Char = A
        // 「\」に続いて文字のコードポイントを8進数か16進数で表明した値
        val c = '\101' //-> Char = A

        println("--------- エスケープシーケンス -----------------")
        //エスケープ処理
        val es = "\"\'\\" // -> "'\
        //「"""」（ダブルクオート3つ）を使用して文字列を囲むと、「生の文字列（raw string）」を使え
        val es3 = """\"\'\\""" // -> \"\'\\
            
        println("------------ Symbolシンボルリテラル --------------")
        val s = 'sSymbol
        println("Symbol.name = " + s.name)

        println("------------ 配列 --------------")
        val a1 = new Array[Int](10) // -> a1: Array[Int] = Array(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        val a2 = Array(1,2,3) // -> a2: Array[Int] = Array(1, 2, 3)
        a2(0) = 100 //配列の要素に値を設定
        println("a2(0) = " + a2(0)) //配列の要素を取得
        println(a2.length)
        println(a2) //Array[Int] = Array(100, 2, 3)
        println("------------ a2(1) = a2.apply(1) --------------")
        println(a2(1) + " = " + a2.apply(1))
        println("------------ a2(1) = 10  as well as  a2.update(1,10) --------------")
        a2.update(1,10)
        println(a2(1))

        println("------------ 演算子 --------------")
        println("1+2= " + (1 + 2))
        println("1 .+(2) =  " + (1 .+(2))) //1の後に「.」を付けると、Doubleと判断されるので、スペースを入れる


        println("------------ 比較演算子(Scalaではプリミティブ型、参照型でも同じく ==, !=で比較) --------------")
        val aa = 1
        val bb = 1
        println("aa == bb -> " + (aa == bb))
        
        val k1 = "hello"
        val k2 = "hello"
        println("k1 == k2 -> " + (k1 == k2))
    }
    
    def syntax2() = {
        println("-------------- Scalaの基本的なコレクション4タイプ -------------------")
        println("//【1】 可変と不変 ( mutable/immutable )")
        val lis1 = List(1) // -> List[Int]
        //::はリストに要素を追加して新しいリストを返すメソッド
        val lis2 = 2 :: lis1
        println("2 :: lis1 -> " + lis2)
        
        println("//【2】 シーケンス型 --- Seq ~~ java.util.List")
        // 「java.util.List」（を実装したクラス）がよく使用されるコレクションだが、Scalaでは、Seq
        // またはこれを継承したLinearSeqクラスを継承したListクラス
        // Listは「scala.collection.immutable」パッケージに属する不変コレクション
        // 可変なListを使用したい場合、「scala.collection.mutable」パッケージの「ListBuffer」を使用
        val list1 = List("Scala")
        println("list1(0) = " + list1(0))
        val ls1 = Nil
        val ls2 = "Scala" :: ls1
        val ls3 = "Java" :: ls2
        println("ls3 = \"Java\" :: ls2 :: ls1 = " + ls3)

        println("//【3】 マップ型")
        // scala.collection.immutable.Map　と　scala.collection.mutable.Map
        val m = Map[String, Int]("Scala" -> 1, "Java" -> 2, "Ruby" -> 3)
        println("m(\"Scala\") = " + m("Scala"))

        println("//【4】タプル(Tuple) 型 --- 異なる型の要素を格納可能")
        // 異なる型の要素を格納可能
        // 要素の数は22個まで指定でき、要素の数に応じてクラスが決定
        val t1 = ("Scala", 10)
        val t2:Tuple2[String, Int] = ("Java", 2)
        println("t1=" + t1 + ", t2=Tuple2=" + t2)
        val t = ("Scala", 10, 100.0d) // -> (java.lang.String, Int, Double) = (Scala,10,100.0)
        println("t._1 = " + t._1)
        println("t._3 = " + t._3)
        
    }

    def syntax3() = {
        println("----------------- 条件分岐「if」 -----------------------")
        val x = 0
        val result = if (x == 0) "x is zero" else "x is one"
        println(result)
        val x1 = 1
        val ret = if (x1 == 0) "x is zero" // Any = ()
        println(ret)
        
        println("----------------- 繰り返し「for」 -----------------------")
        //for(
        //   [ジェネレータ]
        //   [フィルタ]　※任意
        //) 式
        val listItems = List("Scala", "Java", "Ruby")
        println("// for example1 ----------")
        for (
            item <- listItems // ジェネレータ
            if item.length > 4 // フィルタ
        ) println("item = " + item)
        println("// for example2 簡易版 ----------")
        for (item <- listItems) println("item = " + item)
        println("// yield (for式で加工した結果を保持)----------")
        val resultList = for(item <- listItems) yield {
            "I use " + item
        }
        println("result of yield -> " + resultList)
        println("------------------- 繰り返し「while」「do-while」 ------------------------")
        var i = 0
        while(i < 4) {
            println("i = " + i)
            i += 1
        }
        i = 0
        do {
            println("do-while i = " + i)
            i += 1
        } while(i < 4)

        println("------------------- 例外処理 ------------------------")
        val res = try {
            "a".toInt
        } catch {
            case e:NumberFormatException => {
                println("exception!")
                -1
            }
        } finally {
            println("finally!")
        }
        println("res = " + res)
        println("// throw Exception -------")
//        println(throw new Exception("thrwo Exception!!"))

    }
    
    def syntax4() = {
        println("----------------- パターンマッチ match ----------------------")
        //match構文
        val x = 10
        x match {
            case 1 => println("x is 1")
            case 10 => println("x is 10")
            case _ => println("x is other number")
        }

        //型の形式でmatch
        //Any: Scalaのすべての親クラス
        val y:Any = "hello"
        val res = y match {
            case i:Int => println("y = " + i.toString()); 1
            case s:String => println("y = " + s); 2
            case _ => println("other"); 3
        }
        
        println("// パターンガード (if文を使用) --------------------")
        // 
        val z:Any = 50
        val reslt = z match {
            case i:Int if i >= 100 => println("i > 100")
            case _ => println("other")
        }
    }

    def syntax5() = {
        println("--------------- 関数の定義と呼び出し --------------------")
        // def 関数名[型パラメータ](引数名:引数の型名,……):返り値 = 関数本体
        def add(x:Int, y:Int):Int = x + y
        println("add(1,2) => " + add(1,2))
        def print():Unit = println("Hello")
        println("print() => ");
        print()
        def print2:Unit = println("hello2")
        println("print2 => ");
        print2
        def print3 = println("hello3")
        println("print3(型指定なし) => ");
        print3
        println("--------------- ファーストオブジェクトクラス --------------------")
        // 関数リテラル
        // (変数名:型名,……) => 関数本体
        println("// トレイト(FUNCTION) -----------")
        val func = (x:Int, y:Int) => x + y
        println("func(2,3) => " + func(2,3))
        val func2: (Int, Int) => Int = (x:Int, y:Int) => x + y
        println("func2(2,4) => " + func2(2, 4))
        println("// 関数を引数に取る関数 ----------")
        // 「Int型の引数を2つ取り、Int型を返す関数」と数値の2つの引数
        def calc(f:(Int, Int) => Int, num:Int):Int = f(num,num)
        println("calc((x,y) => x + y, 10) ==> " + (calc((x,y) => x + y, 10)))
        val ad = (x:Int, y:Int) => x + y
        val ret = calc(ad, 10)
        println("ad = (x:Int, y:Int) => x + y --> calc(ad, 10) => " + ret)
        println("---------------- 高階関数 -------------------")
        def getFunc(str:String):(Int, Int) => String = (x:Int, y:Int) => str + (x + y)
        val f = getFunc("result is ")
        println("f(5, 2) => " + f(5, 2))
    }

    def syntax6() {
        println("------------------- 可変長引数 -------------------------")
        println("// def 関数名(引数名:引数の型名*) = 関数本体 ---------------")
        println("def showMessages(args: String*) = for(arg <- args) println(arg)")
        def showMessages(args: String*) = for(arg <- args) println(arg)
        println("// 空 ----------------")
        println("showMessages() => ")
        println(showMessages())
        println("// サイズ1 ----------------")
        println("showMessages(\"hello\") => ")
        println(showMessages("hello!"))
        println("// サイズ2 ----------------")
        println("showMessages(\"Java\", \"Scala\") => ")
        println(showMessages("Java", "Scala"))
        println("// Array[String]を直接引数に渡したい場合 -----")
        val array = Array("Hello", "Scalaaaa")
        println("val array = Array(\"Hello\", \"Scalaaaa\") ")
        println("showMessages(array: _*) => ")
        println(showMessages(array: _*))
        println("// デフォルト引数 -------")
        def show(message: String = "hello", count:Int = 1) = {
            var i = 0
            while(i < count) {
                println(message)
                i += 1
            }
        }
        println("def show(message: String = \"hello\", count:Int = 1) = { ... }")
        println("show() => " + show())
        println("show(count = 2) => " + show(count = 2))
        println("show(message = \"bye\", count = 3) => " + show(message = "bye", count = 3))
        println("------------------- プレイスホルダ構文 ----------------------")
        val func:(Int,Int) => Int = _ + _
        println("func:(Int,Int) => Int = _ + _ ---> func(2, 3) => " + func(2, 3))
        val func2 = (_:Int) + (_:Int)
        println("func2 = (_:Int) + (_:Int) ---> func2(3, 4) => " + func2(3, 4))
        println("------------------- 部分適用された関数 --------------------")
        def add(x:Int, y:Int):Int = x * y
        val func_add = add _
        println("def add(x:Int, y:Int):Int = x * y --> val func_add = add _")
        val func_add2 = func_add(_:Int, 5)
        println("func_add(_:Int, 5) --> func_add2(2) => " + func_add2(2))
        val func_add3:(Int) => Int = func_add(_, 5)
        println("func_add3:(Int) => Int = func_add(_, 5)")
        println("func_add3(2) => " + func_add3(2))
        println("------------------- クロージャ --------------------")
        def counter() = {
            var count = 0
            () => {
                count += 1
                count
            }
        }
        println("def counter() = {")
        println("    var count = 0")
        println("    () => {")
        println("         count += 1")
        println("         count")
        println("    }")
        println("}")
        println("val c1 = counter()")
        val c1 = counter()
        println("c1() => " + c1())
        println("c1() => " + c1())
        val c2 = counter()
        println("c2() => " + c2())
        println("c1() => " + c1())
        println("------------------- ローカル関数--------------------")
        println("// 関数の中で定義する[ローカル関数] ------")
        def showLanguages(title:String, langList:List[String]) = {
            def printLang(item:String) = {
                println(title + ":" + item)
            }
            for (lang <- langList) printLang(lang)
        }
        println("showLanguages(\"Programming Language\", List(\"Java\", \"Scala\", \"Ruby\"))")
        showLanguages("Programming Language", List("Java", "Scala", "Ruby"))
    }
    
    
}