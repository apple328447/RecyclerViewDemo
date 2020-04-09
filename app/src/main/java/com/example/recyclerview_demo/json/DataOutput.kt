package com.example.recyclerview_demo.json

import com.google.gson.annotations.SerializedName

class DataOutput {
    /**
     * userId
     * createTime:製造日期
     * amount:多少錢
     * detail:明細
     * */
    //@SerializedName("t")
    class DepositDetail {
        var userId: Integer? = null
        var createTime: String? = null
        var amount: String? = null
        var detail: Int? = null
    }
}
