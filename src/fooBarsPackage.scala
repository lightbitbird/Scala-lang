package foo {

    //ここでclass X とすれば、fooパッケージにXクラスが定義される.
    
    package bar {
        class MyFoo(val name:String)
    }

    package object bar2 {
        val baz = "baz!"
        def getBaz = baz
    }
    
    //fooパッケージ（と、そのサブパッケージ）からは見える
    private[foo] class Y
    
}
