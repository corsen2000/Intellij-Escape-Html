import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.command.UndoConfirmationPolicy;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

public class EscapeHtmlHandler extends EditorActionHandler {
    @Override
    public void execute(final Editor editor, DataContext dataContext) {
        if (editor == null) {
            return;
        }

        final String selectedText = editor.getSelectionModel().getSelectedText();
        if (selectedText == null) {
            return;
        }
        final String newText = escapeHtml(selectedText);

        final int startPos = editor.getSelectionModel().getSelectionStart();
        final int endPos = editor.getSelectionModel().getSelectionEnd();

        CommandProcessor.getInstance().executeCommand(editor.getProject(), new Runnable() {
            @Override
            public void run() {
                ApplicationManager.getApplication().runWriteAction( new Runnable() {
                    @Override
                    public void run() {
                        editor.getDocument().replaceString(startPos, endPos, newText);
                    }});
            }
        }, "", null, UndoConfirmationPolicy.DO_NOT_REQUEST_CONFIRMATION);
    }
}