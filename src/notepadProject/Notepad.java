package notepadProject;

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