package com.assignment2.transactions;

import com.assignment2.callingClasses.HashFun;
import com.assignment2.entities.Trader;
import com.assignment2.enums.CoinStatus;
import com.assignment2.enums.TransactionStatus;
import com.assignment2.operationEntities.Sell;


/**
 * Having all the functionalities of sellCoin Transaction thread.
 */
public class SellCoinTransaction extends Transaction {

    private final Sell sell;

    /**
     * @param sell Require Sell object to get the information of the sell Transaction.
     */
    public SellCoinTransaction(Sell sell) {
        this.sell = sell;
    }

    /**
     * Implemented the run function for sell Transaction.
     * it's first check that trader has the coin or not,
     * or if the quantity they want to sell is more than the quantity they own-> then decline the Transaction .
     * If the trader has sufficient quantity to sell then it will check that the coin is AVAILABLE or not
     * i.e. is it occupied by some other thread or not.
     * if occupied then current thread goes to wait state.
     * if the coin is available then the current thread make the coin status not available so that no other thread
     * can access the coin will current thread is working
     * then it performs the sell operation
     * after that it notify all the other thread and then make the coin status available.
     */
    @Override
    public void run() {

        Trader currentTrader= traderMap.get(this.sell.getWalletAddress());
        String currentCoinSymbol=this.sell.getCoin().getSymbol();
        if (!currentTrader.coinOwnByTheTrader.containsKey(currentCoinSymbol)
                || currentTrader
                .coinOwnByTheTrader.get(currentCoinSymbol).getVolume() < this.sell.getQuantity()){
            this.sell.setStatus(TransactionStatus.DECLINED);
            return;
        }
        synchronized (this.sell.getCoin()) {
            while (this.sell.getCoin().getStatus() == CoinStatus.NOT_AVAILABLE) {
                try {
                    this.sell.getCoin().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.sell.getCoin().setStatus(CoinStatus.NOT_AVAILABLE);
            setTransactionId(HashFun.getBlockHash());

            Double releasedRevenue = this.sell.getCoin().getPrice() * this.sell.getQuantity()
                    + currentTrader.getReleasedRevenue();
            currentTrader.setReleasedRevenue(releasedRevenue);
            Long quantity = currentTrader.coinOwnByTheTrader.get(currentCoinSymbol).getVolume();
            currentTrader.coinOwnByTheTrader.get(currentCoinSymbol).setVolume(quantity - this.sell.getQuantity());
            this.sell.getCoin().setVolume(sell.getCoin().getVolume() + sell.getQuantity());
            this.sell.getCoin().notifyAll();
            this.sell.getCoin().setStatus(CoinStatus.AVAILABLE);
            this.sell.setStatus(TransactionStatus.COMPLETED);
        }
    }
}

