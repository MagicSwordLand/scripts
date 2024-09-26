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
val NPC1Icon = IconFonts.getNPC(6);

val questID = "side_copper_05"

class Side_Copper_05: Quest(questID,"§7[§b銅牌支線§7] §f肯特的委託"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e肯特§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8哎呀，這把劍還差一點素材阿..",
            "&x&d&6&d&0&b&8沒想到比我預想的還耗素材",
            "&x&d&6&d&0&b&8欸！你來的剛好",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8麻煩你了",
            "&x&d&6&d&0&b&8能去幫我收集一些素材嗎?",
            "&x&d&6&d&0&b&8我會給你酬勞的！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e痾..好吧",
            "§x§d§6§c§1§a§e你需要甚麼素材?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我需要...",
            "&x&d&6&d&0&b&832個鑌礦、16個惡沼花、24個受感染的骸骨",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e包在我身上！",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8太好了！",
            "&x&d&6&d&0&b&8這樣我就能完成這把劍了",
            "&x&d&6&d&0&b&8這是你的酬勞，拿去吧！",
        )
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",6,*mes1)
            .setInstruction("§7與 §e肯特 §7對話");
        val obj2 = GiveMultiItemObjective(this,"obj2",6,
            mapOf(
                "COLLECTMATERIAL:M001" to 32,
                "COLLECTMATERIAL:M033" to 16,
                "MATERIAL:M003" to 24,
            ))
            .setInstruction{"§7將 §7${it.get("COLLECTMATERIAL:M001")}/32 個鑌礦、§7${it.get("COLLECTMATERIAL:M033")}/16 個惡沼花、§7${it.get("MATERIAL:M003")}/24 個受感染的骸骨 §7交給 §7與 §e肯特§r"}
        val obj3 = ListenNPCObjective(this,"obj3",6,*mes2)
            .setInstruction("§7與 §e肯特 §7對話");

        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(30), //30錢幣
            ClassExpReward(200),
            QuestExpReward(12), //15傭兵聲
            MessageReward("&a➯ &7稱號: §7[§f\uE055§x§e§4§d§7§d§1不擅長拒絕請求的§f\uE055§7]")
        )
        addEndHook({
            Utils.command("lp user ${it.name} permission set ps.suffix.10")
        })
    }
}


val quest = Side_Copper_05();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



