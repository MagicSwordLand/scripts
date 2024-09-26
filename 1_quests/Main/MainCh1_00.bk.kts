@file:RequiredPlugins("ScriptedQuests","MMOItems","MythicLib")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.objectives.Objective
import net.brian.scriptedquests.api.player.PlayerCompleter
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mmoitems.UseItemObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.messages.NPCMessage
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import net.brian.scriptedquests.conversation.messages.MessageQueue
import net.brian.scriptedquests.conversation.messages.SimpleMessage
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.ClassExpReward
import net.brian.scriptedquests.rewards.CommandReward
import net.brian.scriptedquests.rewards.MoneyReward
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import net.brian.scriptedquests.utils.Utils
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.scheduler.BukkitRunnable
import java.util.function.Consumer


val questID = "main_ch1_00";
class Main_Ch1_00 : Quest(questID,"§7[§c主線委託§7] §f序章"){
    val playerIcon = "%squests_icon%";
    val npcIcon = IconFonts.getNPC(21);
    val npcIcon2 = IconFonts.getNPC(21);
    val npcIcon3 = IconFonts.getNPC(23);


    private val world: World = Bukkit.getWorld("intro")!!
    private val world2: World = Bukkit.getWorld("2k")!!
    private val playerName = "§c● §f%player_displayname%:§r";
    private val greyNPC = "§c● §e葛雷§r:";
    private val seanNPC = "§c● §e肖恩§r:";
    private val unknown = "§c● §f???:";


