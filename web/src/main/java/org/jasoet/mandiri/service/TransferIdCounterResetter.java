package org.jasoet.mandiri.service;

import org.jasoet.mandiri.util.counter.TransferIdCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * User: Deny Prasetyo, S.T.
 * Email : jasoet87@gmail.com
 * Date: 5/1/11
 * Time: 1:39 PM
 */
@Service(value = "transferIdCounterService")
public class TransferIdCounterResetter {

    private TransferIdCounter transferIdCounter;

    public TransferIdCounter getTransferIdCounter() {
        return transferIdCounter;
    }

    @Autowired
    public void setTransferIdCounter(TransferIdCounter transferIdCounter) {
        this.transferIdCounter = transferIdCounter;
    }

    public void run() {
        this.transferIdCounter.reset();
    }
}
