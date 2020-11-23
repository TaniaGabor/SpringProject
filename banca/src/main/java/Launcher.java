import controller.LoginController;
import view.LoginView;

public class Launcher {
    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.getInstance(false);
        new LoginController(new LoginView(), componentFactory.getAuthenticationService(),componentFactory.getAccountService(),componentFactory.getClientService());
    }
}
