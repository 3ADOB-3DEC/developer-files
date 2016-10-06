package poject.root.start;

import poject.root.creates.Dir;
import poject.root.creates.File;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    /**
     * Системные переменные
     */
    private static boolean isRuning = true, isCreat = false, statusCreat= false;
    private static Scanner scn;
    private static String str = "";

    /**
     * команды и атрибуты
     */
    private static String[] commands = {"@html", "@css", "@js", "@php"};
    private static String[] keys = {"s=", "f="};


    /**
     * Переменные для работы с файлом. Тип, путь и имя.
     */
    private static String dir = ".";
    private static String type = "txt";
    private static String names = "fail";


    /**
     * Точка входа в программу
     */
    public static void main(String[] args) {

        //вечный цикл для ввода команд, пока не будет введена команда останавливающая его
        while (isRuning){

            //Переменная для ввода
            scn = new Scanner(System.in);

            //Вывод требования
            System.out.print("Введите команду->");

            //запись в переменную значения из консоли
            str = new String(scn.nextLine());

            //разбиения на массив по пробелам
            String[] out = str.split("\\s");

            //цикл обработки команд
            for (String index: commands){
                //Если есть совпадения команды и первого слова из введённого значения
                if (out[0].equals(index)){
                    //запись в переменную, тип файлов
                    type = out[0].substring(1,out[0].length());
                    //позволяет дальше работать со строкой
                    isCreat = true;
                    //останавливает текщий цикл
                    break;
                }

            }
            //Если была введена правельная команда, то продолжает работать со строкой
            if (isCreat){
                //цикл для просмотра есть ли атрибут "s="
                for (String index: out){
                    //Работа с регулярным выраженияем
                    Pattern p = Pattern.compile(keys[0]);
                    Matcher m = p.matcher(index);
                    //записывает было ли найдено совподение регулярного выражения со строкой
                    boolean root = m.lookingAt();
                    //если есть совпадение
                    if (root){
                        //записывает в переменную значения пути для файлов
                        dir = index.substring(2,index.length());

                    }
                }

                //цикл для просмотра есть ли атрибут "f="
                for (String index: out){
                    //Работа с регулярным выраженияем
                    Pattern p = Pattern.compile(keys[1]);
                    Matcher m = p.matcher(index);
                    //записывает было ли найдено совподение регулярного выражения со строкой
                    boolean root = m.lookingAt();
                    //если есть совпадение
                    if (root){
                        //записывает в переменную имена файлов
                        names = index.substring(2,index.length());
                    }
                }

                //разбивает строку имён по "," и записывает в массив
                String[] name = names.split(",");

                //цикл обработки имён
                for (String index : name){

                    //создание каталогов
                    Dir creatDir = new Dir(dir);

                    //Если каталоги были созданны успешно
                    if (creatDir.creat()){
                        //создаются файлы на каждой итерации
                        File creatFile = new File(creatDir.getSrc()+ "/" + index + "." + type);
                        //Если файл создан успешно, вывод статуса
                        if(creatFile.creat()){
                            System.out.println("Файл: " + creatDir.getSrc()+ "/" + index + "."
                                    + type + " - был успешно создан" );
                        }else{
                            System.out.println("Файл: " + creatDir.getSrc()+ "/" + index + "."
                                    + type + " - невозможно создать" );
                        }
                    }
                    //завершение обработки имён
                }
                //после работы с файлами, идёт отчистка переменных
                destructor();
            /**
             * Если же не была переданна правельная команда для создания,
             * то идёт проверка на соответсвия запроса на выход
             */
            }else if (out[0].equals("@exit")){
                //выход из цикла ввода
                isRuning = false;

            /**
             * Если же не была переданна правельно ни одна команда,
             * то идёт вывод списка команд и атрибутов
             */
            }else {
                System.out.println("help: \n\tкоманды - @html, @css, @js, @php " +
                        "\n\tатрибуты - s=, f=" +
                        "\n\tпример - @php s=C://php/main f=index,main");
            }


            System.out.println();


        }




    }


    //деструктор, отчищает все переменные для работы с файлами
    private static void destructor(){
        isCreat = false;
        str = "";
        dir = ".";
        type = "txt";
        names = "fail";
    }

}
