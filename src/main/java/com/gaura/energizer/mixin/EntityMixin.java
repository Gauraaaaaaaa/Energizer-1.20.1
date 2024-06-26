package com.gaura.energizer.mixin;

import com.gaura.energizer.Energizer;
import com.gaura.energizer.utils.IPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "getJumpVelocityMultiplier", at = @At("RETURN"), cancellable = true)
    private void modifyJumpHeight(CallbackInfoReturnable<Float> cir) {

        Entity entity = (Entity) (Object) this;

        if (entity instanceof PlayerEntity player && ((IPlayerEntity) player).getStopSprint() && Energizer.CONFIG.lower_jump) {

            cir.setReturnValue(cir.getReturnValue() * Energizer.CONFIG.lower_jump_multiplier);
        }
    }

    @Inject(method = "getVelocityMultiplier", at = @At("RETURN"), cancellable = true)
    private void modifyWalkSpeed(CallbackInfoReturnable<Float> cir) {

        Entity entity = (Entity) (Object) this;

        if (entity instanceof PlayerEntity player && ((IPlayerEntity) player).getStopSprint() && Energizer.CONFIG.slower_walk) {

            cir.setReturnValue(cir.getReturnValue() * Energizer.CONFIG.slower_walk_multiplier);
        }
    }
}
