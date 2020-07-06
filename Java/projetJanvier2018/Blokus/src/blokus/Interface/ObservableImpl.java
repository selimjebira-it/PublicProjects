/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blokus.Interface;

import java.util.ArrayList;


public class ObservableImpl implements Observable {
    
    private final ArrayList<Observer> observers;

    public ObservableImpl() {
        this.observers = new ArrayList<>();
    }
    
    

    @Override
    public void addObs(Observer obs) {
        observers.add(obs);
        notifyObs();
        
    }

    @Override
    public void removeObs(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObs() {
        observers.forEach(Observer::update);
    }
    
}
