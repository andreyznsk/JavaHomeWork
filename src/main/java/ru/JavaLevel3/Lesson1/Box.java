package ru.JavaLevel3.Lesson1;

import java.util.ArrayList;

public class Box <T extends Fruit> {
    private ArrayList<T> box;

    public Box(){
        box = new ArrayList<>();
    }

    public void addFruit(T a){
        box.add(a);
    }

    public float getWeight (){
        if(this.box == null||this.box.size()==0)
            return 0;


       // System.out.println("Вес одного фрукта в коробке: " + box.get(0).getWEIGHT());
        return  box.size() * box.get(0).getWEIGHT() ;
    }

    public boolean compare (Box<?> second){
       return  (Math.abs(this.getWeight() - second.getWeight()) < 0.0001);

    }

    public void intersperse(Box<T> source){
        if (this == source) return;

        this.box.addAll(source.box);

        source.box.clear();

    }


}
