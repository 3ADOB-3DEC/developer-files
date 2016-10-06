package poject.root.creates;

public class File {
    /**
     * переменная для записи имени
     */
    private String name;

    /**
     * Конструктор
     */
    public File(String name) {

        this.name = name;
    }

    /**
     * Метод для создания файла
     */
    public boolean creat(){
        //передётся имя файла системному классу для создаия файла
        java.io.File file = new java.io.File(name);

        //для работы с ошибками
        try {

            //создание файла
            file.createNewFile();
            //выход из метода и возвращение true
            return true;

        } catch (Exception e) {
            System.out.println("Не возможно создать файл(ы)");
        }

        //выход из метода и возвращение false
        return false;

    }
}
