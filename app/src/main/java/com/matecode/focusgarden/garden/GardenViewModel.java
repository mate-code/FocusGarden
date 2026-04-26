package com.matecode.focusgarden.garden;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GardenViewModel extends ViewModel {
    private MutableLiveData<List<GardenTileData>> gardenMap;
    private List<Integer> randomIndexes;
    private int occupated;
    private int rows;
    private final int cols;
    private int size;

    public MutableLiveData<List<GardenTileData>> getGardenMapForObservers() {
        return gardenMap;
    }

    public GardenViewModel(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.size = rows * cols;

        gardenMap = new MutableLiveData<>();
        List<GardenTileData> gardenMapList = new ArrayList<GardenTileData>();
        randomIndexes = new ArrayList<Integer>();

        for (int i = 0; i < size; i++) {
            gardenMapList.add(new GardenTileData(GardenTileStatusEnum.EMPTY));
            randomIndexes.add(i);
        }

        Collections.shuffle(randomIndexes);
        addGardenMapElements(gardenMapList);
    }

    public void addPlant(GardenTileStatusEnum status) {
        int randomIndex = this.getRandomIndex(this.getOccupatedStatus());
        Log.println(Log.DEBUG, "Random index", "index: " + randomIndex);

        this.getGardenMap().get(randomIndex).setStatus(status);
        this.increaseOccupated();

        Log.println(Log.DEBUG, "Timer - DEAD", "occupated - size: " + this.getOccupatedStatus() + " - " + this.getSize());

        if (this.isFull()) {
            this.increaseGardenMap();
            Log.println(Log.DEBUG, "GardenMap increased", "occupated - size: " + this.getOccupatedStatus() + " - " + this.getSize());
        }
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    private void addGardenMapElements(List<GardenTileData> items) {
        List<GardenTileData> current = gardenMap.getValue();
        if (current == null) current = new ArrayList<>();

        List<GardenTileData> updated = new ArrayList<>(current);
        updated.addAll(items);

        gardenMap.setValue(updated);
    }

    private void increaseGardenMap() {
        List<GardenTileData> gardenMapList = new ArrayList<GardenTileData>();

        for (int i = 0; i < cols; i++) {
            gardenMapList.add(new GardenTileData(GardenTileStatusEnum.EMPTY));
            randomIndexes.add(size + i);
        }
        Collections.shuffle(randomIndexes.subList(size, size + cols));
        addGardenMapElements(gardenMapList);

        rows += 1;
        size += cols;
    }

    private int getRandomIndex(int index) {
        return randomIndexes.get(index);
    }

    private int getSize() {
        return this.size;
    }

    private List<GardenTileData> getGardenMap() {
        return this.gardenMap.getValue();
    }

    private boolean isFull() {
        return occupated == Objects.requireNonNull(gardenMap.getValue()).size();
    }

    private int getOccupatedStatus() {
        return occupated;
    }

    private void increaseOccupated() {
        occupated += 1;
    }
}
