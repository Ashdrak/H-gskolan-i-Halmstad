import java.io.*;

public class Trader extends Thread {
    DataBuffer<StockPick> stockPicks;
    // Fler intstansvariabler?

    int nrPicks; // nr of stock picks for printing to log-file each second
    int endTime; // time to run in seconds

    public Trader(DataBuffer<StockPick> stockPicks,
            int bufferSize, int nrPicks, int endTime) {
        this.stockPicks = stockPicks;
        this.nrPicks = nrPicks;
        this.endTime = endTime;
    }

    public void run() {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("log.txt"));
            writer.write("Start\n");
            writer.close();
        } catch (IOException e) {
        }

        int time = 0;
        while (time < endTime) {

            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }

            /*
             * LÃ¤gg till kod hÃ¤r.
             * 
             * 1: TÃ¶m stockPicks och lÃ¤gg elementen i en tom priortietskÃ¶.
             * Denna prioritetskÃ¶ kan t.ex. vara en instansvariabel och initieras i
             * konstruktorn.
             * 
             * 2: Ta de nrPicks stÃ¶rsta elementen frÃ¥n prioritetskÃ¶n och skriv
             * ut dessa i prioritetsordning sist i log.txt. Ni ska inte skriva
             * Ã¶ver det som finns i filen utan lÃ¤gga till pÃ¥ slutet.
             */

            try (OutputStreamWriter dataOut = new OutputStreamWriter(new FileOutputStream("log.txt", true))) {
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            time++;

            System.out.println("Time elapsed: " + time
                    + " seconds.");
        }
    }

    public static void main(String[] cmdLn) {
        int bufferSize = 50;
        DataBuffer<StockPick> stockPicks = new DataBuffer<StockPick>(bufferSize);

        // StockPicker 1
        String[] stocks1 = new String[] {
                "TSLA", "CCJ", "GME", "UUUU",
                "MFST", "GOOGL", "AAPL",
                "AMZN" };

        StockPicker Stockpicker1 = new StockPicker("North America analyzer",
                stockPicks, stocks1, 10);

        // StockPicker 2
        String[] stocks2 = new String[] {
                "ETH", "BTC" };

        StockPicker Stockpicker2 = new StockPicker("Cryptocurrencices analyzer",
                stockPicks, stocks2, 10);

        // trader
        Trader trader = new Trader(stockPicks, bufferSize, 3, 10);

        // run simulation
        Stockpicker1.start();
        Stockpicker2.start();
        trader.start();
    }

}