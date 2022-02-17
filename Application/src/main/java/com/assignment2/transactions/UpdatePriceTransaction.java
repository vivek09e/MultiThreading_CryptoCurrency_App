package com.assignment2.transactions;

import com.assignment2.callingClasses.HashFun;
import com.assignment2.enums.CoinStatus;
import com.assignment2.enums.TransactionStatus;
import com.assignment2.operationEntities.UpdatePrice;

/**
 * Having all the functionalities of updatePrice Transaction thread.
 */
public class UpdatePriceTransaction extends Transaction {

    private final UpdatePrice updatePrice;


    /**
     * @param updatePrice Require UpdatePrice object to get the information of the UpdatePrice Transaction.
     */
    public UpdatePriceTransaction(UpdatePrice updatePrice) { this.updatePrice = updatePrice;}

    /**
     * Implemented the run function for Update Price Transaction.
     * first it checks that the coin is AVAILABLE or not
     * i.e. is it occupied by some other thread or not.
     * if occupied then current thread goes to wait state.
     * if the coin is available then the current thread make the coin status not available so that no other thread
     * can access the coin will it is working
     * after that it perform the Update Price operation
     * after that it notify all the other thread and then make the coin status available.
     */
    @Override
    public void run() {
        synchronized (this.updatePrice.getCoin()) {
            while (this.updatePrice.getCoin().getStatus() == CoinStatus.NOT_AVAILABLE) {
                try {
                    this.updatePrice.getCoin().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.updatePrice.getCoin().setStatus(CoinStatus.NOT_AVAILABLE);
            setTransactionId(HashFun.getBlockHash());
            this.updatePrice.getCoin().setPrice(updatePrice.getPrice());
            this.updatePrice.getCoin().notifyAll();
            this.updatePrice.getCoin().setStatus(CoinStatus.AVAILABLE);
            this.updatePrice.setStatus(TransactionStatus.COMPLETED);
        }

    }
}
