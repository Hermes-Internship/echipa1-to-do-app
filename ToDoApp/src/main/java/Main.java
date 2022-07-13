import repository.TaskRepository;
import repository.UserRepository;
import service.Service;
import ui.UI;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        TaskRepository taskRepository = new TaskRepository();
        Service service = new Service(userRepository, taskRepository);
        UI ui = new UI(service);

        ui.run();

        System.out.println("Hello world! :>");
    }
}
