@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player

val questID = "side_gold_13"

class Side_Gold_13: Quest(questID,"§7[§b金牌支線§7] §f沙原部落的異常"){


    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(203);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e阿麥菈§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8沙原部落一直以來",
            "&x&d&6&d&0&b&8以綠洲草原的沙漠烈鳥君臨天下。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e然而除了抓其他動物來吃，",
            "§x§d§6§c§1§a§e或是領土爭奪的情況外",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8不會無意義的攻擊其他生物。",
            "&x&d&6&d&0&b&8而且還是這麼頻繁的...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e現在牠們的行動有些異常。",
            "§x§d§6§c§1§a§e不是為了打獵，而是拼命殺死其他動物。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8如果你有調查到關於沙原部落的事情，",
            "&x&d&6&d&0&b&8請務必告訴我們。",
        ),
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8謝謝。好像獲得很大的線索了。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8持續調查沙原部落，",
            "&x&d&6&d&0&b&8是因為何種勢力才那麼猖狂。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e但是在找到原因並解決之前，",
            "§x§d§6§c§1§a§e不能眼睜睜看著他們暴走。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8稍有差池，",
            "&x&d&6&d&0&b&8說不定整個綠洲生態就會崩壞。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8傭兵冒險家啊！",
            "&x&d&6&d&0&b&8請幫忙阻止猖獗的沙原部落。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8務必要小心。不能小看對方。",
        ),
    )

    private val mes3 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我一直關注著你的表現。",
            "&x&d&6&d&0&b&8了不起的實力！",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8綠洲草原的幼鳥",
            "&x&d&6&d&0&b&8是過著群居生活的草食動物。",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e主要吃植物或 ",
            "§x§d§6§c§1§a§e和一樣小的果實。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8不過，最近沙原幼鳥的數量劇增。",
            "&x&d&6&d&0&b&8沙原部落四處殺害幼鳥，",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8對部落的人而言，",
            "&x&d&6&d&0&b&8整個草原四處都是食物。",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e他們的數量一增加， ",
            "§x§d§6§c§1§a§e幼鳥和植物的數量就逐漸減少。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8就算採取人為措施，",
            "&x&d&6&d&0&b&8也要阻止沙原綠洲的平衡受到破壞。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8冒險家啊",
            "&x&d&6&d&0&b&8請你阻止沙原部落的捕食。",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "§x§d§6§c§1§a§e沙原部落總是集體行動。 ",
            "§x§d§6§c§1§a§e要特別小心原隱雙刃。",
        ),
    )

    private val mes4 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8以後還要見多少血呢..真心痛。",
        ),
    )


    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",203,*mes1)
            .setInstruction("§7與 §e阿麥菈 §7對話");
        val obj2 = MoveToObjective(this,"obj2",Location(world,524.00, 100.00, 274.00),5.0)
            .setInstruction("§7調查到關於沙原部落的事情")
            .setEndProcess {
                it.sendMessage("§7")
                it.sendMessage("§7[§a任務提示§7] §c● §f%player_displayname%§r: §f到底發生什麼事了...情況比想像中的還嚴重。")
                it.sendMessage("§7[§a任務提示§7] §c● §f%player_displayname%§r: §f彷彿要逼到盡頭的必殺攻擊...")
                it.sendMessage("§7")}
        val obj3 = KillMultiMobsObjective(this,"obj3", mapOf("沙原綠洲_" to 32))
            .setInstruction{ "§7擊殺  ${it.get("沙原綠洲_")}/32 個沙原綠洲部落的族民"};
        val obj4 = ListenNPCObjective(this,"obj4",203,*mes2)
            .setInstruction("§7與 §e阿麥菈 §7對話");
        val obj5 = KillMultiMobsObjective(this,"obj5", mapOf("沙原綠洲_" to 128))
            .setInstruction{ "§7擊殺  ${it.get("沙原綠洲_")}/128 個沙原綠洲部落的族民"};
        val obj6 = ListenNPCObjective(this,"obj6",203,*mes3)
            .setInstruction("§7與 §e阿麥菈 §7對話");
        val obj7 = KillMultiMobsObjective(this,"obj7", mapOf("沙原綠洲_原隱雙刃" to 3))
            .setInstruction{ "§7擊殺  ${it.get("沙原綠洲_原隱雙刃")}/3 個沙原綠洲的原隱雙刃"};
        val obj8 = ListenNPCObjective(this,"obj8",203,*mes4)
            .setInstruction("§7與 §e阿麥菈 §7對話");

        pushObjective(obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8)

        addRewards(
            MoneyReward(600), //金牌 300~800
            QuestExpReward(100), //金牌 100~200
            ClassExpReward(500) //金牌 300~500
        )
    }
}


val quest = Side_Gold_13();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



