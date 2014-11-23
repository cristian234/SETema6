import mta.se.controller.WeatherController;
import mta.se.model.WeatherModel;
import mta.se.view.WeatherView;

/**
 * Created by Cristian on 11/22/2014.
 */
public class WeatherMain {
    public static void main(String [] args)
    {
       // WeatherView form=new WeatherView();
       // NewForm form = new NewForm();
        WeatherModel nModel = new WeatherModel();
        WeatherController nController = new WeatherController();
        WeatherView nView = new WeatherView();

        nModel.addModelListener(nView);

        nView.addModel(nModel);
        nView.addController(nController);

        nController.addModel(nModel);
        nController.addView(nView);

        nView.setVisible(true);
        /*// Attach the view to the model
        model.addModelListener(view);

        // Tell the view about the model and the controller
        view.addModel(model);
        view.addController(controller);

        // Tell the controller about the model and the view
        controller.addModel(model);
        controller.addView(view);

        // Display the view
        view.setVisible(true);*/
    }
}
