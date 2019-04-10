import java.util.ArrayList;

public class CoinSet {
    private ArrayList<Coin> coinSet;

    public CoinSet() {
        coinSet = new ArrayList<Coin>();
    }

    public void addCoin(Coin coin) {
        coinSet.add(coin);
    }

    public String removeCoins() {
        String result = "" + getTotal();
        coinSet.removeAll(coinSet);
        return result;
    }

    public ArrayList<Coin> getCoins() {
        return coinSet;
    }

    public Coin[] getCoinsArray() {
        ArrayList<Coin> coinsArrayList = getCoins();
        coinsArrayList.trimToSize();
        Coin[] coinsArray = new Coin[coinsArrayList.size()];
        coinsArray = coinsArrayList.toArray(coinsArray);

        return coinsArray;
    }

    public void addCoin(ArrayList<Coin> coinSet) {
        this.coinSet.addAll(coinSet);
    }

    public double getTotal() {
        double result = 0;

        for (int i = 0; i < coinSet.size(); i++) {
            result += coinSet.get(i).getValue();
        }

        return result;
    }


    public double getChange(double price, double valueInputted) {
        // this doesn't actually give coin objects back. it just calculates the difference in values.
        // i honestly couldn't be arsed to do that shit.

        return valueInputted - price;
    }
}
