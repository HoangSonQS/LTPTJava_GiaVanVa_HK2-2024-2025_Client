package iuh.fit.security;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Utility class for managing UI permissions
 */
public class UIPermissionManager {

    /**
     * Apply permission to a UI element. If the user doesn't have permission,
     * the element remains visible but shows a warning when clicked.
     *
     * @param node The UI element to check
     * @param permission The required permission
     */
    public static void applyPermission(Node node, Permission permission) {
        if (node == null) {
            return; // Skip if the UI element is null
        }

        SecurityContext securityContext = SecurityContext.getInstance();

        if (!securityContext.isAuthenticated() || !securityContext.hasPermission(permission)) {
            // Keep the node visible but add a click event to show a warning
            node.setOnMouseClicked(event -> {
                showAccessDeniedAlert();
                event.consume();
            });

            // Add a visual indication that this feature is restricted
            node.setOpacity(0.7); // Make it slightly transparent
            node.setStyle("-fx-border-color: #ff9999; -fx-border-width: 1;"); // Add a light red border
        }
    }

    /**
     * Apply permission to a Pane. If the user doesn't have permission,
     * the pane remains visible but shows a warning when clicked.
     *
     * @param pane The pane to check
     * @param permission The required permission
     */
    public static void applyPermission(Pane pane, Permission permission) {
        if (pane == null) {
            return; // Skip if the pane is null
        }

        SecurityContext securityContext = SecurityContext.getInstance();

        if (!securityContext.isAuthenticated() || !securityContext.hasPermission(permission)) {
            // Keep the pane visible but add a click event to show a warning
            pane.setOnMouseClicked(event -> {
                showAccessDeniedAlert();
                event.consume();
            });

            // Add a visual indication that this feature is restricted
            pane.setOpacity(0.7); // Make it slightly transparent
            pane.setStyle("-fx-border-color: #ff9999; -fx-border-width: 1;"); // Add a light red border
        }
    }

    /**
     * Apply permission to a VBox. If the user doesn't have permission,
     * the VBox remains visible but shows a warning when clicked.
     *
     * @param vbox The VBox to check
     * @param permission The required permission
     */
    public static void applyPermission(VBox vbox, Permission permission) {
        if (vbox == null) {
            return; // Skip if the VBox is null
        }

        SecurityContext securityContext = SecurityContext.getInstance();

        if (!securityContext.isAuthenticated() || !securityContext.hasPermission(permission)) {
            // Keep the VBox visible but add a click event to show a warning
            vbox.setOnMouseClicked(event -> {
                showAccessDeniedAlert();
                event.consume();
            });

            // Add a visual indication that this feature is restricted
            vbox.setOpacity(0.7); // Make it slightly transparent
            vbox.setStyle("-fx-border-color: #ff9999; -fx-border-width: 1;"); // Add a light red border
        }
    }

    /**
     * Apply permission to a MenuItem. If the user doesn't have permission,
     * the menu item remains visible but shows a warning when clicked.
     *
     * @param menuItem The menu item to check
     * @param permission The required permission
     */
    public static void applyPermission(MenuItem menuItem, Permission permission) {
        if (menuItem == null) {
            return; // Skip if the menu item is null
        }

        SecurityContext securityContext = SecurityContext.getInstance();

        if (!securityContext.isAuthenticated() || !securityContext.hasPermission(permission)) {
            // Store the original action
            EventHandler<ActionEvent> originalAction = menuItem.getOnAction();

            // Replace with our action that shows a warning
            menuItem.setOnAction(event -> {
                showAccessDeniedAlert();
                event.consume();
            });

            // Add a visual indication that this feature is restricted
            menuItem.setStyle("-fx-text-fill: #999999;"); // Gray out the text
        }
    }

    /**
     * Apply permission to a Tab. If the user doesn't have permission,
     * the tab remains visible but shows a warning when clicked.
     *
     * @param tab The tab to check
     * @param permission The required permission
     */
    public static void applyPermission(Tab tab, Permission permission) {
        if (tab == null) {
            return; // Skip if the tab is null
        }

        SecurityContext securityContext = SecurityContext.getInstance();

        if (!securityContext.isAuthenticated() || !securityContext.hasPermission(permission)) {
            // Store the original selection listener
            EventHandler<Event> originalHandler = tab.getOnSelectionChanged();

            // Replace with our handler that shows a warning
            tab.setOnSelectionChanged(event -> {
                showAccessDeniedAlert();
                // Switch back to the previous tab
                tab.getTabPane().getSelectionModel().select(tab.getTabPane().getSelectionModel().getSelectedIndex() - 1);
                event.consume();
            });

            // Add a visual indication that this feature is restricted
            tab.setStyle("-fx-text-fill: #999999;"); // Gray out the text
        }
    }

    /**
     * Apply permission to a Button. If the user doesn't have permission,
     * the button remains visible but shows a warning when clicked.
     *
     * @param button The button to check
     * @param permission The required permission
     */
    public static void applyButtonPermission(Button button, Permission permission) {
        if (button == null) {
            return; // Skip if the button is null
        }

        SecurityContext securityContext = SecurityContext.getInstance();

        if (!securityContext.isAuthenticated() || !securityContext.hasPermission(permission)) {
            // Store the original action
            EventHandler<ActionEvent> originalAction = button.getOnAction();

            // Replace with our action that shows a warning
            button.setOnAction(event -> {
                showAccessDeniedAlert();
                event.consume();
            });

            // Add a visual indication that this feature is restricted
            button.setOpacity(0.7); // Make it slightly transparent
            button.setStyle("-fx-border-color: #ff9999; -fx-border-width: 1;"); // Add a light red border
        }
    }

    /**
     * Show an access denied alert
     */
    private static void showAccessDeniedAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Quyền truy cập bị từ chối");
        alert.setHeaderText(null);
        alert.setContentText("Bạn không có quyền truy cập chức năng này.");
        alert.showAndWait();
    }
}
