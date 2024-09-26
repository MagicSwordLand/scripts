@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.classcore.ReachLevelObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.compability.mythicmobs.SignalObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location




val questID = "main_ch6_02"

class MainCh6_02: Quest(questID,"§7[§c主線委託§7] §f第六章-不明所以"){
    val playerIcon = "%squests_icon%";
    val deleIcon = IconFonts.getNPC(47);
    val charsIcon = IconFonts.getNPC(48);
    val maylukIcon = IconFonts.getNPC(170);
    val sandIcon = IconFonts.getNPC(33);
    val nalmieIcon = IconFonts.getNPC(181);
    val unknowIcon = IconFonts.getNPC(29);
    val amayliaIcon = IconFonts.getNPC(237);
    val ameraIcon = IconFonts.getNPC(203);


    private val playerName = "§c● §f%player_displayname%§r:";
    private val deleNPC = "§c● §e迪利§r:";
    private val charsNPC = "§c● §e查爾斯§r:";
    private val unknowNPC = "§c● §e未知聲音§r:";
    private val maylukNPC = "§c● §e梅露可§r:";
    private val sandNPC = "§c● §e山德§r:";
    private val nalmielNPC = "§c● §e那爾邁§r:";
    private val amayliaNPC = "§c● §e約翰夫§r:";
    private val ameraNPC = "§c● §e阿麥菈§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e梅露可~!我回來了!",
            "§x§d§6§c§1§a§e沒想到吧~我可是真的完成了這委託喔",
        ),
        *Utils.getMessage(maylukIcon,
            "$maylukNPC",
            "§x§d§6§c§1§a§e沒想到傳說是真的… ",
            "§x§d§6§c§1§a§e但…這法器得怎麼用啊?",
            "§x§d§6§c§1§a§e上面有沒有附上使用說明啊?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e阿這…原來你也不會",
            "§x§d§6§c§1§a§e要不問問看迪利村長吧",
            "§x§d§6§c§1§a§e也許他知道些甚麼 ",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e迪利城主，關於求雨法器的",
            "§x§d§6§c§1§a§e就是…這法器要怎麼用啊..?",
        ),
        *Utils.getMessage(deleIcon,
            "$deleNPC",
            "§x§d§6§c§1§a§e你真的帶回了這個法器!?",
            "§x§d§6§c§1§a§e這可是傳說阿…以往探詢真相的人",
            "§x§d§6§c§1§a§e都已經一去不返了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e嘿嘿…路上確實經歷了一波三折",
            "§x§d§6§c§1§a§e不過終究是帶回來啦",
        ),
        *Utils.getMessage(deleIcon,
            "$deleNPC",
            "§x§d§6§c§1§a§e是說…使用方法…我也…",
            "§x§d§6§c§1§a§e對了，這讓我想到沙漠的另一端",
            "§x§d§6§c§1§a§e薩哈南海岸礦鎮，雖然鄰近怪物所待的區域",
        ),
        *Utils.getMessage(deleIcon,
            "$deleNPC",
            "§x§d§6§c§1§a§e卻仍然有不少村民在那駐紮",
            "§x§d§6§c§1§a§e那裡的人也許知道一些秘密",
            "§x§d§6§c§1§a§e只是因為那裡的怪物近期特別多且危險",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e怪物多甚麼的，才不算甚麼呢",
            "§x§d§6§c§1§a§e我就先去找找薩哈南海岸礦鎮的位子吧",
        ),
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e好大的一座礦山!",
            "§x§d§6§c§1§a§e沒想到還有這麼一座礦區在這",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e也許他們的頭頭就在附近",
            "§x§d§6§c§1§a§e找找看吧 ",
        ),
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e請問…這裡的頭頭在哪啊?",
        ),
        *Utils.getMessage(amayliaIcon,
            "$amayliaNPC",
            "§x§d§6§c§1§a§e噢~我就是阿",
            "§x§d§6§c§1§a§e你肯定就是新人了吧",
            "§x§d§6§c§1§a§e我稍後能帶你熟悉一下附近",
        ),
        *Utils.getMessage(amayliaIcon,
            "$amayliaNPC",
            "§x§d§6§c§1§a§e新人的礦工稿都在帆恩那裏",
            "§x§d§6§c§1§a§e如果你要定居下來我會再推薦房子給你",
            "§x§d§6§c§1§a§e我稍後能帶你熟悉一下附近",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e等等等等！",
            "§x§d§6§c§1§a§e我…是從阿納斯塔來的傭兵",
            "§x§d§6§c§1§a§e我是來詢問關於祈雨神器阿塔卡之事的",
        ),
        *Utils.getMessage(amayliaIcon,
            "$amayliaNPC",
            "§x§d§6§c§1§a§e原來你就是那個阿納斯塔來的人啊..",
            "§x§d§6§c§1§a§e關於那個神器，我是知道些甚麼",
            "§x§d§6§c§1§a§e但現在鎮上有著繁多麻煩是要處理",
        ),
        *Utils.getMessage(amayliaIcon,
            "$amayliaNPC",
            "§x§d§6§c§1§a§e能不能請你先去找阿麥菈問問",
            "§x§d§6§c§1§a§e他是鎮上最知識淵博的人了",
            "§x§d§6§c§1§a§e他也許就在附近監工呢",
        ),
    )
    private val mes5 = arrayOf(

        *Utils.getMessage(ameraIcon,
            "$ameraNPC",
            "§x§d§6§c§1§a§e你是新來的?",
            "§x§d§6§c§1§a§e新面孔..別站著礙事",
            "§x§d§6§c§1§a§e下去跟著挖礦抵禦裂縫怪物吧",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e不..那個我是阿納斯塔成來的傭兵",
            "§x§d§6§c§1§a§e是來詢問關於祈雨神器的事情的",
            "§x§d§6§c§1§a§e想必您就是阿麥菈小姐了吧?",
        ),
        *Utils.getMessage(ameraIcon,
            "$ameraNPC",
            "§x§d§6§c§1§a§e傭兵?怪不得想說怎麼一身鎧甲呢",
            "§x§d§6§c§1§a§e在那之前你先去下面幫我清掉一些怪物吧",
            "§x§d§6§c§1§a§e自從那個裂縫出現後，這座鎮子就不曾安靜過",
        ),
        *Utils.getMessage(ameraIcon,
            "$ameraNPC",
            "§x§d§6§c§1§a§e除了要供應各種地方的礦材需求",
            "§x§d§6§c§1§a§e同時又要抵禦各種怪物",
            "§x§d§6§c§1§a§e犧牲無數的鎮民才換取的部分和平",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e既然如此，就交給我吧",
            "§x§d§6§c§1§a§e畢竟是我在百忙之中打擾的您",
        ),
        *Utils.getMessage(ameraIcon,
            "$ameraNPC",
            "§x§d§6§c§1§a§e還挺有禮貌的",
            "§x§d§6§c§1§a§e就這麼說定了",
        ),
    )
    private val mes6 = arrayOf(

        *Utils.getMessage(ameraIcon,
            "$ameraNPC",
            "§x§d§6§c§1§a§e阿…可算是輕鬆多了",
            "§x§d§6§c§1§a§e看不出來你還真能打",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e阿這…我都是傭兵了…多少還能有點戰力吧",
            "§x§d§6§c§1§a§e那麼…能否替我解答關於法器的問題了呢?",
        ),
        *Utils.getMessage(ameraIcon,
            "$ameraNPC",
            "§x§d§6§c§1§a§e恩，讓我看看這法器吧",
            "§x§d§6§c§1§a§e(一陣鑽研)",
            "§x§d§6§c§1§a§e這…是阿塔卡，看起來是還缺少一塊部件",
        ),
        *Utils.getMessage(ameraIcon,
            "$ameraNPC",
            "§x§d§6§c§1§a§e就在旁邊的暮光烈谷的底部",
            "§x§d§6§c§1§a§e有著一顆顆凝聚烈陽能量的暮陽聖石",
            "§x§d§6§c§1§a§e得將那顆聖石頭嵌進神器的寶石孔才行",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e暮光烈谷…?那豈不是堪比火山一般炎熱的地方?",
            "§x§d§6§c§1§a§e這真的能行嗎?",
        ),
        *Utils.getMessage(ameraIcon,
            "$ameraNPC",
            "§x§d§6§c§1§a§e這可是你問我的，還能假嗎?",
            "§x§d§6§c§1§a§e快去吧",
        ),
    )
    private val mes7 = arrayOf(

        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這地方…真的比想像中更加炎熱阿…",
            "§x§d§6§c§1§a§e好想…回到草原吹著涼風…",
            "§x§d§6§c§1§a§e突然就想起了老家了呢…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e是說這就是入口了吧",
            "§x§d§6§c§1§a§e感覺下面會有更強烈的灼燒感呢…",
        ),
    )
    private val mes8 = arrayOf(

        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這地方…真的彷彿煉獄一般..太熱了吧…",
            "§x§d§6§c§1§a§e就快受不了了…武器都快拿不穩了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e居然還有那麼多怪物! ",
            "§x§d§6§c§1§a§e看來得處理掉才能前進了",
        ),
    )
    private val mes9 = arrayOf(

        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e是誰，闖入這片禁地",
            "§x§d§6§c§1§a§e日光如此不穩定，居然還有闖入者?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e阿不是的，我是…",
        ),
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e接招吧，你是無法活著離開的",
        ),
    )
    private val mes10 = arrayOf(

        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e烈日當空…不詳將至",
            "§x§d§6§c§1§a§e你若真的…那就別讓我…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e阿不是的，怎麼大家都喜歡說謎語嘛…",
            "§x§d§6§c§1§a§e就趁現在帶點暮陽聖石回去吧",
        ),
    )
    private val mes11 = arrayOf(

        *Utils.getMessage(ameraIcon,
            "$ameraNPC",
            "§x§d§6§c§1§a§e哼，沒想到真的有人能打敗了那裏的怪物",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我還帶回來這些了喔~",
        ),
        *Utils.getMessage(ameraIcon,
            "$ameraNPC",
            "§x§d§6§c§1§a§e啊!?你帶回了那麼多啊?",
            "§x§d§6§c§1§a§e把一顆拿來吧",
            "§x§d§6§c§1§a§e馬上就能恢復了",
        ),
        *Utils.getMessage(ameraIcon,
            "$ameraNPC",
            "§x§d§6§c§1§a§e完成了",
            "§x§d§6§c§1§a§e這下就搞定了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e發出了好耀眼的光芒喔!",
        ),
        *Utils.getMessage(ameraIcon,
            "$ameraNPC",
            "§x§d§6§c§1§a§e說明他已經能夠使用了",
            "§x§d§6§c§1§a§e是說，你就要這樣走了嗎",
            "§x§d§6§c§1§a§e我們這還需要很多的戰力呢",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e放心吧，我們傭兵，一旦有需求",
            "§x§d§6§c§1§a§e發點委託自然就會有人來啦",
            "§x§d§6§c§1§a§e那麼我就先回去了",
        ),
    )
    init {
        cancelAble = false
        val world = Bukkit.getWorld("2k");
        val obj0 = ReachLevelObjective("obj0", 35)
            .setInstruction("§735等後將繼續任務");
        val obj1 = ListenNPCObjective(this,"obj1",170,*mes1)
            .setInstruction("§7與 &e梅露可 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",47,*mes2)
            .setInstruction("§7與 &e迪利 §7對話");
        val obj3Loc = Location(world,230.29,80.0,395.65);
        val obj3 = MoveToObjective(this,"obj3", obj3Loc,5.0)
            .setInstruction ("§7前往薩哈南海岸礦鎮 230 80 395 §8(請開啟GPS追蹤目標位置)");
        val obj4 = ListenTalkObjective(this,"obj4",*mes3)
        val obj5 = ListenNPCObjective(this,"obj5",216,*mes4)
            .setInstruction("§7與 &e約翰夫 §7對話");
        val obj6 = ListenNPCObjective(this,"obj6",203,*mes5)
            .setInstruction("§7與 &e阿麥菈 §7對話");
        val obj7 = KillMobsObjective(this,"obj7","薩哈南海岸礦鎮_裂縫章魚",30)
            .setInstruction { "擊殺 ${it.amount} / 30 隻裂縫章魚" };
        val obj8= ListenNPCObjective(this,"obj8",203,*mes6)
            .setInstruction("§7與 &e阿麥菈 §7對話");
        val obj9Loc = Location(world,528.29,74.0,588.65);
        val obj9 = MoveToObjective(this,"obj9", obj9Loc,10.0)
            .setInstruction ("§7前往暮光烈谷 528 74 588 §8(請開啟GPS追蹤目標位置)");
        val obj10 = ListenTalkObjective(this,"obj10",*mes7)
        val obj11Loc = Location(world,537.29,57.0,515.65);
        val obj11 = MoveToObjective(this,"obj11", obj11Loc,5.0)
            .setInstruction ("§7前往暮光烈谷內部 537 57 515 §8(請開啟GPS追蹤目標位置)");
        val obj12 = ListenTalkObjective(this,"obj12",*mes8)
        val obj13 = KillMultiMobsObjective(this,"obj13", mapOf(
            "暮光烈谷_烈日氣息" to 12,
            "暮光烈谷_能量守護者" to 20))
            .setInstruction{ "擊殺烈日氣息 ${it.get("暮光烈谷_烈日氣息")}/12、能量守護者 ${it.get("暮光烈谷_能量守護者")}/20"}
        val obj14Loc = Location(world,503.29,37.0,566.65);
        val obj14 = MoveToObjective(this,"obj14", obj14Loc,5.0)
            .setInstruction ("§7前往暮光烈谷底部 503 37 566 §8(請開啟GPS追蹤目標位置)");
        val obj15 = ListenTalkObjective(this,"obj15",*mes9)
        val obj16 = KillMobsObjective(this,"obj16","暮光烈谷_烈日之陽--拓笊",1)
            .setInstruction { "擊殺 ${it.amount} / 1 烈日之陽--拓笊" };
        val obj17 = ListenTalkObjective(this,"obj17",*mes10)
        val obj18 = GiveMultiItemObjective(this,"obj18",203,
            mapOf(
                "COLLECTMATERIAL:M022" to 32,//蒼山金
                "QUESTMATERIAL:M022" to 1,//靈木
            ))
            .setInstruction{"§7將 ${it.get("COLLECTMATERIAL:M008")} / 32 個暮陽聖石、祈雨神器-阿塔卡 交給 §e阿麥菈"}
        val obj19= ListenNPCObjective(this,"obj19",203,*mes11)
            .setInstruction("§7與 &e阿麥菈 §7對話");
        /*val obj7 = KillMobsObjective(this,"obj7","阿努比斯神殿_",40)
            .setInstruction { "擊殺 ${it.amount} / 40 個阿努比斯神殿的怪物" };
        val obj8Loc = Location(world,96.29,88.0,-27.65);
        val obj8 = MoveToObjective(this,"obj8", obj8Loc,11.0)
            .setInstruction ("§7前往神殿 96,88,-27 §8(請開啟GPS追蹤目標位置)");
        val obj9 = ListenNPCObjective(this,"obj9",181,*mes5)
            .setInstruction("§7與 &e那爾邁 §7對話");
        val obj10Loc = Location(world,47.29,88.0,-27.65);
        val obj10 = MoveToObjective(this,"obj10", obj10Loc,5.0)
            .setInstruction ("§7前往神殿後院 47,88,-27  §8(請開啟GPS追蹤目標位置)");
        val obj11 = ListenTalkObjective(this,"obj11",*mes6)
        val obj12 = KillMobsObjective(this,"obj12","阿努比斯神殿_阿努比斯",1)
            .setInstruction { "擊殺阿努比斯 ${it.amount} / 1" };
        val obj13 = ListenTalkObjective(this,"obj13",*mes7)
        val obj14 = ListenNPCObjective(this,"obj14",181,*mes8)
            .setInstruction("§7與 &e那爾邁 §7對話");
        val obj15 = ListenNPCObjective(this,"obj15",182,*mes9)
            .setInstruction("§7與 &e克麗奧 §7對話");

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
        pushObjective(obj0,obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10,obj11,obj12,obj13,obj14,obj15,obj16,obj17,obj18,obj19)

        addRewards(
            MoneyReward(1000), //錢幣
            QuestExpReward(400), //傭兵聲望
            ClassExpReward(2000),
            MessageReward("&a➯ &7神器: &c祈雨神器-阿塔卡&6(暮陽)")
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch7_01")
            Utils.command("get ${it.name} ARTIFACT A005 1 0 1")
            Utils.command("lp user ${it.name} permission set main06.done")
        } )
    }
}


val quest = MainCh6_02();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



