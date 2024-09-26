@file:RequiredPlugins("Schedulers")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.schedulers.api.scheduler.HalfHourHook
import org.bukkit.Bukkit


fun command(cmd: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),cmd)
}


val runnable = Runnable {
    command("broadcast &7[&x&C&2&7&B&A&0首領&7] &f野外首領重生時間刷新，傭兵們，前往討伐吧！")
    command("mm s resettimers 哥布林水晶礦場_哥布林英雄王")
    command("mm s resettimers noahgoblinking")
    command("mm s resettimers 毒氣林_蒐人")
    command("mm s resettimers 盜賊據點_盜賊首領")
    command("mm s resettimers 石靈森林_元素魔像")
    command("mm s resettimers 低語森林_死靈衛士")
    command("mm s resettimers 艾靈長廊_艾靈守衛巨龍")
    command("mm s resettimers 砂岩巨龜")
    command("mm s resettimers 米諾陶洛斯")
    command("mm s resettimers 迷惑離石像")
    command("mm s resettimers 原隱雙刃")
    command("mm s resettimers 烈日之陽--拓笊")
    command("mm s resettimers 卜普王")
    command("mm s resettimers 阿努比斯")
}

HalfHourHook.getInstance().hook(runnable)

fun onDispose(){
    HalfHourHook.getInstance().unregister(runnable)
}