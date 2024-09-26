@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.classcore.ReachLevelObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player




val questID = "main_ch3_01"

class MainCh3_01: Quest(questID,"§7[§c主線委託§7] §f第三章-艾靈之門的消失"){


    val playerIcon = "%squests_icon%";
    val calitmanIcon = IconFonts.getNPC(157);
    val alielIcon = IconFonts.getNPC(123);
    val alisterIcon = IconFonts.getNPC(129);
    val yodaIcon = IconFonts.getNPC(97);
    val crayliaIcon = IconFonts.getNPC(100);
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
        *Utils.getMessage(alielIcon,
            "$alielNPC",
            "§x§d§6§c§1§a§e 在你離開前，我想囑咐你幾句..",
            "§x§d§6§c§1§a§e 如果看到任何異狀，就趕緊離開",
            "§x§d§6§c§1§a§e 在危險發生以前就趕緊撤退",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我…我知道了放心吧，這就交給我了",
            "§x§d§6§c§1§a§e 有任何結果我會立馬回報的",
        ),
        *Utils.getMessage(alielIcon,
            "$alielNPC",
            "§x§d§6§c§1§a§e解藥我也會加快製作",
            "§x§d§6§c§1§a§e以報答你的救命之恩",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e請問你是?",
        ),
        *Utils.getMessage(calitmanIcon,
            "$calitmanNPC",
            "§x§d§6§c§1§a§e我是地質學家，卡里特曼",
            "§x§d§6§c§1§a§e 受諾亞鎮亞里是德博士所召而來",
            "§x§d§6§c§1§a§e 你又是誰?為何出現在此",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我只是個平凡的傭兵，接受委託來此罷了",
            "§x§d§6§c§1§a§e此行目的是為了調查艾靈之門的",
        ),
        *Utils.getMessage(calitmanIcon,
            "$calitmanNPC",
            "§x§d§6§c§1§a§e 艾靈傳送門?你也在調查艾靈一族的怪事?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e應該說…我是受艾靈一族之託",
            "§x§d§6§c§1§a§e才會前來調查的...",
        ),
        *Utils.getMessage(calitmanIcon,
            "$calitmanNPC",
            "§x§d§6§c§1§a§e你說…艾靈一族!?但是…他們不是早已消失了嗎",
            "§x§d§6§c§1§a§e難不成這片大地上仍有剩餘的艾靈一族?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e不管怎麼樣，先讓我進到裡面吧",
            "§x§d§6§c§1§a§e但裡面看起來…好不安全阿 ",

            ),
        *Utils.getMessage(calitmanIcon,
            "$calitmanNPC",
            "§x§d§6§c§1§a§e我也是這麼想的 ",
            "§x§d§6§c§1§a§e還帶著濃濃的沼氣…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e不管了，硬著頭皮上吧",
        )
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這些怪物也太多了吧",
            "§x§d§6§c§1§a§e但至少清出一條路了得以前進了…",

            ))
    private val mes4 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這一片…難道就是傳送門?",
            "§x§d§6§c§1§a§e居然長出了一顆…寄生樹",
            "§x§d§6§c§1§a§e這究竟是怎麼回事…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這看起來…可不妙阿",
            "§x§d§6§c§1§a§e得回去與艾黎爾商討看看",
        ))
    private val mes5 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e艾黎爾!傳送門…大事不妙了",
            "§x§d§6§c§1§a§e有顆…充滿著膿包的寄生樹",
        ),
        *Utils.getMessage(alielIcon,
            "$alielNPC",
            "§x§d§6§c§1§a§e等等…你慢慢說，到底怎麼回事了 ",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e若我沒看錯，洞穴深處的傳送門依舊存在 ",
            "§x§d§6§c§1§a§e然而上面卻長出了奇特的寄生樹…",
            "§x§d§6§c§1§a§e好多的怪物也藉此爬了出來",
        ),
        *Utils.getMessage(alielIcon,
            "$alielNPC",
            "§x§d§6§c§1§a§e看來這就是混亂的原因",
            "§x§d§6§c§1§a§e謝謝你的調查，解藥我也已經製作完畢了 ",
            "§x§d§6§c§1§a§e你..先去拯救這座諾亞島吧",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那艾靈之境和長廊怎麼辦! ",
            "§x§d§6§c§1§a§e總不能都放著不…",
        ),
        *Utils.getMessage(alielIcon,
            "$alielNPC",
            "§x§d§6§c§1§a§e在我們能進行下一步前",
            "§x§d§6§c§1§a§e都還不算晚 ",
            "§x§d§6§c§1§a§e趁此去了解更多的謎團…",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e等等!你這是甚麼意思! ",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e她好像…被靜止了…",
        ),
    )
    init {
        cancelAble = false
        val obj0 = ReachLevelObjective("obj0", 999)
            .setInstruction("§c該任務尚未開放，開放後將自動解鎖");
        //val obj0 = ReachLevelObjective(this,"obj0", 12)
        //.setInstruction("§712等後將繼續任務");
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",123,*mes1)
            .setInstruction("§7與 §e艾黎爾 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",157,*mes2)
            .setInstruction("§7與 §e卡里特曼 §7對話");
        val obj3 = KillMobsObjective(this,"obj3","艾靈洞穴_腐敗看守者",25)
            .setInstruction { "擊殺 ${it.amount} / 25 個腐敗看守者" };
        val obj4 = ListenTalkObjective(this,"obj4",*mes3)
        val obj5Loc = Location(world,-636.24,16.0,357.12);
        val obj5 = MoveToObjective(this,"obj5", obj5Loc,5.0)
            .setInstruction ("§7前往艾靈之門");
        val obj6 = ListenTalkObjective(this,"obj6",*mes4)
        val obj7 = ListenNPCObjective(this,"obj7",123,*mes5)
            .setInstruction("§7與 §e艾黎爾 §7對話")
            .setEndProcess {
                it.sendTitle("§7[§a獲取道具§7]","§f艾靈淨化粉末§8(虛擬道具)")
            };
        pushObjective(obj0,obj1,obj2,obj3,obj4,obj5,obj6,obj7)

        addRewards(
            MoneyReward(100), //錢幣
            QuestExpReward(100), //傭兵聲望
            ClassExpReward(400),
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch3_02")
            Utils.command("lp user ${it.name} permission set group.chapter2")
        } )
    }
}


val quest = MainCh3_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



