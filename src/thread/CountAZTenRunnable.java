package thread;

public class CountAZTenRunnable implements Runnable {
    private char alphabet = 0;

    // main メソッドはプログラムのエントリーポイントです。
    public static void main(String[] args){
        // 2つの文字を初期化します。
        char[] c = new char[26]; // ASCII値 97 は 'a' です
        c[0] = 97;
        for(int i = 1; i < 26; i ++){
            c[i] = (char)(c[i-1] + 1);
        }

        // 初期化した文字をコンソールに出力します。
        for(int i = 0; i < c.length; i++){
            System.out.println(c[i]);
        }

        // CountTenRunnable クラスのインスタンスを作成します。
        CountAZTenRunnable[] ct = new CountAZTenRunnable[26];
        for(int i = 0; i < ct.length; i ++){
            ct[i] = new CountAZTenRunnable();
            ct[i].setChar(c[i]);
        }
        
        // ct を実行する新しいスレッドを作成します。
        Thread[] th = new Thread[26];
        for(int i = 0; i < th.length; i ++){
            th[i] = new Thread();
            th[i] = new Thread(ct[i]);
        }

        // スレッドを開始します。これにより、CountTenRunnable の run メソッドが呼び出されます。
        for(int i = 0; i < th.length; i ++){
            th[i].start();
        }

        // この try-catch ブロックは、0 から 9 までの値を 500 ミリ秒間隔で出力するループを実行します。
        try {
            for(int i = 0; i < 10; i++) {
                System.out.println("main:i=" + i);

                // メインスレッドを 500 ミリ秒間一時停止します。
                Thread.sleep(500);  // ミリ秒単位のスリープ時間
            }
        }
        catch(InterruptedException e) {
            // スレッドが中断された場合は、例外を出力します。
            System.err.println(e);
        }
    }

    // run メソッドは、新しいスレッドが実行するコードを含みます。
    public void run() {
        // この try-catch ブロックは、0 から 9 までの値を 1000 ミリ秒間隔で出力するループを実行します。
        try {
            for(int i = 0; i < 10; i++) {
                System.out.println(alphabet + " runnable thread:i=" + i);

                // スレッドを 1000 ミリ秒間一時停止します。
                Thread.sleep(1000);  // ミリ秒単位のスリープ時間
            }
        }
        catch(InterruptedException e) {
            // スレッドが中断された場合は、例外を出力します。
            System.err.println(e);
        }
    }

    public void setChar(char alphabet){
        this.alphabet = alphabet;
    }
}
