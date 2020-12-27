package app.doggy.charactermemosample

//データクラスは一つにまとめる。
data class CharacterData(
    //MainActivityで表示するデータ。
    val characterImageResource: Int,
    var name: String = "",

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
)