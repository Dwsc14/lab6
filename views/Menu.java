package views;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Menu<T> {
    protected String tiltle;
    protected ArrayList<T> mChoice;

    public Menu() {
    }

    public Menu(String tiltle, String[] mChoice) {
        this.tiltle = tiltle;
        this.mChoice = new ArrayList<>();
        for (String st : mChoice) {
            this.mChoice.add((T) st);
        }
    }

    public void display() {
        System.out.println(tiltle);
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < mChoice.size(); i++) {
            System.out.println((i + 1) + ". " + mChoice.get(i));
        }
        System.out.println("---------------------------------------------------------");
    }

    public int getChoice() {
        display();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        return sc.nextInt();
    }

    public abstract void excute(int n);

    public void run() {
        while (true) {
            int n = getChoice();
            excute(n);
            if (n > mChoice.size())
                break;
        }
    }
}
