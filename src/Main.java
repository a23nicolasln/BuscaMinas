import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);

        int cy = sc.nextInt();
        int cx = sc.nextInt();

        //Creacion de tablas y almacenamiento de los datos
        char[][] minas= new char[cy][cx];//Solucion de la tabla
        char[][] prog= new char[cy][cx];//Progreso de la tabla
        boolean[][] visited = new boolean[cy][cx];

        for (int i = 0; i < cy; i++) {
            String row = sc.next();
            for (int j = 0; j < cx; j++) {
                minas[i][j] = row.charAt(j);
                prog[i][j] = 'X';
            }
        }

        int repe = sc.nextInt();
        for (int i = 0;i < repe;i++){
            int y = sc.nextInt()-1;
            int x = sc.nextInt()-1;
            procesarCelda(minas,prog,visited,y,x);

        }

        for (int i = 0; i < cy; i++) {
            for (int j = 0; j < cx; j++) {
                System.out.print(prog[i][j]);
            }
            System.out.println();
        } 
        
    }

    private static void procesarCelda(char[][] minas, char[][] prog,boolean[][] visited, int y, int x) {

        if (visited[y][x]) {
            return;
        }

        visited[y][x] = true;

        char c = minas[y][x];
        int mc = 0;    //Minas cercanas

        if (c == '*'){
            prog[y][x] = minas[y][x];
            System.out.println("GAME OVER");
            System.exit(0);
        }

        if (c == '-') {
            if (y > 0) {
                if (minas[y - 1][x] == '*') { // arriba
                    mc++;
                }
                if (x > 0) {
                    if (minas[y - 1][x - 1] == '*') { // arriba izquierda
                        mc++;
                    }
                }
                if (x < minas[y - 1].length - 1) {
                    if (minas[y - 1][x + 1] == '*') { // arriba derecha
                        mc++;
                    }
                }
            }
            if (x > 0) {
                if (minas[y][x - 1] == '*') { // izquierda
                    mc++;
                }
            }
            if (x < minas[y].length - 1) {
                if (minas[y][x + 1] == '*') { // derecha
                    mc++;
                }
            }

            if (y < minas.length - 1) {
                if (x > 0) {
                    if (minas[y + 1][x - 1] == '*') { // abajo izquierda
                        mc++;
                    }
                }
                if (minas[y + 1][x] == '*') { // abajo
                    mc++;
                }
                if (x < minas[y + 1].length - 1) {
                    if (minas[y + 1][x + 1] == '*') { // abajo derecha
                        mc++;
                    }
                }
            }
        }

        if (mc == 0) {
            if (y < minas.length - 1) {
                if (x < minas[y + 1].length - 1) {
                    if (prog[y + 1][x + 1] == 'X') {
                        procesarCelda(minas, prog, visited, y + 1, x + 1);
                    }
                }
                if (prog[y + 1][x] == 'X') {
                    procesarCelda(minas, prog, visited, y + 1, x);
                }
                if (x > 0) {
                    if (prog[y + 1][x - 1] == 'X') {
                        procesarCelda(minas, prog, visited, y + 1, x - 1);
                    }
                }
            }
            if (x < minas[y].length - 1) {
                if (prog[y][x + 1] == 'X') {
                    procesarCelda(minas, prog, visited, y, x + 1);
                }
            }
            if (x > 0) {
                if (prog[y][x - 1] == 'X') {
                    procesarCelda(minas, prog, visited, y, x - 1);
                }
            }
            if (y > 0) {
                if (x < minas[y - 1].length - 1) {
                    if (prog[y - 1][x + 1] == 'X') {
                        procesarCelda(minas, prog, visited, y - 1, x + 1);
                    }
                }
                if (x > 0) {
                    if (prog[y - 1][x - 1] == 'X') {
                        procesarCelda(minas, prog, visited, y - 1, x - 1);
                    }
                }
                if (prog[y - 1][x] == 'X') {
                    procesarCelda(minas, prog, visited, y - 1, x);
                }
            }
        }

        prog[y][x] = mc > 0 ? (char) (mc + '0') : minas[y][x];
    }
}