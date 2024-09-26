@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.classcore.ReachLevelObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player




val questID = "main_ch3_02"

class MainCh3_02: Quest(questID,"§7[§c主線委託§7] §f第三章-迷霧重重"){


    val playerIcon = "%squests_icon%";
    val calitmanIcon = IconFonts.getNPC(157);
    val alielIcon = IconFonts.getNPC(123);
    val alisterIcon = IconFonts.getNPC(129);
    val yodaIcon = IconFonts.getNPC(97);
    val crayliaIcon = IconFonts.getNPC(105);
    val ruburgIcon = IconFonts.getNPC(158);
    val ruviaIcon = IconFonts.getNPC(159);
    val sizIcon = IconFonts.getNPC(160);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val calitmanNPC = "§c● §e卡里特曼§r:";
    private val alielNPC = "§c● §e艾黎爾§r:";
    private val alisterlNPC = "§c● §e亞里士德§r:";
    private val yodaNPC = "§c● §e尤爾達§r:";
    private val crayliaNPC ="§c● §e克雷利亞§r: ";
    private val ruburgNPC ="§c● §e 魯柏格§r: ";
    private val ruviaNPC ="§c● §e 魯維亞§r: ";
    private val sizNPC ="§c● §e 西斯§r: ";

    private val mes1 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e卡特先生，請問..你能否告訴我一些線索 ",
            "§x§d§6§c§1§a§e關於這片洞穴的…線索",
        ),
        *Utils.getMessage(calitmanIcon,
            "$calitmanNPC",
            "§x§d§6§c§1§a§e不瞞你說，自我到這片大陸以來",
            "§x§d§6§c§1§a§e就總覺得這片大陸的不平凡",
            "§x§d§6§c§1§a§e但更多的…還是得問亞里士德博士",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e請問…您就是亞里士德博士對吧",
        ),
        *Utils.getMessage(alisterIcon,
            "$alisterlNPC",
            "§x§d§6§c§1§a§e你是誰啊?新來的傭兵嗎?",
            "§x§d§6§c§1§a§e怎麼會突然找上我?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e想請問您一些關於艾靈一族的事",
        ),
        *Utils.getMessage(alisterIcon,
            "$alisterlNPC",
            "§x§d§6§c§1§a§e是誰告訴你的(警惕)!?",
            "§x§d§6§c§1§a§e在這座鎮子裡，千萬別提起他們",
        ),
        *Utils.getMessage(alisterIcon,
            "$alisterlNPC",
            "§x§d§6§c§1§a§e一切等你到了阿納斯塔城就會有答案了",
            "§x§d§6§c§1§a§e就在阿納斯塔城最高的塔樓裡 ",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e知道了…",
            "§x§d§6§c§1§a§e啊!完蛋了，顧著處理這事情，都忘了解藥的事!",
        ),
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(crayliaIcon,
            "$crayliaNPC",
            "§x§d§6§c§1§a§e你可算回來了 ",
            "§x§d§6§c§1§a§e多虧我這還有一些抑制劑，免於了擴散 ",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e嘿嘿…路途中出了些意外",
            "§x§d§6§c§1§a§e來，這是淨化粉末",
        ),
        *Utils.getMessage(crayliaIcon,
            "$crayliaNPC",
            "§x§d§6§c§1§a§e行了，這下就沒事了 ",
            "§x§d§6§c§1§a§e阿，還有這個，尤爾達要的那瓶毒藥 ",
            "§x§d§6§c§1§a§e這次可要小心了喔!不然弄到身上可是會出事的! ",
        ),
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(yodaIcon,
            "$yodaNPC",
            "§x§d§6§c§1§a§e呦，你回來啦~那個老巫婆很難搞吧~ ",
            "§x§d§6§c§1§a§e 這個藥水…果然很純，看看這霧",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e小..小心點啊，撒了可就不妙了",
            "§x§d§6§c§1§a§e對了，要準備出行了吧?",
        ),
        *Utils.getMessage(yodaIcon,
            "$yodaNPC",
            "§x§d§6§c§1§a§e恩…能請你先行前往嗎?",
            "§x§d§6§c§1§a§e就當作是替我探探路吧 ",
            "§x§d§6§c§1§a§e到了就與他們的村長說一聲吧~ ",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這樣啊…那…關於獎賞…",
        ),
        *Utils.getMessage(yodaIcon,
            "$yodaNPC",
            "§x§d§6§c§1§a§e和村長說一聲就行~我會委託他的",
            "§x§d§6§c§1§a§e那就麻煩你順路斬斷一路的危險啦~",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e唉…知道了，那我就先走一步囉",
        ),
    )
    private val mes5 = arrayOf(

        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這是…天然的噴泉?不過路標指向那邊… ",
            "§x§d§6§c§1§a§e不應該這樣啊…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e另一條路貌似是個村莊 ",
            "§x§d§6§c§1§a§e還有個人!要不去探詢看看吧",
        ),
    )
    init {
        cancelAble = false
        val obj0 = ReachLevelObjective("obj0", 999)
            .setInstruction("§c該任務尚未開放，開放後將自動解鎖");
        //val obj0 = ReachLevelObjective(this,"obj0", 12)
        //.setInstruction("§712等後將繼續任務");
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",157,*mes1)
            .setInstruction("§7與 §e卡特里曼 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",129,*mes2)
            .setInstruction("§7與 §e亞里士德 §7對話");
        val obj3 = ListenNPCObjective(this,"obj3",100,*mes3)
            .setInstruction("§7與 §e克雷利亞 §7對話")
            .setEndProcess {
                it.sendTitle("§7[§a獲取道具§7]","§f特製毒藥§8(虛擬道具)")
            };
        val obj4 = ListenNPCObjective(this,"obj4",97,*mes4)
            .setInstruction("§7與 §e尤爾達 §7對話");
        val obj5Loc = Location(world,-189.29,79.0,366.65);
        val obj5 = MoveToObjective(this,"obj5", obj5Loc,8.0)
            .setInstruction ("§7前往行商路線");
        pushObjective(obj0,obj1,obj2,obj3,obj4,obj5)

        addRewards(
            MoneyReward(100), //錢幣
            QuestExpReward(100), //傭兵聲望
            ClassExpReward(500),
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch3_03")
        } )
    }
}


val quest = MainCh3_02();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



