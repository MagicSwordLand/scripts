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




val questID = "main_ch7_01"

class MainCh7_01: Quest(questID,"§7[§c主線委託§7] §f第七章-異端發源"){
    val playerIcon = "%squests_icon%";
    val maylukIcon = IconFonts.getNPC(170);
    val lunaIcon = IconFonts.getNPC(204);
    val unknowIcon = IconFonts.getNPC(29);


    private val playerName = "§c● §f%player_displayname%§r:";
    private val mob1NPC = "§c● §e卜普人§r:";
    private val maylukNPC = "§c● §c[傭兵接待員] §e梅露可§r:";
    private val lunaNPC = "§c● §c[荒原拾荒者] §e露娜§r:";
    private val boss3NPC = "§c● §c[蛛菇沙源Boss] §e卜普王§r:";
    private val unknowNPC = "§c● §e未知聲音§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e梅露可!我剛剛聽到了一個巨大的響聲",
            "§x§d§6§c§1§a§e好像是在公會後面的高原那發出的",
        ),
        *Utils.getMessage(maylukIcon,
            "$maylukNPC",
            "§x§d§6§c§1§a§e那不是…蛛菇沙源!?",
            "§x§d§6§c§1§a§e那曾經是一片樂土…直到裂縫被打開以後…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e裂縫!?難道跟薩哈南海岸礦鎮那個裂縫有關…?",
        ),
        *Utils.getMessage(maylukIcon,
            "$maylukNPC",
            "§x§d§6§c§1§a§e也許那裏的魔物開始壯大了",
            "§x§d§6§c§1§a§e有點難以解決…我這就發起委託",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這就交給我吧，委託就不用貼出來了",
            "§x§d§6§c§1§a§e我帶走就行了",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e又是岔路，果然這地方到處都是岔路",
            "§x§d§6§c§1§a§e旁邊有個人，去問問他好了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e先生，不好意思，我想請問",
            "§x§d§6§c§1§a§e蛛菇沙源的中心怎麼走?",
        ),
        *Utils.getMessage(unknowIcon,
            "$mob1NPC",
            "§x§d§6§c§1§a§e?磃??醭圚揱?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e阿這…說的是甚麼奇怪的語言阿",
            "§x§d§6§c§1§a§e難道不是這地方的人?",
        ),
        *Utils.getMessage(unknowIcon,
            "$mob1NPC",
            "§x§d§6§c§1§a§e矙?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e不好…看來是怪物阿，都圍上來了!",
        ),
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e是時候該前進了",
            "§x§d§6§c§1§a§e再這麼待著也許會被夾攻呢",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e就選右邊這條路吧",
        ),
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e沒想到連這片高原都還有建築…",
            "§x§d§6§c§1§a§e前面好像…又是個人!不過他和那些怪物不一樣",
        ),
    )
    private val mes5 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e不好意思，請問…這是甚麼地方啊?",
            "§x§d§6§c§1§a§e我是來探查這一帶探查異相的",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e先前聽聞有爆炸聲",
            "§x§d§6§c§1§a§e避免危險奉命來調查的",
        ),
        *Utils.getMessage(lunaIcon,
            "$lunaNPC",
            "§x§d§6§c§1§a§e恩",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e不是..你這麼冷淡是甚麼意思啊",
            "§x§d§6§c§1§a§e我可是來調查這邊的安全的",
        ),
        *Utils.getMessage(lunaIcon,
            "$lunaNPC",
            "§x§d§6§c§1§a§e在另一條路",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e另一條路…這樣我明白了",
            "§x§d§6§c§1§a§e不過你一個人在這也挺危險的吧",
            "§x§d§6§c§1§a§e要不跟我一起…",
        ),
        *Utils.getMessage(lunaIcon,
            "$lunaNPC",
            "§x§d§6§c§1§a§e露娜，不要",
            "§x§d§6§c§1§a§e露娜，不會人類的話",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e難道…你也是裂縫中出來的人…?",
            "§x§d§6§c§1§a§e不過你和他們好像都不一樣",
        ),
        *Utils.getMessage(lunaIcon,
            "$lunaNPC",
            "§x§d§6§c§1§a§e露娜，害怕…卜普",
            "§x§d§6§c§1§a§e露娜…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這樣看來，只能先離開這了",
            "§x§d§6§c§1§a§e可是這一座鎮子..跟個死城一樣",
            "§x§d§6§c§1§a§e只能從另一條路著手調查了",
        ),
    )
    private val mes6 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e好耀眼的閃電…那是甚麼! ",
            "§x§d§6§c§1§a§e是那群怪物!?",
            "§x§d§6§c§1§a§e可是她更高…而且…好像斷了一隻手?",
        ),
        *Utils.getMessage(unknowIcon,
            "$boss3NPC",
            "§x§d§6§c§1§a§e那扁是什摩傢伙!",
            "§x§d§6§c§1§a§e尼久是人類?",
            "§x§d§6§c§1§a§e矮小，又弱卜禁風，這片大陸國然沒什麼偉害",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你這…你到底是誰?",
            "§x§d§6§c§1§a§e甚麼這片大陸那片大路的",
        ),
        *Utils.getMessage(unknowIcon,
            "$boss3NPC",
            "§x§d§6§c§1§a§e真馬翻，我是卜普國的國網!",
            "§x§d§6§c§1§a§e尼一葛人類也想跟來小滅窩?",
            "§x§d§6§c§1§a§e雖然窩裡量不如從淺，也可鱉小勘我!",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e等等…甚麼消滅!?",
            "§x§d§6§c§1§a§e別甚麼都不說就攻上來了呀!",
        ),
    )
    private val mes7 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這傢伙，也太難處理了吧",
            "§x§d§6§c§1§a§e不過真不愧是另一個世界出來的怪物",
            "§x§d§6§c§1§a§e真的難纏阿",
        ),
        *Utils.getMessage(unknowIcon,
            "$boss3NPC",
            "§x§d§6§c§1§a§e除了窩，旁邊的姿色高塌裡面",
            "§x§d§6§c§1§a§e還有個傢伙在籌備著呢",
            "§x§d§6§c§1§a§e他芎追不捨，就威了滅窩族人",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你還…還能說話?可是不都死透了嗎",
        ),
        *Utils.getMessage(unknowIcon,
            "$boss3NPC",
            "§x§d§6§c§1§a§e我很蒯就要裡開?",
            "§x§d§6§c§1§a§e忍淚，是窩低谷你?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你說高塔…難道旁邊那座…",
            "§x§d§6§c§1§a§e這麼一說…旁邊的高地竟然是浮空的!",
            "§x§d§6§c§1§a§e難道又是裂縫的力量?",
        ),
    )
    private val mes8 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e周圍的水晶…裡面透著的是甚麼啊?",
            "§x§d§6§c§1§a§e好像是…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e是人!?怎麼有那麼多人被受困在水晶裡面啊?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e也許一切得真相只有進到塔裡才能知道了",
        ),
    )
    private val mes9 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e前面那個是…露娜!?",
            "§x§d§6§c§1§a§e他怎麼會出現在這",
            "§x§d§6§c§1§a§e難道又是自己一個人…",
        ),
    )
    private val mes10 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e露娜!你怎麼會出現在這!",
            "§x§d§6§c§1§a§e這裡很危險啊!",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e要不要我帶你離開?",
        ),
        *Utils.getMessage(lunaIcon,
            "$lunaNPC",
            "§x§d§6§c§1§a§e你要進去高塔嗎?",
            "§x§d§6§c§1§a§e要進去得帶著一些東西進去喔",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e甚麼意思?帶一些東西…?",
        ),
        *Utils.getMessage(lunaIcon,
            "$lunaNPC",
            "§x§d§6§c§1§a§e要獻祭，才能進去",
            "§x§d§6§c§1§a§e爸爸不喜歡別人打擾",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e爸爸…!?塔裡的人是你的爸爸嗎?",
            "§x§d§6§c§1§a§e娜露娜你知道為甚麼你的爸爸會來到這嗎? ",
        ),
        *Utils.getMessage(lunaIcon,
            "$lunaNPC",
            "§x§d§6§c§1§a§e露娜…不知道 ",
            "§x§d§6§c§1§a§e爸爸和露娜說，要帶露娜來人界玩",
            "§x§d§6§c§1§a§e所以…露娜只知道這些",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e露娜，我得進去找你的爸爸聊聊",
            "§x§d§6§c§1§a§e可不可以告訴我要準備甚麼呢?",
        ),
        *Utils.getMessage(lunaIcon,
            "$lunaNPC",
            "§x§d§6§c§1§a§e要那個平原的黑色團團",
            "§x§d§6§c§1§a§e爸爸說那是塔裡重要的資源",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e露娜所指的應該是那個黑黑的異界產物吧…",
            "§x§d§6§c§1§a§e那還挺簡單的",
        ),
    )
    private val mes11 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這些夠嗎?露娜",
        ),
        *Utils.getMessage(lunaIcon,
            "$lunaNPC",
            "§x§d§6§c§1§a§e恩，夠了",
            "§x§d§6§c§1§a§e要小心爸爸",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我會的，謝謝你露娜",
        ),
    )
    init {
        cancelAble = false
        val world = Bukkit.getWorld("2k");
        val obj0 = ReachLevelObjective("obj0", 41)
            .setInstruction("§741等後將繼續任務");
        val obj1 = ListenNPCObjective(this,"obj1",170,*mes1)
            .setInstruction("§7與 &e梅露可 §7對話");
        val obj2Loc = Location(world,629.29,74.0,52.65);
        val obj2 = MoveToObjective(this,"obj2", obj2Loc,5.0)
            .setInstruction ("§7前往蛛菇沙源 629 74 52 §8(請開啟GPS追蹤目標位置)");
        val obj4 = ListenTalkObjective(this,"obj4",*mes2)
        val obj5 = KillMobsObjective(this,"obj5","蛛菇沙原_卜普人",30)
            .setInstruction { "擊殺 ${it.amount} / 30 隻卜普人" };
        val obj6 = ListenTalkObjective(this,"obj6",*mes3)
        val obj7Loc = Location(world,740.29,90.0,28.65);
        val obj7 = MoveToObjective(this,"obj7", obj7Loc,5.0)
            .setInstruction ("§7前往蛛菇沙源 740 90 28 §8(請開啟GPS追蹤目標位置)");
        val obj8 = ListenTalkObjective(this,"obj8",*mes4)
        val obj9= ListenNPCObjective(this,"obj9",204,*mes5)
            .setInstruction("§7與 &e露娜 §7對話");
        val obj10Loc = Location(world,658.29,78.0,-96.65);
        val obj10 = MoveToObjective(this,"obj10", obj7Loc,5.0)
            .setInstruction ("§7前往蛛菇沙源 658 78 -96 §8(請開啟GPS追蹤目標位置)");
        val obj11 = ListenTalkObjective(this,"obj11",*mes6)
        val obj12 = KillMobsObjective(this,"obj12","蛛菇沙原_卜普王",1)
            .setInstruction { "擊殺 ${it.amount} / 1 卜普王" };
        val obj13 = ListenTalkObjective(this,"obj13",*mes7)
        val obj14Loc = Location(world,816.29,93.0,-324.65);
        val obj14 = MoveToObjective(this,"obj14", obj14Loc,10.0)
            .setInstruction ("§7前往 816 93 -324  §8(請開啟GPS追蹤目標位置)");
        val obj15 = ListenTalkObjective(this,"obj15",*mes8)
        val obj16Loc = Location(world,873.29,104.0,-210.65);
        val obj16 = MoveToObjective(this,"obj16", obj16Loc,10.0)
            .setInstruction ("§7前往 873 104 -210  §8(請開啟GPS追蹤目標位置)");
        val obj17 = ListenTalkObjective(this,"obj17",*mes9)
        val obj18= ListenNPCObjective(this,"obj18",205,*mes10)
            .setInstruction("§7與 &e露娜 §7對話");
        val obj19 = GiveMultiItemObjective(this,"obj19",205,
            mapOf(
                "MATERIAL:M020" to 32,//蒼山金
            ))
            .setInstruction{"§7將 ${it.get("MATERIAL:M020")} / 32 個異界產物 交給 §e露娜"}
        val obj20= ListenNPCObjective(this,"obj20",205,*mes11)
            .setInstruction("§7與 &e露娜 §7對話");
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
        val obj7 = MoveToObjective(this,"obj7", obj7Loc,10.0)
            .setInstruction ("§7前往座標 191,78,261 §8(請開啟GPS追蹤目標位置)")
        val obj8 = ListenTalkObjective(this,"obj8",*mes6)
        val obj9Loc = Location(world,143.29,80.0,237.65);
        val obj9 = MoveToObjective(this,"obj9", obj9Loc,10.0)
            .setInstruction ("§7前往火光來源 143,80,237 §8(請開啟GPS追蹤目標位置)");
        val obj10 = ListenTalkObjective(this,"obj10",*mes7)
        val obj11 = ListenNPCObjective(this,"obj11",33,*mes8)
            .setInstruction("§7與 &e山德 §7對話");
        val obj12 = ListenNPCObjective(this,"obj12",170,*mes9)
            .setInstruction("§7與 &e梅露可 §7對話");*/
        pushObjective(obj0,obj1,obj2,obj4,obj5,obj6,obj7,obj8,obj9,obj10,obj11,obj12,obj13,obj14,obj15,obj16,obj17,obj18,obj19,obj20)

        addRewards(
            MoneyReward(1000), //錢幣
            QuestExpReward(400), //傭兵聲望
            ClassExpReward(2000),
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch8_01")
            Utils.command("lp user ${it.name} permission set main07.done")
        } )
    }
}


val quest = MainCh7_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



