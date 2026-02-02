package com.example.jumpscaremod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import com.example.jumpscaremod.JumpscareModMain;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = 
        DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, JumpscareModMain.MODID);

    public static final RegistryObject<SoundEvent> JUMPSCARE_SOUND = SOUND_EVENTS.register("jumpscare",
        () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(JumpscareModMain.MODID, "jumpscare")));

    public static void register(IEventBus modEventBus) {
        SOUND_EVENTS.register(modEventBus);
    }
}
