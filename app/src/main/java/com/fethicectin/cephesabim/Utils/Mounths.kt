package com.fethicectin.cephesabim.Utils

enum class Mounths(val mounth:String) {
    JANUARY("Ocak"),
    FEBRUARY("Şubat"),
    MATCH("Mart"),
    APRIL("Nisan"),
    MAY("Mayıs"),
    JUNE("Haziran"),
    JULY("Temmuz"),
    AUGUST("Ağustos"),
    SEPTEMBER("Eylül"),
    OCTOBER("Ekim"),
    NOVEMBER("Kasım"),
    DECEMBER("Aralık")
}

    fun returnMounthList():MutableList<String>{
        var mounthList = mutableListOf<String>()
        Mounths.values().forEach {
            mounthList.add(it.mounth)
        }
        return mounthList
    }
