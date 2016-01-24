package access {
    class X {
        //barパッケージからはアクセス可能
        def print = (new Y).name
    }
    class Y {
        protected[access] val name = "name"
    }
}

package scope {
    class Z extends access.Y{
        //サブクラスからアクセス可能
        def print = this.name
    }
}
