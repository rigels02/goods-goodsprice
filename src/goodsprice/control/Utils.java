
package goodsprice.control;

import goodsprice.model.Good;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Raitis
 */
class Utils {
    
    static List<String> getGoodsNameList(List<Good> goods){
        List<String> list= new ArrayList<>();
        goods.stream()
             .filter((good) -> ( !list.contains(good.getName())))
             .forEachOrdered((good) -> {
            list.add(good.getName());
        });
        return list;
    }
    
    static List<String> getShopNameList(List<Good> goods){
        List<String> list= new ArrayList<>();
        for (Good good : goods) {
            if( !list.contains(good.getShop())){
              list.add(good.getShop());
            }
        }
        return list;
    }
}
