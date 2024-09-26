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




val questID = "main_ch4_02"

class MainCh4_02: Quest(questID,"§7[§c主線委託§7] §f第四章-水源匱乏"){
    val playerIcon = "%squests_icon%";
    val deleIcon = IconFonts.getNPC(47);
    val maylukIcon = IconFonts.getNPC(170);
    val nalmieIcon = IconFonts.getNPC(181);
    val cleoIcon = IconFonts.getNPC(182);
    val anopisIcon = IconFonts.getNPC(29);


    private val playerName = "§c● §f%player_displayname%§r:";
    private val deleNPC = "§c● §c[阿納斯塔城城主] §e迪利§r:";
    private val mayluklNPC = "§c● §e[傭兵接待員] §e梅露可§r:";
    private val nalmielNPC = "§c● §e[法老王魂] §e那爾邁§r:";
    private val cleolNPC = "§c● §e[祭司殘影] §e克麗奧§r:";
    private val anopislNPC = "§c● §c[埃及諸神] §e阿努比斯§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e梅露可~最近還有甚麼任務嗎?",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e阿，是諾亞鎮來的傭兵，我正巧要找你呢",
            "§x§d§6§c§1§a§e有個艱難的委託想要找你",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e嗯?艱難的委託，說來聽聽看",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e是這樣的，你也知道阿納斯塔城位在沙漠",
            "§x§d§6§c§1§a§e三不五時鬧乾旱已經是常有的事情",
            "§x§d§6§c§1§a§e但每過10年都會經歷一次大型乾旱",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e往往都會伴隨著很多的意外",
            "§x§d§6§c§1§a§e甚至會引來許多沙漠盜賊qianru城鎮",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e正巧過不久就是這週期的時間",
            "§x§d§6§c§1§a§e而傳說，在阿努比斯神殿底下",
            "§x§d§6§c§1§a§e藏有懲治旱災的法器",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e雖然不清楚真假",
            "§x§d§6§c§1§a§e但城裡總是將希望寄於這件法器",
            "§x§d§6§c§1§a§e苦於城裡的傭兵太少所以..",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你說…在阿努比斯神殿嗎?",
            "§x§d§6§c§1§a§e(思考)",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e你是在思考該怎麼辦嗎?",
            "§x§d§6§c§1§a§e也許真的太勉強了",
            "§x§d§6§c§1§a§e我們城裡的命運也許…早就注定了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e噢噢!不是，啦我是在思考",
            "§x§d§6§c§1§a§e神殿到底在哪裡啊",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e這麼說…你願意幫我們嗎!",
            "§x§d§6§c§1§a§e太好了!村子有救了!",
            "§x§d§6§c§1§a§e至於神殿，就在城鎮西側的出口出去就能看到 ",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這座神殿看起來也是挺雄偉的阿!",
            "§x§d§6§c§1§a§e果然沙漠都是文明的起點…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那些是…是守衛!",
            "§x§d§6§c§1§a§e難道連這神殿都有防盜墓的機制嗎?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e感覺無法潛入…看來只能殺進去了",
        ),
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這樣下去只會被包圍",
            "§x§d§6§c§1§a§e先衝進神殿再說",
        ),
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e不是吧…連這邊也都有? ",
            "§x§d§6§c§1§a§e難不成他們也會無限重生?",
            "§x§d§6§c§1§a§e武力感覺更強了呢",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e不管了，委託目標是在神殿裡吧…",
            "§x§d§6§c§1§a§e那清掉一些就趕快去找法器吧",
        ),
    )
    private val mes5 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e看來沒有路可以下去了 ",
            "§x§d§6§c§1§a§e去旁邊看看吧",
            "§x§d§6§c§1§a§e感覺這層很安靜呢",
        ),
    )
    private val mes6 = arrayOf(
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e你是阿努比斯新派來的監管者嗎?",
            "§x§d§6§c§1§a§e放心吧，我想逃也逃不掉的",
            "§x§d§6§c§1§a§e我…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你在說甚麼?",
            "§x§d§6§c§1§a§e甚麼監管者?你又是誰?",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e你難道…",
            "§x§d§6§c§1§a§e我是那爾邁，這裡的初代法老",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e由於體內擁有超出凡体的力量",
            "§x§d§6§c§1§a§e導致被眾神盯上，為求保護，才去拜託了阿努比斯",
            "§x§d§6§c§1§a§e而受到阿努比斯的亡魂詛咒，即便是亡魂也將會被囚禁於此",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你說甚麼!?甚麼詛咒",
            "§x§d§6§c§1§a§e我只是想找傳說中能治旱災的法器而已呀!",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e能治旱災的法器…是在說凌波魔珠吧",
            "§x§d§6§c§1§a§e要不…我們來做個交易吧",
            "§x§d§6§c§1§a§e你替我解除詛咒，我指引你尋找法器",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e真的嗎!那…我該怎麼做",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e其實…我也不太清楚..",
            "§x§d§6§c§1§a§e但也許打敗阿努比斯就行了吧!",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e阿這…人家可是古神阿…我怎麼...",
            "§x§d§6§c§1§a§e而且他怎麼會出現在...",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e之後有的是時間解釋",
            "§x§d§6§c§1§a§e得趁他還在凡間的時候行動",
            "§x§d§6§c§1§a§e這時是他力量最薄弱的時候了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那…那我就試試看吧",
            "§x§d§6§c§1§a§e但他會在哪裡...?",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e這時間的他，肯定還在神殿後方",
        ),
    )
    private val mes7 = arrayOf(
        *Utils.getMessage(anopisIcon,
            "$anopislNPC",
            "§x§d§6§c§1§a§e凡人，你怎麼會在這?",
            "§x§d§6§c§1§a§e你身上也有很香甜的魔力",
            "§x§d§6§c§1§a§e你也是來尋求庇護的?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e是誰在說話?",
            "§x§d§6§c§1§a§e難道是那尊神像?"
        ),
        *Utils.getMessage(anopisIcon,
            "$anopislNPC",
            "§x§d§6§c§1§a§e我就在這",
            "§x§d§6§c§1§a§e讓我看看你的力量吧",
        ),
    )
    private val mes8 = arrayOf(
        *Utils.getMessage(anopisIcon,
            "$anopislNPC",
            "§x§d§6§c§1§a§e凡人，你很有實力我果然沒有看錯...",
            "§x§d§6§c§1§a§e正在在崩塌...(消失)",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e他...究竟在說甚麼...",
            "§x§d§6§c§1§a§e突然就消失了../",
            "§x§d§6§c§1§a§e不過...可算是解決他了...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e得快回去看看那爾邁怎麼樣了",
        ),
    )
    private val mes9 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那爾邁!我做到了",
            "§x§d§6§c§1§a§e你有感覺到甚麼變化嗎",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e我感覺…詛咒力量有變弱",
            "§x§d§6§c§1§a§e但並沒有辦法完全解開我身上的詛咒",
            "§x§d§6§c§1§a§e也許要想想別的辦法…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e難道就…沒有甚麼古籍之類的嗎?",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e你這麼一說…確實有",
            "§x§d§6§c§1§a§e但不是古籍，而是智囊者，也是祭司-克麗奧",
            "§x§d§6§c§1§a§e但千年過去了…不曉得",
        ),
        *Utils.getMessage(nalmieIcon,
            "$nalmielNPC",
            "§x§d§6§c§1§a§e也許…他還守在過往的房間裡 ",
            "§x§d§6§c§1§a§e就在神殿的三樓…你可以搜索看看 ",
        ),
    )
    private val mes10 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那個…請問你是克麗奧小姐嗎?",
        ),
        *Utils.getMessage(cleoIcon,
            "$cleolNPC",
            "§x§d§6§c§1§a§e你是…誰?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e是那爾邁請我來的",
            "§x§d§6§c§1§a§e我是來替他解除詛咒的",
        ),
        *Utils.getMessage(cleoIcon,
            "$cleolNPC",
            "§x§d§6§c§1§a§e那爾邁大人…詛咒…",
            "§x§d§6§c§1§a§e需要…紅寶石…項鍊，還有…生命之泉…",

            ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e紅寶石項鍊…?生命之泉又是甚麼?",
        ),
        *Utils.getMessage(cleoIcon,
            "$cleolNPC",
            "§x§d§6§c§1§a§e項鍊…就在房間裡…",
            "§x§d§6§c§1§a§e生命之泉…就在古墓…深處…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e既然如此..我就先尋找紅寶石項鍊吧!",
            "§x§d§6§c§1§a§e克麗奧看起來無法在說話了…",
        ),
    )
    private val mes11 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e原來就在這裡",
            "§x§d§6§c§1§a§e但接下來的生命之泉…",
            "§x§d§6§c§1§a§e看來得回村莊向城主打聽打聽了",
        ),
    )
    init {
        cancelAble = false
        val world = Bukkit.getWorld("2k");
        val obj0 = ReachLevelObjective("obj0", 23)
            .setInstruction("§723等後將繼續任務");
        val obj1 = ListenNPCObjective(this,"obj1",170,*mes1)
            .setInstruction("§7與 &e梅露可 §7對話");
        val obj2Loc = Location(world,236.29,78.0,-39.65);
        val obj2 = MoveToObjective(this,"obj2", obj2Loc,5.0)
            .setInstruction ("§7前往阿努比斯神殿入口 236,78,-39 §8(請開啟GPS追蹤目標位置)");
        val obj2_2 = ListenTalkObjective(this,"obj2_2",*mes2)
        val obj3 = KillMobsObjective(this,"obj3","阿努比斯神殿_",30)
            .setInstruction { "擊殺 ${it.amount} / 30 個阿努比斯神殿的怪物" };
        val obj4 = ListenTalkObjective(this,"obj4",*mes3)
        val obj5Loc = Location(world,133.29,86.0,-27.65);
        val obj5 = MoveToObjective(this,"obj5", obj5Loc,5.0)
            .setInstruction ("§7前往神殿 133,86,-27 §8(請開啟GPS追蹤目標位置)");
        val obj6 = ListenTalkObjective(this,"obj6",*mes4)
        val obj7 = KillMobsObjective(this,"obj7","阿努比斯神殿_",40)
            .setInstruction { "擊殺 ${it.amount} / 40 個阿努比斯神殿的怪物" };
        val obj7_2 = ListenTalkObjective(this,"obj7_2",*mes5)
        val obj8Loc = Location(world,96.29,88.0,-27.65);
        val obj8 = MoveToObjective(this,"obj8", obj8Loc,11.0)
            .setInstruction ("§7前往神殿 96,88,-27 §8(請開啟GPS追蹤目標位置)");
        val obj9 = ListenNPCObjective(this,"obj9",181,*mes6)
            .setInstruction("§7與 &e那爾邁 §7對話");
        val obj10Loc = Location(world,47.29,88.0,-27.65);
        val obj10 = MoveToObjective(this,"obj10", obj10Loc,5.0)
            .setInstruction ("§7前往神殿後院 47,88,-27  §8(請開啟GPS追蹤目標位置)");
        val obj11 = ListenTalkObjective(this,"obj11",*mes7)
        val obj12 = KillMobsObjective(this,"obj12","阿努比斯神殿_阿努比斯",1)
            .setInstruction { "擊殺阿努比斯 ${it.amount} / 1" };
        val obj13 = ListenTalkObjective(this,"obj13",*mes8)
        val obj14 = ListenNPCObjective(this,"obj14",181,*mes9)
            .setInstruction("§7與 &e那爾邁 §7對話");
        val obj15 = ListenNPCObjective(this,"obj15",182,*mes10)
            .setInstruction("§7與 &e克麗奧 §7對話");
        val obj16 = SignalObjective("obj16","main04",1)
            .setInstruction { "在神殿頂樓房間中的雜物找出紅寶石項鍊 ${it.amount} / 1" }
            .setEndProcess {
                it.sendTitle("§7[§a獲得物品§7]","§c紅寶石項鍊")
            };
        val obj17 = ListenTalkObjective(this,"obj17",*mes11)
        /*
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
            .setInstruction("§7與 &e山德 §7對話");
        val obj12 = ListenNPCObjective(this,"obj12",170,*mes9)
            .setInstruction("§7與 &e梅露可 §7對話");*/
        pushObjective(obj0,obj1,obj2,obj2_2,obj3,obj4,obj5,obj6,obj7,obj7_2,obj8,obj9,obj10,obj11,obj12,obj13,obj14,obj15,obj16,obj17)

        addRewards(
            MoneyReward(300), //錢幣
            QuestExpReward(150), //傭兵聲望
            ClassExpReward(1000),
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch5_01")
        } )
    }
}


val quest = MainCh4_02();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



