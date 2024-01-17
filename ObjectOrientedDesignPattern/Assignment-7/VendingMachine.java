public class VendingMachine {
    private State idleState;
    private State outOfStockState;
    private State currentState;
    private int count;

    //set Method
    public void setState(State state) {
        this.currentState = state;
    }

    public VendingMachine( int count ) {
        // make the needed states
        idleState = new IdleState();
        outOfStockState = new OutOfStockState();
        if (count > 0) {
            currentState = idleState;
            this.count = count;
        } else {
            currentState = outOfStockState;
            this.count = 0;
        }
    }

    public void insertDollar() {
        currentState.insertDollar( this );
    }
    public void ejectMoney() {
        currentState.ejectMoney( this );
    }
    public void dispense() {
        currentState.dispense( this );
    }
    public void doReturnMoney() {
        System.out.println("Retuning money");
    }
    public void doReleaseProduct() {
        System.out.println("Product released");
    }
    //get methods
    public int getCount() {
        return count;
    }
    public State getIdleState() {
        return new IdleState();
    }
    public State getOutOfStockState() {
        return new OutOfStockState();
    }
    public State getHasOneDollarState() {
        return new HasOneDollarState();
    }
}












// public class VendingMachine {
//     private State currentState;

//     public State getHasOneDollarState() {
//         return new HasOneDollarState();
//     }
//     public void setState(State hasOneDollarState) {
//     }
//     public void doReturnMoney() {
//     }
//     public Object getIdleState() {
//         return null;
//     }
//     public int getCount() {
//         return 0;
//     }
//     public void doReleaseProduct() {
//     }
//     public Object getOutOfStockState() {
//         return null;
//     }

//     private State idleState;
//     private State hasOneDollarState;
//     private State outOfStockState;
    
//     private int count;


//     public VendingMachine( int count ) {
//         // make the needed states
//         idleState = new IdleState();
//         hasOneDollarState = new HasOneDollarState();
//         outOfStockState = new OutOfStockState();
//         if (count > 0) {
//             currentState = idleState;
//             this.count = count;
//         } else {
//             currentState = outOfStockState;
//             this.count = 0;
//         }
//     }

//     public void insertDollar() {
//         currentState.insertDollar( this );
//     }
//     public void ejectMoney() {
//         currentState.ejectMoney( this );
//     }
//     public void dispense() {
//         currentState.dispense( this );
//     }
// }
