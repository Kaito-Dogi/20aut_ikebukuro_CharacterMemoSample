package app.doggy.charactermemosample

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

//データクラスは一つにまとめる。
open class CharacterData(
    @PrimaryKey open var id: String = UUID.randomUUID().toString(),
    //MainActivityで表示するデータ。
    open var characterImageResource: Int = 0,
    open var name: String = "",

    //MainActivityでは表示しないデータ。
    var gender: String = "",
    var nickname: String = "",
    var position: String = "",
    var job: String = "",
    var bloodType: String = "",
    var age: String = "",
    var birthday: String = "",
    var ability: String = "",
    var firstPerson: String = "",
    var favorite: String = "",
    var height: String = "",
    var weight: String = "",
    var voice: String = "",
    var personality: String = "",
    var specialSkill: String = "",
    var appearance: String = "",
    var upbring: String = "",
    var scene: String = "",
    var remark: String = "",
    var write: String = "",
    var other: String = ""
) : RealmObject()