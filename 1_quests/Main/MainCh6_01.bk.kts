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




val questID = "main_ch6_01"

class MainCh6_01: Quest(questID,"§7[§c主線委託§7] §f第六章-綠洲探查"){
    val playerIcon = "%squests_icon%";
    val maylukIcon = IconFonts.getNPC(170);
    val deleIcon = IconFonts.getNPC(47);
    val amayliaIcon = IconFonts.getNPC(198);
    val ameraIcon = IconFonts.getNPC(203);
    val unknowIcon = IconFonts.getNPC(29);


    private val playerName = "§c● §f%player_displayname%§r:";
    private val unknowNPC = "§c● §c[???] §e未知聲音§r:";
    private val boss1NPC = "§c● §c[沙原綠洲Boss] §e原隱雙刃§r:";
    private val mayluklNPC = "§c● §c[傭兵接待員] §e梅露可§r:";
    private val deleNPC = "§c● §c[阿納斯塔城城主] §e迪利§r:";
    private val amayliaNPC = "§c● §7[砂礦鎮鎮長] §e阿梅莉亞§r:";
    private val ameraNPC = "§c● §7[砂礦鎮學者] §e阿麥菈§r:";
    private val boss2NPC = "§c● §7[暮光烈谷Boss] §e拓笊§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這就是神器所在的地區嗎…",
            "§x§d§6§c§1§a§e看起來也沒有想像中的危險",
        ),
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e你是來挑戰我的冒險者嗎?",
            "§x§d§6§c§1§a§e好久沒有那麼刺激的戰鬥了呢",
            "§x§d§6§c§1§a§e為了證明實力，你先帶幾隻沙漠幼鳥得身體回來吧",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e居然又有戰鬥…",
            "§x§d§6§c§1§a§e再說了你又是誰啊? ",
            "§x§d§6§c§1§a§e我為甚麼還要跟你戰鬥?",
        ),
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e你不是想取得神器嗎? ",
            "§x§d§6§c§1§a§e獲得我的認可後就能給你",
            "§x§d§6§c§1§a§e在此之前你得先證明你的力量",
        ),
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e至於我是誰",
            "§x§d§6§c§1§a§e等你回來你便知道了",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我…我回來了",
            "§x§d§6§c§1§a§e你說的那些幼鳥，未免也太兇狠了吧",
        ),
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e哈哈哈哈，這幾隻幼鳥你就不行了?",
            "§x§d§6§c§1§a§e看來你根本還沒有和我戰鬥的資本阿",
            "§x§d§6§c§1§a§e你還是在練練吧，我會等著你的",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你這是…在小瞧我嗎?",
        ),
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e阿哈哈哈我可不想欺負一個",
            "§x§d§6§c§1§a§e連幼鳥都得打上那麼久的孩子",
            "§x§d§6§c§1§a§e等你變強了，我們再來一場公平的戰鬥吧",
        ),
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e呦?你小子回來的那麼快阿",
            "§x§d§6§c§1§a§e氣焰增長了不少",
            "§x§d§6§c§1§a§e那就再完成一次我的挑戰吧! ",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e又是那幾頭鳥嗎?",
            "§x§d§6§c§1§a§e誰怕誰!",
        ),
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e沒想到你變得那麼厲害了",
            "§x§d§6§c§1§a§e不錯不錯，不過這還遠遠不夠阿",
            "§x§d§6§c§1§a§e上吧我的赤侯們!",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e甚麼?還有護衛?",
        ),
    )
    private val mes5 = arrayOf(
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e恩…好久沒有那麼厲害的強敵了呢",
            "§x§d§6§c§1§a§e去拿遺跡寶物吧",
            "§x§d§6§c§1§a§e那是屬於你的了",
        ),
    )

    private val mes6 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e啊!(淒厲)",
        ),
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e你以為真的那麼簡單就能得到這神器?",
            "§x§d§6§c§1§a§e人類果然還是那麼大意",
            "§x§d§6§c§1§a§e在處決你之前，給你最後一次機會",
        ),
        *Utils.getMessage(unknowIcon,
            "$unknowNPC",
            "§x§d§6§c§1§a§e擊敗我，我就讓你帶走神器並放你走",
            "§x§d§6§c§1§a§e輸了我?人頭落地!",
            "§x§d§6§c§1§a§e我原隱雙刃可不是浪得虛名，你最好想清楚",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我還有選擇嗎?只能拚死一搏了",
        ),
    )
    private val mes7 = arrayOf(
        *Utils.getMessage(unknowIcon,
            "$boss1NPC",
            "§x§d§6§c§1§a§e人類…你果然如我所料的那麼強大 ",
            "§x§d§6§c§1§a§e這阿塔卡歸你了",
        ),
        *Utils.getMessage(unknowIcon,
            "$boss1NPC",
            "§x§d§6§c§1§a§e但願你能拯救蒼生",
            "§x§d§6§c§1§a§e楞著幹嘛?在我反悔前快走吧",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e等等…甚麼阿塔卡?而且..這竟然才是神器…?",
            "§x§d§6§c§1§a§e那遺跡上的那個是…?",
        ),
        *Utils.getMessage(unknowIcon,
            "$boss1NPC",
            "§x§d§6§c§1§a§e還以為你們人類多聰明呢",
            "§x§d§6§c§1§a§e那不過是贗品罷了",
            "§x§d§6§c§1§a§e至於阿塔卡，則是他的名字",
        ),
        *Utils.getMessage(unknowIcon,
            "$boss1NPC",
            "§x§d§6§c§1§a§e是上一位聖者所賜",
            "§x§d§6§c§1§a§e你可得好好對她",
            "§x§d§6§c§1§a§e至於使用方法…這我就不知道了",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e原來如此…我會好好對他的!",
            "§x§d§6§c§1§a§e我得趕在旱季來臨前回到城鎮才是",
        ),
    )
    init {
        cancelAble = false
        val world = Bukkit.getWorld("2k");
        /*val obj0 = ReachLevelObjective("obj0", 999)
            .setInstruction("§c該任務尚未開放，開放後將自動解鎖");*/
        val obj0 = ReachLevelObjective("obj0", 32)
            .setInstruction("§732等後將繼續任務");
        val obj1Loc = Location(world,455.29,82.0,133.65);
        val obj1 = MoveToObjective(this,"obj1", obj1Loc,15.0)
            .setInstruction ("§7前往沙原綠洲 455,82,133 §8(請開啟GPS追蹤目標位置)");
        val obj2 = ListenTalkObjective(this,"obj2",*mes1)
        val obj3 = KillMobsObjective(this,"obj3","沙原綠洲_沙漠幼鳥",10)
            .setInstruction { "擊殺 ${it.amount} / 10 隻沙漠幼鳥" }
        val obj4 = ReachLevelObjective("obj4", 34)
            .setInstruction("§734等後將繼續任務");
        val obj5Loc = Location(world,455.29,82.0,133.65);
        val obj5 = MoveToObjective(this,"obj5", obj5Loc,15.0)
            .setInstruction ("§7前往沙原綠洲 455,82,133 §8(請開啟GPS追蹤目標位置)");
        val obj6 = ListenTalkObjective(this,"obj6",*mes3)
        val obj7 = KillMobsObjective(this,"obj7","沙原綠洲_沙漠幼鳥",20)
            .setInstruction { "擊殺 ${it.amount} / 20 隻沙漠幼鳥" }
        val obj8 = ListenTalkObjective(this,"obj8",*mes4)
        val obj9 = KillMultiMobsObjective(this,"obj9", mapOf(
            "沙原綠洲_沙原斥侯" to 20,
            "沙原綠洲_沙原勇士" to 5))
            .setInstruction{ "擊殺沙原斥侯 ${it.get("沙原綠洲_沙原斥侯")}/20、沙原勇士 ${it.get("沙原綠洲_沙原勇士")}/20"}
        val obj10 = ListenTalkObjective(this,"obj10",*mes5)
        val obj11Loc = Location(world,610.29,120.0,295.65);
        val obj11 = MoveToObjective(this,"obj11", obj11Loc,2.0)
            .setInstruction ("§7前往神器所在地 610,120,295 §8(請開啟GPS追蹤目標位置)")
            .setEndProcess {
                Utils.command("effect ${it.name} blindness 3 ")
                it.teleport(Location(world,568.0, 96.0, 300.0 ,-459.94f,-12.56f))
            };
        val obj12 = ListenTalkObjective(this,"obj12",*mes6)
        val obj13 = KillMobsObjective(this,"obj13","沙原綠洲_原隱雙刃",1)
            .setInstruction { "擊殺 ${it.amount} / 1 原隱雙刃"}
                val obj14 = ListenTalkObjective(this,"obj14",*mes7)
                pushObjective(obj0,obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10,obj11,obj12,obj13,obj14)

                addRewards(
                    MoneyReward(200), //錢幣
                    QuestExpReward(180), //傭兵聲望
                    ClassExpReward(1200),
                    MessageReward("&a➯ &7任務道具: 祈雨神器-阿塔卡 &c(請勿遺失)")
                )
                addEndHook({
                    Utils.command("sq start ${it.name} main_ch6_02")
                    Utils.command("get ${it.name} QUESTMATERIAL M022")
                } )
            }
            }


        val quest = MainCh6_01();
        ScriptedQuests.getInstance().questManager.register(quest);

        fun onDispose(){
            quest.unregister();
        }



