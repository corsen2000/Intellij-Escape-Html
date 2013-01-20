import com.intellij.openapi.editor.actionSystem.EditorAction;

public class EscapeHtml extends EditorAction {
    public EscapeHtml() {
        super(new EscapeHtmlHandler());
    }
}
