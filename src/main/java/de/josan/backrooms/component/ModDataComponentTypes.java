package de.josan.backrooms.component;

import com.mojang.serialization.Codec;
import de.josan.backrooms.Backrooms;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {

    public static final ComponentType<Integer> MOTHJELLY_OVERDOSE =
            register("moth_jelly_overdose", builder -> builder.codec(Codec.INT));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Backrooms.id(name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        Backrooms.LOGGER.info("Register DataComponentTypes for: " + Backrooms.MOD_ID);
    }
}
