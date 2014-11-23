package mta.se.interfaces;

/**
 * Created by Cristian on 11/22/2014.
 */
public interface IView {
    public void onMessage(boolean isError, String message);
}
