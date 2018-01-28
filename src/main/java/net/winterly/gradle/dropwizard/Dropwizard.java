package net.winterly.gradle.dropwizard;

import org.gradle.api.Action;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.tasks.TaskContainer;

public class Dropwizard {

    private final Project project;
    private final TaskContainer tasks;

    public String main = null;
    public String classpath = "main";
    public String configuration = null;
    public String prefix = "dropwizard";

    public Dropwizard(Project project) {
        this.project = project;
        this.tasks = project.getTasks();
    }

    /**
     * Finds or creates dropwizard task
     *
     * @param name task suffix
     * @return existing or created task, null if existing task has other type
     */
    public DropwizardExec command(String name) {
        String taskName = taskName(prefix, name);

        Task task = tasks.findByPath(taskName);
        if (task == null) {
            return tasks.create(taskName, DropwizardExec.class);
        }

        if (task instanceof DropwizardExec) {
            return (DropwizardExec) task;
        }

        return null;
    }

    /**
     * Same as {@link #command(String)} with configuration action
     *
     * @param name          task suffix
     * @param configuration configuration closure
     */
    public void command(String name, Action<? super DropwizardExec> configuration) {
        configuration.execute(command(name));
    }

    /**
     * Creates task name, if prefix is empty just returns name. <br/>
     * For example taskName("dw", "check") returns "dwCheck"
     *
     * @param prefix global name prefix
     * @param name   task name
     * @return generated task name
     */
    private static String taskName(String prefix, String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Task name can not be empty");
        }

        if (prefix.isEmpty()) {
            return name;
        }

        return name.substring(0, 1).toUpperCase().concat(name.substring(1));
    }
}
