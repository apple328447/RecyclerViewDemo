package com.example.recyclerview_demo.RecyclerViewAddRecyclerView


class DataOutput {

    var dataList: MutableList<contentTypeData>? = null

    class contentTypeData {
        var id: Int? = null
        var fk: String? = null
        var type: Int? = null
        var content: MutableList<contentData>? = null
    }

    class contentData {
        var name: String? = null
        var img: MutableList<Int>? = null
    }
}

