@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location

val playerIcon = "%squests_icon%";
val NPC1Icon = IconFonts.getNPC(4);

val questID = "side_silver_02"

class Side_Silver_02: Quest(questID,"§7[§b銀牌支線§7] §f石像鬼的侵擾"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e那莫斯克§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8欸欸,你有空嗎?",
            "&x&d&6&d&0&b&8我剛剛收到一個銀牌委託",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8但我突然有更重要的委託",
            "&x&d&6&d&0&b&8能不能把這個委託轉交給你?",
            "&x&d&6&d&0&b&8我這裡也會提供一些補償給你",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e是甚麼委託?",
            "§x§d§6§c§1§a§e會不會很危險?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8這委託需要調查低語森林的情況",
            "&x&d&6&d&0&b&8過去因為某些原因",
            "&x&d&6&d&0&b&8那邊一直存在各種鬼怪",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8原本不會影響到我們",
            "&x&d&6&d&0&b&8但最近那些鬼怪開始往外擴散了",
            "&x&d&6&d&0&b&8尤其是人形的石像鬼",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8公會那邊開始重視這個問題",
            "&x&d&6&d&0&b&8因此這委託內容是需要擊殺40個石像鬼",
            "&x&d&6&d&0&b&8就交給你了！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e蝦?",
            "§x§d§6§c§1§a§e等等！",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8(跑走...)",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8太棒了！你完成委託了！",
            "&x&d&6&d&0&b&8太感謝了，你等我一下喔",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e欸",
            "§x§d§6§c§1§a§e等等",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我剛剛去回報委託了",
            "&x&d&6&d&0&b&8這邊有公會的委託獎勵",
            "&x&d&6&d&0&b&8我自己再加一點給你",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e痾..好吧",
            "§x§d§6§c§1§a§e算了,就這樣吧",
        ),
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",4,*mes1)
            .setInstruction("§7與 §e那莫斯克 §7對話");
        val obj2 = KillMobsObjective(this,"obj2","低語森林_石像鬼",40)
        .setInstruction { "擊殺 ${it.amount}/40 隻石像鬼" }
        val obj3 = ListenNPCObjective(this,"obj3",4,*mes2)
            .setInstruction("§7與 §e那莫斯克 §7對話");
        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(150), //30錢幣
            QuestExpReward(40), //15傭兵聲
            ClassExpReward(300)
        )
    }
}


val quest = Side_Silver_02();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



