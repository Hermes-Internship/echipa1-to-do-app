import repository.TaskRepository;
import repository.UserRepository;
import service.Service;
import ui.UI;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props=new Properties();
        try {
            props.load(new FileReader("bd.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }

        UserRepository userRepository = new UserRepository(props);
        TaskRepository taskRepository = new TaskRepository(props);
        Service service = new Service(userRepository, taskRepository);
        UI ui = new UI(service);

        ui.run();
    }
}
