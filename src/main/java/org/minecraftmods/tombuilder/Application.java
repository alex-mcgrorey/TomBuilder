package org.minecraftmods.tombuilder;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.minecraftmods.tombuilder.builder.Builder;
import org.minecraftmods.tombuilder.listeners.SelectionListener;

import java.util.ArrayList;

public class Application extends JavaPlugin {
    public ArrayList<Builder> builders = new ArrayList<>();

    @Override
    public void onEnable() {
        SelectionListener selectionListener = new SelectionListener();
        Bukkit.getPluginManager().registerEvents(selectionListener, this);
    }

    @Override
    public void onDisable() {

    }
}
