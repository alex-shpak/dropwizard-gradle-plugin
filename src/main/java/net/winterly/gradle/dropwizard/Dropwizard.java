package net.winterly.gradle.dropwizard;

import org.gradle.api.Action;
import org.gradle.api.Project;

public class Dropwizard {

    private final Project project;

    private String main = null;
    private String classpath = null;

    public Dropwizard(Project project) {
        this.project = project;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public DropwizardExec command(String name) {
        String taskName = String.format("dropwizard%s", name);
        return project.getTasks().create(taskName, DropwizardExec.class);
    }

    public void command(String name, Action<? super DropwizardExec> configuration) {
        configuration.execute(command(name));
    }
}