    private val mes1 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e想起來了，這裡是我們村莊",
            "§x§d§6§c§1§a§e但為甚麼只剩廢墟了...",
            "§x§d§6§c§1§a§e肚子上的傷口還在流血",
            playerName,
            "§x§d§6§c§1§a§e先來看看有沒有可以止血的東西好了...",
        )
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e傷口是止住了",
            "§x§d§6§c§1§a§e但是村子裡怎麼變的那麼殘破",
            "§x§d§6§c§1§a§e這些是…甚麼東西啊!!",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e還會攻擊人！好像是肉塊所組合的怪物！！",
            "§x§d§6§c§1§a§e看來不解決他們是無法離開了",
        )
    )

    private val mes3 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e他好像在源源不斷的出現",
            "§x§d§6§c§1§a§e村子裡的其他人貌似也都不清楚狀況",
            "§x§d§6§c§1§a§e這樣下去沒完沒了...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e還是先逃出村莊再說吧！",
        )
    )

    private val mes4 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e應該逃離了吧 (喘氣",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$unknown",
            "&x&d&6&d&0&b&8喂!那邊那個是誰!",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$unknown",
            "&x&d&6&d&0&b&8甚麼嘛是個人阿",
        )
    )


    private val mes5 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e請問你是?",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8我叫葛雷，是附近一代的尤葛傭兵團長",
            "&x&d&6&d&0&b&8你呢?看你的樣子剛成年吧",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我叫 §f%player_displayname%",
            "§x§d§6§c§1§a§e來自於前面的村子...",
            "§x§d§6§c§1§a§e但村子裡現在滿是怪物...",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8甚麼意思?這樣吧！",
            "&x&d&6&d&0&b&8我們的據點就在附近",
            "&x&d&6&d&0&b&8先跟我一起回去吧！",
        )
    )

    private val mes6 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e謝謝你願意收留我",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8不客氣",
            "&x&d&6&d&0&b&8不過你剛剛說的怪物...",
            "&x&d&6&d&0&b&8我想也許是希爾王城的秘密實驗",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那個人類大陸中最大的國家?",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8是的，忘了告訴你",
            "&x&d&6&d&0&b&8我是來自於尤葛帝國的傭兵團",
            "&x&d&6&d&0&b&8不介意的話，要不要與我一同回到帝國呢?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e...看來沒辦法了",
            "§x§d§6§c§1§a§e村子都毀了...我也無處可去了",
            "§x§d§6§c§1§a§e我就跟你走吧！",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8不過我們還要先完成這次的委託才行",
            "&x&d&6&d&0&b&8這樣吧！你來為我們帶路",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e知道了！這一帶我很熟悉",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8那太棒了！不過你要先整裝才安全",
            "&x&d&6&d&0&b&8去找肖恩吧，他那裏有多餘的武器",
            "&x&d&6&d&0&b&8有了基本的武器後再回來找我吧！",
        )
    )

    private val mes7 = arrayOf(
        *Utils.getMessage(
            npcIcon3,
            "$seanNPC",
            "&x&d&6&d&0&b&8你肯定就是那個新來的孩子吧",
            "&x&d&6&d&0&b&8這把武器給你，雖然不是那麼好",
            "&x&d&6&d&0&b&8但至少你再不是手無寸鐵",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e沒關係的！",
            "§x§d§6§c§1§a§e我能夠自保就足夠了！",
        ),
        *Utils.getMessage(
            npcIcon3,
            "$seanNPC",
            "&x&d&6&d&0&b&8哈哈，你挺有覺悟的",
            "&x&d&6&d&0&b&8沒事的話，可以回去找葛雷了",
        )
    );

    private val mes8  = arrayOf(
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8看來你已經拿到裝備了",
            "&x&d&6&d&0&b&8那我來說說這次我們的委託任務",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8根據傭兵公會給出的情報",
            "&x&d&6&d&0&b&8這一帶的其中一棵古樹上有著千年才會產生的果實",
            "&x&d&6&d&0&b&8他由一個能力超群的村莊所守護",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這跟媽媽曾說過的傳說很接近",
            "§x§d§6§c§1§a§e難道...",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8沒錯！你所在的村子就是守護古樹一族的村子",
            "&x&d&6&d&0&b&8你們守護著精靈所祝福的千歲果",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e但經世事紛亂後，他們決心守護村莊與果實",
            "§x§d§6§c§1§a§e因此而設下結界",
            "§x§d§6§c§1§a§e然而不知為何..結界被打破",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e導致村莊突發怪事，至於千歲果...",
        ),
        *Utils.getMessage(
            npcIcon2,
            greyNPC,
            "&x&d&6&d&0&b&8意思是！",
        )
    )
    val believeMsg = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e但是那附近充斥著怪物...",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8沒事的！我們有足夠的戰力",
            "&x&d&6&d&0&b&8你大可放心！",
        ))

    val disBelieveMsg = arrayOf(
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8也許是我太唐突了",
            "&x&d&6&d&0&b&8剛經歷了這麼多，又要你相信一個陌生人",
            "&x&d&6&d&0&b&8抱歉剛剛太過激動",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e不.. 我知道那棵古樹在哪..只是",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8有甚麼顧慮的嗎？",
            "&x&d&6&d&0&b&8沒事! 有我們在，你大可放心！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那就由我來帶路吧！",
        )
    )


    private val mes9 = arrayOf(
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8怎麼那麼多奇怪的生物..",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e那棵樹就在村莊的中央!",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8不行…怪物太多了",
            "&x&d&6&d&0&b&8我們得先殺出重圍!",
        )
    )

    private val mes10 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這就是那顆古樹!",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8看來…還是來遲一步了",
            "&x&d&6&d&0&b&8千歲果已經被奪走了...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e被奪走了?",
            "§x§d§6§c§1§a§e千歲果有什麼作用嗎?",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8其實我也不清楚",
            "&x&d&6&d&0&b&8我們也只是依照上級所示來辦事",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8看來該回去了，但關於千歲果..",
            "&x&d&6&d&0&b&8還是得帶點樹液回去檢驗才行",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我來幫你吧！",
        )
    )
    private val mes11 = arrayOf(
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8謝謝你啊，小不點",
            "&x&d&6&d&0&b&8對了，我看你沒有地方可以去了",
            "&x&d&6&d&0&b&8要不要成為傭兵呢?",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8雖然當傭兵有一定的危險性",
            "&x&d&6&d&0&b&8但賺得錢夠養活你了",
        ),
        *Utils.getMessage(playerIcon,
            playerName,
            "§x§d§6§c§1§a§e可…可以嗎!",
        ),
        *Utils.getMessage(
            npcIcon2,
            greyNPC,
            "&x&d&6&d&0&b&8那有甚麼不行，你可是立下大功了呢",
        ),
        *Utils.getMessage(playerIcon,
            playerName,
            "§x§d§6§c§1§a§e那當然好啊！",
        ),
        *Utils.getMessage(
            npcIcon2,
            "$greyNPC",
            "&x&d&6&d&0&b&8那就跟我們一起回到帝國的公會吧！",
        )
    )


    init {
        cancelAble = false;

        val obj0 = object: Objective("obj0"){

            @EventHandler
            fun onClick(event: PlayerInteractEvent){
                val block = event.clickedBlock;
                if(block != null && playerIsDoing(event.player)){
                    send(event.player);
                }
            }

            fun getOptions(c: Char): Array<PlayerOption>{
                val result = Consumer<Player> { player ->
                    finish(PlayerCompleter.getInstance(player))
                    Bukkit.dispatchCommand(player, "sq icon $c");
                }
                return arrayOf(
                    PlayerOption(c.toString()).setResult(result),
                    PlayerOption("       ").setResult(result),
                    PlayerOption("       ").setResult(result),
                    PlayerOption("       ").setResult(result))
            }
            fun send(player: Player){
                player.sendMessage("§c● §f${player.name} §x§d§6§c§1§a§e頭好痛...")
                val q = NPCQuestion("$playerName §x§d§6§c§1§a§e我是?...","§7[§a點擊下方圖示選擇身分頭像§7]","§7若看不到選項,請將強制Unicode字型關閉","§7ESC->選項->語言->下方的強制使用Unicode字型:關閉","")
                    .addPlayerOptions(
                        *getOptions('\uF622'),*getOptions('\uF624'),*getOptions('\uF625'),*getOptions('\uF626')
                    );
                Bukkit.getScheduler().runTaskLater(ScriptedQuests.getInstance(),
                    Runnable { q.send(player) }
                    ,30L)
            }
        }

        val obj1 = ListenTalkObjective(this,"obj1", *mes1)
            .setEndProcess {
                Utils.command("vanilla spawnpoint ${it.name} intro 0.43 63.0 -5.77 -138.43f 0.39f")
                it.teleport(Location(world,0.43,63.0,-5.77,-138.43f,0.39f))
                it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
            }

        val obj2 = UseItemObjective("obj2","QUESTMATERIAL","QM001",1)
        obj2.setInstruction("找到繃帶並使用")
        obj2.location = Location(world,10.0,65.0,-17.0)
        obj2.setEndProcess {
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
        }


        val obj3 = ListenTalkObjective(this,"obj3",*mes2)
        obj3.setEndProcess { it.sendTitle("擊殺未知怪物","") }
        obj3.setEndProcess {
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
        }

        val obj4 = KillMobsObjective(this,"obj4", "UnknownZombie" , 2)
        obj4.setInstruction { "擊殺未知怪物 ${it.amount}/2" }
        obj4.location = Location(world,22.0, 63.0, -26.0);
        obj4.setEndProcess {
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
        }

        val obj5 = ListenTalkObjective(this,"obj5",*mes3)

        val obj6Loc = Location(world,55.0,63.0,-24.0)
        val obj6 = MoveToObjective("obj6",obj6Loc,5.0)
        obj6.setInstruction("逃離村莊 並尋找安身之處")
        obj6.location = obj6Loc
        obj6.setEndProcess {
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
        }


        val obj7 = ListenTalkObjective(this,"obj7",*mes4)

        val obj8 = ListenNPCObjective(this,"obj8",21,*mes5);
        obj8.setInstruction("與 §e葛雷§7 對話")
        obj8.setEndProcess {
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
        }
        val obj8_5location = Location(world,73.0,63.0,-8.0);
        val obj8_5 = ExecuteCommandObjective("obj8_5","/squest tracking track main_ch1_00")
        obj8_5.setInstruction("嘗試使用/sq list->主線委託->點選任務,來追蹤任務目標");
        obj8_5.setLocation(obj8_5location);
        obj8_5.setEndProcess {
            it.sendMessage("§7[§c提示§7] §e若找不到任務目標請善用/sq list")
            it.sendMessage("§7[§c提示§7] §e現在再次使用追蹤下個任務階段目標吧！")
        };

        val obj9Loc = Location(world,73.0,63.0,-8.0);
        val obj9 = MoveToObjective("obj9", obj9Loc,5.0);
        obj9.setInstruction("前往傭兵據點");
        obj9.location = obj9Loc;
        val objEndLoc = Location(world,79.48, 63.00, -11.99, -775.20f, 7.49f)
        obj9.setEndProcess { it.teleport(objEndLoc)
            Utils.command("vanilla spawnpoint ${it.name} intro 79.48 63.0 -11.99 -775.20f 7.49f")
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
        };

        val obj10 = ListenNPCObjective(this,"obj10",22,*mes6);
        obj10.setInstruction("與 §e葛雷§7 對話");
        obj10.location = Location(world,123.0,67.0,27.0);
        obj10.setEndProcess {
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
        };

        val obj11 = ListenNPCObjective(this,"obj11",23,*mes7);
        obj11.location = Location(world,134.0,66.0,1.0);
        obj11.setInstruction("與 §e肖恩§7 對話")
        obj11.setEndProcess {
            it.sendTitle("§7[§a獲得物品§7]","§f破損的木劍")
            Utils.command("get ${it.name} SWORD S000 1")
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
        }

        val obj11_5 = ListenNPCObjective(this,"obj11_5",22,*mes8);
        obj11_5.setInstruction("回去找 §e葛雷§7 討論下一步計畫");
        obj11_5.location = Location(world,123.0,67.0,27.0);
        obj11_5.setEndProcess {
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
        };

        val obj12 = ConversationObjective("obj12",22,){
            NPCQuestion(*Utils.getMessage(
                npcIcon2,
                "$greyNPC",
                "&x&d&6&d&0&b&8你知道那棵樹的位置嗎?",
                "&x&d&6&d&0&b&8可以馬上帶我們去嗎?",
            )).addPlayerOptions(
                PlayerOption("§7[§a對話選項§7] §f....")
                    .setResult { p->
                        val q = MessageQueue(*disBelieveMsg);
                        q.setOnComplete { _->it.finish(PlayerCompleter.getInstance(p)) }
                        q.send(p)
                    },
                PlayerOption("§7[§a對話選項§7] §f我知道，那棵樹就在村子裡而已")
                    .setResult { p->
                        val q = MessageQueue(*believeMsg);
                        q.setOnComplete { _-> it.finish(PlayerCompleter.getInstance(p)) }
                        q.send(p)
                    }
            )
        }
        obj12.location =  Location(world,123.0,67.0,27.0);
        obj12.setInstruction("與 §e葛雷§7 對話並選擇對話選項");

        val obj13Loc = Location(world,96.0 ,64.0 ,0.0);
        val obj13 = MoveToObjective("obj13",obj13Loc,5.0);
        obj13.setInstruction("帶路返回村莊");
        obj13.setEndProcess {
            it.teleport(Location(world,-18.0, 63.0, -26.0 ,91.94f,-5.56f))
            Utils.command("vanilla spawnpoint ${it.name} intro -18 63.0 -26.0 91.94f -5.56f")
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
        }

        val obj14 = ListenTalkObjective(this,"obj14",*mes9);

        val obj15 = KillMobsObjective(this,"obj15","UnknownZombie",10);
        obj15.setInstruction { "擊殺 ${it.amount}/10 隻未知怪物" }
        obj15.setEndProcess{it.sendMessage("§c● §7[尤葛傭兵團長] §e葛雷§r: §7行了！ 趁怪物再次湧上前，我們趕緊前往古樹！")
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")}

        val obj16 = MoveToObjective("obj16", Location(world,-87.0, 63.0,-33.0),5.0);
        obj16.setEndProcess { it.teleport(Location(world,-94.0,63.0,-33.0,95.29f,-0.28f))
            Utils.command("vanilla spawnpoint ${it.name} intro -94.0 63.0 -33.0 95.29f -0.28f")
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")}
        obj16.setInstruction("前往古樹");

        val obj17 = ListenNPCObjective(this,"obj17",146,*mes10)
            .setInstruction("與 §e葛雷§7 對話")
        obj17.setEndProcess {
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
        }

        val obj18 = GiveItemObjective(this,"obj18",146,"MATERIAL:M001",5)
            .setInstruction("採集 古樹樹液x5 並交給 §e葛雷§7 §8(採集請破壞史萊姆方塊)")
        obj18.setEndProcess {
            it.sendMessage("§7[§c提示§7] §e使用 /sq list 來查詢當前任務進度與目標，點選任務將開啟GPS追蹤目標位置")
        }

        val obj19 = ListenNPCObjective(this,"obj19",146,*mes11)
            .setInstruction("與 §e葛雷§7 對話")
            .setEndProcess {
                it.sendMessage("§7[§c提示§7] §e使用/nv可以開啟新手提示,請新進玩家善用該指令")
                it.sendTitle("§7[§c提示§7]","§e使用/nv可以開啟新手提示")
            }

        pushObjective(
            obj0,obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj8_5,obj9,obj10,obj11,obj11_5,obj12,
            obj13,obj14,obj15,obj16,obj17,obj18,obj19
        )

        addRewards(
            CommandReward("lp user %player% permission set fkey.*","&a➯ &7權限: &f開啟選單(/menu)"),
            MoneyReward(200),
            ClassExpReward(50),
        )

        addStartHook(
            {it.teleport(Location(world,0.0,65.0,0.0))}
        )

        addEndHook({
            it.teleport(Location(world2,-313.00, 64.50, 483.90, 181.94f, 2.18f))
            Utils.command("vanilla spawnpoint ${it.name} 2k -313.0 64.5 483.9 181.94f 2.18f")
            it.sendMessage("§c[提示] §7與§7[尤葛傭兵團長] §e葛雷§7對話")
            Utils.command("lp user ${it.name} permission set cmi.spawngroup.noob false")
            Utils.command("lp user ${it.name} permission set packet.seeplayer true")
            Utils.command("lp user ${it.name} permission set fkey.* true")
            Utils.command("sq start ${it.name} main_ch1_01")
            Utils.command("sq limit set ${it.name} 20")
        })
    }



}

val quest = Main_Ch1_00();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}
