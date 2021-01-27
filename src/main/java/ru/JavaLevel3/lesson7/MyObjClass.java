package ru.JavaLevel3.lesson7;

public class MyObjClass {
    private static int personNumber = 0;//Статическая переменная - количество сторудников. Количество объектов даного класса.


    private String fullname;
    private int age;


    public MyObjClass() {//Конструктор по умолчанию
        this.fullname = "Default";
        this.age = 0;
        personNumber++;

    }

    //Конструктор со строками
    public MyObjClass(String fullname, int age) {
        this.fullname = fullname;
        this.age = age;
        personNumber++;

    }

    public void setFullname(String fullname) {
        this.fullname = fullname;

    }

    public void setAge(int age) {
        this.age = age;
    }

    public void printMyObjClass(){
        System.out.println("Информация и сотрудинке: ");

        System.out.println("1. ФИО: " + this.fullname);
        System.out.println("6. Возраст: " + this.age);

    }


    public int getAge() {
        return this.age;
    }


    @Test
    public static int getPersonNumber() {
        return personNumber;
    }

    @Test(priority = 7)
    public static void methodTest1(){
        System.out.println("Test1");
    }

    @Test
    public static void methodTest2(){
        System.out.println("Test2");
    }

    @Test(priority = 6)
    public static void methodTest3(){
        System.out.println("Test3");
    }


    @BeforeSuite
    public static void makeInstance(){
        MyObjClass obj = new MyObjClass("Andrey",36);
        System.out.println("Заустилп до тестирования и создал объеке тестируемого класса");
    }

   // @BeforeSuite
    public static void testBefroe(){
        System.out.println("Не должно быть запущенно");
    }

    @AfterSuite
    public static void makeAfter(){
        System.out.println("После тестированя, удалил объект тестирования");
    }

    //@AfterSuite
    public static void makeAfterTest(){
        System.out.println("Не должно быть запущенно");
    }


}
