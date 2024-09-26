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
import net.brian.scriptedquests.rewards.*
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import net.brian.scriptedquests.utils.Utils

val questID = "daily_copper_03"
fun command(line: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),line);
}

class Daily_Copper_03: TimeLimitQuest(questID,"§7[§a銅牌每日§7] §f收割稻草"){
    private val playerName = "§c● §f%player_displayname%:&x&d&6&c&1&a&e";
    private val dayNPC = "§c● §e莉亞莫:&x&d&6&d&0&b&8";
    val npcIcon = IconFonts.getNPC(16);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8尊敬的傭兵你好",
            "&x&d&6&d&0&b&8諾亞村附近有個稻草田",
            "&x&d&6&d&0&b&8因為缺少人員管理",
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

        val obj2 = GiveItemObjective(this,"obj2",16,"COLLECTMATERIAL:M032",64)
            .setInstruction { "收割 ${it.amount} / 64 個 稻草並交給莉亞莫" }

        val obj3 = ListenNPCObjective(this,"obj3",16,*mes2)
            .setInstruction("§7與 §e莉亞莫 §7對話")

        pushObjective(obj1,obj2,obj3);

        addRewards(
            MoneyReward(15), //錢幣
            QuestExpReward(8), //傭兵聲望
            ClassExpReward(100),
        )
    }
}

val quest = Daily_Copper_03();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}