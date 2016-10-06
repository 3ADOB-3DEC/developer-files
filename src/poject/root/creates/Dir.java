package poject.root.creates;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dir {
    /**
     * Переменные для работы с каталогом
     */
    private String src;
    private boolean root;

    /**
     * Конструктор
     */
    public Dir(String src) {
        this.src = src;
    }

    /**
     * Метод для создания каталогов
     */
    public boolean creat(){


        //Работа с регулярным выраженияем
        Pattern p = Pattern.compile("[A-Za-z]://");
        Matcher m = p.matcher(src);
        //возврат истины, если в указанном пути есть кориню файловой системы
        root = m.lookingAt();

        //если нет коря файловой системы генерирует путь из текущего каталога
        if(!root){ src = System.getProperty("user.dir") + src.substring(1,src.length()); }

        //передётся имя каталога системному классу для создаия каталога
        File creatDir = new File(src);

        //для работы с ошибками
        try {
            //создание каталогов
            creatDir.mkdirs();
            //выход из метода и возвращение true
            return true;
        } catch (Exception e) {
            System.out.println("Не возможно создать каталог(и)");
        }

        //выход из метода и возвращение false
        return false;


    }

    //возврат значения пути
    public String getSrc() {
        return src;
    }
}
