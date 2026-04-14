package com.matecode.focusgarden.Garden;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GardenViewModel extends ViewModel {
    private MutableLiveData<List<GardenTileData>> gardenMap;
    private List<Integer> randomIndexes;
    private int occupated;

    private GardenTileStatusEnum plantStatus;
    private int rows;
    private final int cols;
    private int size;

    public void addGardenMapElement(GardenTileData item) {
        List<GardenTileData> current = gardenMap.getValue();
        if (current == null) current = new ArrayList<>();

        List<GardenTileData> updated = new ArrayList<>(current);
        updated.add(item);

        gardenMap.setValue(updated);
    }

    public void addGardenMapElements(List<GardenTileData> items) {
        List<GardenTileData> current = gardenMap.getValue();
        if (current == null) current = new ArrayList<>();

        List<GardenTileData> updated = new ArrayList<>(current);
        updated.addAll(items);

        gardenMap.setValue(updated);
    }



    public MutableLiveData<List<GardenTileData>> getGardenMapForObservers() {
        return gardenMap;
    }


    public GardenViewModel(int rows, int cols) {
        plantStatus = GardenTileStatusEnum.EMPTY;
        
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

    public void increaseGardenMap() {
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

    public int getRandomIndex(int index) {
        return randomIndexes.get(index);
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

    public GardenTileStatusEnum getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(GardenTileStatusEnum plantStatus) {
        this.plantStatus = plantStatus;
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public int getSize() {
        return this.size;
    }

    public List<GardenTileData> getGardenMap() {
        return this.gardenMap.getValue();
    }

    public boolean isFull() {
        return occupated == gardenMap.getValue().size();
    }

    public int getOccupatedStatus() {
        return occupated;
    }

    public void increaseOccupated() {
        occupated += 1;
    }
}
