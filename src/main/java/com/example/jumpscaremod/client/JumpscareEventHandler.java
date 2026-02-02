package com.example.jumpscaremod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import com.example.jumpscaremod.sound.ModSounds;

import java.util.Random;

@Mod.EventBusSubscriber(modid = "jumpscaremod", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class JumpscareEventHandler {

    private static final Random RANDOM = new Random();
    private static final int TOTAL_FRAMES = 26; // Total number of animation frames
    private static final int FRAMES_PER_TICK = 1; // How many game ticks per animation frame
    private static final ResourceLocation[] FRAME_TEXTURES = new ResourceLocation[TOTAL_FRAMES];
    
    static {
        // Pre-load all frame textures
        for (int i = 0; i < TOTAL_FRAMES; i++) {
            String frameNum = String.format("%06d", i + 1);
            FRAME_TEXTURES[i] = new ResourceLocation("jumpscaremod", "textures/frame_" + frameNum + ".png");
        }
    }
    
    private static int frameCounter = 0;
    private static int jumpscareFrameTarget = 0;
    private static boolean jumpscareActive = false;
    private static int jumpscareDisplayCounter = 0;
    private static int currentAnimationFrame = 0;
    private static int animationTickCounter = 0;
    private static boolean soundPlayed = false;

    @SubscribeEvent
    public static void onGuiRender(RenderGuiOverlayEvent.Post event) {
        frameCounter++;

        // Check if it's time to trigger jumpscare
        if (!jumpscareActive && frameCounter >= jumpscareFrameTarget) {
            jumpscareActive = true;
            jumpscareDisplayCounter = 0;
            currentAnimationFrame = 0;
            animationTickCounter = 0;
            soundPlayed = false; // Reset sound flag for next jumpscare
        }

        // Display jumpscare
        if (jumpscareActive) {
            GuiGraphics guiGraphics = event.getGuiGraphics();
            int width = event.getWindow().getWidth();
            int height = event.getWindow().getHeight();

            // Get current frame texture
            ResourceLocation currentFrameTexture = FRAME_TEXTURES[currentAnimationFrame];
            
            // Draw the current animation frame (full screen overlay)
            guiGraphics.blit(currentFrameTexture, 0, 0, 0, 0, width, height, width, height);

            // Play sound on first frame of jumpscare
            if (jumpscareDisplayCounter == 0 && !soundPlayed) {
                Minecraft minecraft = Minecraft.getInstance();
                if (minecraft.player != null) {
                    minecraft.getSoundManager().play(
                        net.minecraft.client.resources.sounds.SimpleSoundInstance.forUI(
                            ModSounds.JUMPSCARE_SOUND.get(), 
                            1.0F, 
                            1.0F
                        )
                    );
                }
                soundPlayed = true;
            }

            jumpscareDisplayCounter++;
            animationTickCounter++;

            // Advance to next animation frame based on tick counter
            if (animationTickCounter >= FRAMES_PER_TICK) {
                animationTickCounter = 0;
                currentAnimationFrame++;
                
                // Loop the animation if we've reached the end
                if (currentAnimationFrame >= TOTAL_FRAMES) {
                    currentAnimationFrame = 0;
                }
            }

            // End jumpscare after display duration (26 frames * FRAMES_PER_TICK ticks)
            int jumpscareDisplayDuration = TOTAL_FRAMES * FRAMES_PER_TICK * 2; // Play animation twice
            if (jumpscareDisplayCounter >= jumpscareDisplayDuration) {
                jumpscareActive = false;
                resetJumpscare();
            }
        }
    }

    private static void resetJumpscare() {
        frameCounter = 0;
        // Random between 600 and 6000 frames (30 seconds to 5 minutes)
        jumpscareFrameTarget = RANDOM.nextInt(5400) + 600;
    }
}
