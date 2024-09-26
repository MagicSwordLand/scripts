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
import net.brian.scriptedquests.rewards.MessageReward
import net.brian.scriptedquests.rewards.MoneyReward
import net.brian.scriptedquests.rewards.QuestExpReward
import net.brian.scriptedquests.utils.Utils

val questID = "daily_hunt_01"

class Daily_Hunt_01: TimeLimitQuest(questID,"§7[§a每日懸賞§7] §f煩人的小樹妖"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val dayNPC = "§c● §e莉亞莫§r:";
    val npcIcon = IconFonts.getNPC(16);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e擊殺煩人的小樹精...",
            "&x&d&6&c&1&a&e看起來是個挺簡單的懸賞",
            "&x&d&6&c&1&a&e就決定是這個了",
        ),
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "&x&d&6&c&1&a&e我是來回報這個懸賞委託的",
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8好的，已確認完成委託",
            "&x&d&6&d&0&b&8這些是這次委託的獎勵",
        )
    )


    init {

        val obj1 = ListenTalkObjective(this,"obj1",*mes1)
            .setInstruction("§7查看選賞內容");

        val obj2 = KillMultiMobsObjective(this,"obj2", mapOf(
            "水窟洞_小樹妖" to 10,))
            .setInstruction{ "擊殺小樹妖 ${it.get("水窟洞_小樹妖")}/10"}

        val obj3 = ListenNPCObjective(this,"obj3",16,*mes2)
            .setInstruction("§7與 §e莉亞莫 §7對話")

        pushObjective(obj1,obj2,obj3);
        addRewards(
            MoneyReward(20), //30錢幣
            ClassExpReward(30),
            QuestExpReward(5), //15傭兵聲望
            MessageReward("&a➯ &7古銀: &7+1"),
        )
        addEndHook({
            Utils.command("gamepoints add ${it.name} 1")
        })
    }

}
val quest = Daily_Hunt_01();
ScriptedQuests.getInstance().questManager.register(quest);
fun onDispose(){
    quest.unregister();
}