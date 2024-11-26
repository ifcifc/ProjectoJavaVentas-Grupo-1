package com.ventas.data;

import com.ventas.models.ArticuloModel;
import com.ventas.models.HistoryModel;

public class StockData {
    private HistoryModel<Integer> history;
    private ArticuloModel articulo;

    public StockData(HistoryModel<Integer> history, ArticuloModel articulo) {
        this.history = history;
        this.articulo = articulo;
    }

    public HistoryModel<Integer> getHistory() {
        return history;
    }

    public ArticuloModel getArticulo() {
        return articulo;
    }

    @Override
    public String toString() {
        return "StockData{" + "history=" + history + ", articulo=" + articulo + '}';
    }
    
    
}
