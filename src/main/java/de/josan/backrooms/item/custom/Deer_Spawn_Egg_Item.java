package de.josan.backrooms.item.custom;

import de.josan.backrooms.Backrooms;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class Deer_Spawn_Egg_Item extends SpawnEggItem {

    public Deer_Spawn_Egg_Item(EntityType<? extends MobEntity> type, Settings settings) {
        super(type, 9999999, 9999999, settings);
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            if(Backrooms.AccessAllowed(player)) {
                player.sendMessage(Text.translatable("function.indevelopment"));
                player.setHealth(0f);
            } else {
                player.sendMessage(Text.translatable("function.access_denied"));
            }
        }
        return ActionResult.SUCCESS;
    }
}
