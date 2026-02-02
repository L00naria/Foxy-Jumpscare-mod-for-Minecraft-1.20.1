# Jumpscare Mod

A mod for minecraft 1.20.1 Minecraft Forge mod that randomly displays the Withered Foxy jumpscare with sound!

## Features

- Random jumpscare events after a random number of frames (30 seconds to 5 minutes)
- Full-screen image overlay
- Jumpscare sound effect that plays automatically

## Installation

1. Ensure you have Minecraft Forge 1.20.1 (version 47.2.19 or later) installed
2. Place the mod JAR file in your `mods` folder
3. Launch Minecraft with the Forge profile

## Building

```bash
./gradlew build
```

The compiled mod will be in `build/libs/jumpscaremod-1.0.jar`

## Asset Files (REQUIRED)

You **must** add these files for the mod to work:

### Texture/Image
Place your jumpscare image (PNG format) at:
```
src/main/resources/assets/jumpscaremod/textures/jumpscare.png
```
- **Recommended size**: 1920x1080 or higher (will scale to screen resolution)
- **Format**: PNG file
- This will be displayed full-screen when jumpscare triggers

### Sound File
Place your jumpscare sound file at:
```
src/main/resources/assets/jumpscaremod/sounds/jumpscare_sound.ogg
```
- **Format**: OGG Vorbis audio format
- **Recommended duration**: 1-3 seconds
- This will play automatically when the jumpscare triggers

## Configuration

You can adjust the jumpscare timing by modifying values in [src/main/java/com/example/jumpscaremod/client/JumpscareEventHandler.java](src/main/java/com/example/jumpscaremod/client/JumpscareEventHandler.java):

- `JUMPSCARE_DISPLAY_DURATION`: How long the jumpscare stays visible (in ticks, default 40 = 2 seconds)
- Random range in `resetJumpscare()`: Adjust the min/max frame count for when jumpscare triggers
  - Default: 600-6000 frames (30 seconds to 5 minutes at 20 TPS)

## Sound Files

To add sound to your jumpscare, place OGG audio files in:
```
src/main/resources/assets/jumpscaremod/sounds/
```

File name should match what's registered in [src/main/java/com/example/jumpscaremod/sound/ModSounds.java](src/main/java/com/example/jumpscaremod/sound/ModSounds.java) - currently `jumpscare_sound.ogg`

## How It Works

1. The mod tracks frame count using Minecraft's render event
2. Every jumpscare trigger, a random interval (30 sec to 5 min) is set
3. When the interval is reached, the jumpscare image displays full-screen
4. The registered sound effect plays automatically
5. After 2 seconds, the jumpscare disappears and a new random interval starts

