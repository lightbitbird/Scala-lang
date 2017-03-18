class Trait {

    println("||||||||||||||||||||||||||| Trait ||||||||||||||||||||||||||||||||")
    def syntax1() {
        println("-------------- トレイト ------------------")
        // トレイトは実装を持つことができる
        println("// ScalaでもJavaと同じく多重継承は禁止されていますが、インターフェイスの代わりに")
        println("// トレイト（trait）を使用。複数のトレイトをミックスイン（mix-in）することが可能")
        // <アクセス修飾子> trait トレイト名 {
        //     フィールドやメソッドの定義
        // }
        println("class ChildClass extends ParentClass with TraitA with TraitB")
        trait Programmer {
            def coding = println("コーディングします!")
        }
        println("trait Programmer { def coding = println(\"コーディングします!\")}")
        class Person(val name:String) extends Programmer
        println("class Person(val name:String) extends Programmer")
        val p = new Person("taro")
        println("val p = new Person(\"taro\")")
        println("p.coding => ")
        p.coding
        
        println("// インスタンス化時にmix-inもできる")
        trait Designer {
            def design = println("デザインします")
        }
        println("trait Designer { def design = println(\"デザインします\") }")
        val pd = new Person("taro") with Designer
        println("pd.coding => ")
        pd.coding
        println("pd.design => ")
        pd.design
        // トレイトは、メソッドだけではなくフィールドも持てます。さらに、本体にコードを書けばコンストラクタ処理もできる。
        // 但し、パラメータは取れず、補助コンストラクタの定義ができない
        trait ProjectManager {
            val budget:Int = 1000000
            println("budget = " + budget)
            def manage = println("プロジェクト管理します")
        }
        println("val pp = new Person(\"taro\") with ProjectManager => ")
        val pp = new Person("taro") with ProjectManager
        
        println("// コンストラクタが実行される順番 ----")
        class Parent {println("Parent")}
        class Child extends Parent {println("Child")}
        trait A {println("trait A")}
        trait B { println("trait B")}
        trait C {println("trait C")}
        println("val c = new Child with A with B with C")
        val c = new Child with A with B with C
        
        println("// 別のトレイトで両方とも同じシグネチャ（名前）のメソッドを実装していた場合 ----")
        println("// 同じシグネチャのメソッドを持つトレイトを複数ミックスインしたクラスでは、そのメソッドを必ずオーバーライドしなければ")
        trait Programme {def write = println("コードを書きます")}
        println("trait Programme {def write = println(\"コードを書きます\")}")
        trait Writer {def write = println("記事を書きます")}
        println("trait Writer {def write = println(\"記事を書きます\")}")
        class Personal extends Programme with Writer {
            override def write = println("ドキュメントを書きます")
        }
        println("class Personal extends Programme with Writer {")
        println("    override def write = println(\"ドキュメントを書きます\")")
        println("}")
        val mp = new Personal
        println("val mp = new Personal")
        println("mp.write => ")
        mp.write
        println("// トレイトで「super」を使用 ----")
        class Person2 extends Programme with Writer {
            override def write = super.write
        }
        println("class Person2 extends Programme with Writer {")
        println("    override def write = super.write")
        println("}")
        val p2 = new Person2
        println("val p2 = new Person2")
        println("p2.write => ")
        p2.write
        // 任意のトレイトのメソッドを呼び出したい場合
        println("// super[トレイト名].メソッド ---")
        class Person3 extends Programme with Writer {
            override def write = super[Programme].write
        }
        println("class Person3 extends Programme with Writer {")
        println("    override def write = super[Programme].write")
        println("}")
        
    }
    
    def syntax2() {
        println("// トレイト単体でもインスタンス化可能  -----")
        trait Programmer {
            def write = println("コーディングします!")
        }
        val p = new Programmer{}
        println("// トレイト名の後ろの「{}」は必須 -----")
        println("val p = new Programmer{}")
        println("p.write => ")
        p.write
        // トレイトのメソッドが抽象メソッドだった場合、インスタンス化のタイミングで実装できる。
        trait Programme {
            def write
        }
        println("trait Programme {")
        println("    def write")
        println("}")
        val pv = new Programme { def write = println("コーディングします")}
        println("val pp = new Programme { def write = println(\"コーディングします\")}")

        //複数のトレイトをミックスインしたインスタンスも作成可能
        trait T1 { def t1 = println("t1") }
        trait T2 { def t2 = println("t2") }
        val t = new T1 with T2 //複数のトレイトをミックスインしてインスタンス化
        
        println("------------ 積み重ね可能なトレイト ------------------")
        abstract class Engineer {
            println("class Engineer constructor")
            def work(time:Int)
        }
        class Person extends Engineer{
            println("class Person constructor")
         
            def work(time:Int) = {
                println("Person#work start")
                println("1つのタスクを" + time + "分で行います")
                println("Person#work end")
            }
        }
        val pｔ = new Person
        println("val pｔ = new Person(extends Engineer)")
        println("pｔ.work(60) => ")
        pｔ.work(60)

        // 「abstract override」キーワード
        trait Programming extends Engineer {
            println("trait Programmer constructor")
         
            abstract override def work(time:Int) = {
                println("Programmer#work start")
                //abstract宣言されたメソッドの中でsuperの呼び出しを行っている
                super.work(time - 15)
                println("Programmer#work end")
            }
        }
        val pp = new Person with Programming
        println("val pp = new Person with Programming")
        println("pp.work(60) => ")
        pp.work(60)
        
    }
    
    def syntax3() {
        println("--------------- 型パラメータの基本 -------------------")
        println("val list = List[String](\"a\",\"b\",\"c\")")
        val list = List[String]("a","b","c")
        println("val slist = List(\"a\",\"b\",\"c\")")
        val sList = List("a","b","c")
        println("sList.head => ")
        println(sList.head)
        println("// [Any]型 : 「型安全」 ----")
        println("val alist = List(\"a\",\"b\",1)")
        val aList = List("a","b",1)
        println(" --> aList: list[Any] = List(a, b, 1)")
        //Scalaでの型確認
        println("// 型を調べる isInstanceOf メソッド")
        println("list(0).isInstanceOf[String] => ")
        println(aList(0).isInstanceOf[String])
        println("list(2).isInstanceOf[Int] => ")
        println(aList(2).isInstanceOf[Int])
        // ScalaのCast
        println("// Cast: asInstanceOf メソッド")
        println("aList(0).asInstaceOf[String] => ")
        println(aList(0).asInstanceOf[String])
        
        println("-------------- 型パラメータを使用したクラスの定義 ------------------")
        println("class MySample[A]{}")
        println("// 「A」は何らかの型を取るという意 -------")
        class MySample[A]{
            var param:A = _
            def get:A = param
            def set(param:A) = this.param = param
        }
        // インスタンス化時に、[]を使用して型パラメータに実際の型を指定
        println("val x = new MySample[String]")
        val x = new MySample[String]
        x.set("Hello!")
        println("x.set(\"Hello!\")")
        println("x.get => ")
        println(x.get)

        println("// 型パラメータを2つ取るクラスをnewでインスタンス化 -------")
        class Collect
        class Error
        class OR[L,R]
        //ORという“演算子”を使っているように見えるが、実際には「OR」クラスをインスタンス化している
        new (Collect OR Error)
        println("class Collect")
        println("class Error")
        println("class OR[L,R]")
        println("new (Collect OR Error)")

        println("------------ 型パラメータを使用したメソッドの定義 -------------")
        // def 関数名[A](引数):戻り値 = 関数本体
        def func[A](arg:A):A = arg
        println("def func[A](arg:A):A = arg")
        println("func(\"fantastic!\") => ")
        println(func("fantastic!"))
        println("func[Int](10) => ")
        println(func[Int](10))
        
    }

    def syntax4() {
        println("------------ 上限境界／下限境界とパラメータ制約 -------------")
        class Base
        class Ex1 extends Base
        class Ex2 extends Ex1
        println("// 上限境界 [<:] -----")
        println("「<:」は、Aに指定できる型はBaseクラスか、「Baseのサブクラスである」という条件")
        class MySample[A <: Base]
        println("class Base")
        println("class Ex1 extends Base")
        println("class Ex2 extends Ex1")
        // AにはBaseかEx1かEx2（とNothingクラス）の指定が可能
        println("class MySample[A <: Base]")
        new MySample[Ex1]
        println("new MySample[Ex1]")
        //エラー
//        new MySample[String]

        println("// 下限境界 [>:] -----")
        println("// 「>:」の右側で指定された型、もしくはそのスーパークラスであるという条件")
        class MyTemp[A >: Ex1]
        // AにはBaseかEx1かEx2（とNothingクラス）の指定が可能
        println("class MyTemp[A >: Ex1]")
        new MyTemp[Ex1]
        println("new MyTemp[Ex1]")
        new MyTemp[Base]
        println("new MyTemp[Base]")
        //エラー
//        new MyTemp[Ex2]
        println("// 下限境界と上限境界を同時に指定 -----")
        //Ex1かBaseのみに限定
        class MyRange[A >: Ex1 <: Base]
        
        println("// 型パラメータに“クラス定義”を指定 -----")
        //「doit」という名前で引数なし、戻り値なしのメソッドを指定
        class MyDefine[A  <: { def doit():Unit }]
        println("class MyDefine[A  <: { def doit():Unit }]")
        class Far { def doit():Unit = println("doit!") }
        new MyDefine[Far]
        
        println("------------- 変位指定 ---------------")
        // 非変
        class Foo[T]
        println("class Foo[T]")
        def invariant(arg:Foo[Base]) = println("ok")
        //Foo[Base]型の引数を受け取る関数を定義
        println("def invariant(arg:Foo[Base]) = println(\"ok\")")
        //エラー発生
        println("invariant(new Foo[Ex]) => エラーとなる")
        println("// 標準ではFoo[Base]型に代入できるのはFoo[Base]型だけ")
//        invariant(new Foo[Ex1])
        
        // 共変
        println("// [+T]により、型パラメータにBasaまたはBaseのサブクラスを指定できる")
        class Foo2[+T]
        println("class Foo2[+T]")
        def covariant(arg:Foo2[Base]) = println("ok")
        println("def covariant(arg:Foo2[Base]) = println(\"ok\")")
        println("covariant(new Foo2[Ex1]) => ")
        covariant(new Foo2[Ex1])

        // 反変
        class Foo3[-T]
        println("// [-T]により、Foo[Base]型はFoo[Ex]型に代入可能")
        def contravariant(arg:Foo3[Ex1]) = println("ok")
        println("def contravariant(arg:Foo3[Ex1]) = println(\"ok\")")
        println("contravariant(new Foo3[Base]) => ")
        contravariant(new Foo3[Base])

    }

}