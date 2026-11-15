package ru.optivis.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import ru.optivis.config.OptiVisConfig;

public class OptiVisScreen extends Screen {

    private int currentTab = 0;

    public OptiVisScreen() {
        super(Text.literal("OptiVis"));
    }

    @Override
    protected void init() {
        this.clearChildren();

        int tabY = 8;
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Частицы"), b -> {
            currentTab = 0;
            init();
        }).dimensions(10, tabY, 80, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Мир"), b -> {
            currentTab = 1;
            init();
        }).dimensions(95, tabY, 80, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Рендер"), b -> {
            currentTab = 2;
            init();
        }).dimensions(180, tabY, 80, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Небо"), b -> {
            currentTab = 3;
            init();
        }).dimensions(265, tabY, 80, 20).build());

        int y = 40;
        int left = 20;
        int widthBtn = 220;

        if (currentTab == 0) {
            addToggle("Все частицы", OptiVisConfig.INSTANCE.disableAllParticles, v -> OptiVisConfig.INSTANCE.disableAllParticles = v, left, y, widthBtn);
            y += 24;
            addToggle("Взрывы", OptiVisConfig.INSTANCE.disableExplosionParticles, v -> OptiVisConfig.INSTANCE.disableExplosionParticles = v, left, y, widthBtn);
            y += 24;
            addToggle("Дым", OptiVisConfig.INSTANCE.disableSmokeParticles, v -> OptiVisConfig.INSTANCE.disableSmokeParticles = v, left, y, widthBtn);
            y += 24;
            addToggle("Порталы", OptiVisConfig.INSTANCE.disablePortalParticles, v -> OptiVisConfig.INSTANCE.disablePortalParticles = v, left, y, widthBtn);
            y += 24;
            addToggle("Зелья / эффекты", OptiVisConfig.INSTANCE.disablePotionParticles, v -> OptiVisConfig.INSTANCE.disablePotionParticles = v, left, y, widthBtn);
            y += 24;
            addToggle("Критические удары", OptiVisConfig.INSTANCE.disableCriticalParticles, v -> OptiVisConfig.INSTANCE.disableCriticalParticles = v, left, y, widthBtn);
            y += 24;
            addToggle("Вода / пузыри", OptiVisConfig.INSTANCE.disableWaterParticles, v -> OptiVisConfig.INSTANCE.disableWaterParticles = v, left, y, widthBtn);
            y += 24;
            addToggle("Огонь / лава", OptiVisConfig.INSTANCE.disableFireParticles, v -> OptiVisConfig.INSTANCE.disableFireParticles = v, left, y, widthBtn);
        } else if (currentTab == 1) {
            addToggle("Трава", OptiVisConfig.INSTANCE.disableGrass, v -> OptiVisConfig.INSTANCE.disableGrass = v, left, y, widthBtn);
            y += 24;
            addToggle("Цветы", OptiVisConfig.INSTANCE.disableFlowers, v -> OptiVisConfig.INSTANCE.disableFlowers = v, left, y, widthBtn);
            y += 24;
            addToggle("Лианы / лоза", OptiVisConfig.INSTANCE.disableVines, v -> OptiVisConfig.INSTANCE.disableVines = v, left, y, widthBtn);
            y += 24;
            addToggle("Папоротники", OptiVisConfig.INSTANCE.disableFerns, v -> OptiVisConfig.INSTANCE.disableFerns = v, left, y, widthBtn);
            y += 24;
            addToggle("Мёртвый куст", OptiVisConfig.INSTANCE.disableDeadBush, v -> OptiVisConfig.INSTANCE.disableDeadBush = v, left, y, widthBtn);
        } else if (currentTab == 2) {
            addToggle("Заморозка анимаций текстур", OptiVisConfig.INSTANCE.freezeTextureAnimations, v -> OptiVisConfig.INSTANCE.freezeTextureAnimations = v, left, y, widthBtn);
            y += 24;
            addToggle("Отключить руку (1-е лицо)", OptiVisConfig.INSTANCE.disableFirstPersonHand, v -> OptiVisConfig.INSTANCE.disableFirstPersonHand = v, left, y, widthBtn);
            y += 24;
            addToggle("Отключить модель игрока (1-е лицо)", OptiVisConfig.INSTANCE.disableFirstPersonModel, v -> OptiVisConfig.INSTANCE.disableFirstPersonModel = v, left, y, widthBtn);
            y += 28;

            this.addDrawableChild(new SliderWidget(left, y, widthBtn, 20, Text.literal("Дистанция сущностей: " + (int) OptiVisConfig.INSTANCE.entityRenderDistance), OptiVisConfig.INSTANCE.entityRenderDistance / 128.0) {
                @Override
                protected void updateMessage() {
                    setMessage(Text.literal("Дистанция сущностей: " + (int) (this.value * 128.0)));
                }

                @Override
                protected void applyValue() {
                    OptiVisConfig.INSTANCE.entityRenderDistance = this.value * 128.0;
                }
            });
        } else if (currentTab == 3) {
            addToggle("Отключить небо", OptiVisConfig.INSTANCE.disableSky, v -> OptiVisConfig.INSTANCE.disableSky = v, left, y, widthBtn);
            y += 24;
            addToggle("Отключить облака", OptiVisConfig.INSTANCE.disableClouds, v -> OptiVisConfig.INSTANCE.disableClouds = v, left, y, widthBtn);
            y += 24;
            addToggle("Отключить солнце", OptiVisConfig.INSTANCE.disableSun, v -> OptiVisConfig.INSTANCE.disableSun = v, left, y, widthBtn);
            y += 24;
            addToggle("Отключить луну", OptiVisConfig.INSTANCE.disableMoon, v -> OptiVisConfig.INSTANCE.disableMoon = v, left, y, widthBtn);
            y += 24;
            addToggle("Отключить звёзды", OptiVisConfig.INSTANCE.disableStars, v -> OptiVisConfig.INSTANCE.disableStars = v, left, y, widthBtn);
        }

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Сохранить"), b -> {
            OptiVisConfig.save();
            this.close();
        }).dimensions(this.width / 2 - 100, this.height - 28, 200, 20).build());
    }

    private void addToggle(String name, boolean value, java.util.function.Consumer<Boolean> setter, int x, int y, int w) {
        this.addDrawableChild(ButtonWidget.builder(Text.literal(name + ": " + (value ? "§aON" : "§cOFF")), b -> {
            setter.accept(!value);
            init();
        }).dimensions(x, y, w, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 12, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
