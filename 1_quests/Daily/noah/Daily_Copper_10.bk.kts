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

val questID = "daily_copper_10"
fun command(line: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),line);
}

class Daily_Copper_10: TimeLimitQuest(questID,"§7[§a銅牌每日§7] §f盧克的困擾"){
    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e盧克§r:";
    private val cat = "§c● §e黑貓§r:";
    val NPC1Icon = IconFonts.getNPC(30);
    val playerIcon = "%squests_icon%";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我家老婆養的一隻黑貓在前幾天",
            "&x&d&6&d&0&b&8從陽台跳走後就不見了......",
            "&x&d&6&d&0&b&8你能幫我找到牠嗎?牠應該還在諾亞鎮內",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e好我盡我所能!",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e終於找到了!"
        ),
        "$cat 喵~",
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e快點跟我回家吧盧克很擔心你!"
        ),
        "$cat 喵~喵~",
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8嗯?冒險者你找到我老婆的黑貓了嗎",
            "&x&d&6&d&0&b&8對!就是他非常謝謝你的幫助",
            "&x&d&6&d&0&b&8這是你的酬勞",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8小聲說(看來晚上不用跪算盤了~",
        )
    )

    init {

        val obj1 = ConversationObjective("obj1",84){

            NPCQuestion(*Utils.getMessage(NPC1Icon,
                "$NPC1",
                "§7這位傭兵","§7你是來買東西的嗎?","§7我這裡有個委託你有興趣嗎?")).addPlayerOptions(
                PlayerOption("§7[§a對話選項§7] §f甚麼委託?")
                    .setResult { player -> it.finish(PlayerCompleter.getInstance(player)) },
                PlayerOption("&7[&a對話選項&7] &f我是來買東西的")
                    .setResult { player-> player.sendMessage(*Utils.getMessage(NPC1Icon,
                        "$NPC1",
                        "§x§d§6§d§0§b§8好吧...",
                        "§x§d§6§d§0§b§8慢慢看喔...",
                    )
                    )})
        }
            .setStartInstantly(false);
        obj1.setInstruction("§7與 §e盧克 §7對話並選擇選項")
        val obj2 = ListenNPCObjective(this,"obj2",84,*mes1)
            .setInstruction("§7與 §e盧克 §7對話")
        val obj3 = ListenNPCObjective(this,"obj3",165,*mes2)
            .setInstruction("§7找到黑貓")
        val obj4 = ListenNPCObjective(this,"obj4",84,*mes3)
            .setInstruction("§7與 §e盧克 §7對話")
        pushObjective(obj1,obj2,obj3,obj4);
        addRewards(
            MoneyReward(15), //錢幣
            ClassExpReward(80),
            QuestExpReward(10) //傭兵聲望
        )
    }
}

val quest = Daily_Copper_10();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}