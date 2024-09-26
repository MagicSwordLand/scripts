@file:RequiredPlugins("AltSkill2","PlayerDataSync","MythicMobs")

import io.github.clayclaw.altskill2.AltSkill2
import io.github.clayclaw.altskill2.mana.ManaEntity
import io.github.clayclaw.altskill2.mana.getManaEntity
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import io.lumine.mythic.api.adapters.AbstractEntity
import io.lumine.mythic.api.config.MythicLineConfig
import io.lumine.mythic.api.skills.ITargetedEntitySkill
import io.lumine.mythic.api.skills.SkillMetadata
import io.lumine.mythic.api.skills.SkillResult
import io.lumine.mythic.api.skills.conditions.IEntityCondition
import io.lumine.mythic.api.skills.placeholders.PlaceholderDouble
import io.lumine.mythic.api.skills.placeholders.PlaceholderString
import io.lumine.mythic.bukkit.MythicBukkit
import io.lumine.mythic.bukkit.events.MythicConditionLoadEvent
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent
import io.lumine.mythic.bukkit.utils.numbers.RangedDouble
import io.lumine.mythic.core.skills.SkillCondition
import io.lumine.mythic.core.skills.SkillExecutor
import io.lumine.mythic.core.skills.SkillMechanic
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import java.util.UUID

fun getMana(uuid: UUID): ManaEntity?{
    val player = Bukkit.getPlayer(uuid)
    return player?.getManaEntity();
}

class ConsumeMana(skillExecutor: SkillExecutor,line: String,mlc: MythicLineConfig)
    : SkillMechanic(skillExecutor,line,mlc),ITargetedEntitySkill {
    val amount: PlaceholderDouble;
    init {
        amount = mlc.getPlaceholderDouble(arrayOf("a","amount"),1.0);
    }
    override fun castAtEntity(meta: SkillMetadata, target: AbstractEntity): SkillResult {
        getMana(meta.caster.entity.uniqueId)?.consume(amount.get(meta));
        return SkillResult.SUCCESS;
    }
}

class HasMana(mlc:MythicLineConfig) :SkillCondition(mlc.line),IEntityCondition{
    val amount: PlaceholderString;
    init {
        amount = mlc.getPlaceholderString(arrayOf("a","amount"),"0");
    }
    override fun check(target: AbstractEntity): Boolean {
        val entity = getMana(target.uniqueId);
        if(entity != null){
            return RangedDouble(amount.get(target)).equals(entity.getManaStored());
        }
        return false;
    }
}

class MythicLoader: Listener{

    @EventHandler
    fun onMechanicLoad(event: MythicMechanicLoadEvent){
        val name = event.mechanicName;
        val skillExecutor = MythicBukkit.inst().skillManager;
        if(name.equals("ConsumeMana",true)){
            event.register(ConsumeMana(skillExecutor,event.config.line,event.config))
        }
    }

    @EventHandler
    fun onConditionLoad(event: MythicConditionLoadEvent){
        if(event.conditionName.equals("HasMana",true)){
            event.register(HasMana(event.config))
        }
    }

}


val mythicLoader = MythicLoader();
Bukkit.getPluginManager().registerEvents(mythicLoader,AltSkill2.instance);

fun onDispose(){
    HandlerList.unregisterAll(mythicLoader)
}