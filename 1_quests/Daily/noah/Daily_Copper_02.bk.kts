@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import jdk.jshell.execution.Util
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.quests.TimeLimitQuest
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.CommandReward
import net.brian.scriptedquests.rewards.MoneyReward
import net.brian.scriptedquests.rewards.QuestExpReward
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.entity.Player

val questID = "daily_copper_02"
fun command(line: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),line);
}

class Daily_Copper_02: TimeLimitQuest(questID,"§7[§a銅牌每日§7] §f清理洞穴"){

    private val playerName = "§c● §f%player_displayname%§r:";
    private val dayNPC = "§c● §e莉亞莫§r:";
    val npcIcon = IconFonts.getNPC(16);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8尊敬的傭兵你好",
            "&x&d&6&d&0&b&8近期水窟洞的魔物生成過快",
            "&x&d&6&d&0&b&8導致許多新進傭兵無法正常訓練",
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8能稍微幫我清除一些魔物嗎?",
        ),
        *Utils.getMessage(playerIcon,"$playerName:","&x&d&6&c&1&a&e沒問題,就交給我了"),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8尊敬的傭兵你好",
            "&x&d&6&d&0&b&8好的,完成委託後回來找我即可")
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,"$playerName:","&x&d&6&c&1&a&e我完成委託了"),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8是的傭兵,經過確認你已經完成委託了",
            "&x&d&6&d&0&b&8這是委託的獎勵",)
    )


    init {

        val obj1 = ListenTalkObjective(this,"obj1",*mes1)
            .setInstruction("§7與 §e莉亞莫 §7對話");

        val obj2 = KillMultiMobsObjective(this,"obj2", mapOf(
            "水窟洞_小樹妖" to 5,
            "水窟洞_小蠕蟲" to 5,
            "水窟洞_樹藤殭屍" to 5))
            .setInstruction{ "擊殺小樹妖 ${it.get("水窟洞_小樹妖")}/5、樹藤殭屍 ${it.get("水窟洞_樹藤殭屍")}/5、小蠕蟲 ${it.get("水窟洞_小蠕蟲")}/5"}

        val obj3 = ListenNPCObjective(this,"obj3",16,*mes2)
            .setInstruction("§7與 §e莉亞莫 §7對話")

        pushObjective(obj1,obj2,obj3)
        addRewards(
            MoneyReward(15), //30錢幣
            QuestExpReward(8), //15傭兵聲望
            ClassExpReward(100),
        )
    }
}

val quest = Daily_Copper_02();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}