package notepadProject;

import java.util.Stack;

//Geri al�nacak i�lemleri ger�ekle�tirecek Receiver nesnesi
class Document {
 private String content;

 public void setContent(String content) {
     this.content = content;
 }

 public void undoSetContent(String oldContent) {
     this.content = oldContent;
 }
}

//Geri al�nacak i�lemleri tan�mlayan Command nesnesi
interface Command {
 void execute();
 void undo();
}

class SetContentCommand implements Command {
 private Document document;
 private String oldContent;
 private String newContent;

 public SetContentCommand(Document document, String newContent) {
     this.document = document;
     this.newContent = newContent;
 }

 @Override
 public void execute() {
     oldContent = document.getContent();
     document.setContent(newContent);
 }

 @Override
 public void undo() {
     document.undoSetContent(oldContent);
 }
}

//Geri al�nacak i�lemleri tutan Invoker nesnesi
class History {
 private Stack<Command> undoStack = new Stack<>();
 private Stack<Command> redoStack = new Stack<>();

 public void execute(Command command) {
     command.execute();
     undoStack.push(command);
     redoStack.clear();
 }

 public void undo() {
     if (undoStack.isEmpty()) {
         return;
     }

     Command command = undoStack.pop();
     command.undo();
     redoStack.push(command);
 }

 public void redo() {
     if (redoStack.isEmpty()) {
         return;
     }

     Command command = redoStack.pop();
     command.execute();
     undoStack.push(command);
 }
}

//Kullan�m �rne�i
class Notepad {
 public static void main(String[] args) {
     Document document = new Document();
     History history = new History();

     // ��erik de�i�tirme i�lemi ger�ekle�tiriliyor
     history.execute(new SetContentCommand(document, "Bu bir notepad uygulamas�d�r."));

     // ��erik de�i�tirme i�lemi geri al�n�y
     history.undo();

     // ��erik de�i�tirme i�lemi tekrar ger�ekle�tiriliyor
     history.redo();
 }
}
