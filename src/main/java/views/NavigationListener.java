package main.java.views;

import main.java.models.Project;

public interface NavigationListener {
    void onLoginSelected();
    void onDashboardSelected(Project project);
}
