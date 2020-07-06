
package blokus.Interface;

/**
 *
 * @author selim
 */
public interface Observable {
    
    public void addObs(Observer obs);
    public void removeObs(Observer obs);
    public void notifyObs();
    
}
