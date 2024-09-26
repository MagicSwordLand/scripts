@file:RequiredPlugins("ScriptedQuests")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.player.PlayerCompleter
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import net.brian.scriptedquests.objectives.ConversationObjective
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

val questID = "daily_copper_09"
fun command(line: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),line);
}

class Daily_Copper_09: TimeLimitQuest(questID,"§7[§a銅牌每日§7] §f缺錢的賈爾"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val dayNPC = "§c● §e賈爾§r:";
    val npcIcon = IconFonts.getNPC(109);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8欸欸欸！等等你別走",
            "&x&d&6&d&0&b&8那個...我是個賞金獵人",
            "&x&d&6&d&0&b&8專門抓捕怪物販賣",
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8雖然是違法的...但錢真的很多...",
            "&x&d&6&d&0&b&8不過最近傭兵公會開始嚴厲清查了...",
            "&x&d&6&d&0&b&8我...我已經沒有錢吃飯了...",
        ),
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8能...",
            "&x&d&6&d&0&b&8給我一點錢去買東西吃嗎...",
        )
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(npcIcon,
            "$dayNPC",
            "&x&d&6&d&0&b&8太感謝你了！",
            "&x&d&6&d&0&b&8今天終於有東西可以吃了...",
        )
    )


    init {

        val obj1 = ListenNPCObjective(this,"obj1",109,*mes1)
            .setInstruction("§7與 §e賈爾 §7對話")

        val obj2 = ConversationObjective("obj2",109){
            NPCQuestion(*Utils.getMessage(npcIcon,
                "$dayNPC",
                "§7拜託了！","§7給我一點點錢就好...")).addPlayerOptions(
                PlayerOption("§7[§a對話選項§7] §f好吧...這些錢給你 §8[給賈爾20$]")
                    .addConditions ({  Utils.getDouble(it,"%cmi_user_balance%") >= 20 })
                    .setResult { player-> it.finish(PlayerCompleter.getInstance(player)) },
                PlayerOption("&7[&a對話選項&7] &f我自己也沒多少錢呢！")
                    .setResult { player -> player.sendMessage(*Utils.getMessage(npcIcon,
                        "$dayNPC",
                        "§x§d§6§d§0§b§8等等別走阿！",
                        "§x§d§6§d§0§b§8我真的好餓...",
                    )
                    )})
        }
            .setStartInstantly(false);
        obj2.setInstruction("§7與 §e賈爾 §7對話並選擇選項")
            .setEndProcess {
                it.sendMessage(*Utils.getMessage(npcIcon,
                    "$dayNPC",
                    "§x§d§6§d§0§b§8太感謝你了！",
                    "§x§d§6§d§0§b§8今天終於有東西可以吃了...",
                ))
                Utils.command("money take ${it.name} 20 -s")
            }
        pushObjective(obj1,obj2);
        addRewards(
            ClassExpReward(20),
            QuestExpReward(10) //傭兵聲望
        )
    }
}

val quest = Daily_Copper_09();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}