@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.quests.TimeLimitQuest
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.MoneyReward
import net.brian.scriptedquests.rewards.QuestExpReward
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val questID = "daily_copper_06"
fun command(line: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),line);
}

class Daily_Copper_06: TimeLimitQuest(questID,"§7[§a銅牌每日§7] §f跑腿傭兵"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val dayNPC = "§c● &e莉亞莫§r:";
    private val dayNPCA = "§c● &e克斯爾§r:";
    private val dayNPCB = "§c● &e亞頓§r:";
    private val dayNPCC = "§c● &e厄特摩§r:";
    private val shopNPC = "§c● &e麵寶§r:";
    val npcIcon = IconFonts.getNPC(16);
    val npcIconA = IconFonts.getNPC(53);
    val npcIconB = IconFonts.getNPC(52);
    val npcIconC = IconFonts.getNPC(54);
    val npcIconshopc = IconFonts.getNPC(66);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8尊敬的傭兵你好",
            "&x&d&6&d&0&b&8傭兵公會有一個委託",
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8幫忙去烘焙店拿守衛們的食物",
            "&x&d&6&d&0&b&8並分給駐守在公會的三個守衛",
            "&x&d&6&d&0&b&8你要接受這個委託嗎?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName:",
            "&x&d&6&c&1&a&e沒問題,就交給我了"
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8好的,完成委託後回來找我即可",
        )
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName:",
            "&x&d&6&c&1&a&e你好,我是來拿傭兵公會訂購的食物"
        ),
        *Utils.getMessage(npcIconshopc,
            "$shopNPC",
            "&x&d&6&d&0&b&8阿！稍等我一下喔",
            "&x&d&6&d&0&b&8這些就是",
            "&x&d&6&d&0&b&8&e(獲得守衛們的食物)",
        )
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName:",
            "&x&d&6&c&1&a&e這是傭兵公會分配的食物"
        ),
        *Utils.getMessage(npcIconA,
            "$dayNPCA",
            "&x&d&6&d&0&b&8謝謝,我餓死了",
        )
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName:",
            "&x&d&6&c&1&a&e這是傭兵公會分配的食物"
        ),
        *Utils.getMessage(npcIconB,
            "$dayNPCB",
            "&x&d&6&d&0&b&8謝啦",
        )
    )
    private val mes5 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName:",
            "&x&d&6&c&1&a&e這是傭兵公會分配的食物"
        ),
        *Utils.getMessage(npcIconC,
            "$dayNPCC",
            "&x&d&6&d&0&b&8放著就好",
        )
    )
    private val mes6 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName:",
            "&x&d&6&c&1&a&e我完成委託了"
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8是的傭兵",
            "&x&d&6&d&0&b&8經過確認你已經完成委託了",
            "&x&d&6&d&0&b&8這是委託的獎勵",
        )
    )

    init {

        val obj1 = ListenTalkObjective(this,"obj1",*mes1)
            .setInstruction("§7與 §e莉亞莫 §7對話");

        val obj2 = ListenNPCObjective(this,"obj2",66,*mes2)
            .setInstruction("§7與 §e麵寶 §7對話")

        val obj3 = ListenNPCObjective(this,"obj3",53,*mes3)
            .setInstruction("§7與 §e克斯爾 §7對話")

        val obj4 = ListenNPCObjective(this,"obj4",52,*mes4)
            .setInstruction("§7與 §e亞頓 §7對話")

        val obj5 = ListenNPCObjective(this,"obj5",54,*mes5)
            .setInstruction("§7與 §e厄特摩 §7對話")

        val obj6 = ListenNPCObjective(this,"obj6",16,*mes6)
            .setInstruction("§7與 §e莉亞莫 §7對話")

        pushObjective(obj1,obj2,obj3,obj4,obj5,obj6);
        addRewards(
            MoneyReward(10), //錢幣
            ClassExpReward(100),
            QuestExpReward(8) //傭兵聲望
        )
    }
}

val quest = Daily_Copper_06();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}