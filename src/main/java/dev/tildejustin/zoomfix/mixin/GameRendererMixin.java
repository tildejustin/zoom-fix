package dev.tildejustin.zoomfix.mixin;

import com.llamalad7.mixinextras.sugar.Share;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Dynamic
    @Redirect(method = "getFov", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;getCode()I"), require = 0)
    private int skipCheckForUnbound(KeyBinding ofKeyBindZoom, @Share("key") LocalRef<KeyBinding> key) {
        // force all calls to go to the same branch, capture zoom keybind without reflection
        key.set(ofKeyBindZoom);
        return 0;
    }

    @Dynamic
    @Redirect(method = "getFov", at = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;isKeyDown(I)Z"), require = 0)
    private boolean useIsPressed(int i, @Share("key") LocalRef<KeyBinding> key) {
        return key.get().isPressed();
    }
}
