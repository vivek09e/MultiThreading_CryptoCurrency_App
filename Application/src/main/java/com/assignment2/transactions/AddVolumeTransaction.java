package com.assignment2.transactions;
import com.assignment2.callingClasses.HashFun;
import com.assignment2.enums.CoinStatus;
import com.assignment2.enums.TransactionStatus;
import com.assignment2.operationEntities.AddVolume;

/**
 * Having all the functionalities of Add volume Transaction thread.
 */
public class AddVolumeTransaction extends Transaction{
    private final AddVolume addVolume;


    /**
     * @param addVolume Require AddVolume object to get the information of the Add Volume Transaction.
     */
    public AddVolumeTransaction(AddVolume addVolume) {
        this.addVolume = addVolume;
    }

    /**
     * Implemented the run function for AddVolume Transaction.
     * first it checks that the coin is AVAILABLE or not
     * i.e. is it occupied by some other thread or not.
     * if occupied then current thread goes to wait state.
     * if the coin is available then the current thread make the coin status not available so that no other thread
     * can access the coin will it is working
     * after that it perform the AddVolume operation
     * after that it notify all the other thread and then make the coin status available.
     */
    @Override
    public void run() {
        synchronized (this.addVolume.getCoin()){
            while(this.addVolume.getCoin().getStatus()== CoinStatus.NOT_AVAILABLE){
                try {
                    this.addVolume.getCoin().wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            this.addVolume.getCoin().setStatus(CoinStatus.NOT_AVAILABLE);
            setTransactionId(HashFun.getBlockHash());
            this.addVolume.getCoin().setVolume(addVolume.getCoin().getVolume()+addVolume.getVolume());
            this.addVolume.getCoin().notifyAll();
            this.addVolume.getCoin().setStatus(CoinStatus.AVAILABLE);
            this.addVolume.setStatus(TransactionStatus.COMPLETED);
        }

    }
}
