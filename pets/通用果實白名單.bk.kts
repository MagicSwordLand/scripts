@file:RequiredPlugins("HeroesPets","PlayerDataSync","HeroesSkills","HeroesPetUpgrading")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroespet.upgrading.utils.PetExpGlobalWhiteList


PetExpGlobalWhiteList.add("PETEXP003")
PetExpGlobalWhiteList.add("PETEXP004")
PetExpGlobalWhiteList.add("PETEXP005")
PetExpGlobalWhiteList.add("PETEXP006")
PetExpGlobalWhiteList.add("PETEXP007")
PetExpGlobalWhiteList.add("PETEXP008")
PetExpGlobalWhiteList.add("PETEXP009")
PetExpGlobalWhiteList.add("PETEXP010")
PetExpGlobalWhiteList.add("PETEXP011")
PetExpGlobalWhiteList.add("PETEXP012")

fun onDisposed(){
    PetExpGlobalWhiteList.clear();
}