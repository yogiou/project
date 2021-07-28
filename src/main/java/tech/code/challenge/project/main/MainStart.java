package tech.code.challenge.project.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MainStart {
    private final Main main;

    @Autowired
    public MainStart(Main main) {
        this.main = main;
    }

    @EventListener(classes = { ContextStartedEvent.class })
    public void onStartup() throws Exception {
        main.run();
    }
}
