package thread;

public class ExThreadsMain implements Runnable{
    private int min;
    private int max;
    private int threadNumber;
    public static void main(String[] args){
        int maxNum = 100;   //計算する数の最大値
        int threadNum = 4;  //使用するスレッドの数

        //クラスのインスタンス作成
        ExThreadsMain[] ex = new ExThreadsMain[threadNum];
        for(int i = 0; i < threadNum; i ++){
            ex[i] = new ExThreadsMain((maxNum/threadNum) * i, (maxNum/threadNum)*(i + 1) ,i);
        }

        //スレッドの作成
        Thread[] th = new Thread[threadNum];
        for(int i = 0; i < threadNum; i ++){
            th[i] = new Thread(ex[i]);
        }

        //スレッドの開始
        for(int i = 0; i < th.length; i ++){
            th[i].start();
        }

    }

    public void run() {

        // この try-catch ブロックは、0 から 9 までの値を 1000 ミリ秒間隔で出力するループを実行します。
        try {
            //1000ミリ秒に一回指定範囲の小さい順から素数の判定を行う
            for(int i = min; i <= max; i ++){
                boolean isPrime = true;
                if(i <= 1) isPrime = false;
                else if(i == 2) isPrime = true;
                else if(i%2 == 0) isPrime = false;
                else{
                    for(int j = 3; j <= Math.sqrt(i); j += 2){
                        if(i%j == 0) isPrime = false;
                    }
                }
                if(isPrime) System.out.println("NO." + (threadNumber+1)+ "の計算結果" +i);

                // スレッドを 1000 ミリ秒間一時停止します。
                Thread.sleep(1000);  // ミリ秒単位のスリープ
            }
        }
        catch(InterruptedException e) {
            // スレッドが中断された場合は、例外を出力します。
            System.err.println(e);
        }
    }

    public ExThreadsMain(int min, int max, int threadNumber){
        this.min = min;
        this.max = max;
        this.threadNumber = threadNumber;
    }
}