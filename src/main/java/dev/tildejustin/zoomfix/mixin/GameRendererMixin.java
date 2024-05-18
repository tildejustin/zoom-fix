package dev.tildejustin.zoomfix.mixin;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Dynamic
    @ModifyConstant(method = "getFov", constant = @Constant(expandZeroConditions = Constant.Condition.LESS_THAN_ZERO), require = 0)
    private int skipCheckForUnbound(int constant) {
        return -1;
    }
}
