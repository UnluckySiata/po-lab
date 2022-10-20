package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        Animal a = new Animal();
        MoveDirection[] moves = OptionsParser.parse(args);

        for (int i = 0; i < moves.length; ++i) {
            a.move(moves[i]);
        }

        System.out.println(a);

    }

}
