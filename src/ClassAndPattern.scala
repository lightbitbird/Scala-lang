class ClassAndPattern {
    println("||||||||||||||||||||||||||||||| ClassAndPattern |||||||||||||||||||||||||||||||")
    def syntax1() {
        println("------------------ クラス ------------------------")
        println("// 構文 ---------")
        println("class クラス名 {")
        println("    //フィールドやメソッドの定義")
        println("}")
        class Programmer {
            var language = "Scala"
            def coding() = println(language + "を使ってコーディングします")
        }
        println("class Programmer {var langauge; def coding()}")
        println("val pg = new Programmer")
        val pg = new Programmer
        println("pg.coding() => ")
        pg.coding()
        println("pg.language = \"Java\", pg.coding() => ")
        pg.language = "Java"
        pg.coding()
        println("--------------- 基本コンストラクタ　-------------------")
        println("// コンストラクタで処理を記述するには、クラス内に直接処理を記述 -----")
        class Programming {
            //ここにインスタンス化時の処理を記述する
            println("Programmingインスタンスを生成します")
            var language = "Scala"
            def coding() = println(language + "を使ってコーディングします")
        }
        val pgming = new Programming
        println("// コンストラクタが引数を受け取る -----")
        println("// class Programme(var language:String) { } ----")
        println("// varやvalを指定しないとクラス外からアクセスできなくなる ----")
        println("// (_language:String) or (val langauge:String) ----")
        println("// とすることで参照用に公開することになる ----")
        class Programme(var language:String) {
//        class Programme(_language:String) {
//        class Programme(val language:String) {
            println("Programmeインスタンスを生成します")
            println("language = " + language)
            def coding() = println(language + "を使ってコーディングします")
        }
        val pgmme = new Programme("Java")
    }
    
    def syntax2() {
        println("--------------- 補助コンストラクタと「this」キーワード　-------------------")
        println("// def this ( {任意の引数} ) = { 補助コンストラクタの処理 }")
        class Assist(val language:String) {
            println("Assistnインスタンスを生成します")
            println("language = " + language)
            
            /** 補助コンストラクタ **/
            def this() = this("Scala")
            def coding() = println(this.language + "を使ってコーディングします")
        }
        val assist = new Assist("Java")
        println("assist.coding() => ")
        assist.coding()

        println("----------- require --------------")
        println("// require(｛条件式｝) ------")
        class Require(_language: String) {
            println("language = " + _language)
            println("require(_language != null)")
            println("// 引数がnullだったらエラー -----")
            require(_language != null)
            val language = _language
            /** 補助コンストラクタ */
            def this() = this("Scala")
            def coding() = println(language + "を使ってコーディングします")
        }
//        println("new Require(null)")
//        val req = new Require(null)
        println("new Require(\"Java\")")
        val reqire = new Require("Java")

        println("---------- オーバーロード ------------")
        class Sample {
            def add(x:Int, y:Int) = x + y
            def add(x:Double, y:Double) = x + y
            def add(x:Int, y:Int, z:Int) = x + y + z
        }
        println("val s = new Sample")
        val s = new Sample
        println("add(1,2) => ")
        println(s.add(1, 2))
        println("add(1D,2D) => ")
        println(s.add(1D,2D))
        println("add(1,2,3) => ")
        println(s.add(1,2,3))

        println("---------- object(シングルトンオブジェクト) ------------")
        //Scalaにはstaticメソッドがない
        object SampleUtil {
            def hello() = println("hello!")
        }
        println("SampleUtil.hello() => ")
        SampleUtil.hello()

        println("---------- コンパニオンオブジェクト ------------")
        //classとobjectが同じファイル、同じパッケージの中に、同じ名前で宣言
        //コンパニオンクラスのprivateなフィールドやメソッドに対してアクセスができる
        class SampleCompanion private (num:Int)
        object SampleCompanion {
            def apply(num:Int) = {
                new SampleCompanion(num)
            }
        }
        val ins = SampleCompanion(10)
        println(ins)

    }
    
    def syntax3() {
        println("----------- Optionクラス --------------")
        // 「Map」の「get」メソッドはキーに対応する値が見つかったときにSome（値）を返し、キーが見つからなければNoneを返す
        val m = Map("Scala"->1, "Java"->2)
        println("val m = Map(\"Scala\"->1, \"Java\"->2)")
        println("m.get(\"Scala\") => ")
        println(m.get("Scala"))
        println("m.get(\"Ruby\") => ")
        println(m.get("Ruby"))
        println("m.get(\"Scala\").get => ")
        println(m.get("Scala").get)
//        println("m.get(\"Ruby\").get => ")
//        println(m.get("Ruby").get)
        println("m.get(\"Ruby\").getOrElse(\"nothing!\") => ")
        println(m.get("Ruby").getOrElse("nothing!"))
        println("// パターンマッチ -----")
        def testMatch(opt:Option[Int]) = {
            opt match {
                case Some(n) => println(n)
                case None => println("None!")
            }
        }
        println("testMatch(Some(10)) => ")
        testMatch(Some(10))
        println("testMatch(None) => ")
        testMatch(None)

        println("----------- unapply「抽出子」 --------------")
        println("// メソッド定義することで、オブジェクトをcase部分に指定でき ---")
        class Apple(val name:String)
        object Apple {
            def unapply(a: Any): Boolean = {
                if (a.isInstanceOf[Apple]) true else false
            }
        }
        class Orange(val name:String)
        object Orange {
            def apply(name: String): Orange = new Orange(name)
//            def unapply(a: Any): Boolean = {
//                if (a.isInstanceOf[Orange]) true else false
//            }
            def unapply(a: Orange):Option[String] = Some(a.name)
        }

        println("// Orangeに対するcase部分を下記のようにすると、nameフィールドの値を変数にバインドして使用できる ---")
        println(" case Orange(name) => println(\"Orange.name=\" + name)")
        def matchFruit(value:Any) = {
            value match {
                case Apple => println("Apple")
                case Orange("DEKOPON") => println("Orange.name = DEKOPON")
                case _ => println("other")
            }
        }
        println("matchFruit(Orange(\"something\") => ")
        matchFruit(Orange("something"))
        println("matchFruit(Orange(\"DEKOPON\") => ")
        matchFruit(Orange("DEKOPON"))
        println("matchFruit(Apple) => ")
        matchFruit(Apple)
        println("matchFruit(\"hello\") => ")
        matchFruit("hello")

        println("----------- caseクラス --------------")
        // 「caseクラス」とは、あらかじめ想定されるデフォルト処理を定義した状態でクラスを作成するための仕組み
        // ・applyが定義されるので、newを使用せずにインスタンス化可能
        // ・caseクラスのコンストラクタ引数は、すべてvalとして扱われる
        case class Carot(name:String)
        val carot = Carot("kagawa")
        println("carot.name = " + carot.name)
        // ・toStringやequals、copyなどのメソッドが提供される
        println("carot.toString = " + carot.toString)
        val newCarot = carot.copy(name = "Kyoninjin")
        println("val newCarot = carot.copy(name = \"Kyoninjin\")")
        println("newCarot.name = " + newCarot.name)

        // ・unapplyが定義されるので、パターンマッチで使用することが可能
        case class Eggplant(name:String)
        def matchVeggie(value:Any) = {
            value match {
                case Carot(name) => println("Carot.name = " + name)
                case Eggplant("NASUBI") => println("Eggplant")
                case _ => println("other")
            }
        }
        println("matchVeggie(Carot) => ")
        matchVeggie(Carot("Kagawa"))
        println("matchVeggie(Eggplant(\"NASUBI\")) => ")
        matchVeggie(Eggplant("NASUBI"))

    }

    def syntax4() {
        println("----------- package --------------")
        // Javaと違って物理的な階層構造とパッケージ階層が一致しなくてもよい。
        //java.util.Listとjava.util.Mapをインポート
        import java.util.{List,Map}
        import foo.bar.MyFoo
        //foo.barパッケージのすべてのクラスをインポート
        //import foo.bar._
        object Package {
            def myfoo(name:String) = {
                val mf = new MyFoo(name)
                println(mf.name)
            }
        }
        //「=>」を使用すれば、インポートしたクラスに別名を与えられる
        import java.util.{Date => JDate}
        println("import java.util.{Date => JDate}")
        println("new JDate")
        //java.sql.DateをSDateとしてインポートし、それ以外のクラスもすべてインポート
        import java.sql.{Date => SDate,_}
        println("import java.sql.{Date => SDate,_}")
        //すでにインポートされているパッケージや自分が所属するパッケージから相対的にインポート可能
        println("import java.util")
        println("import util.Date")
        //明示的な絶対指定も可「 _root_」 から始める。
        import _root_.scala.collection.Map
        // 拡張子が.scalaでファイルには、3つのimport宣言が自動で追加
        println("import java.lang._")
        println("import sclala._")
        println("import Predef._")
        
        println("----------- パッケージオブジェクト --------------")
        // パッケージオブジェクトを使用することで、パッケージ自体にメソッドや定数を定義できる。
        println("package object ｛パッケージ名｝ {")
        println("    //メソッドや定数の定義")
        println("}")
        println("// MyFoo.scalaにfoo.barオブジェクトを追加")
        //これでfoo.barパッケージにあるクラスからはimport文なしでbaz変数やgetBazメソッドが使える。
        println("// objpackage.scalaではimport文なしでbaz変数やgetBazメソッドが使える")
        println("import foo.bar2._")
        println("val mfo = new MyFoo2(\"which?\")")
        import foo.bar2._
        val mfo = new MyFoo2("which?")
        println("mfo.myFooBaz => ")
        println(mfo.myFooBaz)
        
        println("----------- アクセス制限 --------------")
        //公開権限と非公開権限private
        //アクセス修飾子を何も指定しない場合、公開権限
        //Scalaに公開権限を示すキーワードはなし。
        class X {
            //指定なし。公開メンバとして、どこからでもアクセス可能
            def foo = println("foo")

            //非公開権限private このクラスからのみアクセス可能
            private val name = "name"
            def print = {
                val x = new X
                println(x.name) //これはok
                
            }
        }
        // 限定公開権限（protected） 
        //Javaではサブクラスに加えて同一パッケージからのアクセスも可能ですが、Scalaではサブクラスからのみアクセス可能

        println("----------- スコープ限定子 --------------")
        println("//fooパッケージ（と、そのサブパッケージ）からは見える")
        println("private[foo] class Y")

        //「private[this]」とすると、通常のprivateよりも厳しい制限となり、そのインスタンスからのみアクセス可能
        class Y {
            private[this] val name = "name"
            def print = {
                //これはNG 
                //val y = new Y
                //println(y.name) 
          
                println(this.name) //これはOK
            }
        }
        
    }
    
    def syntax5() {
        
        println("------------ 抽象クラスの定義と継承の仕方 --------------")
        println("abstract class Engineer {")
        println("    def work():Unit")
        println("    def study() = printf(\"勉強します\")")
        println("}")
        abstract class Engineer {
            def work():Unit
            def study() = printf("勉強します")
        }
        println("class Programmer(name:String, age:Int) extends Engineer {")
        println("    def work() = printf(\"%s(%d)さんはコーディングします\", name, age)")
        println("    override def study() = printf(\"プログラミングの勉強します\")")
        println("}")
        class Programmer(name:String, age:Int) extends Engineer {
            def work() = printf("%s(%d)さんはコーディングします", name, age)
            override def study() = printf("プログラミングの勉強します")
        }
        
        println("// スーパークラスのコンストラクタにアクセス -----")
        abstract class Engine(val name:String) {
            println("Engine.name=" + name)
            def work():Unit
        }
        class Programme(name:String,val age:Int) extends Engine(name){
            println("Programme.name=" + name)
            println("Programme.age=" + age)
          
            def work() = printf("%s(%d)さんはコーディングします",name,age)
        }
        val p = new Programme("taro",30)
        
        println("// 「final」で継承やオーバーライドの禁止 -----")
        //finalクラス
        final class XX {
            def print() = println("final class")
        }
        class YY {
            //finalメソッド
            final def print() = println("final method")
        }
        //エラー
//        class EX_X extends XX
        class EX_Y extends YY {
            //エラー
//            override def print() = println("override method")
        }
        
        println("// 「シールドクラス」でサブクラスの制限 -----")
        //サブクラスを作成するための制限を付けたクラスです。
        // シールドクラスを継承するケースクラスを使ってマッチ式を書くと、対応できていないパターンの組み合わせをチェックして警告してくれる。
        // クラス定義の先頭に「sealed」キーワードを付ける
        sealed abstract class Engineering
        case class Programming() extends Engineering
        case class Tester() extends Engineering
        case class Architect() extends Engineering
        object Check {
            def check() = {
                val e:Engineering = new Programming
                e match {
                    case p:Programming => println("Programmer")
                    case t:Tester => println("Tester")
//                    case a:Architect => println("Architect")
                }
            }
        }
        Check.check()
        println("match is not exhaustive! missing combination Architect")

        println("// @uncheckedアノテーション -----")
        // matchにおいて、実行されないことが分かっている場合、チェックをしないようにアノテーションを使える
        println("(e: @unchecked) match {")
        println("    case p:Programmer => println(\"Programmer\")")
        println("    case t:Tester => println(\"Tester\")")
        println("}")

    }
}