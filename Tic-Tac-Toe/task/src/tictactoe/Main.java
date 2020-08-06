package tictactoe;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static boolean currentTurn = true;
    public static void main(String[] args) {
        char[][] arr = createField();
        char[] line;
        String result;
        do {
            printField(arr);
            enterCell(arr);
            line = twoDimIntoOne(arr);
            result = checkWin(line);
            if (!isMoreSpace(line) & result.length() < 6) {
                result = "Draw";
                break;
            }
        } while (result.equals("nan"));
        printField(arr);
        System.out.println(result);
    }

    public static char[][] createField() {
        char[][] arr = new char[3][3];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = ' ';
            }
        }
        return arr;
    }


    public static boolean isMoreSpace(char[] arr) {
        boolean isMore = false;
        for (char ch : arr) {
            if (ch == ' ') {
                isMore = true;
            }
        }
        return isMore;
    }

    public static char[] twoDimIntoOne(char[][] arr) {
        char[] line = new char[9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                line[i * 3 + j] = arr[i][j];
            }
        }
        return line;
    }

    public static void printField(char[][] arr) {
        System.out.print("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("\n| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print("|");
        }
        System.out.println("\n---------");
    }

    public static String checkWin(char[] field) {
        StringBuilder sb = new StringBuilder();
        int[] winningPos = {
                0, 1, 2,
                3, 4, 5,
                6, 7, 8,
                0, 3, 6,
                1, 4, 7,
                2, 5, 8,
                0, 4, 8,
                2, 4, 6
        };
        for (int i = 0; i < 24;  i += 3) {
                if (getOSum(field, winningPos[i], winningPos[i + 1], winningPos[i + 2])) {
                    sb.append("O wins");
                }
                if (getXSum(field, winningPos[i], winningPos[i + 1], winningPos[i + 2])) {
                    sb.append("X wins");
                }
        }
        return sb.length() != 0 ? sb.toString() : "nan";
    }

    public static boolean getOSum(char[] inputLine, int first, int second, int third) {
        int sum = inputLine[first] + inputLine[second]+ inputLine[third];
        return sum == 237;
    }

    public static boolean getXSum(char[] inputLine, int first, int second, int third) {
        int sum = inputLine[first] + inputLine[second]+ inputLine[third];
        return sum == 264;
    }

    public static char[][] enterCell(char[][] field) {
        boolean stop = false;
        String xI;
        String yI;
        int x;
        int y;
        do {
            System.out.print("Enter the coordinates: ");
            xI = sc.next();
            yI = sc.next();
            if (xI.matches("[-+]?\\d+") & yI.matches("[-+]?\\d+")) {
                x = Integer.parseInt(xI);
                y = Integer.parseInt(yI);
            } else {
                System.out.println("You should enter numbers!");
                continue;
            }
            if (x > 3 | y > 3 | x <= 0 | y <= 0) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } if (field[3-y][x-1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
            } else  {
             field[3-y][x-1] = currentTurn ? 'X' : 'O';
             currentTurn = !currentTurn;
             stop = true;
            }
        } while (!stop);
        return field;
    }
}