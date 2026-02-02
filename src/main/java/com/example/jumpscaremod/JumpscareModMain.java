package com.example.jumpscaremod;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafxmod.FXModLoadingContext;

import com.example.jumpscaremod.sound.ModSounds;

@Mod("jumpscaremod")
public class JumpscareModMain {

    public static final String MODID = "jumpscaremod";

    public JumpscareModMain() {
        IEventBus modEventBus = FXModLoadingContext.getInstance().getModEventBus();
        
        // Register sounds
        ModSounds.register(modEventBus);
    }
}
