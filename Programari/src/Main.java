import Presentation.Controller.*;
import Presentation.Views.*;

public class Main {

    public static void main(String[] args) throws java.io.IOException {

        ViewLogin viewLogin = new ViewLogin();
        ControllerLogin controllerLogin = new ControllerLogin(viewLogin);
        viewLogin.registerController(controllerLogin);
        viewLogin.setVisible(false);

        ViewRegister viewRegister = new ViewRegister();
        ControllerRegister controllerRegister = new ControllerRegister(viewRegister);
        viewRegister.registerController(controllerRegister);
        viewRegister.setVisible(false);

        ViewNewGame viewNewGame = new ViewNewGame();
        ControllerNewGame controllerNewGame = new ControllerNewGame(viewNewGame);
        viewNewGame.registerController(controllerNewGame);
        viewNewGame.setVisible(false);

        ViewObjective viewObjective = new ViewObjective();
        ControllerObjective controllerObjective = new ControllerObjective(viewObjective);
        viewObjective.registerController(controllerObjective);
        viewObjective.setVisible(false);

        ViewPlayerAction viewPlayerAction = new ViewPlayerAction();
        ControllerPlayerAction controllerPlayerAction = new ControllerPlayerAction(viewPlayerAction);
        viewPlayerAction.registerController(controllerPlayerAction);
        viewPlayerAction.setVisible(false);

        ViewGameManagement viewGameManagement = new ViewGameManagement();
        ControllerGameManagement controllerGameManagement = new ControllerGameManagement(viewGameManagement);
        viewGameManagement.registerController(controllerGameManagement);
        viewGameManagement.setVisible(false);

        ViewLogout viewLogout = new ViewLogout();
        ControllerLogout controllerLogout = new ControllerLogout(viewLogout);
        viewLogout.logoutController(controllerLogout);
        viewLogout.setVisible(false);

        ViewLog viewLog = new ViewLog();
        ControllerLog controllerLog = new ControllerLog(viewLog);
        viewLog.registerController(controllerLog, controllerLog);
        viewLog.setVisible(false);


        MainController mainController = new MainController(viewRegister, controllerRegister, viewLogin, controllerLogin, viewGameManagement, controllerGameManagement, viewLogout, controllerLogout, viewNewGame, controllerNewGame, viewPlayerAction, controllerPlayerAction, viewObjective, controllerObjective,viewLog,controllerLog);

        controllerLogin.setMainController(mainController);
        controllerRegister.setMainController(mainController);
        controllerNewGame.setMainController(mainController);
        controllerObjective.setMainController(mainController);
        controllerPlayerAction.setMainController(mainController);
        controllerGameManagement.setMainController(mainController);
        controllerLogout.setMainController(mainController);
        controllerLog.setMainController(mainController);

        mainController.showVistaLogin();

    }
}
