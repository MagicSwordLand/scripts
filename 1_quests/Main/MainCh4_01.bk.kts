@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.classcore.ReachLevelObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location




val questID = "main_ch4_01"

class MainCh4_01: Quest(questID,"§7[§c主線委託§7] §f第四章-消失的團員"){


    val playerIcon = "%squests_icon%";
    val deleIcon = IconFonts.getNPC(47);
    val casIcon = IconFonts.getNPC(54);
    val maylukIcon = IconFonts.getNPC(170);
    val sandIcon = IconFonts.getNPC(33)
    val unknowIcon = IconFonts.getNPC(29);


    private val playerName = "§c● §f%player_displayname%§r:";
    private val deleNPC = "§c● §e迪利§r:";
    private val casNPC = "§c● §e卡司§r:";
    private val mayluklNPC = "§c● §e梅露可§r:";
    private val sandNPC = "§c● §e山德§r:";
    private val unknowNPC = "§c● §e未知聲音§r:"

    private val mes1 = arrayOf(
        *Utils.getMessage(casIcon,
            "$casNPC",
            "§x§d§6§c§1§a§e你是誰?能否告知來意",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我是來自諾亞鎮的傭兵",
            "§x§d§6§c§1§a§e我是來找城主的 ",
            "§x§d§6§c§1§a§e(出示傭兵證明)",
        ),
        *Utils.getMessage(casIcon,
            "$casNPC",
            "§x§d§6§c§1§a§e了解，歡迎來到阿納斯塔城",
            "§x§d§6§c§1§a§e城主就在港口附近",
            "§x§d§6§c§1§a§e我們就不送行了",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e請問…您就是阿納斯塔城的城主吧",
            "§x§d§6§c§1§a§e我是來自諾亞鎮的傭兵",
            "§x§d§6§c§1§a§e受尤爾達先生之託來找您",
        ),
        *Utils.getMessage(deleIcon,
            "$deleNPC",
            "§x§d§6§c§1§a§e尤爾達那傢伙真的是",
            "§x§d§6§c§1§a§e到處跑都不知道回來報平安",
        ),
        *Utils.getMessage(deleIcon,
            "$deleNPC",
            "§x§d§6§c§1§a§e對了，尤爾達是讓你來叫我聯絡他回來的吧",
            "§x§d§6§c§1§a§e果然還是那麼傲驕",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e原來…尤爾達先生也是阿納斯塔城的居民阿",
            "§x§d§6§c§1§a§e本以為他和我一樣來自無人知曉的小鎮呢",
        ),
        *Utils.getMessage(deleIcon,
            "$deleNPC",
            "§x§d§6§c§1§a§e這麼說來，你不是諾亞的原住民囉? ",
            "§x§d§6§c§1§a§e那麼你是從哪裡過來的?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我來自於守護者村莊 ",
            "§x§d§6§c§1§a§e但都與世人隔閡所以對外面一無所知",
        ),
        *Utils.getMessage(deleIcon,
            "$deleNPC",
            "§x§d§6§c§1§a§e你是從那來的啊，阿納斯塔很歡迎你喔",
            "§x§d§6§c§1§a§e你還沒去阿納斯塔的傭兵基地報到吧? ",
            "§x§d§6§c§1§a§e就在旁邊而已，和梅露可接待員打個招呼就好",
        ),
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e您好，我是來自諾亞鎮的傭兵",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e好的，我是梅露可，是這座傭兵基地的負責人",
            "§x§d§6§c§1§a§e也是傭兵接待員",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我想問…怎麼感覺阿納斯塔的傭兵那麼少阿?",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e是這樣的…由於近期周遭怪事頻傳",
            "§x§d§6§c§1§a§e怪物也越來越兇惡",
            "§x§d§6§c§1§a§e導致大量傭兵不敢前來",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e原來如此…那…我能否嘗試看看呢?",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e真的嗎?",
            "§x§d§6§c§1§a§e焱恪先生果然沒有看錯人",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e那能否先幫助鎮上的考古團隊找回遺失的寶物",
            "§x§d§6§c§1§a§e還有消失的團員嗎?",
            "§x§d§6§c§1§a§e這件事導致鎮上知名的考古團重創",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e不知道為甚麼聽起來那麼詭異…",
            "§x§d§6§c§1§a§e不過我就試試看吧!",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e細節的話還得請你去和考古團隊長了解了 ",
        ),
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e您是山德先生嗎?",
            "§x§d§6§c§1§a§e我是接手您任務的傭兵",
        ),
        *Utils.getMessage(sandIcon,
            "$sandNPC",
            "§x§d§6§c§1§a§e唉…來多少人都是一樣的…",
            "§x§d§6§c§1§a§e我那些隊員…恐怕也都是凶多吉少了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這…能不能和我說說到底發生了甚麼",
            "§x§d§6§c§1§a§e無論如何我也會盡力完成的",
        ),
        *Utils.getMessage(sandIcon,
            "$sandNPC",
            "§x§d§6§c§1§a§e唉…事情是這樣的…",
            "§x§d§6§c§1§a§e我們帶領的遺跡考古隊在穹頂沙淵",
            "§x§d§6§c§1§a§e遇到一些意外事件",
        ),
        *Utils.getMessage(sandIcon,
            "$sandNPC",
            "§x§d§6§c§1§a§e天色不佳，又起沙塵暴",
            "§x§d§6§c§1§a§e在不知不覺間總有隊員感覺",
            "§x§d§6§c§1§a§e被甚麼東西搭肩",
        ),
        *Utils.getMessage(sandIcon,
            "$sandNPC",
            "§x§d§6§c§1§a§e直到天氣變好",
            "§x§d§6§c§1§a§e大家也心神不寧的開啟了考古工作",
            "§x§d§6§c§1§a§e但…在我回頭後大家卻突然消失了",
        ),
        *Utils.getMessage(sandIcon,
            "$sandNPC",
            "§x§d§6§c§1§a§e就連帶來的工具也是…都消失了",
            "§x§d§6§c§1§a§e只有幾位提早離開的團員還在村子裡…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e總感覺…聽起來更像靈異故事",
            "§x§d§6§c§1§a§e雖然從那走來就總有種怪怪的感覺…",
            "§x§d§6§c§1§a§e不過還是得實際探查才知道呢",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e就等我的好消息吧",
            "§x§d§6§c§1§a§e我不會讓你們失望的",
        ),
    )
    private val mes5 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e又回來這條路了",
            "§x§d§6§c§1§a§e雖然不知道山德大叔是在哪一條出事的",
            "§x§d§6§c§1§a§e總之都走走看就知道了",
        ),
    )
    private val mes6 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e前面有火光!",
            "§x§d§6§c§1§a§e原來在這條路…怪不得來的時候沒有看到",
            "§x§d§6§c§1§a§e雖然不知道是不是，去看看有沒有線索好了",
        ),
    )
    private val mes7 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e好奇怪，這裡居然只是堵牆",
            "§x§d§6§c§1§a§e就沒有甚麼重要的東西遺落嗎…?",
        ),
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e外面的!外面有人對吧!",
            "§x§d§6§c§1§a§e我在這個裡面!",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e(驚訝)",
            "§x§d§6§c§1§a§e是誰?哪裡來的聲音? ",
        ),
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e我們是阿納斯塔考古團的團員",
            "§x§d§6§c§1§a§e我們原先只是在進行考古",
            "§x§d§6§c§1§a§e突然就被吸入洞口了，牆壁也隨之掉落",

            ),
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e但是這堵牆太堅硬了，我們無法突破…",
            "§x§d§6§c§1§a§e貌似也無法在推動了 ",
            "§x§d§6§c§1§a§e但是貌似還有路能夠前進 ",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我這就回阿納斯塔幫你們找支援",
            "§x§d§6§c§1§a§e千萬不要離開這裡! ",
        ),
    )
    private val mes8 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e山德先生!",
            "§x§d§6§c§1§a§e我找到您的團員了!",
            "§x§d§6§c§1§a§e只是… ",
        ),
        *Utils.getMessage(sandIcon,
            "$sandNPC",
            "§x§d§6§c§1§a§e只是甚麼?快說阿!",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e他們被困在一睹堅固的牆後",
            "§x§d§6§c§1§a§e貌似無法輕易突破",
            "§x§d§6§c§1§a§e不過他們有說，還有路能夠前進，所以...",
        ),
        *Utils.getMessage(sandIcon,
            "$sandNPC",
            "§x§d§6§c§1§a§e是嗎…那這件事就謝謝你了",
            "§x§d§6§c§1§a§e剩下交給我就行，可以去回報委託了",
            "§x§d§6§c§1§a§e抱歉對你那麼兇",
        ),
    )
    private val mes9 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e梅露可先生",
            "§x§d§6§c§1§a§e我已經完成委託了!",
            "§x§d§6§c§1§a§e山德先生說剩下的交給他就行",
        ),
        *Utils.getMessage(maylukIcon,
            "$mayluklNPC",
            "§x§d§6§c§1§a§e好的，山德先生也都跟我說了",
            "§x§d§6§c§1§a§e這些就是獎勵 ",
        ),
    )
    init {
        cancelAble = false
        val world = Bukkit.getWorld("2k");
        val obj0 = ReachLevelObjective("obj0", 20)
            .setInstruction("§720等後將繼續任務");
        val obj1 = ListenNPCObjective(this,"obj1",168,*mes1)
            .setInstruction("§7與 §e卡司 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",47,*mes2)
            .setInstruction("§7與 §e迪利 §7對話");
        val obj3 = ListenNPCObjective(this,"obj3",170,*mes3)
            .setInstruction("§7與 §e梅露可 §7對話");
        val obj4 = ListenNPCObjective(this,"obj4",33,*mes4)
            .setInstruction("§7與 §e山德 §7對話");
        val obj5Loc = Location(world,268.29,79.0,263.65);
        val obj5 = MoveToObjective(this,"obj5", obj5Loc,5.0)
            .setInstruction ("§7前往穹頂沙淵 268,79,263 §8(請開啟GPS追蹤目標位置)");
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
            .setInstruction("§7與 §e梅露可 §7對話");
        pushObjective(obj0,obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10,obj11,obj12)

        addRewards(
            MoneyReward(300), //錢幣
            QuestExpReward(150), //傭兵聲望
            ClassExpReward(500),
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch4_02")
        } )
    }
}


val quest = MainCh4_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



