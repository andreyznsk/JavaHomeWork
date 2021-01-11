package ru.geekbrains.Lesson8;


import ru.geekbrains.Lesson7.Cat;
import ru.geekbrains.Lesson7.Plate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {
      Cat cat = new Cat("Безимянный",10);
      Plate plate = new Plate(100);

    public static void main(String[] args) {
        MyWindow window = new MyWindow();
    }


    public MyWindow() {


        setTitle("Test Window");



            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setBounds(250, 100, 300, 200);

            final JTextField fieldName = new JTextField(); // Поле имя кота


            fieldName.setPreferredSize(new Dimension(67, 26)); //Устанока размера поля
            JButton buttonPrintInfo = new JButton("Вывести информацию коте");
            JButton buttonPlateInfo = new JButton("Информация о тарелке");
            JButton buttonRename = new JButton("Назвать кота");
            JButton buttonEat = new JButton("Есть");
            JButton buttonAddFood = new JButton("добавить еды");
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
            setLayout(new FlowLayout());
            add(buttonRename);
            add(fieldName);

            add (buttonPrintInfo);
            add(buttonPlateInfo);
            add(buttonEat);
            add(buttonAddFood);

            buttonRename.addActionListener(new ActionListener() {//Листнер на переименование
                                         @Override
                                        public void actionPerformed(ActionEvent e) {
                                            String name = fieldName.getText();
                                            if(name.length()==0)
                                                System.out.println("Введите имя кота");

                                            else {
                                                cat.setName(name);

                                            }
                                        }
            });
        //Лимтнеры на все кнопки
        buttonPrintInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             cat.printInfo();
            }
        });

            buttonEat.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                  cat.eat(plate);
                }

                });

            buttonPlateInfo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    plate.info();
                }
            });
            buttonAddFood.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    plate.addFood(10);
                }
            });
            setVisible(true);
        }

    }




