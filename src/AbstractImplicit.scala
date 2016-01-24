class AbstractImplicit {
    println("||||||||||||||||||||||||||||||| AbstractImplicit |||||||||||||||||||||||||||||||")

    def syntax1() {
        println("------------ 抽象型 --------------")
        // Scalaではクラスやメソッド以外に、フィールドや型も抽象的に宣言ができる
        println("// 「type」キーワードで抽象型の宣言 ----")
        abstract class Base {
            // 抽象クラスBaseでは、「type SomethingFoo」という宣言
            type SomethingFoo
            def show(something:SomethingFoo)
        }
        println("// 抽象型の使い方 ----")
        class Foo {
            def exec = println("Foo#execを実行")
        }
        class Ex1 extends Base {
            // SomethingFoo抽象型に具体的なFooという型を指定
            type SomethingFoo = Foo
            //SomethingFoo(Foo)のexecメソッドを呼び出すように実装
            def show(something:SomethingFoo) = something.exec
        }
        val x = new Ex1
        println("// showメソッドにFoo型を渡して実行")
        println("x.show(new Foo) => ")
        println(x.show(new Foo))

        println("// 抽象型での型境界の指定ができる ------")
        println("abstract class Base {")
        println("    type SomethingFoo <: Foo")
        println("    ....")
        println("}")

        println("// 別名を付ける役割も ------")
        type X = List[(Int,String,Double)]
        println("type X = List[(Int,String,Double)]")
        def func(arg1:X,arg2:X):X = List(1,"Sample",0.4).asInstanceOf[X]

        println("------------ 暗黙の型変換（implicit conversion） --------------")
        // ある型から別の型への変換を事前に変換用関数を用意して自動で行う機能
        println("// 暗黙の型変換を行う関数は「implicit」キーワードを付与し、引数に変換元の型、戻り値に変換先の型を指定")
        implicit def intToString(num:Int):String = {
            println("数値から文字列へ変換")
            num.toString()
        }
        println("implicit def intToString(num:Int):String = {")
        println("    println(\"数値から文字列へ変換\")")
        println("    num.toString()")
        println("}")
        println("val str:String = 10 => ")
        val str:String = 10

        //Date型からString型へ暗黙の型変換
        println("implicit def dateToString(date:java.util.Date):String")
        implicit def dateToString(date:java.util.Date):String = {
            import java.text._
            println("java.util.DateからStringへ変換")
            val sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            sdf.format(date)
        }
        println("val strDate:String = new java.util.Date() => ")
        val strDate:String = new java.util.Date()
    }

    def syntax2() {
        println("// 暗黙の引数 ----")
        // 引数の型に合わせた暗黙の値を用意しておくことで、メソッド呼び出し時の引数を省略できる仕組み
        println("def メソッド名(implicit 引数名:型名) : 戻り値型名 = 関数内容")
        //引数リストの先頭の変数にしか付けられないので、複数の引数リストを持たせる形で使用
        println("def メソッド名(引数名:型名…)…(implicit 引数名:型名) : 戻り値型名 = 関数内容")
        println("複数の引数リスト ----")
        def sum(x:Int)(y:Int)(z:Int):Int = x + y + z
        println("def sum(x:Int, y:Int, z:Int):Int = x + y + z = ")
        println("sum(1)(2)(3) => ")
        println(sum(1)(2)(3))
        def greeting(name:String)(implicit greet:String) = {
            println(greet + name)
        }
        println("greeting(\"taro\")(\"good morning!\") => ")
        greeting("taro")("good morning!")
        //implicitな値を追加
        implicit val hello:String = "hello!"
        println("implicit val hello:String = \"hello!\"")
        println("greeting(\"taro\") => ")
        greeting("taro")
        println("greeting(\"taro\")(\"Baby!\") => ")
        greeting("taro")("Baby!")
        
        println("// 可視境界の指定方法 ----------")
        println("class X[A <% T]")
        
        class X
        class Y
        class Z[A <% X]
        val a = new Z[X]
        println("val a = new Z[X]")
//        val a = new Z[Y]
        println("val b = new Z[Y] => エラー")

        implicit def yTox(y:Y):X = new X
        //YクラスからXクラスへの暗黙の型変換を行う関数が定義されているので、問題なくインスタンス化
        val aa = new Z[Y]

        println("------------  暗黙の型パラメータの制約 ----------")
        println("// A =:= B ----------")
        // Aの型がBの型と等しければメソッドを呼び出せる        
        class V[A] {
            def exec(implicit t:A =:= Int):Unit = println("exec")
        }
        
        val v = new V[Int]
        println("val v = new V[Int]")
        println("v.exec => ")
        v.exec
        val v2 = new V[String]
        println("val v2 = new V[String]")
        //エラー
        println("v2.exec => error")
//       v2.exec
        
        println("// A <:< B ----------")
        //AはBと同じ、もしくはサブクラス
        class Base
        class Ex1 extends Base
        class Another
        class U[A] {
            def exec(implicit t:A <:< Base):Unit = println("exec")
        }
        val u = new U[Ex1]
        u.exec
        val u2 = new U[Another]
        //エラー
//        u2.exec
        
        println("// 「A => B」の場合 -------")
        // 可視境界と意味は同じで、「Aは暗黙の型変換でBに変換できる（AをBとして扱える）
        class XX
        class YY
        class ZZ[A]{
            def exec(implicit t:A => YY):Unit = println("exec")
        }
        val zz = new ZZ[XX]
        //エラー
//        zz.exec
        implicit def xxToyy(x:XX):YY = new YY
        //暗黙の型変換後はエラーなし
        zz.exec
    }
}