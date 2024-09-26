@file:RequiredPlugins("HeroesPets","PlayerDataSync","HeroesSkills","HeroesPetUpgrading","MythicMobs")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import io.lumine.mythic.bukkit.MythicBukkit
import io.lumine.mythic.core.skills.mechanics.SudoSkillMechanic
import net.brian.heroespet.upgrading.models.UpgradeablePet
import net.brian.heroespet.upgrading.utils.PetExpGlobalWhiteList
import net.brian.heroespets.HeroesPets
import net.brian.heroespets.api.pets.HeroesPet
import net.brian.heroespets.api.pets.Pet
import net.brian.heroespets.api.pets.SpawnedPet
import net.brian.heroespets.bukkit.compatible.mythiclib.MythicStats
import net.brian.heroespets.bukkit.compatible.mythicmobs.MythicPetSkill
import net.brian.heroespets.bukkit.configs.configs.Language
import net.brian.heroespets.core.pets.skills.SudoOwnerSkill
import net.brian.heroespets.utils.SkullBuilder
import net.brian.playerdatasync.util.IridiumColorAPI
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Newyear_Dragon_Classic: HeroesPet("newyear_dragon_classic","青龍"), UpgradeablePet {


    init {
        display = "青龍"
        initTier = 3
        height = 2.2
    }

    override fun getEntityName(player: Player, pet: Pet): MutableList<String> {
        return arrayListOf(
            "§f${player.name} 的寵物",
            "§7Lv.${pet.level} 青龍"
        )
    }

    override fun getIcon(player: Player?, pet: Pet): ItemStack {
        val itemStack = ItemStack(Material.CARVED_PUMPKIN)
        val itemMeta = itemStack.itemMeta;
        itemMeta.setCustomModelData(184)
        itemStack.setItemMeta(itemMeta)
        itemMeta.setDisplayName("§f青龍 §7Lv.${pet.level}")
        val lore = buildList {
            add("")
            if(player != null) add("§7▪ 主人: &f${player.name}")
            add("§7▪ 品階: &6傑出")
            if(pet.nextLevelExp == -1){
                add(Language.MAX_LEVEL);
            }
            else add("§7▪ 經驗: &f${pet.exp}/${pet.nextLevelExp}")
            add("")
            add("§7§l在新年才會出現的龍")
            add("§7§l有著能帶來財運的能力")
            add("")
            add("&f&l龍華富貴 &7| &fCD: 10分鐘 &7| &f被動")
            add("&7中國龍會為主人帶來財運")
            add("&7使主人獲得不固定的金錢")
            add("")
            add("&7➤ + ${expBuff(pet)} 經驗加成")
            add("&7➤ + ${MGdamage(pet)} 攻擊魔傷")
            add("&7➤ + ${ATdamage(pet)} 攻擊傷害")
            add("&7➤ &7每30秒恢復主人些許飽食度") }


        itemMeta.lore = IridiumColorAPI.process(lore);

        itemStack.itemMeta = itemMeta;
        return itemStack;
    }
    override fun getWhiteLists(): MutableList<String> {
        val list = mutableListOf<String>();
        list.addAll(PetExpGlobalWhiteList.get());//全局通用果实，如果不要通用吃，就删掉这行
        return  list;
    }
    private fun expBuff(pet: Pet): Double {
        return (pet.level*0.4);
    }
    private fun MGdamage(pet: Pet): Double {
        return (pet.level*0.4);
    }
    private fun ATdamage(pet: Pet): Double {
        return (pet.level*0.4);
    }

    override fun onTick(pet: SpawnedPet, ticks: Long) {
        // 每個 Tick 要做的事
        if(ticks % (30*20) == 0L){
            pet.owner.addPotionEffect(PotionEffect(PotionEffectType.SATURATION, 60, 1))
            pet.owner.sendMessage("§7[§x§B§E§9§B§7§B寵物§7] §f青龍施展能力為主人恢復了些許飽食度")
        }
        if(ticks % (10*20*60) == 0L){
            MythicBukkit.inst().getAPIHelper().castSkill(pet.getOwner(),"青龍_龍華富貴")
        }
    }
    val modifierKey = "heroes-pet";
    override fun onSpawn(spawnedPet: SpawnedPet) {
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"MAX_HEALTH",expBuff(spawnedPet.pet),modifierKey)
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"MG_WEAPON_DAMAGE",MGdamage(spawnedPet.pet),modifierKey)
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"ATTACK_DAMAGE",ATdamage(spawnedPet.pet),modifierKey)
    }

    override fun onDespawn(spawnedPet: SpawnedPet) {
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"MAX_HEALTH",modifierKey);
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"MG_WEAPON_DAMAGE",modifierKey);
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"ATTACK_DAMAGE",modifierKey);
    }


}

HeroesPets.getPlugin().petManager.register(Newyear_Dragon_Classic());