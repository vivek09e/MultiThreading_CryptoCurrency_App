package com.assignment2.transactions;

import com.assignment2.callingClasses.HashFun;
import com.assignment2.entities.Coin;
import com.assignment2.entities.Trader;
import com.assignment2.enums.CoinStatus;
import com.assignment2.enums.TransactionStatus;
import com.assignment2.operationEntities.Buy;

/**
 * Having all the functionalities of buyCoin Transaction thread.
 */
public class BuyCoinTransaction extends Transaction {
    private final Buy buy;

    /**
     * @param buy Require buy object to get the information of the buy Transaction.
     */
    public BuyCoinTransaction(Buy buy) {
        this.buy = buy;
    }

    /**
     * Implemented the run function for buy Transaction.
     * first it checks that the coin is AVAILABLE or not
     * i.e. is it occupied by some other thread or not.
     * if occupied then current thread goes to wait state.
     * if the coin is available then the current thread make the coin status not available so that no other thread
     * can access the coin will it is working
     * next it checks that the trader owns the coin or not if own it update the vale of the coin own buy them
     * else it creates a new coin object and assign the values of the buy transaction and add it in the hashmap of
     * coinOwnByTrader
     * after that it perform the buy operation
     * after that it notify all the other thread and then make the coin status available.
     */
    @Override
    public void run() {

        synchronized (this.buy.getCoin()) {
            while (this.buy.getCoin().getStatus() == CoinStatus.NOT_AVAILABLE ||
                    this.buy.getCoin().getVolume() < this.buy.getQuantity()) {
                try {
                    this.buy.getCoin().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.buy.getCoin().setStatus(CoinStatus.NOT_AVAILABLE);
            setTransactionId(HashFun.getBlockHash());

            Trader currentTrader = traderMap.get(this.buy.getWalletAddress());
            String currentCoinSymbol = this.buy.getCoin().getSymbol();

            if (currentTrader.coinOwnByTheTrader.containsKey(currentCoinSymbol)) {

                long vol = currentTrader.coinOwnByTheTrader
                        .get(currentCoinSymbol).getVolume() + this.buy.getQuantity();

                currentTrader.coinOwnByTheTrader.get(currentCoinSymbol).setVolume(vol);

                Double expense = currentTrader.getExpense()
                        + this.buy.getQuantity() * this.buy.getCoin().getPrice();
                currentTrader.setExpense(expense);
            } else {
                Coin coin = new Coin();
                coin.setName(this.buy.getCoin().getName());
                coin.setSymbol(currentCoinSymbol);
                coin.setVolume(this.buy.getQuantity());
                coin.setPrice(this.buy.getCoin().getPrice());
                coin.setRank(this.buy.getCoin().getRank());
                coin.setStatus(CoinStatus.AVAILABLE);

                currentTrader.coinOwnByTheTrader.put(coin.getSymbol(), coin);
                Double expense = this.buy.getQuantity() * this.buy.getCoin().getPrice();
                currentTrader.setExpense(expense);
            }
            this.buy.getCoin().setVolume(buy.getCoin().getVolume() - buy.getQuantity());
            this.buy.getCoin().notifyAll();
            this.buy.getCoin().setStatus(CoinStatus.AVAILABLE);
            this.buy.setStatus(TransactionStatus.COMPLETED);
        }

    }


}
