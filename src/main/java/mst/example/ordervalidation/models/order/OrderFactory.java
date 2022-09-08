package mst.example.ordervalidation.models.order;

public class OrderFactory {

    Order createOrder(String type ){

        Order order = null;
        switch (type){
            case "ANALYSIS" :
                order = new AnalysisOrder();
                break;
            case "REPAIR" :
                break;

            case "REPLACEMENT":
                break;
            default:
              throw new  UnsupportedOperationException("Not supported yet.");
        }

            return  null;
    }
}
