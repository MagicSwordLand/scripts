@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.objectives.MoveToObjective
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location

val playerIcon = "%squests_icon%";
val NPCIcon = IconFonts.getNPC(59);

val questID = "side_copper_01"

class Side_Copper_01: Quest(questID,"§7[§b銅牌支線§7] §f盜賊據點"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPCName = "§c● §e梅里§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPCIcon,
            "$NPCName",
            "&x&d&6&d&0&b&8尊貴的傭兵你好",
            "&x&d&6&d&0&b&8諾亞鎮附近有盜賊駐紮的痕跡",
            "&x&d&6&d&0&b&8能否請你前去探查一番",
        ),
        *Utils.getMessage(NPCIcon,
            "$NPCName",
            "&x&d&6&d&0&b&8若有對諾亞鎮造成危害的跡象",
            "&x&d&6&d&0&b&8請幫忙剷除盜賊",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e知道了",
            "§x§d§6§c§1§a§e這點小事就交給我吧！",
        )
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這...",
            "§x§d§6§c§1§a§e帳篷...營火...",
            "§x§d§6§c§1§a§e每個盜賊身上都配有武器...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e看來的確是有想要侵略諾亞鎮的打算",
            "§x§d§6§c§1§a§e沒辦法了",
            "§x§d§6§c§1§a§e只好將盜賊們都剷除了！",
        )

    )
    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我完成委託了",
            "§x§d§6§c§1§a§e那群盜賊的確有打算入侵諾亞鎮",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我剷除一大半了",
            "§x§d§6§c§1§a§e但數量太多了無法全部剷除",
            "§x§d§6§c§1§a§e不過短時間他們應該是沒有餘力入侵了",
        ),
        *Utils.getMessage(NPCIcon,
            "$NPCName",
            "&x&d&6&d&0&b&8知道了，那這樣委託就算結束了",
            "&x&d&6&d&0&b&8這是你這次委託的獎勵",
        )
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",59,*mes1)
            .setInstruction("§7與 §e梅里 §7對話");
        val obj2 = MoveToObjective(this,"obj2",Location(world,-334.39, 74.00, 194.83),5.0)
            .setInstruction("§7前往 盜賊據點");
        val obj3 = ListenTalkObjective(this,"obj3",*mes2)
            .setInstruction("§7探查周遭的環境");
        val obj4 = KillMobsObjective(this,"obj4","盜賊據點_",50)
            .setInstruction { "擊殺 ${it.amount} / 50 個盜賊" };
        val obj5 = ListenNPCObjective(this,"obj5",59,*mes3)
            .setInstruction("§7與 §e梅里 §7對話");

        pushObjective(obj1,obj2,obj3,obj4,obj5)

        addRewards(
            MoneyReward(100), //30錢幣
            QuestExpReward(10), //15傭兵聲望
            ClassExpReward(200),
            MessageReward("&a➯ &7裝備: &7盜賊手套")
        )
        addEndHook({
            Utils.command("get ${it.name} HAND A001 1 0")
        })
    }
}


val quest = Side_Copper_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



