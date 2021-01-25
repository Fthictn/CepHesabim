package com.fethicectin.cephesabi

class CepHesabiModel {

    var id: Int?
    var amount:Int?
    var mounth:String?
    var description:String?
    var addorsub:Int?

    companion object Factory {
        fun create(): CepHesabiModel = CepHesabiModel()
    }

    init {
        id = null
        amount = null
        mounth = null
        description = null
        addorsub = null
    }

}