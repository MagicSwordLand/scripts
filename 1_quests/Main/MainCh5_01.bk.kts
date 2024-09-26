@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.classcore.ReachLevelObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.compability.mythicmobs.SignalObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location




val questID = "main_ch5_01"

class MainCh5_01: Quest(questID,"§7[§c主線委託§7] §f第五章-沙都解密"){
    val playerIcon = "%squests_icon%";
    val deleIcon = IconFonts.getNPC(47);
    val charsIcon = IconFonts.getNPC(48);
    val maylukIcon = IconFonts.getNPC(170);
    val sandIcon = IconFonts.getNPC(33);
    val nalmieIcon = IconFonts.getNPC(181);
    val unknowIcon = IconFonts.getNPC(29);


    private val playerName = "§c● §f%player_displayname%§r:";
    private val deleNPC = "§c● §e迪利§r:";
    private val charsNPC = "§c● §e查爾斯§r:";
    private val unknowNPC = "§c● §e未知聲音§r:";
    private val mayluklNPC = "§c● §e梅露可§r:";
    private val sandNPC = "§c● §e山德§r:";
    private val nalmielNPC = "§c● §e那爾邁§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e城主~我想打聽看看… ",
            "§x§d§6§c§1§a§e你是否知道生命之泉? ",
        ),
        *Utils.getMessage(deleIcon,
            "$deleNPC",
            "§x§d§6§c§1§a§e噢?你說的是那個傳說吧?",
            "§x§d§6§c§1§a§e那東西傳說被埋藏在古代法老的陵墓了",
            "§x§d§6§c§1§a§e不過聽說以往進去的盜墓者沒有一個活著出來",
        ),
        *Utils.getMessage(deleIcon,
            "$deleNPC",
            "§x§d§6§c§1§a§e不過你怎麼會知道這東西?",
            "§x§d§6§c§1§a§e這可是很危險的阿",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這…說來可話長了 ",
            "§x§d§6§c§1§a§e大概就是…為了替人解除詛咒 ",
        ),
        *Utils.getMessage(deleIcon,
            "$deleNPC",
            "§x§d§6§c§1§a§e原來如此…但此行危險?",
            "§x§d§6§c§1§a§e還希望你能慎重考慮",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這都是為了委託",
            "§x§d§6§c§1§a§e所以…我已經決定好了!",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這就是那座古墓了嗎…",
            "§x§d§6§c§1§a§e希望底下不會有甚麼機關…",
        ),
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e前面那個…是人?…怎麼會有人出現在…",
            "§x§d§6§c§1§a§e試著搭話看看吧…也許他能有甚麼線索",
        ),
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e請問…你是?",
        ),
        *Utils.getMessage(charsIcon,
            "$charsNPC",
            "§x§d§6§c§1§a§e我…我是遺跡考古團的查爾斯",
            "§x§d§6§c§1§a§e和團員們受困在底下",
            "§x§d§6§c§1§a§e然而他們…",
        ),
        *Utils.getMessage(charsIcon,
            "$charsNPC",
            "§x§d§6§c§1§a§e不少都在底下迷路…",
            "§x§d§6§c§1§a§e也有被怪物纏上的… ",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e原來如此…那你們究竟是怎麼困在底下的?",
        ),
        *Utils.getMessage(charsIcon,
            "$charsNPC",
            "§x§d§6§c§1§a§e我們在穹頂沙淵進行遺跡探查 ",
            "§x§d§6§c§1§a§e一不小心就掉進了坑裡",
            "§x§d§6§c§1§a§e醒來就和大家倒在了這裡面",
        ),
        *Utils.getMessage(charsIcon,
            "$charsNPC",
            "§x§d§6§c§1§a§e但是路途險峻，一路上都是怪物",
            "§x§d§6§c§1§a§e彷彿在防止誰的侵入一般",
            "§x§d§6§c§1§a§e許多亡者的靈魂都在此處徘徊…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e原來你們就是…",
            "§x§d§6§c§1§a§e看來還有必要往下前進",
        ),
    )
    private val mes5 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e是條岔路…",
            "§x§d§6§c§1§a§e糟糕…還有怪物不斷湧過來…",
            "§x§d§6§c§1§a§e又要解決一波又一波的怪物了",
        ),
    )
    private val mes6 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這種只屬於地表的怪物…",
            "§x§d§6§c§1§a§e全都跑到洞穴來了…",
            "§x§d§6§c§1§a§e看起來還想保護某樣東西… ",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e看來就是這下面不會錯了",
            "§x§d§6§c§1§a§e只是…這底下跟迷宮似的…雖然沒有陷阱",
            "§x§d§6§c§1§a§e但是怪物真的好多阿!",
        ),
    )
    private val mes7 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e可算是解決這些怪物了",
            "§x§d§6§c§1§a§e但是…總感覺在繞著圈呢…",
            "§x§d§6§c§1§a§e就沒有終點的嗎…",
        ),
    )
    private val mes8 = arrayOf(
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e又是一個盜墓者",
            "§x§d§6§c§1§a§e不知道多久沒見到活人了呢",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這個聲音…是誰!",
            "§x§d§6§c§1§a§e前面…那個是…牛頭人!",
        ),
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e這就讓你嘗嘗",
            "§x§d§6§c§1§a§e褻瀆神明的代價!",
        ),
    )
    private val mes9 = arrayOf(
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e沒想到…區區人類…",
            "§x§d§6§c§1§a§e也能有如此神力…",
            "§x§d§6§c§1§a§e是我輸了…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e背水一戰的感覺真是痛快",
            "§x§d§6§c§1§a§e但是前面的大門…貌似打不開了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e旁邊…",
            "§x§d§6§c§1§a§e這該不會就是…生命之泉了吧",
            "§x§d§6§c§1§a§e得小心地採集才行",
        ),
    )
    private val mes10 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e沒想到這東西…到手上居然變成了固體",
            "§x§d§6§c§1§a§e彷彿就像一塊水晶",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e先把生命之泉帶回去吧",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e至於旁邊這扇門…",
            "§x§d§6§c§1§a§e好像得另尋它法才能打開了",
            "§x§d§6§c§1§a§e也許…法器就在裡面…",
        ),
    )
    private val mes11 = arrayOf(
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e這些就是能解除詛咒的東西嗎…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我想是的，根據克麗奧小姐的描述",
            "§x§d§6§c§1§a§e但…詛咒一旦解除",
            "§x§d§6§c§1§a§e你也會依同消失",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e這樣也無所謂，我早已…受夠了活著的滋味了",
            "§x§d§6§c§1§a§e(一飲而盡)",
            "§x§d§6§c§1§a§e感覺…更加輕盈了…",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e這…我自由了嗎…",
            "§x§d§6§c§1§a§e但…感覺沒有消失的感覺…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e也許…詛咒解除並不代表會消失?",
            "§x§d§6§c§1§a§e或許…是紅寶石項鍊的幫助",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e對了，關於你先前所說的法器",
            "§x§d§6§c§1§a§e他其實並不是屬於這座神殿的祕寶",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e而是在沙源綠洲曾經原始部落的神器",
            "§x§d§6§c§1§a§e顧名思義，那是被神所祝福的法器",
            "§x§d§6§c§1§a§e要取得並使用，或許只能被神所選召才行",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e沙源綠洲…你所提及的原始部落，後來怎麼了?",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e數千年來，這片沙漠經歷了太多太多",
            "§x§d§6§c§1§a§e失去只是必然的，消失的部落…也許在其中早已被消滅了也說不定",
            "§x§d§6§c§1§a§e真相，只有自行探索才以熟知",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e好端端的…怎麼就打起了啞謎…",
            "§x§d§6§c§1§a§e無論如何，反正我只有去了才會知道對吧",
            "§x§d§6§c§1§a§e那也只能先回城鎮打聽那地方的情報了…",
        ),
    )
    init {
        cancelAble = false
        val world = Bukkit.getWorld("2k");
        val obj0 = ReachLevelObjective("obj0", 26)
            .setInstruction("§726等後將繼續任務");
        val obj1 = ListenNPCObjective(this,"obj1",47,*mes1)
            .setInstruction("§7與 §e迪利 §7對話");
        val obj2Loc = Location(world,244.29,73.0,69.65);
        val obj2 = MoveToObjective(this,"obj2", obj2Loc,5.0)
            .setInstruction ("§7前往墓地迷城 244,73,69 §8(請開啟GPS追蹤目標位置)");
        val obj3 = ListenTalkObjective(this,"obj3",*mes2)
        val obj4Loc = Location(world,177.29,54.0,117.65);
        val obj4 = MoveToObjective(this,"obj4", obj4Loc,5.0)
            .setInstruction ("§7前往墓地迷城 177,54,117 §8(請開啟GPS追蹤目標位置)");
        val obj5 = ListenTalkObjective(this,"obj5",*mes3)
        val obj6 = ListenNPCObjective(this,"obj6",48,*mes4)
            .setInstruction("§7與 §e查爾斯 §7對話");
        val obj7Loc = Location(world,205.29,47.0,160.65);
        val obj7 = MoveToObjective(this,"obj7", obj7Loc,5.0)
            .setInstruction ("§7前往墓地迷城深處 205,47,160 §8(請開啟GPS追蹤目標位置)");
        val obj8 = ListenTalkObjective(this,"obj8",*mes5)
        val obj9 = KillMobsObjective(this,"obj9","墓地迷域_",50)
            .setInstruction { "擊殺 ${it.amount} / 50 個墓地迷域的怪物" };
        val obj10 = ListenTalkObjective(this,"obj10",*mes6)
        val obj11 = KillMobsObjective(this,"obk11","墓地迷域_",30)
            .setInstruction { "擊殺 ${it.amount} / 30 個墓地迷域的怪物" };
        val obj12 = ListenTalkObjective(this,"obj12",*mes7)
        val obj13Loc = Location(world,239.29,24.0,36.65);
        val obj13 = MoveToObjective(this,"obj13", obj13Loc,5.0)
            .setInstruction ("§7前往墓地迷城座標 239,24,36 §8(請開啟GPS追蹤目標位置)");
        val obj14 = ListenTalkObjective(this,"obj14",*mes8)
        val obj15 = KillMobsObjective(this,"obj15","墓地迷域_米諾陶洛斯",1)
            .setInstruction { "擊殺米諾陶洛斯 ${it.amount} / 1" };
        val obj16 = ListenTalkObjective(this,"obj16",*mes9)
        val obj17 = SignalObjective("obj17","main05",5)
        .setInstruction { "採集生命之泉 ${it.amount} / 5" };
        val obj18 = ListenTalkObjective(this,"obj18",*mes10)
        /*val obj7 = KillMobsObjective(this,"obj7","阿努比斯神殿_",40)
            .setInstruction { "擊殺 ${it.amount} / 40 個阿努比斯神殿的怪物" };
        val obj8Loc = Location(world,96.29,88.0,-27.65);
        val obj8 = MoveToObjective(this,"obj8", obj8Loc,11.0)
            .setInstruction ("§7前往神殿 96,88,-27 §8(請開啟GPS追蹤目標位置)");
        val obj9 = ListenNPCObjective(this,"obj9",181,*mes5)
            .setInstruction("§7與 §e那爾邁 §7對話");
        val obj10Loc = Location(world,47.29,88.0,-27.65);
        val obj10 = MoveToObjective(this,"obj10", obj10Loc,5.0)
            .setInstruction ("§7前往神殿後院 47,88,-27  §8(請開啟GPS追蹤目標位置)");
        val obj11 = ListenTalkObjective(this,"obj11",*mes6)
        val obj12 = KillMobsObjective(this,"obj12","阿努比斯神殿_阿努比斯",1)
            .setInstruction { "擊殺阿努比斯 ${it.amount} / 1" };
        val obj13 = ListenTalkObjective(this,"obj13",*mes7)
        val obj14 = ListenNPCObjective(this,"obj14",181,*mes8)
            .setInstruction("§7與 §e那爾邁 §7對話");
        val obj15 = ListenNPCObjective(this,"obj15",182,*mes9)
            .setInstruction("§7與 §e克麗奧 §7對話");

        val obj6 = ListenTalkObjective(this,"obj6",*mes5)
        val obj7Loc = Location(world,191.29,78.0,261.65);
        val obj7 = MoveToObjective(this,"obj7", obj7Loc,5.0)
            .setInstruction ("§7前往座標 191,78,261 §8(請開啟GPS追蹤目標位置)")
        val obj8 = ListenTalkObjective(this,"obj8",*mes6)
        val obj9Loc = Location(world,143.29,80.0,237.65);
        val obj9 = MoveToObjective(this,"obj9", obj9Loc,5.0)
            .setInstruction ("§7前往火光來源 143,80,237 §8(請開啟GPS追蹤目標位置)");
        val obj10 = ListenTalkObjective(this,"obj10",*mes7)
        val obj11 = ListenNPCObjective(this,"obj11",33,*mes8)
            .setInstruction("§7與 §e山德 §7對話");
        val obj12 = ListenNPCObjective(this,"obj12",170,*mes9)
            .setInstruction("§7與 §e梅露可 §7對話");*/
        pushObjective(obj0,obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10,obj11,obj12,obj13,obj14,obj15,obj16,obj17,obj18)

        addRewards(
            MoneyReward(500), //錢幣
            QuestExpReward(200), //傭兵聲望
            ClassExpReward(1000),
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch6_01")
        } )
    }
}


val quest = MainCh5_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



