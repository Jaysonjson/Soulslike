package json.jayson.common.objects.items.gecko;

import json.jayson.common.objects.items.gecko.client.WandItemRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;

import java.util.Random;
import java.util.function.Consumer;

public class WandItem extends Item implements GeoItem {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public WandItem(Properties properties) {
        super(properties);
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    private PlayState predicate(AnimationState animationState) {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level instanceof ServerLevel serverLevel) {
            player.getCooldowns().addCooldown(this, 30);
            Random random = new Random();
            int c = random.nextInt(3) + 1;
            triggerAnim(player, GeoItem.getOrAssignId(player.getItemInHand(hand), serverLevel), "cast" + c, "cast" + c);
        }
        return super.use(level, player, hand);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        //controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "cast1", 0, state -> PlayState.CONTINUE)
                .triggerableAnim("cast1", RawAnimation.begin().thenPlay("cast1")));

        controllerRegistrar.add(new AnimationController<>(this, "cast2", 0, state -> PlayState.CONTINUE)
                .triggerableAnim("cast2", RawAnimation.begin().thenPlay("cast2")));

        controllerRegistrar.add(new AnimationController<>(this, "cast3", 0, state -> PlayState.CONTINUE)
                .triggerableAnim("cast3", RawAnimation.begin().thenPlay("cast3")));
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private WandItemRenderer renderer;
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(renderer == null) {
                    renderer = new WandItemRenderer();
                }
                return renderer;
            }
        });
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
